package proyecto.spring.asugestionsocios.model.dto.UserDTO;

import lombok.Data;
import proyecto.spring.asugestionsocios.model.entity.PhoneType;

@Data
public class PhoneCreateDTO {
    private String number;
    private PhoneType phoneType;
    private Long location;
}
