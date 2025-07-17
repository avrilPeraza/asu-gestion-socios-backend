package proyecto.spring.asugestionsocios.model.dto.UsuarioDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UsuarioLoginDTO {

    @NotBlank(message = "Email es obligatorio")
    @Email
    private String email;
    @NotBlank(message = "Contraseña es obligatoria")
    private String contrasenia;
}
