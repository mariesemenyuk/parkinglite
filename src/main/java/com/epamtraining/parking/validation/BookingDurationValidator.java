package com.epamtraining.parking.validation;

import com.epamtraining.parking.annotations.BookingDuration;
import com.epamtraining.parking.model.BookingRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.Duration;


public class BookingDurationValidator implements ConstraintValidator<BookingDuration, BookingRequest> {

    @Override
    public void initialize(BookingDuration constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(BookingRequest request, ConstraintValidatorContext context) {
        long parkingTime = Math.abs(Duration.between(request.getTo(), request.getFrom()).toMinutes());
        return request.getFrom().isBefore(request.getTo()) && (parkingTime <= 1440L && parkingTime >= 30L);
    }
}


