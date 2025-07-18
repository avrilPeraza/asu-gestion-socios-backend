package proyecto.spring.asugestionsocios.model.dto.ActividadDTO;

import lombok.Data;
import proyecto.spring.asugestionsocios.model.entity.Inscripcion;

@Data
public class InscripcionCancelDTO {
    private Long actividadId;
    private Long usuarioId;
    private Inscripcion inscripcion;
}
