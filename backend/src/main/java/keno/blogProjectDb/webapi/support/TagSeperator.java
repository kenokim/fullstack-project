package keno.blogProjectDb.webapi.support;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ArticleFormValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TagSeperator {
    String message() default "Invalid Tag input";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
