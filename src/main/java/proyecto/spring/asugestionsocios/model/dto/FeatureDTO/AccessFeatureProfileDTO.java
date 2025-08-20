package proyecto.spring.asugestionsocios.model.dto.FeatureDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import proyecto.spring.asugestionsocios.model.entity.PermissionState;

@Data
@AllArgsConstructor
@Schema(description = "Profile's permissions model")
public class AccessFeatureProfileDTO {
    @Schema(example = "1", description = "Feature's id")
    private Long featureId;
    @Schema(example = "Feature", description = "Feature's name")
    private String name;
    @Schema(example = "DENIED", description = "Permission's state")
    private PermissionState permissionState;
}
