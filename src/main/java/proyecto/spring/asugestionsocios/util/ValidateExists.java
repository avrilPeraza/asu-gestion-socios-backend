package proyecto.spring.asugestionsocios.util;

import org.springframework.stereotype.Component;
import proyecto.spring.asugestionsocios.repository.UserRepository;

@Component
public class ValidateExists {

    private final UserRepository userRepository;

    public ValidateExists(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public boolean validateEmailExists(String email){
        return userRepository.existsUserByEmail(email);
    }

    public boolean validateDocumentExists(String document){
        return userRepository.existsUserByDocument(document);
    }
}
