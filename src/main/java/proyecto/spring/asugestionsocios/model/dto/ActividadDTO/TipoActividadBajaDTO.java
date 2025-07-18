package proyecto.spring.asugestionsocios.model.dto.ActividadDTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TipoActividadBajaDTO {
    private Long tipoActividadId;
    private String comentario;
    private String razon;
    private LocalDate fecha;
    private Long usuarioId;
}
