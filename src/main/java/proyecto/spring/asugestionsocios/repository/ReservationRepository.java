package proyecto.spring.asugestionsocios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import proyecto.spring.asugestionsocios.model.entity.Reservation;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query(value = "SELECT r.* FROM Reservation r " +
            " JOIN activity a ON r.activity_id = a.id " +
            "WHERE a.facility_id = :facilityId " +
            "AND r.reservation_status IN ('RESERVED', 'PENDING')" +
            "AND a.date_time < (CAST(:endDateTime AS timestamp) + interval '30 minutes')" +
            "AND a.date_time + (a.duration || ' hours')::interval > (CAST(:startDateTime AS timestamp) - interval '30 minutes')",
            nativeQuery = true)
    List<Reservation> findReservationByDateTimeAndFacility(Long facilityId, LocalDateTime startDateTime, LocalDateTime endDateTime);
}
