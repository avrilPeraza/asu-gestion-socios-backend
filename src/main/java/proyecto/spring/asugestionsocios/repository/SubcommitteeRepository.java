package proyecto.spring.asugestionsocios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import proyecto.spring.asugestionsocios.model.entity.Subcommittee;

public interface SubcommitteeRepository extends JpaRepository<Subcommittee, Long> {
}
