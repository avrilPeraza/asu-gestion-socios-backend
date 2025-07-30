package proyecto.spring.asugestionsocios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import proyecto.spring.asugestionsocios.model.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT EXISTS (SELECT 1 FROM app_user WHERE email = :email)", nativeQuery = true)
    boolean existsUserByEmail(String email);

    @Query(value = "SELECT EXISTS (SELECT 1 FROM app_user WHERE document = :docuemnt)", nativeQuery = true)
    boolean existsUserByDocument(String document);

    @Query(value = "SELECT count(*) FROM app_user WHERE profile_id = :profileId AND EXTRACT(YEAR FROM created_at)", nativeQuery = true)
    int countUsersByProfileNameAndYear(Long profileId, int currentYear);

    @Query(value = "SELECT u FROM app_user u WHERE u.email = : email", nativeQuery = true)
    Optional<User> findByEmail(String email);

}
