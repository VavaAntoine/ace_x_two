package com.accepted.acextwo.validator;

import com.accepted.acextwo.entity.Match;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SportValidator implements ConstraintValidator<ValidSport, Match.Sport> {

    @Override
    public boolean isValid(Match.Sport value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        return value == Match.Sport.Football || value == Match.Sport.Basketball;
    }
}
