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
import org.springframework.web.bind.annotation.*;
import proyecto.spring.asugestionsocios.model.ApiResponse;
import proyecto.spring.asugestionsocios.model.ErrorResponse;
import proyecto.spring.asugestionsocios.model.dto.FeatureDTO.AccessFeatureDTO;
import proyecto.spring.asugestionsocios.model.dto.FeatureDTO.ProfileAccessDTO;
import proyecto.spring.asugestionsocios.model.dto.ProfileDTO.ProfileCreateDTO;
import proyecto.spring.asugestionsocios.model.dto.ProfileDTO.ProfileDTO;
import proyecto.spring.asugestionsocios.model.dto.ProfileDTO.ProfileStatusChangeDTO;
import proyecto.spring.asugestionsocios.model.dto.ProfileDTO.ProfileUpdateDTO;
import proyecto.spring.asugestionsocios.service.ProfileService;

import java.util.List;

@Tag(name = "Profile")
@RestController
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService){
        this.profileService = profileService;
    }

    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successful Operation, send a succeed message",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = proyecto.spring.asugestionsocios.model.ApiResponse.class))}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "The user doesn't have permission to access the resource",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "409", description = "The request could not be completed due to a conflict with the current state of the resource",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid input data provided",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
    })
    @Operation(
            summary = "Register a new profile",
            description = "Creates a new profile. Access restricted to administrators only"
    )
    @PostMapping("/profiles")
    @PreAuthorize("hasAuthority('PROFILE_CREATE')")
    public ResponseEntity<ApiResponse> createProfile(@Valid @RequestBody ProfileCreateDTO profileCreateDTO){
        profileService.createProfile(profileCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("New Profile created successfully."));
    }

    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successful Operation, send a updated ProfileDTO",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = proyecto.spring.asugestionsocios.model.ApiResponse.class))}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "The user doesn't have permission to access the resource",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "The profile's to be updated is not found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "409", description = "The request could not be completed due to a conflict with the current state of the resource",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid input data provided",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
    })
    @Operation(
            summary = "Profile update",
            description = "Update an existing profile. Access restricted to administrators only"
    )
    @PutMapping("/profiles/{id}")
    @PreAuthorize("hasAuthority('PROFILE_UPDATE')")
    public ResponseEntity<ProfileDTO> updateProfile(@Valid @PathVariable Long id, @RequestBody ProfileUpdateDTO profileUpdateDTO){
        ProfileDTO profileUpdate = profileService.updateProfile(id, profileUpdateDTO);
        return ResponseEntity.status(HttpStatus.OK).body(profileUpdate);
    }

    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successful Operation, send a succeed message",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = proyecto.spring.asugestionsocios.model.ApiResponse.class))}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "The user doesn't have permission to access the resource",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "The user's to be updated is not found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "409", description = "The request could not be completed due to a conflict with the current state of the resource",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid input data provided",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
    })
    @Operation(
            summary = "Deactivate or active a profile",
            description = "Deactivate or active a profile. Access restricted to administrators only"
    )
    @PutMapping("/profiles/status/{id}")
    @PreAuthorize("hasAuthority('PROFILE_UPDATE_STATUS')")
    public ResponseEntity<ApiResponse> updateProfileStatus(@Valid @PathVariable Long id, @RequestBody ProfileStatusChangeDTO statusChangeDTO){
        String message = profileService.updateProfileStatus(id, statusChangeDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(message));
    }

    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successful Operation, send a list of ProfileDTO",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = proyecto.spring.asugestionsocios.model.ApiResponse.class))}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "The user doesn't have permission to access the resource",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
    })
    @Operation(
            summary = "Get all profiles",
            description = "Access to all profile registered. Access restricted to administrators only"
    )
    @GetMapping("profiles")
    @PreAuthorize("hasAuthority('PROFILE_LIST')")
    public ResponseEntity<List<ProfileDTO>> getAllProfiles(){
        return ResponseEntity.status(HttpStatus.OK).body(profileService.getAllProfiles());
    }

    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successful Operation, send a ProfileDTO",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = proyecto.spring.asugestionsocios.model.ApiResponse.class))}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "The user doesn't have permission to access the resource",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "The profile selected is not found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid input data provided",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
    })
    @Operation(
            summary = "Get a profile by Id",
            description = "Access to a profile information. Only public profile data is accessed. Access restricted to administrators only."
    )
    @GetMapping("/profiles/{id}")
    @PreAuthorize("hasAuthority('PROFILE_BY_ID')")
    public ResponseEntity<ProfileDTO> getProfileById(@Valid @PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(profileService.getProfileById(id));
    }

    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successful Operation, send a updated profile",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = proyecto.spring.asugestionsocios.model.ApiResponse.class))}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "The user doesn't have permission to access the resource",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "The profile's to be updated is not found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "409", description = "The request could not be completed due to a conflict with the current state of the resource",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid input data provided",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
    })
    @Operation(
            summary = "Granted or denied profile access",
            description = "Update profile permissions. Access restricted to administrators only"
    )
    @PutMapping("/profiles/{profileId}/access")
    @PreAuthorize("hasAuthority('PROFILE_ACCESS')")
    public ResponseEntity<ProfileAccessDTO> updateFeatureAccess(@Valid @PathVariable Long profileId, @RequestBody AccessFeatureDTO request){
        ProfileAccessDTO updateFeatures = profileService.accessFeature(profileId, request);

        return ResponseEntity.status(HttpStatus.OK).body(updateFeatures);
    }

    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successful Operation, send a succeed message",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = proyecto.spring.asugestionsocios.model.ApiResponse.class))}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "The user doesn't have permission to access the resource",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))}),
               @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid input data provided",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
    })
    @Operation(
            summary = "Get all profile permissions",
            description = "Get all profile permissions. Access restricted to administrators only"
    )
    @GetMapping("/profiles/{profileId}/access")
    @PreAuthorize("hasAuthority('PROFILE_LIST_ACCESSES')")
    public ResponseEntity<ProfileAccessDTO> getAllAccessByProfile(@PathVariable Long profileId){
        ProfileAccessDTO profileAccessDTO = profileService.getAccessFeature(profileId);
        return ResponseEntity.status(HttpStatus.OK).body(profileAccessDTO);
    }
}
