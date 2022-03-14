package com.epamtraining.parking.annotations;

import com.epamtraining.parking.validation.BookingDurationValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BookingDurationValidator.class)

public @interface BookingDuration {
    String message() default "{BookingDuration.invalid}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
