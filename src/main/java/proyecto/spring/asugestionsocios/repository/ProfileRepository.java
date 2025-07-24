package proyecto.spring.asugestionsocios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proyecto.spring.asugestionsocios.model.entity.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
