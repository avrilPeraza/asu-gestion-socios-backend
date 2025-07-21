package proyecto.spring.asugestionsocios.model.dto.PaymentDTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MonthlyFeeDTO {
    private Integer monthlyFeeNumber;
    private Integer totalAnnualMonthlyFee;
    private LocalDateTime paymentDueDate;
}
