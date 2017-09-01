/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josianegamgo.ebooksstore.entities;

import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Josiane Gamgo
 */
public class ReviewsTest {
    
    public ReviewsTest() {
    }

    /**
     * Test of getReviewsid method, of class Reviews.
     */
    @Test
    public void testGetReviewsid() {
        System.out.println("getReviewsid");
        Reviews instance = new Reviews();
        Integer expResult = null;
        Integer result = instance.getReviewsid();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of setReviewsid method, of class Reviews.
     */
    @Test
    public void testSetReviewsid() {
        System.out.println("setReviewsid");
        Integer reviewsid = null;
        Reviews instance = new Reviews();
        instance.setReviewsid(reviewsid);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRating method, of class Reviews.
     */
    @Test
    public void testGetRating() {
        System.out.println("getRating");
        Reviews instance = new Reviews();
        Integer expResult = null;
        Integer result = instance.getRating();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRating method, of class Reviews.
     */
    @Test
    public void testSetRating() {
        System.out.println("setRating");
        Integer rating = null;
        Reviews instance = new Reviews();
        instance.setRating(rating);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getReviewtext method, of class Reviews.
     */
    @Test
    public void testGetReviewtext() {
        System.out.println("getReviewtext");
        Reviews instance = new Reviews();
        String expResult = "";
        String result = instance.getReviewtext();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of setReviewtext method, of class Reviews.
     */
    @Test
    public void testSetReviewtext() {
        System.out.println("setReviewtext");
        String reviewtext = "";
        Reviews instance = new Reviews();
        instance.setReviewtext(reviewtext);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSubmitdate method, of class Reviews.
     */
    @Test
    public void testGetSubmitdate() {
        System.out.println("getSubmitdate");
        Reviews instance = new Reviews();
        Date expResult = null;
        Date result = instance.getSubmitdate();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSubmitdate method, of class Reviews.
     */
    @Test
    public void testSetSubmitdate() {
        System.out.println("setSubmitdate");
        Date submitdate = null;
        Reviews instance = new Reviews();
        instance.setSubmitdate(submitdate);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getApprovalstatus method, of class Reviews.
     */
    @Test
    public void testGetApprovalstatus() {
        System.out.println("getApprovalstatus");
        Reviews instance = new Reviews();
        Boolean expResult = null;
        Boolean result = instance.getApprovalstatus();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of setApprovalstatus method, of class Reviews.
     */
    @Test
    public void testSetApprovalstatus() {
        System.out.println("setApprovalstatus");
        Boolean approvalstatus = null;
        Reviews instance = new Reviews();
        instance.setApprovalstatus(approvalstatus);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBookisbn method, of class Reviews.
     */
    @Test
    public void testGetBookisbn() {
        System.out.println("getBookisbn");
        Reviews instance = new Reviews();
        Bookinventory expResult = null;
        Bookinventory result = instance.getBookisbn();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of setBookisbn method, of class Reviews.
     */
    @Test
    public void testSetBookisbn() {
        System.out.println("setBookisbn");
        Bookinventory bookisbn = null;
        Reviews instance = new Reviews();
        instance.setBookisbn(bookisbn);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getClientid method, of class Reviews.
     */
    @Test
    public void testGetClientid() {
        System.out.println("getClientid");
        Reviews instance = new Reviews();
        Clientrecords expResult = null;
        Clientrecords result = instance.getClientid();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of setClientid method, of class Reviews.
     */
    @Test
    public void testSetClientid() {
        System.out.println("setClientid");
        Clientrecords clientid = null;
        Reviews instance = new Reviews();
        instance.setClientid(clientid);
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class Reviews.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Reviews instance = new Reviews();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class Reviews.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object object = null;
        Reviews instance = new Reviews();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Reviews.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Reviews instance = new Reviews();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
    
}
