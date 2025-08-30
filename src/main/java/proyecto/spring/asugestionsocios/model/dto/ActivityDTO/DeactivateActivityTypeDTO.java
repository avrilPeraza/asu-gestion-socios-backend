package proyecto.spring.asugestionsocios.model.dto.ActivityDTO;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class DeactivateActivityTypeDTO {
    @NotBlank(message = "Comment is mandatory")
    @Size(min = 20, max = 200, message = "Comment must be between 20 and 1000 characters long")
    private String comment;

    @NotBlank(message = "Reason is mandatory")
    @Size(min = 20, message = "Reason must be between 20 and 1000 characters long")
    private String reason;
}
