package proyecto.spring.asugestionsocios.model.dto.EspacioDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ReservaDTO {
    private LocalDateTime fechaHora;
    private Integer duracion;
    private Short cantidadPersonas;
    private BigDecimal importeTotal;
    private BigDecimal importeSenia;
    private LocalDate fechaPagoSenia;
    private BigDecimal seniaPagada;
    private Long usuarioId;
    private Long espacioId;
}
