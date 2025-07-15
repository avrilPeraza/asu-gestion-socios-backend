package proyecto.spring.asugestionsocios.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "espacio")
public class Espacio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "descripcion", nullable = false, length = Integer.MAX_VALUE)
    private String descripcion;

    @Column(name = "capacidad", nullable = false)
    private Integer capacidad;

    @Column(name = "tarifa_no_socio", nullable = false, precision = 10, scale = 2)
    private BigDecimal tarifaNoSocio;

    @Column(name = "tarifa_socio", nullable = false, precision = 10, scale = 2)
    private BigDecimal tarifaSocio;

    @Column(name = "fecha_vigencia_precio", nullable = false)
    private LocalDate fechaVigenciaPrecio;

    @Column(name = "estado", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private Estado estado;

    @OneToMany(mappedBy = "espacio")
    @ToString.Exclude
    private List<Actividad> actividades = new ArrayList<>();

    @OneToMany(mappedBy = "espacio")
    @ToString.Exclude
    private List<Reserva> reservas = new ArrayList<>();

}