package proyecto.spring.asugestionsocios.mapper;

import org.mapstruct.Mapper;
import proyecto.spring.asugestionsocios.model.dto.UserDTO.LocationDTO;
import proyecto.spring.asugestionsocios.model.entity.Location;

@Mapper(componentModel = "spring")
public interface LocationMapper {
    Location toEntity(LocationDTO locationDTO);
    LocationDTO toDto(Location location);
}
