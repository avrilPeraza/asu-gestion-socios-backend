package proyecto.spring.asugestionsocios.model.dto.UserDTO;

import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.Data;
import proyecto.spring.asugestionsocios.model.entity.DocumentType;
import proyecto.spring.asugestionsocios.util.FormatDocument;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@FormatDocument(message = "Format document invalid")
public class UserCreateDTO {
    @NotBlank(message = "First name is mandatory")
    @Size(min = 2, max = 100, message = "First name must be between 2 and 100 characters long")
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    @Size(min = 2, max = 100, message = "Last name must be between 2 and 100 characters long")
    private String lastName;

    @NotNull(message = "Document type is mandatory")
    private DocumentType documentType;

    @NotBlank(message = "Document is mandatory")
    private String document;

    @NotNull(message = "Birthdate is mandatory")
    @Past(message = "Birthdate must be in the past")
    private LocalDate birthdate;

    @NotBlank(message = "Street is mandatory")
    @Size(min = 2, message = "Street must be at leats 2 characters long")
    private String street;

    @NotBlank(message = "House number is mandatory")
    @Size(min = 2, message = "House number must be at leats 2 characters long")
    private String houseNumber;

    @NotBlank(message = "Apartment is mandatory")
    @Size(min = 2, message = "Apartment must be at leats 2 characters long")
    private String apartment;

    @NotBlank(message = "Email is mandatory")
    @Email
    private String email;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, message = "Password must be at leats 8 characters long")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\\\d)[A-Za-z\\\\d]{8,}$",
            message = "Password must be at least 8 characters long, and include at least one uppercase letter, one lowercase letter, and one number")
    private String password;

    @NotNull(message = "Profile is mandatory")
    private Long profileId;

    @NotNull(message = "Phones is mandatory")
    @Size(min = 1, message = "At least one phone is required")
    private List<PhoneCreateDTO> phones = new ArrayList<>();

    private Boolean usesSignLanguage = false;
    private Boolean hasHearingImpairment = false;
    private Boolean belongsToCommittee = false;
    private Long subcommitteeId;

}
