package proyecto.spring.asugestionsocios.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import proyecto.spring.asugestionsocios.model.dto.FacilityDTO.ReservationCreateDTO;
import proyecto.spring.asugestionsocios.model.dto.FacilityDTO.ReservationDTO;
import proyecto.spring.asugestionsocios.model.entity.Reservation;

@Mapper(componentModel = "spring")
public interface ReservationMapper {
    Reservation toEntity(ReservationDTO reservationDTO);
    ReservationDTO toDto(Reservation reservation);
    Reservation toEntityCreate(ReservationCreateDTO reservationCreateDTO);
}
