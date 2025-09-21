package proyecto.spring.asugestionsocios.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import proyecto.spring.asugestionsocios.model.ApiResponse;
import proyecto.spring.asugestionsocios.model.ErrorResponse;
import proyecto.spring.asugestionsocios.model.dto.ActivityDTO.ActivityCreateDTO;
import proyecto.spring.asugestionsocios.service.ActivityService;

@RestController
public class ActivityController {

    private final ActivityService activityService;

    public ActivityController(ActivityService activityService){
        this.activityService = activityService;
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
            summary = "Register a new activity",
            description = "Creates a new activity. Access restricted to administrators only"
    )
    @PostMapping("/activities")
    @PreAuthorize("hasAuthority('ACTIVITY_CREATE')")
    public ResponseEntity<ApiResponse> createActivity(@Valid @RequestBody ActivityCreateDTO activityCreateDTO){
        activityService.createActivity(activityCreateDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("New Activity created successfully."));
    }

}
