/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josianegamgo.ebooksstore.entities;

import java.math.BigDecimal;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author utilisateur
 */
public class InvoicedetailsTest {
    
    public InvoicedetailsTest() {
    }

    /**
     * Test of getInvoicedetailsid method, of class Invoicedetails.
     */
    @Test
    public void testGetInvoicedetailsid() {
        System.out.println("getInvoicedetailsid");
        Invoicedetails instance = new Invoicedetails();
        Integer expResult = null;
        Integer result = instance.getInvoicedetailsid();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of setInvoicedetailsid method, of class Invoicedetails.
     */
    @Test
    public void testSetInvoicedetailsid() {
        System.out.println("setInvoicedetailsid");
        Integer invoicedetailsid = null;
        Invoicedetails instance = new Invoicedetails();
        instance.setInvoicedetailsid(invoicedetailsid);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPrice method, of class Invoicedetails.
     */
    @Test
    public void testGetPrice() {
        System.out.println("getPrice");
        Invoicedetails instance = new Invoicedetails();
        BigDecimal expResult = null;
        BigDecimal result = instance.getPrice();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPrice method, of class Invoicedetails.
     */
    @Test
    public void testSetPrice() {
        System.out.println("setPrice");
        BigDecimal price = null;
        Invoicedetails instance = new Invoicedetails();
        instance.setPrice(price);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getIsbnid method, of class Invoicedetails.
     */
    @Test
    public void testGetIsbnid() {
        System.out.println("getIsbnid");
        Invoicedetails instance = new Invoicedetails();
        Bookinventory expResult = null;
        Bookinventory result = instance.getIsbnid();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of setIsbnid method, of class Invoicedetails.
     */
    @Test
    public void testSetIsbnid() {
        System.out.println("setIsbnid");
        Bookinventory isbnid = null;
        Invoicedetails instance = new Invoicedetails();
        instance.setIsbnid(isbnid);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSaleid method, of class Invoicedetails.
     */
    @Test
    public void testGetSaleid() {
        System.out.println("getSaleid");
        Invoicedetails instance = new Invoicedetails();
        Invoice expResult = null;
        Invoice result = instance.getSaleid();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSaleid method, of class Invoicedetails.
     */
    @Test
    public void testSetSaleid() {
        System.out.println("setSaleid");
        Invoice saleid = null;
        Invoicedetails instance = new Invoicedetails();
        instance.setSaleid(saleid);
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class Invoicedetails.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Invoicedetails instance = new Invoicedetails();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class Invoicedetails.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object object = null;
        Invoicedetails instance = new Invoicedetails();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Invoicedetails.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Invoicedetails instance = new Invoicedetails();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
    
}
