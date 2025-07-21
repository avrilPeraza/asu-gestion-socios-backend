package proyecto.spring.asugestionsocios.mapper;

import org.mapstruct.Mapper;
import proyecto.spring.asugestionsocios.model.dto.ActivityDTO.ActivityTypeCreateDTO;
import proyecto.spring.asugestionsocios.model.dto.ActivityDTO.ActivityTypeDTO;
import proyecto.spring.asugestionsocios.model.dto.ActivityDTO.ActivityTypeUpdateDTO;
import proyecto.spring.asugestionsocios.model.dto.ActivityDTO.DeactivateActivityTypeDTO;
import proyecto.spring.asugestionsocios.model.entity.DeactivateActivityType;
import proyecto.spring.asugestionsocios.model.entity.ActivityType;

@Mapper(componentModel = "spring")
public interface ActivityTypeMapper {
    ActivityType toEntity(ActivityTypeDTO activityTypeDTO);
    ActivityTypeDTO toDto(ActivityType activityType);
    ActivityType toEntityCreate(ActivityTypeCreateDTO activityTypeCreateDTO);
    ActivityType toEntityUpdate(ActivityTypeUpdateDTO activityTypeUpdateDTO);
    DeactivateActivityType toEntityBaja(DeactivateActivityTypeDTO deactivateActivityTypeDTO);
}
