package proyecto.spring.asugestionsocios.mapper;

import org.mapstruct.Mapper;
import proyecto.spring.asugestionsocios.model.dto.FacilityDTO.FacilityCreateDTO;
import proyecto.spring.asugestionsocios.model.dto.FacilityDTO.FacilityDTO;
import proyecto.spring.asugestionsocios.model.entity.Facility;

@Mapper(componentModel = "spring")
public interface FacilityMapper {
    Facility toEntity(FacilityDTO facilityDTO);
    FacilityDTO toDto(Facility facility);
    Facility toEntityCreate(FacilityCreateDTO facilityCreateDTO);
}
