package proyecto.spring.asugestionsocios.util;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UserMemberValidator.class)
public @interface ValidUserMember {
    String message() default "Invalid member user data";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};}
