package proyecto.spring.asugestionsocios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import proyecto.spring.asugestionsocios.model.entity.Feature;

import java.util.Optional;

public interface FeatureRepository extends JpaRepository<Feature, Long> {

    @Query(value = "SELECT * FROM Feature WHERE Feature.name = :name", nativeQuery = true)
    Optional<Feature> findByName(String name);
}
