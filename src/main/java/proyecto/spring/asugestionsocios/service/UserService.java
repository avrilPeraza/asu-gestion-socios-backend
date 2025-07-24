package proyecto.spring.asugestionsocios.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import proyecto.spring.asugestionsocios.exception.ConflictException;
import proyecto.spring.asugestionsocios.mapper.UserMapper;
import proyecto.spring.asugestionsocios.model.dto.UserDTO.UserCreateDTO;
import proyecto.spring.asugestionsocios.model.entity.Profile;
import proyecto.spring.asugestionsocios.model.entity.Subcommittee;
import proyecto.spring.asugestionsocios.model.entity.User;
import proyecto.spring.asugestionsocios.model.entity.UserStatus;
import proyecto.spring.asugestionsocios.repository.ProfileRepository;
import proyecto.spring.asugestionsocios.repository.SubcommitteeRepository;
import proyecto.spring.asugestionsocios.repository.UserRepository;
import proyecto.spring.asugestionsocios.util.MemberNumberGenerator;
import proyecto.spring.asugestionsocios.util.ValidateExists;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ProfileRepository profileRepository;
    private final SubcommitteeRepository subcommitteeRepository;
    private final MemberNumberGenerator memberNumberGenerator;
    private final ValidateExists validateExists;

    public UserService(UserRepository userRepository, UserMapper userMapper, ProfileRepository  profileRepository, SubcommitteeRepository subcommitteeRepository, MemberNumberGenerator memberNumberGenerator, ValidateExists validateExists){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.profileRepository = profileRepository;
        this.subcommitteeRepository = subcommitteeRepository;
        this.memberNumberGenerator = memberNumberGenerator;
        this.validateExists = validateExists;
    }

    //TODO: Adds exceptions
    public void createUser(UserCreateDTO userCreateDTO){

        if (validateExists.validateEmailExists(userCreateDTO.getEmail())){
            throw new ConflictException("The email entered already exists in the system");
        }

        //formato


        if (validateExists.validateDocumentExists(userCreateDTO.getDocument())){
            throw new ConflictException("The document entered already exists in the system");
        }




        //Create User
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

        //add another data
        addSpecificData(builder, userCreateDTO);

        User newUser = builder.build();

        //Save user
        userRepository.save(newUser);

        //Phone registered

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
                builder.hasHearingImpairment(userCreateDTO.getHasHearingImpairment())
                        .usesSignLanguage(userCreateDTO.getUsesSignLanguage())
                        .belongsToCommittee(userCreateDTO.getBelongsToCommittee());

                if(Boolean.TRUE.equals(userCreateDTO.getBelongsToCommittee())&& userCreateDTO.getSubcommitteeId() != null){
                    Subcommittee subcommittee = subcommitteeRepository
                            .findById(userCreateDTO.getSubcommitteeId())
                            .orElseThrow(() -> new EntityNotFoundException("There's not a subcommittee with ID: " + userCreateDTO.getSubcommitteeId()));
                    builder.subcommittee(subcommittee);
                }

                String memberNumber = memberNumberGenerator.memberNumberGenerator(userCreateDTO.getProfileId());
                builder.status(UserStatus.UNVALIDATED).memberNumber(memberNumber).createdAt(LocalDate.now());
                break;
            case "NO_SOCIO", "ADMINISTRADOR", "AUXILIAR_ADMINISTRATIVO":
                builder.status(UserStatus.UNVALIDATED).createdAt(LocalDate.now());
                break;
            default:
                throw new IllegalArgumentException("Unknown profile: " + profileName);
        }
    }


    public Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }


}
