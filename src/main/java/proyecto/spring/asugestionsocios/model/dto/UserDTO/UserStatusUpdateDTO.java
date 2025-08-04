package proyecto.spring.asugestionsocios.model.dto.UserDTO;

import lombok.Data;
import proyecto.spring.asugestionsocios.model.entity.UserStatus;

@Data
public class UserStatusUpdateDTO {
    private UserStatus newStatus;
}
