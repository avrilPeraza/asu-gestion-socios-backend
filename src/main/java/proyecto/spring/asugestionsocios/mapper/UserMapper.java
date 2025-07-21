package proyecto.spring.asugestionsocios.mapper;

import org.mapstruct.Mapper;
import proyecto.spring.asugestionsocios.model.dto.UserDTO.UserCreateDTO;
import proyecto.spring.asugestionsocios.model.dto.UserDTO.UserDTO;
import proyecto.spring.asugestionsocios.model.dto.UserDTO.UserUpdateDTO;
import proyecto.spring.asugestionsocios.model.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserDTO userDto);
    UserDTO toDto(User user);
    User toEntityCreate(UserCreateDTO userCreateDTO);
    User toEntityUpdate(UserUpdateDTO userUpdateDTO);
}
