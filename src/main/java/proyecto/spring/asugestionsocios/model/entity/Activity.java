package proyecto.spring.asugestionsocios.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "activity")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 100, unique = true)
    private String name;

    @Column(name = "description", nullable = false, length = Integer.MAX_VALUE)
    private String description;

    @Column(name = "duration", nullable = false)
    private Integer duration;

    @Column(name = "ticket_cost", nullable = false, precision = 10, scale = 2)
    private BigDecimal ticketCost;

    @Column(name = "method_payment", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private MethodPayment methodPayment;

    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;

    @Column(name = "opening_date_inscription", nullable = false)
    private LocalDateTime openingDateInscription;

    @Column(name = "observations", length = Integer.MAX_VALUE)
    private String observations;

    @Column(name = "has_inscription", nullable = false)
    private Boolean hasInscription = false;

    @Column(name = "status", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "activity_type_id", nullable = false)
    @ToString.Exclude
    private ActivityType activityType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "facility_id", nullable = false)
    @ToString.Exclude
    private Facility facility;

    @OneToMany(mappedBy = "activity")
    @ToString.Exclude
    private List<Enrollment> enrollments = new ArrayList<>();

    @OneToMany(mappedBy = "activity")
    @ToString.Exclude
    private List<Payment> payments = new ArrayList<>();

}