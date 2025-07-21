package proyecto.spring.asugestionsocios.mapper;

import org.mapstruct.Mapper;
import proyecto.spring.asugestionsocios.model.dto.UserDTO.CountryDTO;
import proyecto.spring.asugestionsocios.model.entity.Country;

@Mapper(componentModel = "spring")
public interface CountryMapper {
    Country toEntity(CountryDTO countryDTO);
    CountryDTO toDto(Country country);
}
