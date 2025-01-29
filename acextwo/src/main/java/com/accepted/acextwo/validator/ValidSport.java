package com.accepted.acextwo.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = SportValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidSport {
    String message() default "Sport must be one of the following: 1, 2, Football, Basketball";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
