package proyecto.spring.asugestionsocios.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
public class FuncionalidadPerfilId implements Serializable {
    @Column(name = "funcionalidad_id")
    private Long funcionalidadId;

    @Column(name = "perfil_id")
    private Long perfilId;
}
