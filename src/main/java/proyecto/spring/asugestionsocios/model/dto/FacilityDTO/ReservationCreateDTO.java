package proyecto.spring.asugestionsocios.model.dto.FacilityDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ReservationCreateDTO {
    private LocalDateTime dateTime;
    private Integer duration;
    private Short numberPeople;
    private BigDecimal totalAmount;
    private BigDecimal totalDeposit;
    private LocalDate depositPaymentDate;
    private BigDecimal depositPaid;
    private Long userId;
    private Long facilityId;
}
