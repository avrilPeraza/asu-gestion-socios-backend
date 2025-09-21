package proyecto.spring.asugestionsocios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import proyecto.spring.asugestionsocios.model.entity.Facility;

@Repository
public interface FacilityRepository extends JpaRepository<Facility, Long> {
    @Query(value = "SELECT EXISTS (SELECT 1 FROM facility WHERE name = :name)", nativeQuery = true)
    boolean existsFacilityByName(String name);

    @Query(value = "SELECT facility.capacity FROM facility WHERE id = :id", nativeQuery = true)
    int findCapacityById(Long id);
}
