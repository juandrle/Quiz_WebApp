package validators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import validators.Verschieden.VerschiedenValidator;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = VerschiedenValidator.class)
public @interface Verschieden {
    String message() default "Liste darf keine Duplikate enthalten";

    Class<? extends Payload>[] payload() default {};

    Class<?>[] groups() default {};

    public class VerschiedenValidator implements ConstraintValidator<Verschieden, List<String>> {

        @Override
        public void initialize(Verschieden v) {
        }

        @Override
        public boolean isValid(List<String> list, ConstraintValidatorContext ctx) {
            /*
             * stream() -> converts to Stream object (sequence of elements)
             * map -> applies operation on each element of Stream
             * distinct() -> filters to remove any duplicate elements, returns new Stream
             * with only distinct elements
             * count() -> counts number of elements in Stream
             * 
             * "::" or method reference operator creates reference to method of
             * class String in this case, shorthand for lambda expression
             */
            return list == null || list.stream().map(String::toLowerCase).distinct().count() == list.size();
        }
    }
}
