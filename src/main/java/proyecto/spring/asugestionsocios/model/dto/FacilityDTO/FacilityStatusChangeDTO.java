package proyecto.spring.asugestionsocios.model.dto.FacilityDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import proyecto.spring.asugestionsocios.model.entity.Status;

@Data
public class FacilityStatusChangeDTO {
    @NotNull(message = "Status is mandatory")
    @Schema(example = "ACTIVE", description = "New status for the facility")
    private Status newStatus;
}
