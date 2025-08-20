package proyecto.spring.asugestionsocios.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import proyecto.spring.asugestionsocios.model.dto.FeatureDTO.AccessFeatureDTO;
import proyecto.spring.asugestionsocios.model.dto.FeatureDTO.AccessFeatureProfileDTO;
import proyecto.spring.asugestionsocios.model.dto.FeatureDTO.ProfileAccessDTO;
import proyecto.spring.asugestionsocios.model.entity.*;
import proyecto.spring.asugestionsocios.repository.FeatureProfileRepository;
import proyecto.spring.asugestionsocios.repository.FeatureRepository;
import proyecto.spring.asugestionsocios.exception.ConflictException;
import proyecto.spring.asugestionsocios.mapper.ProfileMapper;
import proyecto.spring.asugestionsocios.model.dto.ProfileDTO.ProfileCreateDTO;
import proyecto.spring.asugestionsocios.model.dto.ProfileDTO.ProfileDTO;
import proyecto.spring.asugestionsocios.model.dto.ProfileDTO.ProfileStatusChangeDTO;
import proyecto.spring.asugestionsocios.model.dto.ProfileDTO.ProfileUpdateDTO;
import proyecto.spring.asugestionsocios.repository.ProfileRepository;
import proyecto.spring.asugestionsocios.util.Auditable;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;
    private final FeatureRepository featureRepository;
    private final FeatureProfileRepository featureProfileRepository;

    public ProfileService(ProfileRepository profileRepository, ProfileMapper profileMapper, FeatureRepository featureRepository, FeatureProfileRepository featureProfileRepository){
        this.profileRepository = profileRepository;
        this.profileMapper = profileMapper;
        this.featureRepository = featureRepository;
        this.featureProfileRepository = featureProfileRepository;
    }

    private Profile findProfileByIdOrThrow(Long id){
        return profileRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Profile not found with ID: "+ id));
    }

    @Transactional
    @Auditable(operation = "PROFILE_CREATE")
    public void createProfile(ProfileCreateDTO profileCreateDTO){

        if (profileRepository.existsProfileByName(profileCreateDTO.getName())) throw new ConflictException("The profile name is already registered");

        Profile newProfile = profileMapper.toEntityCreate(profileCreateDTO);
        newProfile.setStatus(Status.INACTIVE);

        profileRepository.save(newProfile);

        List<Feature> allFeature = featureRepository.findAll();

        List<FeatureProfile> featureProfileList = allFeature.stream()
                .map(feature -> {
                    FeatureProfile p = new FeatureProfile();
                    p.setProfile(newProfile);
                    p.setFeature(feature);
                    p.setHasPermissionState(PermissionState.DENIED);

                    FeatureProfileId id = new FeatureProfileId();
                    id.setProfileId(newProfile.getId());
                    id.setFeatureId(feature.getId());

                    p.setFeatureProfileId(id);
                    return p;
                }).toList();

        featureProfileRepository.saveAll(featureProfileList);
    }

    @Auditable(operation = "PROFILE_LIST")
    public List<ProfileDTO> getAllProfiles(){
        List<Profile> profiles = profileRepository.findAll();

        List<ProfileDTO> profileDTOS = new ArrayList<>();
        for (Profile p: profiles){
            profileDTOS.add(profileMapper.toDto(p));
        }

        return profileDTOS;
    }

    @Auditable(operation = "PROFILE_LIST_ID")
    public ProfileDTO getProfileById(Long id){
        Profile profile = findProfileByIdOrThrow(id);
        return profileMapper.toDto(profile);
    }

    @Auditable(operation = "PROFILE_UPDATE")
    public ProfileDTO updateProfile(Long id, ProfileUpdateDTO profileUpdateDTO){
        Profile profileExisting = findProfileByIdOrThrow(id);

        profileExisting.setDescription(profileUpdateDTO.getDescription());
        profileExisting.setStatus(profileUpdateDTO.getStatus());

        Profile profileUpdated = profileRepository.save(profileExisting);

        return profileMapper.toDto(profileUpdated);
    }

    @Auditable(operation = "PROFILE_UPDATE_STATUS")
    public String updateProfileStatus(Long id, ProfileStatusChangeDTO profileStatusChangeDTO){
        Profile profile = findProfileByIdOrThrow(id);

        if (profile.getStatus().name().equals(profileStatusChangeDTO.getNewStatus().name())){
            throw new ConflictException("Profile account is already " + profile.getStatus().name());
        }

        profile.setStatus(profileStatusChangeDTO.getNewStatus());

        Profile profileUpdated = profileRepository.save(profile);

        switch (profileUpdated.getStatus()) {
            case ACTIVE -> {
                return "Profile " + profile.getName() + " has been successfully activated.";
            }
            case INACTIVE -> {
                return "Profile " + profile.getName() + " has been successfully deactivated.";
            }
            default -> throw new IllegalArgumentException("Invalid profile status: " + profileUpdated.getStatus());
        }
    }

    @Auditable(operation = "PROFILE_ACCESS_FEATURE")
    @Transactional
    public ProfileAccessDTO accessFeature(Long profileId, AccessFeatureDTO accessFeatureDTO){
        Profile profile = findProfileByIdOrThrow(profileId);

        accessFeatureDTO.getPermissions().forEach((featureId, newPermission) -> {
            Feature feature = featureRepository.findById(featureId)
                    .orElseThrow(() -> new EntityNotFoundException("Feature not found with id: " + featureId));

            FeatureProfile featureProfile = featureProfileRepository
                    .findPermissionByProfileAndFeature(profile.getId(), feature.getId())
                    .orElseThrow(() -> new EntityNotFoundException("There's no relationship between profile " + profile.getName() +
                            " and feature " + feature.getName()));

            if (featureProfile.getHasPermissionState().equals(newPermission)){
                throw new ConflictException("The profile " + profile.getName() +
                        " already has the feature " + feature.getName() +
                        " with state " + featureProfile.getHasPermissionState().name());
            }

            featureProfile.setHasPermissionState(newPermission);
            featureProfileRepository.save(featureProfile);
        });

        List<FeatureProfile> featureProfile = featureProfileRepository.findAllByProfileId(profileId);

        List<AccessFeatureProfileDTO> featuresDto = featureProfile.stream()
                .map(fp -> new AccessFeatureProfileDTO(
                        fp.getFeature().getId(),
                        fp.getFeature().getName(),
                        fp.getHasPermissionState()
                ))
                .toList();

        return new ProfileAccessDTO(profile.getId(), profile.getName(), featuresDto);
    }

    @Auditable(operation = "PROFILE_GET_ACCESSES")
    public ProfileAccessDTO getAccessFeature(Long profileId){
        Profile profile = findProfileByIdOrThrow(profileId);

        List<FeatureProfile> featureProfile = featureProfileRepository.findAllByProfileId(profileId);

        List<AccessFeatureProfileDTO> featuresDto = featureProfile.stream()
                .map(fp -> new AccessFeatureProfileDTO(
                        fp.getFeature().getId(),
                        fp.getFeature().getName(),
                        fp.getHasPermissionState()
                ))
                .toList();

        return new ProfileAccessDTO(profile.getId(), profile.getName(), featuresDto);
    }
}
