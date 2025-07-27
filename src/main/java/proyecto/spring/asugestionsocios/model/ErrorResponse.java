package proyecto.spring.asugestionsocios.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Schema(description = "Standard error response body")
public class ErrorResponse {
    @Schema(example = "2025-08-20T18:00:00", description = "Timestamp when the error occurred")
    private LocalDateTime timestamp;
    @Schema(example = "400", description = "HTTP status code of the error")
    private int status;
    @Schema(example = "Bad Request", description = "HTTP status name")
    private String error;
    @Schema(example = "/api/users", description = "Requested path where the error occurred")
    private String path;
    @Schema(example = "Validation failed", description = "Short message describing the error")
    private String message;
    @Schema(description = "List of validation or processing errors, if applicable")
    private List<String> errors;

    public ErrorResponse(){
        this.timestamp = LocalDateTime.now();
    }

    public ErrorResponse(int status, String error, String path, String message, List<String> errors){
        this();
        this.status = status;
        this.error = error;
        this.path = path;
        this.message = message;
        this.errors = errors;
    }
}
