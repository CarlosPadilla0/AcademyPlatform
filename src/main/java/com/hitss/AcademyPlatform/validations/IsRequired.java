package com.hitss.AcademyPlatform.validations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = IsRequiredValidation.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface IsRequired {
	String message() default "{IsRequired}";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};


}
