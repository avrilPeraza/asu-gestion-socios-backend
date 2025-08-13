package proyecto.spring.asugestionsocios.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import proyecto.spring.asugestionsocios.model.entity.Audit;
import proyecto.spring.asugestionsocios.model.entity.Feature;
import proyecto.spring.asugestionsocios.model.entity.User;
import proyecto.spring.asugestionsocios.repository.AuditRepository;
import proyecto.spring.asugestionsocios.repository.FeatureRepository;

import java.time.LocalDateTime;

@Service
public class AuditService {

    private final AuditRepository auditRepository;
    private final FeatureRepository featureRepository;

    public AuditService(AuditRepository auditRepository, FeatureRepository featureRepository){
        this.auditRepository = auditRepository;
        this.featureRepository = featureRepository;
    }

    public void registerAudit(User user, String operationName, String terminal){
        Feature feature = featureRepository.findByName(operationName)
                .orElseThrow(() -> new EntityNotFoundException("Feature not found with name: "+ operationName));

        Audit audit = new Audit();
        audit.setUser(user);
        audit.setFeature(feature);
        audit.setTerminal(terminal);
        audit.setDateTime(LocalDateTime.now());

        auditRepository.save(audit);
    }
}
