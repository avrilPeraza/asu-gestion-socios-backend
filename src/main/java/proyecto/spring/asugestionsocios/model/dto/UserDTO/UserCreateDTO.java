package proyecto.spring.asugestionsocios.model.dto.UserDTO;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;
import proyecto.spring.asugestionsocios.model.entity.DocumentType;
import proyecto.spring.asugestionsocios.util.FormatDocument;
import proyecto.spring.asugestionsocios.util.ValidUserMember;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@FormatDocument(message = "Invalid Document Format")
@ValidUserMember
@Schema(description = "User creation model")
public class UserCreateDTO {

    @NotBlank(message = "First name is mandatory")
    @Size(min = 2, max = 100, message = "First name must be between 2 and 100 characters long")
    @Schema(example = "John Daniel", description = "User's First Name")
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    @Size(min = 2, max = 100, message = "Last name must be between 2 and 100 characters long")
    @Schema(example = "Doe Roe", description = "User's Last Name")
    private String lastName;

    @NotNull(message = "Document type is mandatory")
    @Schema(example = "CI", description = "User's Document Type")
    private DocumentType documentType;

    @NotBlank(message = "Document is mandatory")
    @Schema(example = "67895112", description = "User's Document")
    private String document;

    @NotNull(message = "Birthdate is mandatory")
    @Past(message = "Birthdate must be in the past")
    @Schema(example = "2005-04-10", description = "User's Birthdate")
    private LocalDate birthdate;

    @NotBlank(message = "Street is mandatory")
    @Size(min = 2, message = "Street must be at leats 2 characters long")
    @Schema(example = "Calle 25 de mayo", description = "User's Street")
    private String street;

    @NotBlank(message = "House number is mandatory")
    @Size(min = 2, message = "House number must be at leats 2 characters long")
    @Schema(example = "303", description = "User's House number")
    private String houseNumber;

    @NotBlank(message = "Apartment is mandatory")
    @Size(min = 2, message = "Apartment must be at leats 2 characters long")
    @Schema(example = "B2", description = "User's Apartment")
    private String apartment;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "The email format is not valid")
    @Schema(example = "john.doe@example.com", description = "User's Email")
    private String email;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, message = "Password must be at leats 8 characters long")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,}$",
            message = "Password must be at least 8 characters long, and include at least one uppercase letter, one lowercase letter, and one number")
    @Schema(example = "john123d", description = "User's Password")
    private String password;

    @NotNull(message = "Profile is mandatory")
    @Schema(example = "2", description = "User's Profile Id")
    private Long profileId;

    @NotNull(message = "Phones is mandatory")
    @Size(min = 1, message = "At least one phone is required")
    @ArraySchema(schema = @Schema(implementation = ContactCreateDTO.class))
    private List<ContactCreateDTO> phones = new ArrayList<>();

    @Schema(example = "TRUE", description = "Does User use Sign Language?. Member User Only")
    private Boolean usesSignLanguage = false;

    @Schema(example = "TRUE", description = "Does User have Hearing Impairment?. Member User Only")
    private Boolean hasHearingImpairment = false;

    @Schema(example = "TRUE", description = "Does User belongs To A Committee?. Member User Only")
    private Boolean belongsToCommittee = false;

    @Schema(example = "3", description = "User's Subcommittee Id. Member User Only")
    private Long subcommitteeId;

}
