package proyecto.spring.asugestionsocios.model.dto.ActividadDTO;

import lombok.Data;
import proyecto.spring.asugestionsocios.model.entity.Estado;
@Data
public class TipoActividadDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private Estado estado;
}
