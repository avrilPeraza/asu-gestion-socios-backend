package proyecto.spring.asugestionsocios.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.context.SecurityContextHolder;
import proyecto.spring.asugestionsocios.model.entity.User;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JpaAuditingConfig {

    @Bean
    public AuditorAware<User> auditorProvider(){
        return () -> Optional.ofNullable(
                SecurityContextHolder.getContext()
                        .getAuthentication()
                        .getPrincipal()
        ).map(principal -> (User) principal);
    }
}
