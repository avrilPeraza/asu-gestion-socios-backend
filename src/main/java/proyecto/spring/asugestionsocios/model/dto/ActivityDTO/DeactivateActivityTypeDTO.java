package proyecto.spring.asugestionsocios.model.dto.ActivityDTO;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class DeactivateActivityTypeDTO {
    @NotNull(message = "Activity Type and time is mandatory")
    private Long activityTypeId;

    @NotBlank(message = "Comment is mandatory")
    @Size(min = 20, max = 200, message = "Comment must be between 20 and 1000 characters long")
    private String comment;

    @NotBlank(message = "Reason is mandatory")
    @Size(min = 20, message = "Reason must be between 20 and 1000 characters long")
    private String reason;

    @NotNull(message = "User and time is mandatory")
    private Long userId;
}
