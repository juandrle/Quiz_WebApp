package validators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import validators.Verschieden.VerschiedenValidator;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = VerschiedenValidator.class)
public @interface Verschieden {
    String message() default "Liste enthält doppelte Strings";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    public class VerschiedenValidator implements ConstraintValidator<Verschieden, List<String>> {
        @Override
        public boolean isValid(List<String> values, ConstraintValidatorContext context) {
            HashSet<String> uniqueValues = new HashSet<>(values.stream().map(String::toLowerCase).collect(Collectors.toList()));
            if (uniqueValues.size() != values.size()) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("Liste darf keine Duplikate enthalten").addConstraintViolation();
                return false;
            }
            return true;
        }

    }
    
}
