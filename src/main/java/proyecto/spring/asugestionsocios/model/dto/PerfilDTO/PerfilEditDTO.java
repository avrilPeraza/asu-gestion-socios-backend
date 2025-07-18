package proyecto.spring.asugestionsocios.model.dto.PerfilDTO;

import lombok.Data;
import proyecto.spring.asugestionsocios.model.entity.Estado;

@Data
public class PerfilEditDTO {
    private Long id;
    private String descripcion;
    private Estado estado;
}
