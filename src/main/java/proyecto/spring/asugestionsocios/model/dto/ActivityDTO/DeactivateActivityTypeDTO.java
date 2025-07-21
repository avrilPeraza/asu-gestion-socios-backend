package proyecto.spring.asugestionsocios.model.dto.ActivityDTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DeactivateActivityTypeDTO {
    private Long activityTypeId;
    private String comment;
    private String reason;
    private LocalDate date;
    private Long userId;
}
