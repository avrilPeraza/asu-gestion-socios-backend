package proyecto.spring.asugestionsocios.model.dto.PagoDTO;

import lombok.Data;
import proyecto.spring.asugestionsocios.model.entity.FormaPago;
import proyecto.spring.asugestionsocios.model.entity.TipoPago;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PagoDTO {
    private TipoPago tipoPago;
    private LocalDateTime fecha;
    private BigDecimal monto;
    private FormaPago formaPago;
    private Long usuarioId;

    private Long reservaId;
    private Long cuotaId;
    private Long actividadId;
}
