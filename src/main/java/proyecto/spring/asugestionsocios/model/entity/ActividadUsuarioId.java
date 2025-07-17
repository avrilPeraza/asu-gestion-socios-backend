package proyecto.spring.asugestionsocios.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class ActividadUsuarioId implements Serializable {
    @Serial
    private static final long serialVersionUID = 740801641592048827L;
    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;

    @Column(name = "actividad_id", nullable = false)
    private Long actividadId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ActividadUsuarioId entity = (ActividadUsuarioId) o;
        return Objects.equals(this.usuarioId, entity.usuarioId) &&
                Objects.equals(this.actividadId, entity.actividadId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuarioId, actividadId);
    }

}