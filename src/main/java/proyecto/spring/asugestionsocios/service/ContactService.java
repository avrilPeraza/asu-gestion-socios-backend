package proyecto.spring.asugestionsocios.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import proyecto.spring.asugestionsocios.exception.ConflictException;
import proyecto.spring.asugestionsocios.mapper.PhoneMapper;
import proyecto.spring.asugestionsocios.model.dto.UserDTO.ContactCreateDTO;
import proyecto.spring.asugestionsocios.model.dto.UserDTO.ContactDTO;
import proyecto.spring.asugestionsocios.model.dto.UserDTO.ContactDataUpdateDTO;
import proyecto.spring.asugestionsocios.model.entity.Location;
import proyecto.spring.asugestionsocios.model.entity.Phone;
import proyecto.spring.asugestionsocios.model.entity.User;
import proyecto.spring.asugestionsocios.repository.LocationRepository;
import proyecto.spring.asugestionsocios.repository.PhoneRepository;
import proyecto.spring.asugestionsocios.repository.UserRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ContactService {

    private final PhoneRepository phoneRepository;
    private final LocationRepository locationRepository;
    private final UserRepository userRepository;
    private final PhoneMapper phoneMapper;

    public ContactService(PhoneRepository phoneRepository, PhoneMapper phoneMapper, LocationRepository locationRepository, UserRepository userRepository){
        this.phoneRepository = phoneRepository;
        this.phoneMapper = phoneMapper;
        this.locationRepository = locationRepository;
        this.userRepository = userRepository;
    }

    private void creatAndSaveContact(ContactCreateDTO contactCreateDto, User user){
        Location location = locationRepository.findById(contactCreateDto.getLocationId()).orElse(null);
        Phone newPhone = phoneMapper.toEntityRequest(contactCreateDto);
        newPhone.setLocation(location);
        newPhone.setUser(user);
        phoneRepository.save(newPhone);
    }

    public void createContactsUser(List<ContactCreateDTO> contactCreateDTOS, User user){
        for (ContactCreateDTO phoneDto : contactCreateDTOS){
           creatAndSaveContact(phoneDto, user);
        }
    }

    public void createContacts(Long userId, List<ContactCreateDTO> contactCreateDTOS){
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("There's not user with " + userId));

        validatePhoneNumbers(contactCreateDTOS);

        for (ContactCreateDTO phoneDto : contactCreateDTOS){
            creatAndSaveContact(phoneDto, user);
        }
    }

    public ContactDTO ContactDataUpdate(Long id, ContactDataUpdateDTO contactDataUpdateDTO){
        Phone existingPhone = phoneRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("There's not a phone with the ID: " + id));

        Location location = locationRepository.findById(contactDataUpdateDTO.getLocationId()).orElse(null);

        existingPhone.setNumber(contactDataUpdateDTO.getNumber());
        existingPhone.setPhoneType(contactDataUpdateDTO.getPhoneType());
        existingPhone.setLocation(location);

        Phone phone = phoneRepository.save(existingPhone);

        return phoneMapper.toDto(phone);
    }

    private List<String> findDuplicatedInRequest(List<ContactCreateDTO> phones) {
        Map<String, Long> counts = phones.stream()
                .collect(Collectors.groupingBy(ContactCreateDTO::getNumber, Collectors.counting()));

        return counts.entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey)
                .toList();
    }

    public void validatePhoneNumbers(List<ContactCreateDTO> phones){
        List<String> duplicatedInRequest = findDuplicatedInRequest(phones);
        if (!duplicatedInRequest.isEmpty()) {
            throw new ConflictException("Duplicated numbers in request: " + duplicatedInRequest);
        }

        List<String> numbersToValidate = phones
                .stream()
                .map(ContactCreateDTO::getNumber)
                .toList();

        List<String> duplicateNumbers = phoneRepository.findAllExistingNumbers(numbersToValidate);

        if (!duplicateNumbers.isEmpty()){
            throw new ConflictException("The following numbers already exist: " + duplicateNumbers);
        }
    }
}
