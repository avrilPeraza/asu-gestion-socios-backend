package proyecto.spring.asugestionsocios.model.dto.ProfileDTO;

import lombok.Data;
import proyecto.spring.asugestionsocios.model.entity.status;

@Data
public class ProfileUpdateDTO {
    private Long id;
    private String description;
    private status status;
}
