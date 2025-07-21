package proyecto.spring.asugestionsocios.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Builder
@Entity
@Table(name = "usuario")
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "tipo_documento", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private DocumentType documentType;

    @Column(name = "documento", nullable = false, length = 20, unique = true)
    private String document;

    @Column(name = "email", nullable = false, length = 100, unique = true)
    private String email;

    @Column(name = "nombres", nullable = false, length = 50)
    private String firstName;

    @Column(name = "apellidos", nullable = false, length = 50)
    private String lastName;

    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate birthdate;

    @Column(name = "contrasenia", nullable = false, length = 100)
    private String password;

    @Column(name = "estado", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Column(name = "uso_lenguaje_senia", nullable = false)
    private Boolean usesSignLanguage = false;

    @Column(name = "dif_auditiva", nullable = false)
    private Boolean hasHearingImpairment = false;

    @Column(name = "calle", nullable = false, length = 50)
    private String street;

    @Column(name = "nro_puerta", nullable = false, length = 20)
    private String houseNumber;

    @Column(name = "apartamento", nullable = false, length = 20)
    private String apartment;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "perfil_id", nullable = false)
    @ToString.Exclude
    private Profile profile;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "subcomision_id", nullable = false)
    @ToString.Exclude
    private Subcommittee subcommittee;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<Enrollment> enrollments = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<Audit> audits = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<DeactivateActivityType> DeactivateActivityTypes = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<Payment> payments = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<Reservation> reservations = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<Phone> phones = new ArrayList<>();

}