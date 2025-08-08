package proyecto.spring.asugestionsocios.model.dto.UserDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import proyecto.spring.asugestionsocios.model.entity.UserStatus;

@Data
@Schema(description = "User status change model")
public class UserStatusChangeDTO {
    @NotNull(message = "Status is mandatory")
    @Schema(example = "ACTIVE", description = "New status for the user")
    private UserStatus newStatus;
}
