package proyecto.spring.asugestionsocios.model.dto.ProfileDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import proyecto.spring.asugestionsocios.model.entity.Status;

@Data
@Schema(description = "Profile model")
public class ProfileDTO {
    @Schema(example = "1", description = "Profile's id")
    private Long id;
    @Schema(example = "Profile", description = "Profile's name")
    private String name;
    @Schema(example = "User profile for Admin", description = "Profile's description")
    private String description;
    @Schema(example = "ACTIVE", description = "Profile's status")
    private Status status;
}
