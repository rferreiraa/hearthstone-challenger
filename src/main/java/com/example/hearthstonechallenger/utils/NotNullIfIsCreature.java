package com.example.hearthstonechallenger.utils;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target(value = { ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(NotNullIfIsCreature.List.class)
@Constraint(validatedBy = NotNullIfIsCreatureValidator.class)
@Documented
public @interface NotNullIfIsCreature {

	String fieldName();

	String fieldValue();

	String dependFieldName();

	String message() default "Required field for CREATURES";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	@Target(value = { ElementType.TYPE, ElementType.ANNOTATION_TYPE })
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	@interface List {
		NotNullIfIsCreature[] value();
	}
}
