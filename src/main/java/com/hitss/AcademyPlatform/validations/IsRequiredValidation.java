package com.hitss.AcademyPlatform.validations;

import io.micrometer.common.util.StringUtils;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IsRequiredValidation implements ConstraintValidator<IsRequired, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
	return StringUtils.isNotBlank(value) && !value.trim().isEmpty() && !value.equals("null") && !value.equals("undefined");
	}
	



}
