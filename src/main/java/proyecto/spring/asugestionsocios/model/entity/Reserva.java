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
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora;

    @Column(name = "duracion", nullable = false)
    private Integer duracion;

    @Column(name = "cantidad_personas", nullable = false)
    private Short cantidadPersonas;

    @Column(name = "importe_total", nullable = false, precision = 10, scale = 2)
    private BigDecimal importeTotal;

    @Column(name = "fecha_vencimiento_senia", nullable = false)
    private LocalDate fechaVencimientoSenia;

    @Column(name = "importe_senia", nullable = false, precision = 10, scale = 2)
    private BigDecimal importeSenia;

    @Column(name = "fecha_pago_senia", nullable = false)
    private LocalDate fechaPagoSenia;

    @Column(name = "senia_pagada", nullable = false, precision = 10, scale = 2)
    private BigDecimal seniaPagada;

    @Column(name = "saldo_pendiente", nullable = false, precision = 10, scale = 2)
    private BigDecimal saldoPendiente;

    @Column(name = "estado", nullable = false, length = 20)
    private EstadoReserva estadoReserva;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    @ToString.Exclude
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "espacio_id", nullable = false)
    @ToString.Exclude
    private Espacio espacio;

    @OneToMany(mappedBy = "reserva")
    @ToString.Exclude
    private List<Pago> pagos = new ArrayList<>();

}