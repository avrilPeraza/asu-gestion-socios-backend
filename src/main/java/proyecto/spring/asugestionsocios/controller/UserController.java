package proyecto.spring.asugestionsocios.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import proyecto.spring.asugestionsocios.model.dto.UserDTO.*;
import proyecto.spring.asugestionsocios.model.entity.User;
import proyecto.spring.asugestionsocios.service.ContactService;
import proyecto.spring.asugestionsocios.service.UserService;
import java.util.List;

@Tag(name = "User")
@RestController
@Slf4j
public class UserController {

    private final UserService userService;
    private final ContactService contactService;

    public UserController(UserService userService, ContactService contactService){
        this.userService = userService;
        this.contactService = contactService;
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
    @PreAuthorize("hasAuthority('OBTENER_USUARIOS')")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping("/users/{id}")
    @PreAuthorize("hasAuthority('OBTENER_USUARIO_ID')")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id){
        UserDTO user = userService.getUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PutMapping("/user/personal/{id}")
    public ResponseEntity<UserDTO> personalDataUpdate(@Valid @PathVariable Long id, @RequestBody PersonalDataUpdateDTO personalDataUpdateDTO){
        UserDTO userUpdate = userService.personalDataUpdate(id, personalDataUpdateDTO);
        return ResponseEntity.status(HttpStatus.OK).body(userUpdate);
    }

    @PutMapping("/user/address/{id}")
    public ResponseEntity<UserDTO> addressDataUpdate(@Valid @PathVariable Long id, @RequestBody AddressDataUpdateDTO addressDataUpdateDTO){
        UserDTO userUpdate = userService.addressDataUpdate(id, addressDataUpdateDTO);
        return ResponseEntity.status(HttpStatus.OK).body(userUpdate);
    }

    @PutMapping("/user/phone/{phoneId}")
    public ResponseEntity<ContactDTO> updateContact(@Valid @PathVariable Long phoneId, @RequestBody ContactDataUpdateDTO contactDataUpdateDTO){
        ContactDTO contactUpdate = contactService.ContactDataUpdate(phoneId, contactDataUpdateDTO);
        return ResponseEntity.status(HttpStatus.OK).body(contactUpdate);
    }

    @PostMapping("/user/{userId}/phone")
    public ResponseEntity<proyecto.spring.asugestionsocios.model.ApiResponse> createContact(@Valid @PathVariable Long userId, @RequestBody List<ContactCreateDTO> contactDataUpdateDTO){
        contactService.createContacts(userId, contactDataUpdateDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new proyecto.spring.asugestionsocios.model.ApiResponse("Phone/s registered successfully."));
    }

    @PutMapping("/user/member/{id}")
    public ResponseEntity<UserDTO> memberDataUpdate(@Valid @PathVariable Long id, @RequestBody MemberDataUpdateDTO memberDataUpdateDTO){
        UserDTO userUpdate = userService.memberDataUpdate(id, memberDataUpdateDTO);
        return ResponseEntity.status(HttpStatus.OK).body(userUpdate);
    }

    @PutMapping("/user/password/{id}")
    public ResponseEntity<UserDTO> passwordUpdate(@Valid @PathVariable Long id, @RequestBody PasswordDataUpdateDTO passwordDataUpdateDTO){
        UserDTO userUpdate = userService.passwordUpdate(id, passwordDataUpdateDTO);
        //TODO:Change message
        return ResponseEntity.status(HttpStatus.OK).body(userUpdate);
    }

    @PutMapping("user/status/{id}")
    public ResponseEntity<String> updateUserStatus(@PathVariable Long id, @RequestBody UserStatusChangeDTO userStatusChangeDTO){
        String message = userService.updateUserStatus(id, userStatusChangeDTO);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }
}
