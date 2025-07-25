package proyecto.spring.asugestionsocios.model.dto.PaymentDTO;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class MonthlyFeeDTO {

    @NotNull(message = "Monthly fee number is mandatory")
    @Positive(message = "Monthly fee number must be a positive value")
    private Integer monthlyFeeNumber;

    @NotNull(message = "Total annual monthly fee is mandatory")
    @Positive(message = "Total annual monthly fee must be a positive value")
    private BigDecimal totalAnnualMonthlyFee;

    @NotNull(message = "Payment due date is mandatory")
    @Future(message = "Payment due date must be in the future")
    private LocalDateTime paymentDueDate;
}
