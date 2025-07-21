package proyecto.spring.asugestionsocios.model.dto.ActivityDTO;

import jakarta.validation.constraints.*;
import lombok.Data;
import proyecto.spring.asugestionsocios.model.entity.MethodPayment;
import proyecto.spring.asugestionsocios.model.entity.ActivityType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ActivityCreateDTO {
    @NotBlank(message = "Name is mandatory")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters long")
    private String name;

    @NotBlank(message = "Description is mandatory")
    @Size(min = 20, message = "Description must be between 20 and 1000 characters long")
    private String description;

    @NotNull(message = "Duration is mandatory")
    @Positive(message = "Duration must be a positive value")
    //duration in hours
    private Integer duration;

    @NotNull(message = "Ticket cost is mandatory")
    @Positive(message = "Ticket cost must be a positive value")
    private BigDecimal ticketCost;

    @NotNull(message = "Method of Payment is mandatory")
    private MethodPayment methodPayment;

    @NotNull(message = "Date and time is mandatory")
    @Future(message = "Date and time must be in the future")
    private LocalDateTime dateTime;

    @NotNull(message = "Opening date is mandatory")
    @Future(message = "Opening date must be in the future")
    private LocalDateTime openingDateInscription;

    @Size(max = 1000)
    private String observations;

    @NotNull(message = "HasInscription is mandatory")
    private Boolean hasInscription = false;

    @NotNull(message = "Activity type is mandatory")
    private ActivityType activityType;
}
