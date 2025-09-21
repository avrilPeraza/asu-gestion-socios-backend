package proyecto.spring.asugestionsocios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import proyecto.spring.asugestionsocios.model.entity.Activity;

import java.time.LocalDateTime;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
    @Query(value = "SELECT EXISTS (SELECT 1 FROM activity WHERE name = :name)", nativeQuery = true)
    boolean existsActivitiesByName(String name);

    /*Query(value = "SELECT a FROM Activity a " +
            "WHERE a.id = :activityId AND a.dateTime ")
    Activity findActivitiesByDateTimeAfter(Long activityId, LocalDateTime startDateTime);*/


}
