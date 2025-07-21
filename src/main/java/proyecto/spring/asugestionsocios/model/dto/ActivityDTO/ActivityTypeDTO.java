package proyecto.spring.asugestionsocios.model.dto.ActivityDTO;

import lombok.Data;
import proyecto.spring.asugestionsocios.model.entity.Status;

@Data
public class ActivityTypeDTO {
    private Long id;
    private String name;
    private String description;
    private Status status;
}
