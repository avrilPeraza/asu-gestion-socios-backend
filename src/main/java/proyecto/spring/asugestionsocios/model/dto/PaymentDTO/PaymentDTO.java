package proyecto.spring.asugestionsocios.model.dto.PaymentDTO;

import lombok.Data;
import proyecto.spring.asugestionsocios.model.dto.ActivityDTO.ActivityDTO;
import proyecto.spring.asugestionsocios.model.dto.FacilityDTO.ReservationDTO;
import proyecto.spring.asugestionsocios.model.dto.UserDTO.UserDTO;
import proyecto.spring.asugestionsocios.model.entity.MethodPayment;
import proyecto.spring.asugestionsocios.model.entity.PaymentType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PaymentDTO {
    private Long id;
    private PaymentType paymentType;
    private LocalDateTime date;
    private BigDecimal amount;
    private MethodPayment methodPayment;
    private UserDTO user;

    private ReservationDTO reserva;
    private MonthlyFeeDTO monthlyFee;
    private ActivityDTO activity;
}
