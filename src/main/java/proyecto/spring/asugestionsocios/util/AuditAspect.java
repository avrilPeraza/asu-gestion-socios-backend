package proyecto.spring.asugestionsocios.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import proyecto.spring.asugestionsocios.model.entity.User;
import proyecto.spring.asugestionsocios.service.AuditService;

@Aspect
@Component
public class AuditAspect {
    private final AuditService auditService;
    private final SecurityUtil securityUtil;

    public AuditAspect(AuditService auditService, SecurityUtil securityUtil){
        this.auditService = auditService;
        this.securityUtil = securityUtil;
    }

    @AfterReturning("@annotation(auditable)")
    public void registerAudit(JoinPoint joinPoint, Auditable auditable){
        User user = securityUtil.getCurrentUser();
        String terminal = "127.0.0.1"; //o request.getRemoteAddr();
        auditService.registerAudit(user, auditable.operation(), terminal);
    }
}
