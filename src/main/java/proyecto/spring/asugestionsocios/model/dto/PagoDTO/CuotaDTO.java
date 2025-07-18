package proyecto.spring.asugestionsocios.model.dto.PagoDTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CuotaDTO {
    private Integer numCuota;
    private Integer totalAnualCuota;
    private LocalDateTime fechaVencimiento;
}
