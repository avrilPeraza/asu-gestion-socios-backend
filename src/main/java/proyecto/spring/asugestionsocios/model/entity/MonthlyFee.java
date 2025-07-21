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
@Table(name = "cuota")
public class MonthlyFee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nro_cuota", nullable = false)
    private Integer monthlyFeeNumber;

    @Column(name = "total_anual_cuota", nullable = false)
    private Integer totalAnnualMonthlyFee;

    @Column(name = "fecha_vencimiento", nullable = false)
    private LocalDateTime paymentDueDate;
}