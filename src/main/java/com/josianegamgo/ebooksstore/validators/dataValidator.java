/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josianegamgo.ebooksstore.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author jgamgo
 */
public class dataValidator implements ConstraintValidator<OptionalCellNumber, String> {

    @Override
    public void initialize(OptionalCellNumber constraintAnnotation) {
    }

    /*
     1.  If length of the string is 0 then pass validation
     2.  If the length of the string is not 14 fail validation
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (value.length() == 0) {
            return true;
        }

        return value.length() == 14;
    }
}
