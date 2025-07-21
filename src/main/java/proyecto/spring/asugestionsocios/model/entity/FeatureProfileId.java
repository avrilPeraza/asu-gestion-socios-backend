package proyecto.spring.asugestionsocios.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
public class FeatureProfileId implements Serializable {
    @Column(name = "funcionalidad_id")
    private Long featureId;

    @Column(name = "perfil_id")
    private Long profileId;
}
