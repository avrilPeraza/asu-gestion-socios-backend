package proyecto.spring.asugestionsocios.model.dto.FacilityDTO;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ReservationCreateDTO {

    @NotNull(message = "Total amount is mandatory")
    @Positive(message = "Total amount must be a positive value")
    private BigDecimal totalAmount;

    @NotNull(message = "Total deposit is mandatory")
    @Positive(message = "Total deposit must be a positive value")
    private BigDecimal totalDeposit;

    @NotNull(message = "Deposit payment date is mandatory")
    @Future(message = "Deposit payment date must be in the future")
    private LocalDate depositPaymentDate;

    @NotNull(message = "Deposit paid is mandatory")
    @Positive(message = "Deposit paid must be a positive value")
    private BigDecimal depositPaid;

    @NotNull(message = "User is mandatory")
    private Long userId;

    @NotNull(message = "Activity is mandatory")
    private Long activityId;
}
