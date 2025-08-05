package proyecto.spring.asugestionsocios.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import proyecto.spring.asugestionsocios.mapper.ProfileMapper;
import proyecto.spring.asugestionsocios.model.dto.ProfileDTO.ProfileCreateDTO;
import proyecto.spring.asugestionsocios.model.dto.ProfileDTO.ProfileDTO;
import proyecto.spring.asugestionsocios.model.dto.ProfileDTO.ProfileStatusChangeDTO;
import proyecto.spring.asugestionsocios.model.dto.ProfileDTO.ProfileUpdateDTO;
import proyecto.spring.asugestionsocios.model.entity.Profile;
import proyecto.spring.asugestionsocios.model.entity.Status;
import proyecto.spring.asugestionsocios.repository.ProfileRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;

    public ProfileService(ProfileRepository profileRepository, ProfileMapper profileMapper){
        this.profileRepository = profileRepository;
        this.profileMapper = profileMapper;
    }

    public void createProfile(ProfileCreateDTO profileCreateDTO){
        Profile newProfile = profileMapper.toEntityCreate(profileCreateDTO);
        newProfile.setStatus(Status.INACTIVE);

        profileRepository.save(newProfile);
    }

    public List<ProfileDTO> getAllProfiles(){
        List<Profile> profiles = profileRepository.findAll();

        List<ProfileDTO> profileDTOS = new ArrayList<>();
        for (Profile p: profiles){
            profileDTOS.add(profileMapper.toDto(p));
        }

        return profileDTOS;
    }

    public ProfileDTO getProfileById(Long id){
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There's not a profile with Id: " + id));

        return profileMapper.toDto(profile);
    }

    public ProfileDTO updateProfile(Long id, ProfileUpdateDTO profileUpdateDTO){
        Profile profileExisting = profileRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There's not a profile with Id: " + id));

        profileExisting.setDescription(profileUpdateDTO.getDescription());
        profileExisting.setStatus(profileUpdateDTO.getStatus());

        Profile profileUpdated = profileRepository.save(profileExisting);

        return profileMapper.toDto(profileUpdated);
    }

    public String updateProfileStatus(Long id, ProfileStatusChangeDTO profileStatusChangeDTO){
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There's not a profile with Id: " + id));

        profile.setStatus(profileStatusChangeDTO.getNewStatus());

        Profile profileUpdated = profileRepository.save(profile);

        switch (profileUpdated.getStatus()){
            case ACTIVE -> {
                return "Profile " + profile.getName() + " has been successfully activated.";
            }
            case INACTIVE -> {
                return "Profile " + profile.getName() + " has been successfully deactivated.";
            }
            default -> throw new IllegalArgumentException("Invalid profile status: " + profileUpdated.getStatus());
        }
    }
}
