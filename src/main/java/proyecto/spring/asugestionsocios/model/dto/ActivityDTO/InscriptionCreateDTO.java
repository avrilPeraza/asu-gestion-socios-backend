package proyecto.spring.asugestionsocios.model.dto.ActivityDTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class InscriptionCreateDTO {

    @NotNull(message = "Activity and time is mandatory")
    private Long activityId;

    @NotNull(message = "User and time is mandatory")
    private Long userId;
}
