package proyecto.spring.asugestionsocios.service;

import org.springframework.stereotype.Service;
import proyecto.spring.asugestionsocios.exception.ConflictException;
import proyecto.spring.asugestionsocios.mapper.PhoneMapper;
import proyecto.spring.asugestionsocios.model.dto.UserDTO.PhoneRequestDTO;
import proyecto.spring.asugestionsocios.model.entity.Phone;
import proyecto.spring.asugestionsocios.repository.PhoneRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PhoneService {

    private final PhoneRepository phoneRepository;
    private final PhoneMapper phoneMapper;

    public PhoneService(PhoneRepository phoneRepository, PhoneMapper phoneMapper){
        this.phoneRepository = phoneRepository;
        this.phoneMapper = phoneMapper;
    }

    public void createPhonesUser(List<PhoneRequestDTO> phoneCreateDtos){
        for (PhoneRequestDTO phoneDto : phoneCreateDtos){
            Phone newPhone = phoneMapper.toEntityRequest(phoneDto);
            phoneRepository.save(newPhone);
        }
    }

    private List<String> findDuplicatedInRequest(List<PhoneRequestDTO> phones) {
        Map<String, Long> counts = phones.stream()
                .collect(Collectors.groupingBy(PhoneRequestDTO::getNumber, Collectors.counting()));

        return counts.entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey)
                .toList();
    }

    public void validatePhoneNumbers(List<PhoneRequestDTO> phones){
        List<String> duplicatedInRequest = findDuplicatedInRequest(phones);
        if (!duplicatedInRequest.isEmpty()) {
            throw new ConflictException("Duplicated numbers in request: " + duplicatedInRequest);
        }

        List<String> numbersToValidate = phones
                .stream()
                .map(PhoneRequestDTO::getNumber)
                .toList();

        List<String> duplicateNumbers = phoneRepository.findAllExistingNumbers(numbersToValidate);

        if (!duplicateNumbers.isEmpty()){
            throw new ConflictException("The following numbers already exist: " + duplicateNumbers);
        }
    }
}
