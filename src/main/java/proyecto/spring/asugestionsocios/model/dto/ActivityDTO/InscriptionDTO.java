package proyecto.spring.asugestionsocios.model.dto.ActivityDTO;

import lombok.Data;
import proyecto.spring.asugestionsocios.model.dto.UserDTO.UserDTO;
import proyecto.spring.asugestionsocios.model.entity.EnrollmentStatus;

import java.time.LocalDate;

@Data
public class InscriptionDTO {
    private ActivityDTO activity;
    private UserDTO user;
    private LocalDate registrationDate;
    private EnrollmentStatus enrollmentStatus;
}
