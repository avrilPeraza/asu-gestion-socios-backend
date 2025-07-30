package proyecto.spring.asugestionsocios.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import proyecto.spring.asugestionsocios.model.dto.UserDTO.UserDTO;
import proyecto.spring.asugestionsocios.model.entity.User;
import proyecto.spring.asugestionsocios.service.UserService;
import java.util.List;

@Tag(name = "User")
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation, send a list of userDTO",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = User.class))})
    })
    @Operation(
            summary = "Get all users",
            description = "Access to all user registered. Only public user data is accessed."
    )
    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }
}
