package proyecto.spring.asugestionsocios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import proyecto.spring.asugestionsocios.model.entity.Audit;

public interface AuditRepository extends JpaRepository<Audit, Long> {
}
