package proyecto.spring.asugestionsocios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import proyecto.spring.asugestionsocios.model.entity.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
