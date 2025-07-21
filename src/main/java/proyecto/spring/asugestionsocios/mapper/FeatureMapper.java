package proyecto.spring.asugestionsocios.mapper;

import org.mapstruct.Mapper;
import proyecto.spring.asugestionsocios.model.dto.FeatureDTO.FeatureCreateDTO;
import proyecto.spring.asugestionsocios.model.dto.FeatureDTO.FeatureDTO;
import proyecto.spring.asugestionsocios.model.dto.FeatureDTO.FeatureUpdateDTO;
import proyecto.spring.asugestionsocios.model.entity.Feature;

@Mapper(componentModel = "spring")
public interface FeatureMapper {
    Feature toEntity(FeatureDTO featureDTO);
    FeatureDTO toDto(Feature feature);
    Feature toEntityCreate(FeatureCreateDTO featureCreateDTO);
    Feature toEntityUpdate(FeatureUpdateDTO featureUpdateDTO);
}
