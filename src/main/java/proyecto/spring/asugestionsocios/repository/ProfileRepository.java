package proyecto.spring.asugestionsocios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import proyecto.spring.asugestionsocios.model.entity.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    @Query(value = "SELECT EXISTS (SELECT 1 FROM profile WHERE name = :name)", nativeQuery = true)
    boolean existsProfileByName(String name);
}
