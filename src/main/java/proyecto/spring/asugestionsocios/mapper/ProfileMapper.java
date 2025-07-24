package proyecto.spring.asugestionsocios.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import proyecto.spring.asugestionsocios.model.dto.ProfileDTO.ProfileCreateDTO;
import proyecto.spring.asugestionsocios.model.dto.ProfileDTO.ProfileDTO;
import proyecto.spring.asugestionsocios.model.dto.ProfileDTO.ProfileUpdateDTO;
import proyecto.spring.asugestionsocios.model.entity.Profile;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    Profile toEntity(ProfileDTO profileDTO);
    ProfileDTO toDto(Profile profile);
    Profile toEntityCreate(ProfileCreateDTO profileCreateDTO);
    Profile toEntityUpdate(ProfileUpdateDTO profileUpdateDTO);
}
