package keno.blogProjectDb.webapi.support;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ArticleFormValidator implements ConstraintValidator<TagSeperator, String> {
    @Override
    public void initialize(TagSeperator constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return true;
    }
}
