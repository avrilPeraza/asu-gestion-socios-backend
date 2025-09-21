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
@Table(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "total_amount", precision = 10, scale = 2)
    private BigDecimal totalAmount;

    @Column(name = "expiration_date_deposit")
    private LocalDate expirationDateDeposit;

    @Column(name = "total_deposit", precision = 10, scale = 2)
    private BigDecimal totalDeposit;

    @Column(name = "deposit_payment_date")
    private LocalDate depositPaymentDate;

    @Column(name = "deposit_paid", precision = 10, scale = 2)
    private BigDecimal depositPaid;

    @Column(name = "outstanding_balance", precision = 10, scale = 2)
    private BigDecimal outstandingBalance;

    @Column(name = "reservation_status", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @ToString.Exclude
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "activity_id", nullable = false)
    @ToString.Exclude
    private Activity activity;

    @OneToMany(mappedBy = "reservation")
    @ToString.Exclude
    private List<Payment> payments = new ArrayList<>();

}