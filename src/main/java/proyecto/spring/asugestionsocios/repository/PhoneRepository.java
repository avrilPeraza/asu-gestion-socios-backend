package proyecto.spring.asugestionsocios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import proyecto.spring.asugestionsocios.model.entity.Phone;

import java.util.List;

public interface PhoneRepository extends JpaRepository<Phone, Long> {
    @Query(value = "SELECT phone.number FROM phone WHERE phone.number IN :numbers", nativeQuery = true)
    List<String> findAllExistingNumbers(@Param("numbers") List<String> numbers);
}
