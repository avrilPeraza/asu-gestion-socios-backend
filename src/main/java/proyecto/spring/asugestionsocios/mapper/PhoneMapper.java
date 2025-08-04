package proyecto.spring.asugestionsocios.mapper;

import org.mapstruct.Mapper;
import proyecto.spring.asugestionsocios.model.dto.UserDTO.*;
import proyecto.spring.asugestionsocios.model.entity.Phone;

@Mapper(componentModel = "spring")
public interface PhoneMapper {
    Phone toEntity(ContactDTO contactDTO);
    ContactDTO toDto(Phone phone);
    Phone toEntityRequest(ContactCreateDTO contactCreateDTO);
}
