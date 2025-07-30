package proyecto.spring.asugestionsocios.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import proyecto.spring.asugestionsocios.model.AuthResponse;
import proyecto.spring.asugestionsocios.model.ErrorResponse;
import proyecto.spring.asugestionsocios.model.LoginUserDTO;
import proyecto.spring.asugestionsocios.model.dto.UserDTO.UserCreateDTO;
import proyecto.spring.asugestionsocios.service.AuthenticationService;

@Tag(name = "Auth")
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationService authService;

    public AuthController(AuthenticationService authService){
        this.authService = authService;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User successfully created.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = proyecto.spring.asugestionsocios.model.ApiResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input data. Check required fields and formats.",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))}
            ),
            @ApiResponse(responseCode = "409", description = "Conflict. Email or document already exists.",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))}
            )
    })
    @Operation(
            summary = "Register a new user",
            description = "Registers a new user by validating all required attributes and assigning an initial status."
    )
    @PostMapping("/signup")
    public ResponseEntity<proyecto.spring.asugestionsocios.model.ApiResponse> signUp(@Valid @RequestBody UserCreateDTO userCreateDTO){
        authService.createUser(userCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new proyecto.spring.asugestionsocios.model.ApiResponse("Registered user successfully."));
    }

    @PostMapping("/signIn")
    public ResponseEntity<AuthResponse> signIn(@RequestBody LoginUserDTO userLogin){
        String token = authService.authenticate(userLogin);
        return ResponseEntity.ok(new AuthResponse(token));
    }



}
