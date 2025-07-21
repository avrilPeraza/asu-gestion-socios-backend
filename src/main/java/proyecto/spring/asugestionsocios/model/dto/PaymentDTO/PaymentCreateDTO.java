package proyecto.spring.asugestionsocios.model.dto.PaymentDTO;

import lombok.Data;
import proyecto.spring.asugestionsocios.model.entity.MethodPayment;
import proyecto.spring.asugestionsocios.model.entity.PaymentType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PaymentCreateDTO {
    private PaymentType paymentType;
    private LocalDateTime date;
    private BigDecimal amount;
    private MethodPayment methodPayment;
    private Long userId;

    private Long reservationId;
    private Long monthlyFeeId;
    private Long activityId;
}
