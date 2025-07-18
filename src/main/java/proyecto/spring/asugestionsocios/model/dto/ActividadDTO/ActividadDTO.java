package proyecto.spring.asugestionsocios.model.dto.ActividadDTO;

import lombok.Data;
import proyecto.spring.asugestionsocios.model.entity.Estado;
import proyecto.spring.asugestionsocios.model.entity.FormaPago;
import proyecto.spring.asugestionsocios.model.entity.TipoActividad;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ActividadDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private Integer duracion;
    private BigDecimal costoTicket;
    private FormaPago formaPago;
    private LocalDateTime fechaHora;
    private LocalDateTime fechaAperturaInsc;
    private String observaciones;
    private Boolean inscripcion = false;
    private Estado estado;
    private TipoActividad tipoActividad;
}
