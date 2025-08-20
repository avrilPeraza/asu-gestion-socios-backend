package proyecto.spring.asugestionsocios.model.dto.FeatureDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Schema(description = "Profile permissions model")
public class ProfileAccessDTO {
    @Schema(example = "1", description = "Profile's Id")
    private Long id;
    @Schema(example = "Profile", description = "Profile's name")
    private String name;
    @Schema(description = "List of profile's permissions")
    private List<AccessFeatureProfileDTO> accessFeatureDTOS;
}
