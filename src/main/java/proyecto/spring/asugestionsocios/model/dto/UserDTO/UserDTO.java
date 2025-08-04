package proyecto.spring.asugestionsocios.model.dto.UserDTO;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import proyecto.spring.asugestionsocios.model.dto.ProfileDTO.ProfileDTO;
import proyecto.spring.asugestionsocios.model.entity.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Schema(description = "User data model")
public class UserDTO {

    @Schema(example = "23", description = "User's Id")
    private Long id;

    @Schema(example = "CI", description = "User's Document Type")
    private DocumentType documentType;

    @Schema(example = "67895112", description = "User's Document")
    private String document;

    @Schema(example = "john.doe@example.com", description = "User's Email")
    private String email;

    @Schema(example = "John Daniel", description = "User's First Name")
    private String firstName;

    @Schema(example = "Doe Roe", description = "User's Last Name")
    private String lastName;

    @Schema(example = "2005-04-10", description = "User's Birthdate")
    private LocalDate birthdate;

    @Schema(example = "ACTIVE", description = "User's status")
    private UserStatus status;

    @Schema(example = "TRUE", description = "Does User use Sign Language?. Member User Only")
    private Boolean usesSignLanguage = false;

    @Schema(example = "TRUE", description = "Does User have Hearing Impairment?. Member User Only")
    private Boolean hasHearingImpairment = false;

    @Schema(example = "Calle 25 de mayo", description = "User's Street")
    private String street;

    @Schema(example = "303", description = "User's House number")
    private String houseNumber;

    @Schema(example = "B2", description = "User's Apartment")
    private String apartment;

    @Schema(description = "User's Profile", implementation = ProfileDTO.class)
    private ProfileDTO profile;

    @Schema(example = "TRUE", description = "Does User belongs To A Committee?. Member User Only")
    private Boolean belongsToCommittee = false;

    @Schema(description = "User's Subcommittee. Member User Only", implementation = SubcommitteeDTO.class)
    private SubcommitteeDTO subcommittee;

    @ArraySchema(schema = @Schema(implementation = ContactCreateDTO.class))
    private List<ContactDTO> phones = new ArrayList<>();

    @Schema(example = "SOC-2025-0003", description = "User's Member Number. Member User Only")
    private String memberNumber;
}
