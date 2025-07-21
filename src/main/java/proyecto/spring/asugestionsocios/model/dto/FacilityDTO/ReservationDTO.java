package proyecto.spring.asugestionsocios.model.dto.FacilityDTO;

import lombok.Data;
import proyecto.spring.asugestionsocios.model.dto.UserDTO.UserDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ReservationDTO {
    private Long id;
    private LocalDateTime dateTime;
    private Integer duration;
    private Short numberPeople;
    private BigDecimal totalAmount;
    private BigDecimal totalDeposit;
    private LocalDate depositPaymentDate;
    private BigDecimal depositPaid;
    private UserDTO user;
    private FacilityDTO facility;
}
