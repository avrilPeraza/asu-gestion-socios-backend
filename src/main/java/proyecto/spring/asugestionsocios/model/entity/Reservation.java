package proyecto.spring.asugestionsocios.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "reserva")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime dateTime;

    @Column(name = "duracion", nullable = false)
    private Integer duration;

    @Column(name = "cantidad_personas", nullable = false)
    private Short numberPeople;

    @Column(name = "importe_total", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalAmount;

    @Column(name = "fecha_vencimiento_senia", nullable = false)
    private LocalDate expirationDateDeposit;

    @Column(name = "importe_senia", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalDeposit;

    @Column(name = "fecha_pago_senia", nullable = false)
    private LocalDate depositPaymentDate;

    @Column(name = "senia_pagada", nullable = false, precision = 10, scale = 2)
    private BigDecimal depositPaid;

    @Column(name = "saldo_pendiente", nullable = false, precision = 10, scale = 2)
    private BigDecimal outstandingBalance;

    @Column(name = "estado", nullable = false, length = 20)
    private ReservationStatus reservationStatus;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    @ToString.Exclude
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "espacio_id", nullable = false)
    @ToString.Exclude
    private Facility facility;

    @OneToMany(mappedBy = "reservation")
    @ToString.Exclude
    private List<Payment> payments = new ArrayList<>();

}