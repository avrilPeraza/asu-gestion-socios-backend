package proyecto.spring.asugestionsocios.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import proyecto.spring.asugestionsocios.model.dto.UserDTO.SubcommitteeDTO;
import proyecto.spring.asugestionsocios.model.entity.Subcommittee;

@Mapper(componentModel = "spring")
public interface SubcommitteeMapper {
    Subcommittee toEntity(SubcommitteeDTO subcommitteeDTO);
    SubcommitteeDTO toDto(Subcommittee subcommittee);
}
