package proyecto.spring.asugestionsocios.model.dto.ProfileDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import proyecto.spring.asugestionsocios.model.entity.Status;

@Data
@Schema(description = "Profile updating model")
public class ProfileUpdateDTO {
    @NotBlank(message = "Description is mandatory")
    @Size(min = 20, message = "Description must be between 20 and 1000 characters long")
    private String description;

    @NotNull(message = "Status is mandatory")
    private Status status;
}
