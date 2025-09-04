package proyecto.spring.asugestionsocios.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import proyecto.spring.asugestionsocios.exception.ConflictException;
import proyecto.spring.asugestionsocios.mapper.ActivityTypeMapper;
import proyecto.spring.asugestionsocios.model.dto.ActivityDTO.ActivityTypeCreateDTO;
import proyecto.spring.asugestionsocios.model.dto.ActivityDTO.ActivityTypeDTO;
import proyecto.spring.asugestionsocios.model.dto.ActivityDTO.ActivityTypeUpdateDTO;
import proyecto.spring.asugestionsocios.model.dto.ActivityDTO.DeactivateActivityTypeDTO;
import proyecto.spring.asugestionsocios.model.entity.*;
import proyecto.spring.asugestionsocios.repository.ActivityTypeRepository;
import proyecto.spring.asugestionsocios.repository.DeactivateActivityTypeRepository;
import proyecto.spring.asugestionsocios.repository.UserRepository;
import proyecto.spring.asugestionsocios.util.Auditable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ActivityTypeService {
    private final ActivityTypeRepository activityTypeRepository;
    private final ActivityTypeMapper activityTypeMapper;
    private final DeactivateActivityTypeRepository deactivateActivityTypeRepository;
    private final UserRepository userRepository;

    public ActivityTypeService(ActivityTypeRepository activityTypeRepository, ActivityTypeMapper activityTypeMapper, DeactivateActivityTypeRepository deactivateActivityTypeRepository, UserRepository userRepository){
        this.activityTypeRepository = activityTypeRepository;
        this.activityTypeMapper = activityTypeMapper;
        this.deactivateActivityTypeRepository = deactivateActivityTypeRepository;
        this.userRepository = userRepository;
    }

    private ActivityType findActivityTypeByIdOrThrow(Long id){
        return activityTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Activity type not found with ID: "+ id));
    }

    @Auditable(operation = "ACTIVITY_TYPE_CREATE")
    public void createActivityType(ActivityTypeCreateDTO activityTypeCreateDTO){

        if (activityTypeRepository.existsActivityTypeByName(activityTypeCreateDTO.getName())){
            throw new ConflictException("The activity type name is already registered");
        }

        ActivityType newActivityType = activityTypeMapper.toEntityCreate(activityTypeCreateDTO);
        newActivityType.setStatus(Status.INACTIVE);

        activityTypeRepository.save(newActivityType);
    }

    @Auditable(operation = "ACTIVITY_TYPE_UPDATE")
    public ActivityTypeDTO updateActivityType(Long activityTypeId, ActivityTypeUpdateDTO activityTypeUpdateDTO){
        ActivityType activityType = findActivityTypeByIdOrThrow(activityTypeId);

        if (activityType.getStatus().equals(Status.INACTIVE)){
            throw new ConflictException("The activity type is inactive and cannot be edited.");
        }

        activityType.setDescription(activityTypeUpdateDTO.getDescription());

        ActivityType activityTypeUpdated = activityTypeRepository.save(activityType);

        return activityTypeMapper.toDto(activityTypeUpdated);
    }

    @Auditable(operation = "ACTIVITY_TYPE_DEACTIVATE")
    @Transactional
    public void deactivateActivityType(Long activityTypeId, DeactivateActivityTypeDTO deactivateActivityTypeDTO, Long userId){
        ActivityType activityType = findActivityTypeByIdOrThrow(activityTypeId);

        if (deactivateActivityTypeRepository.existsDeactivateActivityTypeByActivityType(activityType.getId())){
            throw new ConflictException("The activity type is already deactivate.");
        }

        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: "+ userId));

        DeactivateActivityType deactivateActivityType = new DeactivateActivityType();
        deactivateActivityType.setActivityType(activityType);
        deactivateActivityType.setDate(LocalDate.now());
        deactivateActivityType.setUser(user);
        deactivateActivityType.setComment(deactivateActivityTypeDTO.getComment());
        deactivateActivityType.setReason(deactivateActivityTypeDTO.getReason());

        deactivateActivityTypeRepository.save(deactivateActivityType);

        activityType.setStatus(Status.INACTIVE);
        activityTypeRepository.save(activityType);
    }

    @Auditable(operation = "ACTIVITY_TYPE_LIST")
    public List<ActivityTypeDTO> getAllActivityTypes(){
        List<ActivityType> types = activityTypeRepository.findAll();

        List<ActivityTypeDTO> typeDTOS = new ArrayList<>();
        for (ActivityType t: types){
            typeDTOS.add(activityTypeMapper.toDto(t));
        }

        return typeDTOS;
    }

    @Auditable(operation = "ACTIVITY_TYPE_BY_ID")
    public ActivityTypeDTO getActivityTypeById(Long activityTypeId){
        ActivityType type = findActivityTypeByIdOrThrow(activityTypeId);
        return activityTypeMapper.toDto(type);
    }

    @Auditable(operation = "ACTIVITY_TYPE_BY_ID")
    public ActivityType getActivityTypeByIdEntity(Long activityTypeId){
        return findActivityTypeByIdOrThrow(activityTypeId);
    }
}
