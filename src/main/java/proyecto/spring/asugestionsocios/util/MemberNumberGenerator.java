package proyecto.spring.asugestionsocios.util;

import org.springframework.stereotype.Component;
import proyecto.spring.asugestionsocios.model.entity.User;
import proyecto.spring.asugestionsocios.repository.UserRepository;

import java.time.LocalDate;

@Component
public class MemberNumberGenerator {

    private final UserRepository userRepository;

    public MemberNumberGenerator(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public String memberNumberGenerator(Long profileId){
        //Prefix
        String prefix = "SOC-";

        //Registration Year
        LocalDate today = LocalDate.now();
        String memberYear = String.valueOf(today.getYear());

        //number
        int memberCount = userRepository.countUsersByProfileNameAndYear(profileId, LocalDate.now().getYear());
        String memberNumber = String.format("%04d", memberCount + 1);

        return prefix + memberYear + memberNumber;
    }
}
