package proyecto.spring.asugestionsocios.model.dto.UserDTO;

import lombok.Data;
import proyecto.spring.asugestionsocios.model.dto.ProfileDTO.ProfileDTO;
import proyecto.spring.asugestionsocios.model.entity.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class UserDTO {
    private Long id;
    private DocumentType documentType;
    private String document;
    private String email;
    private String firstName;
    private String lastName;
    private LocalDate birthdate;
    private String password;
    private UserStatus status;
    private Boolean usesSignLanguage = false;
    private Boolean hasHearingImpairment = false;
    private String street;
    private String houseNumber;
    private String apartment;
    private ProfileDTO profile;
    private SubcommitteeDTO subcommittee;
    private List<PhoneDTO> phones = new ArrayList<>();
}
