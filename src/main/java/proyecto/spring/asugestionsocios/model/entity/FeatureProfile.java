package proyecto.spring.asugestionsocios.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "funcionalidad_perfil")
public class FeatureProfile {
    @EmbeddedId
    private FeatureProfileId featureProfileId;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("funcionalidadId")
    @JoinColumn(name = "funcionalidad_id")
    private Feature feature;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("perfilId")
    @JoinColumn(name = "perfil_id")
    private Profile profile;

    @Column(name = "acceso", nullable = false)
    @Enumerated(EnumType.STRING)
    private PermissionState hasPermissionState;

}
