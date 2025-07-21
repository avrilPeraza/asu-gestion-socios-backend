package proyecto.spring.asugestionsocios.model.dto.ActivityDTO;

import lombok.Data;
import proyecto.spring.asugestionsocios.model.entity.Status;
import proyecto.spring.asugestionsocios.model.entity.MethodPayment;
import proyecto.spring.asugestionsocios.model.entity.ActivityType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ActivityDTO {
    private Long id;
    private String name;
    private String description;
    private Integer duration;
    private BigDecimal ticketCost;
    private MethodPayment methodPayment;
    private LocalDateTime dateTime;
    private LocalDateTime openingDateInscription;
    private String observations;
    private Boolean hasInscription = false;
    private Status status;
    private ActivityType activityType;
}
