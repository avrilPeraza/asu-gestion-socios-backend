package proyecto.spring.asugestionsocios.model.dto.AuditDTO;

import lombok.Data;
import proyecto.spring.asugestionsocios.model.dto.FeatureDTO.FeatureDTO;
import proyecto.spring.asugestionsocios.model.dto.UserDTO.UserDTO;

import java.time.LocalDateTime;

@Data
public class AuditDTO {
    private Long id;
    private LocalDateTime dateTime;    private String terminal;
    private String description;
    private FeatureDTO feature;
    private UserDTO user;

}
