package proyecto.spring.asugestionsocios.model.dto.FacilityDTO;

import jakarta.validation.constraints.*;
import lombok.Data;
import proyecto.spring.asugestionsocios.model.entity.Status;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class FacilityUpdateDTO {
    @NotBlank(message = "Description is mandatory")
    @Size(min = 20, message = "Description must be between 20 and 1000 characters long")
    private String description;

    @NotNull(message = "Capacity is mandatory")
    @Positive(message = "Capacity must be a positive value")
    private Short capacity;

    @NotNull(message = "Non member rate is mandatory")
    @Positive(message = "Non member rate must be a positive value")
    private BigDecimal nonMemberRate;

    @NotNull(message = "Member rate is mandatory")
    @Positive(message = "Member rate must be a positive value")
    private BigDecimal memberRate;

    @NotNull(message = "Price effective date is mandatory")
    @Future(message = "Price effective date must be in the future")
    private LocalDate priceEffectiveDate;

    @Size(max = 1000)
    private String observations;

    @NotNull(message = "Status is mandatory")
    private Status status;
}
