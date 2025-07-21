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
@Table(name = "actividad")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100, unique = true)
    private String name;

    @Column(name = "descripcion", nullable = false, length = Integer.MAX_VALUE)
    private String description;

    @Column(name = "duracion", nullable = false)
    private Integer duration;

    @Column(name = "costo_ticket", nullable = false, precision = 10, scale = 2)
    private BigDecimal ticketCost;

    @Column(name = "forma_pago", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private MethodPayment methodPayment;

    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime dateTime;

    @Column(name = "fch_apertura_insc", nullable = false)
    private LocalDateTime openingDateInscription;

    @Column(name = "observaciones", length = Integer.MAX_VALUE)
    private String observations;

    @Column(name = "inscripcion", nullable = false)
    private Boolean hasInscription = false;

    @Column(name = "estado", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private status status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tipo_actividad_id", nullable = false)
    @ToString.Exclude
    private ActivityType activityType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "espacio_id", nullable = false)
    @ToString.Exclude
    private Facility facility;

    @OneToMany(mappedBy = "activity")
    @ToString.Exclude
    private List<Enrollment> enrollments = new ArrayList<>();

    @OneToMany(mappedBy = "activity")
    @ToString.Exclude
    private List<Payment> payments = new ArrayList<>();

}