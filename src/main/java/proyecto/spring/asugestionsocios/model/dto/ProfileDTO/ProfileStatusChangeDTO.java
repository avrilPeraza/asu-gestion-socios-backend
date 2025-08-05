package proyecto.spring.asugestionsocios.model.dto.ProfileDTO;

import lombok.Data;
import proyecto.spring.asugestionsocios.model.entity.Status;

@Data
public class ProfileStatusChangeDTO {
    private Status newStatus;
}
