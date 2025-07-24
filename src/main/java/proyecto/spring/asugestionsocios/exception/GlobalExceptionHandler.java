package proyecto.spring.asugestionsocios.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<String> handlerConflict(ConflictException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class) //<- Indica que maneja las excepciones del tipo MethodArgumentNotValidException
    public ResponseEntity<String> handlerValidationError(MethodArgumentNotValidException ex){
        String messageError = ex.getBindingResult() //Obtiene el resultado de la validacion que fallo
                .getFieldErrors()//Devuelve un lista de errores asociados a campos especificos del objeto validado
                .stream()//Convierte en stream
                .map(err -> err.getField() + ": " + err.getDefaultMessage()) //Mapea el resultado para que sea field: errorMessage
                .collect(Collectors.joining(", ")); //Une los mensajes separando por ",".

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messageError);
    }
}
