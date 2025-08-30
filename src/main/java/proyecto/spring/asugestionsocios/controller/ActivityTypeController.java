package proyecto.spring.asugestionsocios.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import proyecto.spring.asugestionsocios.model.ApiResponse;
import proyecto.spring.asugestionsocios.model.ErrorResponse;
import proyecto.spring.asugestionsocios.model.UserDetailsImpl;
import proyecto.spring.asugestionsocios.model.dto.ActivityDTO.ActivityTypeCreateDTO;
import proyecto.spring.asugestionsocios.model.dto.ActivityDTO.ActivityTypeDTO;
import proyecto.spring.asugestionsocios.model.dto.ActivityDTO.ActivityTypeUpdateDTO;
import proyecto.spring.asugestionsocios.model.dto.ActivityDTO.DeactivateActivityTypeDTO;
import proyecto.spring.asugestionsocios.service.ActivityTypeService;

import java.util.List;

@RestController
@Tag(name = "Activity Type")
public class ActivityTypeController {

    private final ActivityTypeService activityTypeService;

    public ActivityTypeController(ActivityTypeService activityTypeService){
        this.activityTypeService = activityTypeService;
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
            summary = "Register a new activity type",
            description = "Creates a new activity type. Access restricted to administrators only"
    )
    @PostMapping("/activityTypes")
    @PreAuthorize("hasAuthority('ACTIVITY_TYPE_CREATE')")
    public ResponseEntity<ApiResponse> createActivityType(@Valid @RequestBody ActivityTypeCreateDTO activityTypeCreateDTO){
        activityTypeService.createActivityType(activityTypeCreateDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("New Activity Type created successfully."));
    }

    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successful Operation, send the updated entity",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = proyecto.spring.asugestionsocios.model.ApiResponse.class))}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "The user doesn't have permission to access the resource",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "The activity type to be updated is not found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "409", description = "The request could not be completed due to a conflict with the current state of the resource",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid input data provided",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
    })
    @Operation(
            summary = "Activity type update",
            description = "Update an existence activity type. Access restricted to administrators only"
    )
    @PutMapping("/activityTypes/{id}")
    @PreAuthorize("hasAuthority('ACTIVITY_TYPE_UPDATE')")
    public ResponseEntity<ActivityTypeDTO> updateActivityType(@Valid @PathVariable Long id, @RequestBody ActivityTypeUpdateDTO activityTypeUpdateDTO){
        ActivityTypeDTO activityTypeDTO = activityTypeService.updateActivityType(id, activityTypeUpdateDTO);
        return ResponseEntity.status(HttpStatus.OK).body(activityTypeDTO);
    }

    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successful Operation, send a succeed message",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = proyecto.spring.asugestionsocios.model.ApiResponse.class))}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "The user doesn't have permission to access the resource",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "The activity type to be deactivated is not found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "409", description = "The request could not be completed due to a conflict with the current state of the resource",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid input data provided",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
    })
    @Operation(
            summary = "Activity type deactivate",
            description = "Deactivate an activity type. Access restricted to administrators only"
    )
    @PostMapping("/activityTypes/{id}/deactivate")
    @PreAuthorize("hasAuthority('ACTIVITY_TYPE_DEACTIVATE')")
    public ResponseEntity<ApiResponse> deactivateActivityType(@Valid @PathVariable Long id, @RequestBody DeactivateActivityTypeDTO deactivateActivityTypeDTO, @AuthenticationPrincipal UserDetailsImpl currentUser){
        activityTypeService.deactivateActivityType(id, deactivateActivityTypeDTO, currentUser.getId());

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse("The Activity Type has been deactivated and is no longer available when creating new activities."));
    }

    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successful Operation, send a list of ActivityTypeDTO",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = proyecto.spring.asugestionsocios.model.ApiResponse.class))}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "The user doesn't have permission to access the resource",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
    })
    @Operation(
            summary = "Get all activity types",
            description = "Access to all activity type registered. Access restricted to administrators only"
    )
    @GetMapping("/activityTypes")
    @PreAuthorize("hasAuthority('ACTIVITY_TYPE_LIST')")
    public ResponseEntity<List<ActivityTypeDTO>> getAllActivityTypes(){
        List<ActivityTypeDTO> typeDTOS = activityTypeService.getAllActivityTypes();
        return ResponseEntity.status(HttpStatus.OK).body(typeDTOS);
    }

    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successful Operation, send a ActivityTypeDTO",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = proyecto.spring.asugestionsocios.model.ApiResponse.class))}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "The user doesn't have permission to access the resource",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "The activity type selected is not found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid input data provided",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
    })
    @Operation(
            summary = "Get an activity type by id",
            description = "Access to a activity type information. Only public activity type data is accessed. Access restricted to administrators only."
    )
    @GetMapping("/activityTypes/{id}")
    @PreAuthorize("hasAuthority('ACTIVITY_TYPE_BY_ID')")
    public ResponseEntity<ActivityTypeDTO> getActivityTypeById(@Valid @PathVariable Long id){
        ActivityTypeDTO type = activityTypeService.getActivityTypeById(id);
        return ResponseEntity.status(HttpStatus.OK).body(type);
    }
}
