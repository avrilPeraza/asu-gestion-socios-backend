package proyecto.spring.asugestionsocios.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "espacio")
public class Facility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100, unique = true)
    private String name;

    @Column(name = "descripcion", nullable = false, length = Integer.MAX_VALUE)
    private String description;

    @Column(name = "capacidad", nullable = false)
    private Integer capacity;

    @Column(name = "tarifa_no_socio", nullable = false, precision = 10, scale = 2)
    private BigDecimal nonMemberRate;

    @Column(name = "tarifa_socio", nullable = false, precision = 10, scale = 2)
    private BigDecimal memberRate;

    @Column(name = "fecha_vigencia_precio", nullable = false)
    private LocalDate priceEffectiveDate;

    @Column(name = "estado", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private status status;

    @Column(name = "observaciones", length = Integer.MAX_VALUE)
    private String observations;

    @OneToMany(mappedBy = "facility")
    @ToString.Exclude
    private List<Activity> activities = new ArrayList<>();

    @OneToMany(mappedBy = "facility")
    @ToString.Exclude
    private List<Reservation> reservations = new ArrayList<>();

}