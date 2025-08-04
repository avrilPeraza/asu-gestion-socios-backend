package proyecto.spring.asugestionsocios.model.dto.UserDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PasswordDataUpdateDTO {
    @NotBlank(message = "New Password is mandatory")
    @Size(min = 8, message = "Password must be at leats 8 characters long")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,}$",
            message = "Password must be at least 8 characters long, and include at least one uppercase letter, one lowercase letter, and one number")
    @Schema(example = "john123d", description = "User's Password")
    private String newPassword;

    @NotBlank(message = "Current Password is mandatory")
    @Size(min = 8, message = "Password must be at leats 8 characters long")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,}$",
            message = "Password must be at least 8 characters long, and include at least one uppercase letter, one lowercase letter, and one number")
    @Schema(example = "john123d", description = "User's Password")
    private String currentPassword;
}
