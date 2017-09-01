/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josianegamgo.ebooksstore.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author utilisateur
 */
public class InvoiceTest {
    
    public InvoiceTest() {
    }

    /**
     * Test of getSaleid method, of class Invoice.
     */
    @Test
    public void testGetSaleid() {
        System.out.println("getSaleid");
        Invoice instance = new Invoice();
        Integer expResult = null;
        Integer result = instance.getSaleid();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSaleid method, of class Invoice.
     */
    @Test
    public void testSetSaleid() {
        System.out.println("setSaleid");
        Integer saleid = null;
        Invoice instance = new Invoice();
        instance.setSaleid(saleid);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGst method, of class Invoice.
     */
    @Test
    public void testGetGst() {
        System.out.println("getGst");
        Invoice instance = new Invoice();
        BigDecimal expResult = null;
        BigDecimal result = instance.getGst();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of setGst method, of class Invoice.
     */
    @Test
    public void testSetGst() {
        System.out.println("setGst");
        BigDecimal gst = null;
        Invoice instance = new Invoice();
        instance.setGst(gst);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDateofsale method, of class Invoice.
     */
    @Test
    public void testGetDateofsale() {
        System.out.println("getDateofsale");
        Invoice instance = new Invoice();
        Date expResult = null;
        Date result = instance.getDateofsale();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDateofsale method, of class Invoice.
     */
    @Test
    public void testSetDateofsale() {
        System.out.println("setDateofsale");
        Date dateofsale = null;
        Invoice instance = new Invoice();
        instance.setDateofsale(dateofsale);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTotalnetsale method, of class Invoice.
     */
    @Test
    public void testGetTotalnetsale() {
        System.out.println("getTotalnetsale");
        Invoice instance = new Invoice();
        BigDecimal expResult = null;
        BigDecimal result = instance.getTotalnetsale();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTotalnetsale method, of class Invoice.
     */
    @Test
    public void testSetTotalnetsale() {
        System.out.println("setTotalnetsale");
        BigDecimal totalnetsale = null;
        Invoice instance = new Invoice();
        instance.setTotalnetsale(totalnetsale);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTotalgrosssale method, of class Invoice.
     */
    @Test
    public void testGetTotalgrosssale() {
        System.out.println("getTotalgrosssale");
        Invoice instance = new Invoice();
        BigDecimal expResult = null;
        BigDecimal result = instance.getTotalgrosssale();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTotalgrosssale method, of class Invoice.
     */
    @Test
    public void testSetTotalgrosssale() {
        System.out.println("setTotalgrosssale");
        BigDecimal totalgrosssale = null;
        Invoice instance = new Invoice();
        instance.setTotalgrosssale(totalgrosssale);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInvoicedetailsList method, of class Invoice.
     */
    @Test
    public void testGetInvoicedetailsList() {
        System.out.println("getInvoicedetailsList");
        Invoice instance = new Invoice();
        List<Invoicedetails> expResult = null;
        List<Invoicedetails> result = instance.getInvoicedetailsList();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of setInvoicedetailsList method, of class Invoice.
     */
    @Test
    public void testSetInvoicedetailsList() {
        System.out.println("setInvoicedetailsList");
        List<Invoicedetails> invoicedetailsList = null;
        Invoice instance = new Invoice();
        instance.setInvoicedetailsList(invoicedetailsList);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getClientnum method, of class Invoice.
     */
    @Test
    public void testGetClientnum() {
        System.out.println("getClientnum");
        Invoice instance = new Invoice();
        Clientrecords expResult = null;
        Clientrecords result = instance.getClientnum();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of setClientnum method, of class Invoice.
     */
    @Test
    public void testSetClientnum() {
        System.out.println("setClientnum");
        Clientrecords clientnum = null;
        Invoice instance = new Invoice();
        instance.setClientnum(clientnum);
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class Invoice.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Invoice instance = new Invoice();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class Invoice.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object object = null;
        Invoice instance = new Invoice();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Invoice.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Invoice instance = new Invoice();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
    
}
