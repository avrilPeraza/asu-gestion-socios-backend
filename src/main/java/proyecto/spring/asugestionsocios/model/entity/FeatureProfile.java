package proyecto.spring.asugestionsocios.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "feature_profile")
public class FeatureProfile {
    @EmbeddedId
    private FeatureProfileId featureProfileId;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("featureId")
    @JoinColumn(name = "feature_id")
    private Feature feature;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("profileId")
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @Column(name = "has_permission_state", nullable = false)
    @Enumerated(EnumType.STRING)
    private PermissionState hasPermissionState;

}
