package proyecto.spring.asugestionsocios.model.dto.FacilityDTO;

import lombok.Data;
import proyecto.spring.asugestionsocios.model.dto.ActivityDTO.ActivityDTO;
import proyecto.spring.asugestionsocios.model.dto.UserDTO.UserDTO;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ReservationDTO {
    private Long id;
    private BigDecimal totalAmount;
    private BigDecimal totalDeposit;
    private LocalDate depositPaymentDate;
    private BigDecimal depositPaid;
    private UserDTO user;
    private ActivityDTO activity;
}
