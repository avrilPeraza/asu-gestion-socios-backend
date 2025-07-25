package proyecto.spring.asugestionsocios.util;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import proyecto.spring.asugestionsocios.model.dto.UserDTO.UserCreateDTO;
import proyecto.spring.asugestionsocios.model.entity.DocumentType;

import java.util.ArrayList;
import java.util.List;

import static proyecto.spring.asugestionsocios.model.entity.DocumentType.*;

public class FormatDocumentValidator implements ConstraintValidator<FormatDocument, UserCreateDTO> {

    @Override
    public boolean isValid(UserCreateDTO user, ConstraintValidatorContext constraintValidatorContext) {
        if (user == null || user.getDocument() == null || user.getDocumentType() == null){
            return false;
        }

        Enum<DocumentType> documentType = user.getDocumentType();
        String document = user.getDocument();

        return switch (documentType){
            case CI -> validateFormatCi(document);
            case PASSPORT -> validateFormatPassport(document);
            default -> true;
        };
    }

    public static boolean validateFormatCi(String ci){
        List<String> digitList = new ArrayList<>(List.of(ci.split("")));

        int lastIndex = digitList.size() - 1;
        int checkDigit = Integer.parseInt(digitList.remove(lastIndex));

        List<Integer> multipliers = List.of(2,9,8,7,6,3,4);

        int sum = 0;
        for (int i = 0; i < digitList.size(); i++){
            sum += Integer.parseInt(digitList.get(i)) * multipliers.get(i);
        }

        int result = sum % 10;

        if (result != 0){
            result = 10 - result;
        }
        return checkDigit == result;
    }

    public static boolean validateFormatPassport(String passport){
        return passport != null && passport.matches("^A\\d{7}$");
    }
}
