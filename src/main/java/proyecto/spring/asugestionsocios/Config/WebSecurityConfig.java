package proyecto.spring.asugestionsocios.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import proyecto.spring.asugestionsocios.exception.AuthExceptionHandler;
import proyecto.spring.asugestionsocios.security.AuthTokenFilter;
import proyecto.spring.asugestionsocios.security.CustomAuthProvider;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private AuthExceptionHandler authExceptionHandler;

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter(){
        return new AuthTokenFilter();
    }

    /*@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/v1/auth/**").permitAll()
                );

    }*/




    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, CustomAuthProvider customAuthProvider) throws Exception{
        return http.getSharedObject(AuthenticationManagerBuilder.class).authenticationProvider(customAuthProvider).build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
