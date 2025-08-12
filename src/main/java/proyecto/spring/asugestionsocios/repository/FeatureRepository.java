package proyecto.spring.asugestionsocios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import proyecto.spring.asugestionsocios.model.entity.Feature;

public interface FeatureRepository extends JpaRepository<Feature, Long> {
}
