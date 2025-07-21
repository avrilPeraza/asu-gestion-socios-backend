package proyecto.spring.asugestionsocios.model.dto.UserDTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserUpdateDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthdate;
    private String street;
    private String houseNumber;
    private String apartment;
    private Long profileId;
    private String password;
    private Boolean usesSignLanguage = false;
    private Boolean hasHearingImpairment = false;
    private Long subcommitteeId;
}
