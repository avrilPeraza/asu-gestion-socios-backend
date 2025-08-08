package proyecto.spring.asugestionsocios.model.dto.ProfileDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import proyecto.spring.asugestionsocios.model.entity.Status;

@Data
@Schema(description = "Profile status change model")
public class ProfileStatusChangeDTO {

    @NotNull(message = "Status is mandatory")
    @Schema(example = "ACTIVE", description = "New status for the user")
    private Status newStatus;
}
