package proyecto.spring.asugestionsocios.model.dto.ActivityDTO;

import jakarta.validation.constraints.*;
import lombok.Data;
import proyecto.spring.asugestionsocios.model.entity.Status;
import proyecto.spring.asugestionsocios.model.entity.MethodPayment;
import proyecto.spring.asugestionsocios.model.entity.ActivityType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ActivityUpdateDTO {
    @NotNull(message = "Status is mandatory")
    private Status status;

    @NotBlank(message = "Description is mandatory")
    @Size(min = 20, message = "Description must be between 20 and 1000 characters long")
    private String description;

    @NotNull(message = "Duration is mandatory")
    @Positive(message = "Duration must be a positive value")
    //duration in hours
    private Integer duration;

    @NotNull(message = "Number of people is mandatory")
    @Positive(message = "Number of people must be a positive value")
    private Short numberPeople;

    @NotNull(message = "HasInscription is mandatory")
    private Boolean hasInscription = false;

    @NotNull(message = "Activity type is mandatory")
    private ActivityType activityType;

    @NotNull(message = "Date and time is mandatory")
    @Future(message = "Date and time must be in the future")
    private LocalDateTime dateTime;

    @Positive(message = "Ticket cost must be a positive value")
    private BigDecimal ticketCost;

    private MethodPayment methodPayment;

    @Future(message = "Opening date must be in the future")
    private LocalDateTime openingDateInscription;

    @Size(max = 1000)
    private String observations;
}
