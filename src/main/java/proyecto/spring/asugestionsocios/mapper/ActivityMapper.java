package proyecto.spring.asugestionsocios.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import proyecto.spring.asugestionsocios.model.dto.ActivityDTO.*;
import proyecto.spring.asugestionsocios.model.entity.Activity;
import proyecto.spring.asugestionsocios.model.entity.Enrollment;

@Mapper(componentModel = "spring")
@Component
public interface ActivityMapper {
    Activity toEntity(ActivityDTO activityDTO);
    ActivityDTO toDto(Activity activity);
    Activity toEntityCreate(ActivityCreateDTO activityCreateDTO);
    Activity toEntityUpdate(ActivityUpdateDTO activityUpdateDTO);
    Enrollment toEntityInscription(InscriptionDTO inscriptionDTO);
    Enrollment toEntityCreateInscription(InscriptionCreateDTO inscriptionCreateDTO);
}
