package proyecto.spring.asugestionsocios.model.dto.FeatureDTO;

import lombok.Data;
import proyecto.spring.asugestionsocios.model.entity.status;

@Data
public class FeatureDTO {
    private Long id;
    private String name;
    private String description;
    private status status;
}
