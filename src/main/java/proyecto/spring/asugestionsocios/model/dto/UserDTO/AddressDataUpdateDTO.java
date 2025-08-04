package proyecto.spring.asugestionsocios.model.dto.UserDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "User Address Data model")
public class AddressDataUpdateDTO {
    @NotBlank(message = "Street is mandatory")
    @Size(min = 2, message = "Street must be at leats 2 characters long")
    private String street;

    @NotBlank(message = "House number is mandatory")
    @Size(min = 2, message = "House number must be at leats 2 characters long")
    private String houseNumber;

    @NotBlank(message = "Apartment is mandatory")
    @Size(min = 2, message = "Apartment must be at leats 2 characters long")
    private String apartment;
}
