package proyecto.spring.asugestionsocios.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "funcionalidad_perfil")
public class FuncionalidadPerfil {
    @EmbeddedId
    private FuncionalidadPerfilId funcionalidadPerfilId;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("funcionalidadId")
    @JoinColumn(name = "funcionalidad_id")
    private Funcionalidad funcionalidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("perfilId")
    @JoinColumn(name = "perfil_id")
    private Perfil perfil;

    @Column(name = "acceso", nullable = false)
    @Enumerated(EnumType.STRING)
    private Acesso acceso;

}
