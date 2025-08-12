package proyecto.spring.asugestionsocios.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import proyecto.spring.asugestionsocios.model.ApiResponse;
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

    @PostMapping("/profile")
    @PreAuthorize("hasAuthority('CREAR_PERFIL')")
    public ResponseEntity<ApiResponse> createProfile(@Valid @RequestBody ProfileCreateDTO profileCreateDTO){
        profileService.createProfile(profileCreateDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("New Profile created successfully."));
    }

    @PutMapping("/profile/{id}")
    @PreAuthorize("hasAuthority('ACTUALIZAR_PERFIL')")
    public ResponseEntity<ProfileDTO> updateProfile(@Valid @PathVariable Long id, @RequestBody ProfileUpdateDTO profileUpdateDTO){
        ProfileDTO profileUpdate = profileService.updateProfile(id, profileUpdateDTO);
        return ResponseEntity.status(HttpStatus.OK).body(profileUpdate);
    }

    @PutMapping("/profile/status/{id}")
    @PreAuthorize("hasAuthority('ACTUALIZAR_ESTADO_PERFIL')")
    public ResponseEntity<ApiResponse> updateProfileStatus(@Valid @PathVariable Long id, @RequestBody ProfileStatusChangeDTO statusChangeDTO){
        String message = profileService.updateProfileStatus(id, statusChangeDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(message));
    }

    @GetMapping("profiles")
    @PreAuthorize("hasAuthority('OBTENER_PERFILES')")
    public ResponseEntity<List<ProfileDTO>> getAllProfiles(){
        return ResponseEntity.status(HttpStatus.OK).body(profileService.getAllProfiles());
    }

    @GetMapping("/profile/{id}")
    @PreAuthorize("hasAuthority('OBTENER_PERFIL_POR_ID')")
    public ResponseEntity<ProfileDTO> getProfileById(@Valid @PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(profileService.getProfileById(id));
    }
}
