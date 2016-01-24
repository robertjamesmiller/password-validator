package com.dropclip.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = { PasswordValidator.class })
@Target({ FIELD }) // Keep it simple to start by only supporting fields. METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER
@Retention(RUNTIME)
@ReportAsSingleViolation
public @interface Password {
    // @TODO: Figure out how to move validation messages to a localized properties file.
	String message() default "Must consist of a mixture of lowercase letters and numerical digits only, with at least one of each. Must be between 5 and 12 characters in length. Must not contain any sequence of characters immediately followed by the same sequence.";
	
	Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { }; 
}
