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
@Table(name = "facility")
public class Facility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 100, unique = true)
    private String name;

    @Column(name = "description", nullable = false, length = Integer.MAX_VALUE)
    private String description;

    @Column(name = "capacity", nullable = false)
    private Short capacity;

    @Column(name = "non_member_rate", nullable = false, precision = 10, scale = 2)
    private BigDecimal nonMemberRate;

    @Column(name = "member_rate", nullable = false, precision = 10, scale = 2)
    private BigDecimal memberRate;

    @Column(name = "price_effective_date", nullable = false)
    private LocalDate priceEffectiveDate;

    @Column(name = "status", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "observations", length = Integer.MAX_VALUE)
    private String observations;

    @OneToMany(mappedBy = "facility")
    @ToString.Exclude
    private List<Activity> activities = new ArrayList<>();

    @OneToMany(mappedBy = "facility")
    @ToString.Exclude
    private List<Reservation> reservations = new ArrayList<>();

}