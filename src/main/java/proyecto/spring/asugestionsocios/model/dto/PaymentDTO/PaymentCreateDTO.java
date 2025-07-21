package proyecto.spring.asugestionsocios.model.dto.PaymentDTO;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import proyecto.spring.asugestionsocios.model.entity.MethodPayment;
import proyecto.spring.asugestionsocios.model.entity.PaymentType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PaymentCreateDTO {
    @NotNull(message = "Payment type is mandatory")
    private PaymentType paymentType;

    @NotNull(message = "date is mandatory")
    @Future(message = "date must be in the future")
    private LocalDateTime date;

    @NotNull(message = "Amount is mandatory")
    @Positive(message = "Amount must be a positive value")
    private BigDecimal amount;

    @NotNull(message = "Method payment is mandatory")
    private MethodPayment methodPayment;

    @NotNull(message = "User is mandatory")
    private Long userId;

    private Long reservationId;
    private Long monthlyFeeId;
    private Long activityId;
}
