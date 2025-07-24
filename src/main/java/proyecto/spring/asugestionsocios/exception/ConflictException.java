package proyecto.spring.asugestionsocios.exception;

//Crear clase conflictException - Excepcion personalizada
public class ConflictException extends RuntimeException{

    public ConflictException(String message){
        super(message);
    }

}
