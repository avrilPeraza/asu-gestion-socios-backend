package proyecto.spring.asugestionsocios.util;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import proyecto.spring.asugestionsocios.model.dto.UserDTO.UserCreateDTO;
import proyecto.spring.asugestionsocios.model.entity.Profile;
import proyecto.spring.asugestionsocios.repository.ProfileRepository;

import java.util.Optional;

public class UserMemberValidator implements ConstraintValidator<ValidUserMember, UserCreateDTO> {
    private final ProfileRepository profileRepository;

    public UserMemberValidator(ProfileRepository profileRepository){
        this.profileRepository = profileRepository;
    }

    @Override
    public boolean isValid(UserCreateDTO userCreateDTO, ConstraintValidatorContext constraintValidatorContext) {
        if (userCreateDTO == null) return true;

        Optional<Profile> profile = profileRepository.findById(userCreateDTO.getProfileId());

        String profileName =
                profile
                        .map(Profile::getName)
                        .orElseThrow(() ->
                                new EntityNotFoundException("There's not a profile with ID: " + userCreateDTO.getProfileId()));

        if ("SOCIO".equalsIgnoreCase(profileName)){
            if (userCreateDTO.getBelongsToCommittee() != null && userCreateDTO.getBelongsToCommittee()){
                if (userCreateDTO.getSubcommitteeId() == null){
                    constraintValidatorContext.disableDefaultConstraintViolation();
                    constraintValidatorContext.buildConstraintViolationWithTemplate("Subcommittee must be selected when user is in a subcommittee.")
                            .addPropertyNode("subcommittee")
                            .addConstraintViolation();
                    return false;
                }
            }
        }
        return true;
    }
}
