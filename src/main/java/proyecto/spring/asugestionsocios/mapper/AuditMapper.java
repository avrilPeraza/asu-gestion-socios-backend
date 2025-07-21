package proyecto.spring.asugestionsocios.mapper;

import org.mapstruct.Mapper;
import proyecto.spring.asugestionsocios.model.dto.AuditDTO.AuditDTO;
import proyecto.spring.asugestionsocios.model.entity.Audit;

@Mapper(componentModel = "spring")
public interface AuditMapper {
    AuditDTO toDto(Audit audit);
}
