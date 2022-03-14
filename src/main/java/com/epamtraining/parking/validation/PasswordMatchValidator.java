package com.epamtraining.parking.validation;

import com.epamtraining.parking.annotations.PasswordMatch;
import com.epamtraining.parking.model.UserRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, UserRequest> {
    @Override
    public void initialize(PasswordMatch constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UserRequest value, ConstraintValidatorContext context) {
        String password = value.getPassword();
        String matchingPassword = value.getMatchingPassword();
        return password.equals(matchingPassword);
    }
}
