package de.hsrm.mi.web.projekt.validators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.beans.BeanUtils;

import de.hsrm.mi.web.projekt.entities.benutzer.Benutzer;
import de.hsrm.mi.web.projekt.ui.benutzer.BenutzerFormular;
import de.hsrm.mi.web.projekt.validators.PasswordMatch.PasswordMatchValidator;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordMatchValidator.class)
public @interface PasswordMatch {
    String message() default "Passwords do not match";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    

    public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, Object> {
   
    @Override
    public void initialize(PasswordMatch constraintAnnotation) {
        
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value instanceof BenutzerFormular) {
            BenutzerFormular benutzer = (BenutzerFormular) value;
            String password = benutzer.getPasswort();
            String confirmPassword = benutzer.getPasswortwiederholung();
            return password != null && password.equals(confirmPassword);
        }

        return false;
    }
}
}