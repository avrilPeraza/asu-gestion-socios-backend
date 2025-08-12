package proyecto.spring.asugestionsocios.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
@Setter
public class FeatureProfileId implements Serializable {
    @Column(name = "feature_id")
    private Long featureId;

    @Column(name = "profile_id")
    private Long profileId;
}
