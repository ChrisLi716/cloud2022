package com.chris.cloud.service.impl;

import com.chris.cloud.anno.IdentityCardNumber;
import com.chris.cloud.util.IdCardValidatorUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class IdentityCardNumberValidator implements ConstraintValidator<IdentityCardNumber, Object> {

    @Override
    public void initialize(IdentityCardNumber identityCardNumber) {
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        if (Objects.isNull(o)) {
            return true;
        }
        return IdCardValidatorUtils.isValidate18Idcard(o.toString());
    }
}