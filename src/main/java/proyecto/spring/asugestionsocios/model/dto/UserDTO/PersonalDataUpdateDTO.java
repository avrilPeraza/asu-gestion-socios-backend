package proyecto.spring.asugestionsocios.model.dto.UserDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
@Schema(description = "User Personal Data model")
public class PersonalDataUpdateDTO {
    @NotBlank(message = "First name is mandatory")
    @Size(min = 2, max = 100, message = "First name must be between 2 and 100 characters long")
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    @Size(min = 2, max = 100, message = "Last name must be between 2 and 100 characters long")
    private String lastName;

    @NotNull(message = "Birthdate is mandatory")
    @Past(message = "Birthdate must be in the past")
    private LocalDate birthdate;
}
