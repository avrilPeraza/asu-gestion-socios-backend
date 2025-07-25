package proyecto.spring.asugestionsocios.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import proyecto.spring.asugestionsocios.exception.ConflictException;
import proyecto.spring.asugestionsocios.mapper.UserMapper;
import proyecto.spring.asugestionsocios.model.dto.UserDTO.UserCreateDTO;
import proyecto.spring.asugestionsocios.model.dto.UserDTO.UserDTO;
import proyecto.spring.asugestionsocios.model.entity.*;
import proyecto.spring.asugestionsocios.repository.ProfileRepository;
import proyecto.spring.asugestionsocios.repository.SubcommitteeRepository;
import proyecto.spring.asugestionsocios.repository.UserRepository;
import proyecto.spring.asugestionsocios.util.MemberNumberGenerator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ProfileRepository profileRepository;
    private final SubcommitteeRepository subcommitteeRepository;
    private final MemberNumberGenerator memberNumberGenerator;
    private final PhoneService phoneService;

    public UserService(UserRepository userRepository, UserMapper userMapper, ProfileRepository  profileRepository, SubcommitteeRepository subcommitteeRepository, MemberNumberGenerator memberNumberGenerator, PhoneService phoneService){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.profileRepository = profileRepository;
        this.subcommitteeRepository = subcommitteeRepository;
        this.memberNumberGenerator = memberNumberGenerator;
        this.phoneService = phoneService;
    }

    public void createUser(UserCreateDTO userCreateDTO){

        if (userRepository.existsUserByEmail(userCreateDTO.getEmail())){
            throw new ConflictException("The email entered already exists in the system");
        }

        if (userRepository.existsUserByDocument(userCreateDTO.getDocument())){
            throw new ConflictException("The document entered already exists in the system");
        }

        User.UserBuilder builder = User.builder()
                .firstName(userCreateDTO.getFirstName())
                .lastName(userCreateDTO.getLastName())
                .documentType(userCreateDTO.getDocumentType())
                .document(userCreateDTO.getDocument())
                .birthdate(userCreateDTO.getBirthdate())
                .houseNumber(userCreateDTO.getHouseNumber())
                .street(userCreateDTO.getStreet())
                .apartment(userCreateDTO.getApartment())
                .email(userCreateDTO.getEmail())
                .password(userCreateDTO.getPassword());

        addSpecificData(builder, userCreateDTO);
        phoneService.validatePhoneNumbers(userCreateDTO.getPhones());

        User newUser = builder.build();
        userRepository.save(newUser);

        phoneService.createPhonesUser(userCreateDTO.getPhones());
    }


    public void addSpecificData(User.UserBuilder builder, UserCreateDTO userCreateDTO){
        Optional<Profile> profile = profileRepository.findById(userCreateDTO.getProfileId());

        String profileName =
                profile
                        .map(Profile::getName)
                        .orElseThrow(() ->
                                new EntityNotFoundException("There's not a profile with ID: " + userCreateDTO.getProfileId()));
        switch (profileName){
            case "SOCIO":
                String memberNumber = memberNumberGenerator.memberNumberGenerator(userCreateDTO.getProfileId());

                builder.hasHearingImpairment(userCreateDTO.getHasHearingImpairment())
                        .usesSignLanguage(userCreateDTO.getUsesSignLanguage())
                        .belongsToCommittee(userCreateDTO.getBelongsToCommittee())
                        .status(UserStatus.UNVALIDATED)
                        .memberNumber(memberNumber)
                        .createdAt(LocalDate.now());

                if (userCreateDTO.getBelongsToCommittee()){
                    Subcommittee subcommittee = subcommitteeRepository
                            .findById(userCreateDTO.getSubcommitteeId())
                            .orElseThrow(() -> new EntityNotFoundException("There's not a subcommittee with ID: " + userCreateDTO.getSubcommitteeId()));

                    builder.subcommittee(subcommittee);
                }
                break;
            case "NO_SOCIO", "ADMINISTRADOR", "AUXILIAR_ADMINISTRATIVO":
                builder.status(UserStatus.UNVALIDATED).createdAt(LocalDate.now());
                break;
            default:
                throw new IllegalArgumentException("Unknown profile: " + profileName);
        }
    }

    public List<UserDTO> getAllUsers(){
        List<User> users = userRepository.findAll();

        List<UserDTO> userDtos = new ArrayList<>();
        for (User user : users){
            userDtos.add(userMapper.toDto(user));
        }

        return userDtos;
    }


}
