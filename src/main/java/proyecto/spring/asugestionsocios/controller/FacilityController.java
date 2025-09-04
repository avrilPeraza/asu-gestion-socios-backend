package proyecto.spring.asugestionsocios.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import proyecto.spring.asugestionsocios.model.ApiResponse;
import proyecto.spring.asugestionsocios.model.ErrorResponse;
import proyecto.spring.asugestionsocios.model.dto.FacilityDTO.FacilityCreateDTO;
import proyecto.spring.asugestionsocios.model.dto.FacilityDTO.FacilityDTO;
import proyecto.spring.asugestionsocios.model.dto.FacilityDTO.FacilityStatusChangeDTO;
import proyecto.spring.asugestionsocios.model.dto.FacilityDTO.FacilityUpdateDTO;
import proyecto.spring.asugestionsocios.model.entity.User;
import proyecto.spring.asugestionsocios.service.FacilityService;

import java.util.List;

@RestController
public class FacilityController {

    private final FacilityService facilityService;

    public FacilityController(FacilityService facilityService){
        this.facilityService = facilityService;
    }


    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Successful Operation, send a succeed message",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = proyecto.spring.asugestionsocios.model.ApiResponse.class))}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "The user doesn't have permission to access the resource",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "409", description = "The request could not be completed due to a conflict with the current state of the resource",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid input data provided",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
    })
    @Operation(
            summary = "Register a new facility",
            description = "Creates a new facility. Access restricted to administrators only"
    )
    @PostMapping("/facilities")
    @PreAuthorize("hasAuthority('FACILITY_CREATE')")
    public ResponseEntity<ApiResponse> createFacility(@Valid @RequestBody FacilityCreateDTO facilityCreateDTO){
        facilityService.createFacility(facilityCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("New Facility created successfully."));
    }


    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successful Operation, send the updated entity",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = proyecto.spring.asugestionsocios.model.ApiResponse.class))}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "The user doesn't have permission to access the resource",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "The facility to be updated is not found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "409", description = "The request could not be completed due to a conflict with the current state of the resource",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid input data provided",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
    })
    @Operation(
            summary = "Facility update",
            description = "Update an existence facility. Access restricted to administrators only"
    )
    @PutMapping("/facilities/{id}")
    @PreAuthorize("hasAuthority('FACILITY_UPDATE')")
    public ResponseEntity<FacilityDTO> updateFacility(@Valid @PathVariable Long id, @RequestBody FacilityUpdateDTO facilityUpdateDTO){
        FacilityDTO facilityUpdated = facilityService.updateFacility(id, facilityUpdateDTO);

        return ResponseEntity.status(HttpStatus.OK).body(facilityUpdated);
    }

    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successful Operation, send a succeed message",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = proyecto.spring.asugestionsocios.model.ApiResponse.class))}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "The user doesn't have permission to access the resource",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "The facility to be updated is not found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "409", description = "The request could not be completed due to a conflict with the current state of the resource",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid input data provided",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
    })
    @Operation(
            summary = "Deactivate or active facility",
            description = "Deactivate or active facility. Only a admin can access"
    )
    @PutMapping("/facilities/status/{id}")
    @PreAuthorize("hasAuthority('FACILITY_UPDATE_STATUS')")
    public ResponseEntity<ApiResponse> updateFacilityStatus(@Valid @PathVariable Long id, @RequestBody FacilityStatusChangeDTO facilityStatusChangeDTO){
        String result = facilityService.updateFacilityStatus(id, facilityStatusChangeDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(result));
    }

    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successful Operation, send a list of facilities",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = User.class))}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "The user doesn't have permission to access the resource",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
    })
    @Operation(
            summary = "Get all facilities",
            description = "Access to all facilities registered. Only public facility data is accessed."
    )
    @GetMapping("/facilities")
    @PreAuthorize("hasAuthority('FACILITY_LIST')")
    public ResponseEntity<List<FacilityDTO>> getAllFacilities(){
        List<FacilityDTO> facilities = facilityService.getAllFacilities();
        return ResponseEntity.status(HttpStatus.OK).body(facilities);
    }

    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successful Operation, send a facility according with Id",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = User.class))}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "The user doesn't have permission to access the resource",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
    })
    @Operation(
            summary = "Get a facility by Id",
            description = "Access to a facility registered. Only public facility data is accessed. Only a admin."
    )
    @GetMapping("/facilities/{id}")
    @PreAuthorize("hasAuthority('FACILITY_BY_ID')")
    public ResponseEntity<FacilityDTO> getFacilityById(@Valid @PathVariable Long id){
        FacilityDTO facility = facilityService.getFacilityById(id);
        return ResponseEntity.status(HttpStatus.OK).body(facility);
    }
}
