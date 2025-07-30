package proyecto.spring.asugestionsocios.Config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.context.annotation.Configuration;
/*
* configuring Swagger for REST API documentation generation
* */

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "ASU - Member Management API",
                description = "Comprehensive API for managing member operations including users, facilities, activities, profiles, and more.",
                version = "V1.0.0"
        ),
        servers = {
                @Server(url = "http://localhost:8080/v1")
        },
        security = @SecurityRequirement(name = "bearerAuth"),
        tags = {
                @Tag(name = "Auth", description = "Authentication management"),
                @Tag(name = "User", description = "User management"),
                @Tag(name = "Activity", description = "Activity and enrollment management"),
                @Tag(name = "Profile", description = "Profile management"),
                @Tag(name = "Feature", description = "Feature management and authorization"),
                @Tag(name = "Audit", description = "Audit management and reports"),
                @Tag(name = "Facility", description = "Facility and reservations management"),
                @Tag(name = "Activity Type", description = "Activity Type management"),
                @Tag(name = "Payment", description = "Payment processing")
        }
)
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)
public class OpenApiConfig {


}
