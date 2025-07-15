package proyecto.spring.asugestionsocios.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "funcionalidad")
public class Funcionalidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "descripcion", nullable = false, length = Integer.MAX_VALUE)
    private String descripcion;

    @Column(name = "estado", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private Estado estado;

    @OneToMany(mappedBy = "funcionalidad")
    @ToString.Exclude
    private List<Auditoria> auditorias = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "funcionalidad_perfil",
    joinColumns = @JoinColumn(name = "funcionalidad_id"),
    inverseJoinColumns = @JoinColumn(name = "perfil_id"))
    @ToString.Exclude
    private List<Perfil> perfiles = new ArrayList<>();

}