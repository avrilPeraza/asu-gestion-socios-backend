package proyecto.spring.asugestionsocios.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "monthly_fee")
public class MonthlyFee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "monthly_fee_number", nullable = false)
    private Integer monthlyFeeNumber;

    @Column(name = "total_annual_monthly_fee", nullable = false)
    private Integer totalAnnualMonthlyFee;

    @Column(name = "payment_due_date", nullable = false)
    private LocalDateTime paymentDueDate;
}