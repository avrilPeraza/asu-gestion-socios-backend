package proyecto.spring.asugestionsocios.util;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import proyecto.spring.asugestionsocios.model.dto.UserDTO.UserCreateDTO;

import java.util.ArrayList;
import java.util.List;

public class FormatDocumentValidator implements ConstraintValidator<FormatDocument, UserCreateDTO> {
    @Override
    public boolean isValid(UserCreateDTO s, ConstraintValidatorContext constraintValidatorContext) {
        String documentType = s.getDocumentType().name();
        String document = s.getDocument();

        List<String> documentList = new ArrayList<>(List.of(document.split("")));

        String digitoValidador = String.valueOf(documentList.remove(documentList.getLast()));

        String result = "";
        return digitoValidador.equals(result);
    }
}
