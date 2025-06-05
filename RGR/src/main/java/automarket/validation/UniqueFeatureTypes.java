package automarket.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueFeatureTypesValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueFeatureTypes {
    String message() default "Машина не может иметь несколько характеристик одного типа";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}