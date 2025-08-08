package proyecto.spring.asugestionsocios.model.dto.UserDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import proyecto.spring.asugestionsocios.model.entity.PhoneType;

@Data
@Schema(description = "User Contact creation model")
public class ContactCreateDTO {
    @NotBlank(message = "Number is mandatory")
    @Schema(example = "092345678", description = "User's phone number")
    private String number;

    @NotNull(message = "Phone Type is mandatory")
    @Schema(example = "MOBILE", description = "describes the phone type")
    private PhoneType phoneType;

    @NotNull(message = "Location is mandatory")
    @Schema(example = "3", description = "Phone number's location")
    private Long locationId;
}


