package proyecto.spring.asugestionsocios.model.dto.UserDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import proyecto.spring.asugestionsocios.model.entity.PhoneType;

@Data
@Schema(description = "User Contact Data model")
public class ContactDataUpdateDTO {
    @NotBlank(message = "Number is mandatory")
    private String number;

    @NotNull(message = "Phone Type is mandatory")
    private PhoneType phoneType;

    @NotNull(message = "Location is mandatory")
    private Long locationId;
}
