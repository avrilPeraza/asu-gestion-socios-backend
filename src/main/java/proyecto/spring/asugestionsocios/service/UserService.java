package proyecto.spring.asugestionsocios.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import proyecto.spring.asugestionsocios.exception.BadRequestException;
import proyecto.spring.asugestionsocios.exception.ConflictException;
import proyecto.spring.asugestionsocios.mapper.UserMapper;
import proyecto.spring.asugestionsocios.model.dto.UserDTO.*;
import proyecto.spring.asugestionsocios.model.entity.*;
import proyecto.spring.asugestionsocios.repository.ProfileRepository;
import proyecto.spring.asugestionsocios.repository.SubcommitteeRepository;
import proyecto.spring.asugestionsocios.repository.UserRepository;
import proyecto.spring.asugestionsocios.util.MemberNumberGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ProfileRepository profileRepository;
    private final PasswordEncoder passwordEncoder;
    private final SubcommitteeRepository subcommitteeRepository;
    private final MemberNumberGenerator memberNumberGenerator;

    public UserService(UserRepository userRepository, UserMapper userMapper, ProfileRepository profileRepository, PasswordEncoder passwordEncoder, SubcommitteeRepository subcommitteeRepository, MemberNumberGenerator memberNumberGenerator){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.profileRepository = profileRepository;
        this.passwordEncoder = passwordEncoder;
        this.subcommitteeRepository = subcommitteeRepository;
        this.memberNumberGenerator = memberNumberGenerator;
    }

    private User findUserByIdOrThrow(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: "+ id));
    }

    public List<UserDTO> getAllUsers(){
        List<User> users = userRepository.findAll();

        List<UserDTO> userDtos = new ArrayList<>();
        for (User user : users){
            userDtos.add(userMapper.toDto(user));
        }

        return userDtos;
    }

    public UserDTO getUserById(Long id){
        User user = findUserByIdOrThrow(id);
        return userMapper.toDto(user);
    }

    public UserDTO personalDataUpdate(Long id, PersonalDataUpdateDTO personalDataUpdateDTO){
        User existingUser = findUserByIdOrThrow(id);

        existingUser.setFirstName(personalDataUpdateDTO.getFirstName());
        existingUser.setLastName(personalDataUpdateDTO.getLastName());
        existingUser.setBirthdate(personalDataUpdateDTO.getBirthdate());

        User userUpdated = userRepository.save(existingUser);

        return userMapper.toDto(userUpdated);
    }

    public UserDTO addressDataUpdate(Long id, AddressDataUpdateDTO addressDataUpdateDTO){
        User existingUser = findUserByIdOrThrow(id);

        existingUser.setApartment(addressDataUpdateDTO.getApartment());
        existingUser.setHouseNumber(addressDataUpdateDTO.getHouseNumber());
        existingUser.setStreet(addressDataUpdateDTO.getStreet());

        User userUpdated = userRepository.save(existingUser);

        return userMapper.toDto(userUpdated);
    }

    public UserDTO memberDataUpdate(Long id, MemberDataUpdateDTO memberDataUpdateDTO){
        User existingUser = findUserByIdOrThrow(id);

        Profile profile = profileRepository.findById(memberDataUpdateDTO.getProfileId())
                .orElseThrow(() -> new EntityNotFoundException("Profile not found with ID: " + memberDataUpdateDTO.getProfileId()));

        if (Objects.equals(profile.getName(), existingUser.getProfile().getName())){
            throw new ConflictException("User already is a " + existingUser.getProfile().getName());
        }

        existingUser.setProfile(profile);
        existingUser.setUsesSignLanguage(memberDataUpdateDTO.getUsesSignLanguage());
        existingUser.setHasHearingImpairment(memberDataUpdateDTO.getHasHearingImpairment());
        existingUser.setBelongsToCommittee(memberDataUpdateDTO.getBelongsToCommittee());

        if (memberDataUpdateDTO.getBelongsToCommittee()) {
            Subcommittee subcommittee = subcommitteeRepository
                    .findById(memberDataUpdateDTO.getSubcommitteeId())
                    .orElseThrow(() -> new EntityNotFoundException("Subcommittee not found with ID: " + memberDataUpdateDTO.getSubcommitteeId()));

            existingUser.setSubcommittee(subcommittee);
        }

        if (!Objects.equals(profile.getName(), existingUser.getProfile().getName()) && !Objects.equals(existingUser.getProfile().getName(), "Socio")){
           String memberNumber = memberNumberGenerator.memberNumberGenerator(memberDataUpdateDTO.getProfileId());
           existingUser.setMemberNumber(memberNumber);
        } else if (!Objects.equals(profile.getName(), existingUser.getProfile().getName())) {
            existingUser.setMemberNumber(null);
        }

        //TODO: implementar cambio de cuotas
        //TODO: Notificar al usuario que debe cambiar sus datos

        User userUpdated = userRepository.save(existingUser);

        return userMapper.toDto(userUpdated);
    }

    public UserDTO passwordUpdate(Long id, PasswordDataUpdateDTO passwordDataUpdateDTO){
        User existingUser = findUserByIdOrThrow(id);

        if (!passwordEncoder.matches(existingUser.getPassword(), passwordDataUpdateDTO.getCurrentPassword())){
            throw new BadRequestException("Current password is incorrect");
        }

        if (!passwordEncoder.matches(existingUser.getPassword(), passwordDataUpdateDTO.getNewPassword())){
            throw new BadRequestException("New password must be different from current password");
        }

        existingUser.setPassword(passwordDataUpdateDTO.getNewPassword());
        User userUpdated = userRepository.save(existingUser);

        return userMapper.toDto(userUpdated);
    }

    public String updateUserStatus(Long id, UserStatusChangeDTO userStatusChangeDTO){
        User user = findUserByIdOrThrow(id);

        if (user.getStatus().name().equals(userStatusChangeDTO.getNewStatus().name())){
            throw new ConflictException("User account is already " + user.getStatus().name());
        }

        user.setStatus(userStatusChangeDTO.getNewStatus());

        User userUpdated = userRepository.save(user);


        switch (userUpdated.getStatus()){
            case ACTIVE -> {
                return "User account has been successfully activated.";
            }
            case INACTIVE -> {
                return "User account has been successfully deactivated.";
            }
            default -> throw new IllegalArgumentException("Invalid user status: " + userUpdated.getStatus());
        }
    }
}