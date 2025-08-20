package proyecto.spring.asugestionsocios.model.dto.FeatureDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import proyecto.spring.asugestionsocios.model.entity.PermissionState;

import java.util.Map;

@Data
@Schema(description = "Map of feature IDs to their permission states for updating profile access.")
public class AccessFeatureDTO {
    @Schema(example = "1: GRANTED", description = "Permission states for updating profile access.")
    @NotNull(message = "Both values are mandatory")
    private Map<Long, PermissionState> permissions;
}
