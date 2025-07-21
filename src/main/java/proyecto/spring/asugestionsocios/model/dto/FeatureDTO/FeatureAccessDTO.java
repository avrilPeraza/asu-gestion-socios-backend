package proyecto.spring.asugestionsocios.model.dto.FeatureDTO;

import lombok.Data;
import proyecto.spring.asugestionsocios.model.entity.PermissionState;

@Data
public class FeatureAccessDTO {
    private Long profileId;
    private Long featureId;
    private PermissionState permissionState;
}
