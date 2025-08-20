package proyecto.spring.asugestionsocios.model.dto.ProfileDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Profile creation model")
public class ProfileCreateDTO {
    @NotBlank(message = "Name is mandatory")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters long")
    @Schema(example = "Profile", description = "Profile's Name")
    private String name;

    @NotBlank(message = "Description is mandatory")
    @Size(min = 20, message = "Description must be between 20 and 1000 characters long")
    @Schema(example = "User profile for Admin", description = "Profile's Description")
    private String description;
}
