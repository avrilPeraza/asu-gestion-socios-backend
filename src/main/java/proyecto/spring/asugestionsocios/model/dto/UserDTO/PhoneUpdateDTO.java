package proyecto.spring.asugestionsocios.model.dto.UserDTO;

import lombok.Data;
import proyecto.spring.asugestionsocios.model.entity.PhoneType;

@Data
public class PhoneUpdateDTO {
    private Long id;
    private String number;
    private PhoneType phoneType;
    private LocationDTO location;
}
