/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josianegamgo.ebooksstore.validators;

import javax.validation.ConstraintValidatorContext;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Josiane Gamgo
 */
public class dataValidatorTest {
    
    public dataValidatorTest() {
    }

    /**
     * Test of initialize method, of class dataValidator.
     */
    @Test
    public void testInitialize() {
        System.out.println("initialize");
        OptionalCellNumber constraintAnnotation = null;
        dataValidator instance = new dataValidator();
        instance.initialize(constraintAnnotation);
        fail("The test case is a prototype.");
    }

    /**
     * Test of isValid method, of class dataValidator.
     */
    @Test
    public void testIsValid() {
        System.out.println("isValid");
        String value = "";
        ConstraintValidatorContext context = null;
        dataValidator instance = new dataValidator();
        boolean expResult = false;
        boolean result = instance.isValid(value, context);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
    
}
