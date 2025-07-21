package proyecto.spring.asugestionsocios.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "actividad_usuario")
public class Enrollment {
    @EmbeddedId
    private EnrollmentId id;

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    @ToString.Exclude
    private User user;

    @MapsId("activityId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "actividad_id", nullable = false)
    @ToString.Exclude
    private Activity activity;

    @Column(name = "fecha_inscripcion", nullable = false)
    private LocalDate registrationDate;

    @Column(name = "estado", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private EnrollmentStatus enrollmentStatus;

}