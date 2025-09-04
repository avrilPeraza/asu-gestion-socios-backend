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
import proyecto.spring.asugestionsocios.model.ErrorResponse;
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
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "401", description = "The user doesn't have permission to access the resource",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
    })
    @Operation(
            summary = "Get all users",
            description = "Access to all users registered. Only public user data is accessed."
    )
    @GetMapping("/users")
    @PreAuthorize("hasAuthority('USER_LIST')")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation, send a userDTO according with Id",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "401", description = "The user doesn't have permission to access the resource",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
    })
    @Operation(
            summary = "Get a user by Id",
            description = "Access to a user registered. Only public user data is accessed. Only a admin or the owner of the data can access it."
    )
    @GetMapping("/users/{id}")
    @PreAuthorize("hasAuthority('USER_BY_ID')")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id){
        UserDTO user = userService.getUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation, send a userDTO with the updated data",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "401", description = "The user doesn't have permission to access the resource",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "404", description = "The user to be updated is not found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
    })
    @Operation(
            summary = "Update user's personal data",
            description = "Update the information in the system. Only a admin or the owner of the data can access"
    )
    @PutMapping("/users/personal/{id}")
    @PreAuthorize("hasAuthority('USER_UPDATE_PERSONAL_DATA')")
    public ResponseEntity<UserDTO> personalDataUpdate(@Valid @PathVariable Long id, @RequestBody PersonalDataUpdateDTO personalDataUpdateDTO){
        UserDTO userUpdate = userService.personalDataUpdate(id, personalDataUpdateDTO);
        return ResponseEntity.status(HttpStatus.OK).body(userUpdate);
    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation, send a userDTO with the updated data",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "401", description = "The user doesn't have permission to access the resource",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "404", description = "The user to be updated is not found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
    })
    @Operation(
            summary = "Update user's address data",
            description = "Update the information in the system. Only a admin or the owner of the data can access"
    )
    @PutMapping("/users/address/{id}")
    @PreAuthorize("hasAuthority('USERS_UPDATE_ADDRESS')")
    public ResponseEntity<UserDTO> addressDataUpdate(@Valid @PathVariable Long id, @RequestBody AddressDataUpdateDTO addressDataUpdateDTO){
        UserDTO userUpdate = userService.addressDataUpdate(id, addressDataUpdateDTO);
        return ResponseEntity.status(HttpStatus.OK).body(userUpdate);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation, send a userDTO with the updated data",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "401", description = "The user doesn't have permission to access the resource",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "404", description = "The user's phone to be updated is not found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "409", description = "The request could not be completed due to a conflict with the current state of the resource",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
    })
    @Operation(
            summary = "Update user's contact data",
            description = "Update the information in the system. Only a admin or the owner of the data can access"
    )
    @PutMapping("/users/phone/{phoneId}")
    @PreAuthorize("hasAuthority('PHONES_UPDATE')")
    public ResponseEntity<ContactDTO> updateContact(@Valid @PathVariable Long phoneId, @RequestBody ContactDataUpdateDTO contactDataUpdateDTO){
        ContactDTO contactUpdate = contactService.ContactDataUpdate(phoneId, contactDataUpdateDTO);
        return ResponseEntity.status(HttpStatus.OK).body(contactUpdate);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation, send a succeed message",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponse.class))}),
            @ApiResponse(responseCode = "401", description = "The user doesn't have permission to access the resource",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "404", description = "The user's phone to be updated is not found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "409", description = "The request could not be completed due to a conflict with the current state of the resource",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
    })
    @Operation(
            summary = "Update user's contact data. Create a new contact",
            description = "Update the information in the system. Only a admin or the owner of the data can access"
    )
    @PostMapping("/users/{userId}/phone")
    @PreAuthorize("hasAuthority('PHONES_CREATE')")
    public ResponseEntity<proyecto.spring.asugestionsocios.model.ApiResponse> createContact(@Valid @PathVariable Long userId, @RequestBody List<ContactCreateDTO> contactDataUpdateDTO){
        contactService.createContacts(userId, contactDataUpdateDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new proyecto.spring.asugestionsocios.model.ApiResponse("Phone/s registered successfully."));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation, send a userDTO with the updated data",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "401", description = "The user doesn't have permission to access the resource",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "404", description = "The user to be updated is not found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "409", description = "The request could not be completed due to a conflict with the current state of the resource",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
    })
    @Operation(
            summary = "Update user type data",
            description = "Update the information in the system. Only a admin or the owner of the data can access"
    )
    @PutMapping("/users/member/{id}")
    @PreAuthorize("hasAuthority('USERS_UPDATE_USER_TYPE')")
    public ResponseEntity<UserDTO> memberDataUpdate(@Valid @PathVariable Long id, @RequestBody MemberDataUpdateDTO memberDataUpdateDTO){
        UserDTO userUpdate = userService.memberDataUpdate(id, memberDataUpdateDTO);
        return ResponseEntity.status(HttpStatus.OK).body(userUpdate);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation, send a userDTO with the updated data",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "401", description = "The user doesn't have permission to access the resource",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "404", description = "The user's to be updated is not found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "409", description = "The request could not be completed due to a conflict with the current state of the resource",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input data provided",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
    })
    @Operation(
            summary = "Update user's password",
            description = "Update the information in the system. Only the owner of the password can access"
    )
    @PutMapping("/users/password/{id}")
    @PreAuthorize("hasAuthority('USERS_UPDATE_PASSWORD')")
    public ResponseEntity<UserDTO> passwordUpdate(@Valid @PathVariable Long id, @RequestBody PasswordDataUpdateDTO passwordDataUpdateDTO){
        UserDTO userUpdate = userService.passwordUpdate(id, passwordDataUpdateDTO);
        //TODO:Change message
        return ResponseEntity.status(HttpStatus.OK).body(userUpdate);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation, send a succeed message",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = proyecto.spring.asugestionsocios.model.ApiResponse.class))}),
            @ApiResponse(responseCode = "401", description = "The user doesn't have permission to access the resource",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "404", description = "The user's to be updated is not found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "409", description = "The request could not be completed due to a conflict with the current state of the resource",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input data provided",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
    })
    @Operation(
            summary = "Deactivate or active user account",
            description = "Deactivate or active user account. Only a admin can access"
    )
    @PutMapping("users/status/{id}")
    @PreAuthorize("hasAuthority('USERS_UPDATE_STATUS')")
    public ResponseEntity<proyecto.spring.asugestionsocios.model.ApiResponse> updateUserStatus(@PathVariable Long id, @RequestBody UserStatusChangeDTO userStatusChangeDTO){
        String message = userService.updateUserStatus(id, userStatusChangeDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new proyecto.spring.asugestionsocios.model.ApiResponse(message));
    }
}
