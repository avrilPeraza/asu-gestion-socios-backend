package proyecto.spring.asugestionsocios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import proyecto.spring.asugestionsocios.model.entity.FeatureProfile;

import java.util.List;
import java.util.Optional;

public interface FeatureProfileRepository extends JpaRepository<FeatureProfile, Long> {

    @Query(value = "SELECT * FROM feature_profile WHERE profile_id = :profileId AND feature_id = :featureId", nativeQuery = true)
    Optional<FeatureProfile> findPermissionByProfileAndFeature(Long profileId, Long featureId);

    @Query(value = "SELECT * FROM feature_profile WHERE profile_id = :profileId", nativeQuery = true)
    List<FeatureProfile> findAllByProfileId(Long profileId);

}
