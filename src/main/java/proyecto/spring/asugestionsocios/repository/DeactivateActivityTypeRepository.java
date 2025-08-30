package proyecto.spring.asugestionsocios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import proyecto.spring.asugestionsocios.model.entity.DeactivateActivityType;

@Repository
public interface DeactivateActivityTypeRepository extends JpaRepository<DeactivateActivityType, Long> {
    @Query(value = "SELECT EXISTS (SELECT 1 FROM deactivate_activity_type WHERE activity_type_id = :activityTypeId)", nativeQuery = true)
    boolean existsDeactivateActivityTypeByActivityType(Long activityTypeId);
}
