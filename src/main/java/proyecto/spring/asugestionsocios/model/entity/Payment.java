package proyecto.spring.asugestionsocios.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "pago")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "tipo_pago", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @Column(name = "fecha", nullable = false)
    private LocalDateTime date;

    @Column(name = "monto", nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(name = "forma_pago", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private MethodPayment methodPayment;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    @ToString.Exclude
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reserva_id")
    @ToString.Exclude
    private Reservation reservation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "actividad_id")
    @ToString.Exclude
    private Activity activity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cuota_id")
    @ToString.Exclude
    private MonthlyFee monthlyFee;

}