package proyecto.spring.asugestionsocios.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import proyecto.spring.asugestionsocios.exception.ConflictException;
import proyecto.spring.asugestionsocios.model.LoginUserDTO;
import proyecto.spring.asugestionsocios.model.UserDetailsImpl;
import proyecto.spring.asugestionsocios.model.dto.UserDTO.UserCreateDTO;
import proyecto.spring.asugestionsocios.model.entity.Profile;
import proyecto.spring.asugestionsocios.model.entity.Subcommittee;
import proyecto.spring.asugestionsocios.model.entity.User;
import proyecto.spring.asugestionsocios.model.entity.UserStatus;
import proyecto.spring.asugestionsocios.repository.ProfileRepository;
import proyecto.spring.asugestionsocios.repository.SubcommitteeRepository;
import proyecto.spring.asugestionsocios.repository.UserRepository;
import proyecto.spring.asugestionsocios.util.MemberNumberGenerator;
import proyecto.spring.asugestionsocios.util.auth.JwtUtils;

import java.time.LocalDate;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final SubcommitteeRepository subcommitteeRepository;
    private final MemberNumberGenerator memberNumberGenerator;
    private final PhoneService phoneService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;


    public AuthenticationService(UserRepository userRepository, ProfileRepository  profileRepository, SubcommitteeRepository subcommitteeRepository, MemberNumberGenerator memberNumberGenerator, PhoneService phoneService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtUtils jwtUtils){
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
        this.subcommitteeRepository = subcommitteeRepository;
        this.memberNumberGenerator = memberNumberGenerator;
        this.phoneService = phoneService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
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
                .password(passwordEncoder.encode(userCreateDTO.getPassword()))
                .createdAt(LocalDate.now())
                .status(UserStatus.UNVALIDATED);

        Profile profile = profileRepository.findById(userCreateDTO.getProfileId()).orElse(null);
        builder.profile(profile);

        if (profile.getName().equalsIgnoreCase("SOCIO")){
            memberData(builder, userCreateDTO);
        }

        phoneService.validatePhoneNumbers(userCreateDTO.getPhones());

        User newUser = builder.build();
        userRepository.save(newUser);

        phoneService.createPhonesUser(userCreateDTO.getPhones(), newUser);
    }

    public void memberData(User.UserBuilder builder, UserCreateDTO userCreateDTO){
        String memberNumber = memberNumberGenerator.memberNumberGenerator(userCreateDTO.getProfileId());

        builder.hasHearingImpairment(userCreateDTO.getHasHearingImpairment())
                .usesSignLanguage(userCreateDTO.getUsesSignLanguage())
                .belongsToCommittee(userCreateDTO.getBelongsToCommittee())
                .memberNumber(memberNumber);

        if (userCreateDTO.getBelongsToCommittee()) {
            Subcommittee subcommittee = subcommitteeRepository
                    .findById(userCreateDTO.getSubcommitteeId())
                    .orElseThrow(() -> new EntityNotFoundException("There's not a subcommittee with ID: " + userCreateDTO.getSubcommitteeId()));

            builder.subcommittee(subcommittee);
        }
    }

    public String authenticate(LoginUserDTO loginUserDTO){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUserDTO.getEmail(),
                        loginUserDTO.getPassword()
                )
        );

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return jwtUtils.generateTokenFromEmail(userDetails);
    }
}
