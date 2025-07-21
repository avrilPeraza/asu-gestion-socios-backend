package proyecto.spring.asugestionsocios.model.dto.ActivityDTO;

import lombok.Data;
import proyecto.spring.asugestionsocios.model.entity.MethodPayment;
import proyecto.spring.asugestionsocios.model.entity.ActivityType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ActivityCreateDTO {
    private String name;
    private String description;
    private Integer duration;
    private BigDecimal ticketCost;
    private MethodPayment methodPayment;
    private LocalDateTime dateTime;
    private LocalDateTime openingDateInscription;
    private String observations;
    private Boolean hasInscription = false;
    private ActivityType activityType;
}
