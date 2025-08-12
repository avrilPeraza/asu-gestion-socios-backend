package proyecto.spring.asugestionsocios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import proyecto.spring.asugestionsocios.model.entity.FeatureProfile;

public interface FeatureProfileRepository extends JpaRepository<FeatureProfile, Long> {
}
