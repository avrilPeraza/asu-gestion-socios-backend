package proyecto.spring.asugestionsocios.model.dto.UserDTO;

import lombok.Data;
import proyecto.spring.asugestionsocios.model.entity.DocumentType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class UserCreateDTO {
    private String firstName;
    private String lastName;
    private DocumentType documentType;
    private String document;
    private LocalDate birthdate;
    private String street;
    private String houseNumber;
    private String apartment;
    private String email;
    private String password;
    private Long profileId;
    private List<PhoneCreateDTO> phones = new ArrayList<>();
    private Boolean usesSignLanguage = false;
    private Boolean hasHearingImpairment = false;
    private Long subcommitteeId;
}
