package proyecto.spring.asugestionsocios.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Standard success response body")
public class ApiResponse {
    @Schema(example = "User created")
    private String message;
}
