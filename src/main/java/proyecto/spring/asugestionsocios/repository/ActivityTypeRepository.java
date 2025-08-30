package proyecto.spring.asugestionsocios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import proyecto.spring.asugestionsocios.model.entity.ActivityType;

@Repository
public interface ActivityTypeRepository extends JpaRepository<ActivityType, Long> {
    @Query(value = "SELECT EXISTS (SELECT 1 FROM activity_type WHERE name = :name)", nativeQuery = true)
    boolean existsActivityTypeByName(String name);
}
