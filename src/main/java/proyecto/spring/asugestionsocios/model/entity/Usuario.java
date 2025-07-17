package proyecto.spring.asugestionsocios.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Builder
@Entity
@Table(name = "usuario")
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "tipo_documento", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private TipoDocumento tipoDocumento;

    @Column(name = "documento", nullable = false, length = 20, unique = true)
    private String documento;

    @Column(name = "email", nullable = false, length = 100, unique = true)
    private String email;

    @Column(name = "nombres", nullable = false, length = 50)
    private String nombres;

    @Column(name = "apellidos", nullable = false, length = 50)
    private String apellidos;

    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    @Column(name = "contrasenia", nullable = false, length = 100)
    private String contrasenia;

    @Column(name = "estado", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private EstadoUsuario estado;

    @Column(name = "uso_lenguaje_senia", nullable = false)
    private Boolean usoLenguajeSenia = false;

    @Column(name = "dif_auditiva", nullable = false)
    private Boolean difAuditiva = false;

    @Column(name = "calle", nullable = false, length = 50)
    private String calle;

    @Column(name = "nro_puerta", nullable = false, length = 20)
    private String numPuerta;

    @Column(name = "apartamento", nullable = false, length = 20)
    private String apartamento;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "perfil_id", nullable = false)
    @ToString.Exclude
    private Perfil perfil;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "subcomision_id", nullable = false)
    @ToString.Exclude
    private Subcomision subcomision;

    @OneToMany(mappedBy = "usuario")
    @ToString.Exclude
    private List<ActividadUsuario> actividadUsuarios = new ArrayList<>();

    @OneToMany(mappedBy = "usuario")
    @ToString.Exclude
    private List<Auditoria> auditorias = new ArrayList<>();

    @OneToMany(mappedBy = "usuario")
    @ToString.Exclude
    private List<BajaTipoActividad> bajaTipoActividades = new ArrayList<>();

    @OneToMany(mappedBy = "usuario")
    @ToString.Exclude
    private List<Pago> pagos = new ArrayList<>();

    @OneToMany(mappedBy = "usuario")
    @ToString.Exclude
    private List<Reserva> reservas = new ArrayList<>();

    @OneToMany(mappedBy = "usuario")
    @ToString.Exclude
    private List<Telefono> telefonos = new ArrayList<>();

}