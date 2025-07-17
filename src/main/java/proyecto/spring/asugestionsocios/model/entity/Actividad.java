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
public class Actividad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100, unique = true)
    private String nombre;

    @Column(name = "descripcion", nullable = false, length = Integer.MAX_VALUE)
    private String descripcion;

    @Column(name = "duracion", nullable = false)
    private Integer duracion;

    @Column(name = "costo_ticket", nullable = false, precision = 10, scale = 2)
    private BigDecimal costoTicket;

    @Column(name = "forma_pago", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private FormaPago formaPago;

    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora;

    @Column(name = "fch_apertura_insc", nullable = false)
    private LocalDateTime fechaAperturaInsc;

    @Column(name = "observaciones", length = Integer.MAX_VALUE)
    private String observaciones;

    @Column(name = "inscripcion", nullable = false)
    private Boolean inscripcion = false;

    @Column(name = "estado", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private Estado estado;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tipo_actividad_id", nullable = false)
    @ToString.Exclude
    private TipoActividad tipoActividad;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "espacio_id", nullable = false)
    @ToString.Exclude
    private Espacio espacio;

    @OneToMany(mappedBy = "actividad")
    @ToString.Exclude
    private List<ActividadUsuario> actividadUsuarios = new ArrayList<>();

    @OneToMany(mappedBy = "actividad")
    @ToString.Exclude
    private List<Pago> pagos = new ArrayList<>();

}