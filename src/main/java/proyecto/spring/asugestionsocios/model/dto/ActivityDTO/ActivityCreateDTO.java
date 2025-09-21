package proyecto.spring.asugestionsocios.model.dto.ActivityDTO;

import jakarta.validation.constraints.*;
import lombok.Data;
import proyecto.spring.asugestionsocios.model.entity.MethodPayment;

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

    @NotNull(message = "Date and time is mandatory")
    @Future(message = "Date and time must be in the future")
    private LocalDateTime dateTime;

    @NotNull(message = "Number of people is mandatory")
    @Positive(message = "Number of people must be a positive value")
    private Short numberPeople;

    @NotNull(message = "HasInscription is mandatory")
    private Boolean hasInscription = false;

    @Positive(message = "Ticket cost must be a positive value")
    private BigDecimal ticketCost;

    private MethodPayment methodPayment;

    @Future(message = "Opening date must be in the future")
    private LocalDateTime openingDateInscription;

    @Size(max = 1000)
    private String observations;

    @NotNull(message = "Activity type is mandatory")
    private Long activityTypeId;

    @NotNull(message = "Facility is mandatory")
    private Long facilityId;
}

