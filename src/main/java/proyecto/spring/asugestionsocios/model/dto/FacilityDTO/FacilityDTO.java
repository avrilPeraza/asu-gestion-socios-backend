package proyecto.spring.asugestionsocios.model.dto.FacilityDTO;

import lombok.Data;
import proyecto.spring.asugestionsocios.model.entity.status;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class FacilityDTO {
    private Long id;
    private String name;
    private String description;
    private Integer capacity;
    private BigDecimal nonMemberRate;
    private BigDecimal memberRate;
    private LocalDate priceEffectiveDate;
    private String observations;
    private status status;
}
