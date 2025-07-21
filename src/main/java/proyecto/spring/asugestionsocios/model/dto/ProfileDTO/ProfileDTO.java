package proyecto.spring.asugestionsocios.model.dto.ProfileDTO;

import lombok.Data;
import proyecto.spring.asugestionsocios.model.entity.Status;

@Data
public class ProfileDTO {
    private Long id;
    private String name;
    private String description;
    private Status status;
}
