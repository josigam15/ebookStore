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
public class BookinventoryTest {
    
    public BookinventoryTest() {
    }

    /**
     * Test of getId method, of class Bookinventory.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Bookinventory instance = new Bookinventory();
        Integer expResult = null;
        Integer result = instance.getId();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of setId method, of class Bookinventory.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        Integer id = null;
        Bookinventory instance = new Bookinventory();
        instance.setId(id);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getIsbn method, of class Bookinventory.
     */
    @Test
    public void testGetIsbn() {
        System.out.println("getIsbn");
        Bookinventory instance = new Bookinventory();
        String expResult = "";
        String result = instance.getIsbn();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of setIsbn method, of class Bookinventory.
     */
    @Test
    public void testSetIsbn() {
        System.out.println("setIsbn");
        String isbn = "";
        Bookinventory instance = new Bookinventory();
        instance.setIsbn(isbn);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTitle method, of class Bookinventory.
     */
    @Test
    public void testGetTitle() {
        System.out.println("getTitle");
        Bookinventory instance = new Bookinventory();
        String expResult = "";
        String result = instance.getTitle();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTitle method, of class Bookinventory.
     */
    @Test
    public void testSetTitle() {
        System.out.println("setTitle");
        String title = "";
        Bookinventory instance = new Bookinventory();
        instance.setTitle(title);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAuthor method, of class Bookinventory.
     */
    @Test
    public void testGetAuthor() {
        System.out.println("getAuthor");
        Bookinventory instance = new Bookinventory();
        String expResult = "";
        String result = instance.getAuthor();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAuthor method, of class Bookinventory.
     */
    @Test
    public void testSetAuthor() {
        System.out.println("setAuthor");
        String author = "";
        Bookinventory instance = new Bookinventory();
        instance.setAuthor(author);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPublisher method, of class Bookinventory.
     */
    @Test
    public void testGetPublisher() {
        System.out.println("getPublisher");
        Bookinventory instance = new Bookinventory();
        String expResult = "";
        String result = instance.getPublisher();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPublisher method, of class Bookinventory.
     */
    @Test
    public void testSetPublisher() {
        System.out.println("setPublisher");
        String publisher = "";
        Bookinventory instance = new Bookinventory();
        instance.setPublisher(publisher);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNumberofpages method, of class Bookinventory.
     */
    @Test
    public void testGetNumberofpages() {
        System.out.println("getNumberofpages");
        Bookinventory instance = new Bookinventory();
        Integer expResult = null;
        Integer result = instance.getNumberofpages();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNumberofpages method, of class Bookinventory.
     */
    @Test
    public void testSetNumberofpages() {
        System.out.println("setNumberofpages");
        Integer numberofpages = null;
        Bookinventory instance = new Bookinventory();
        instance.setNumberofpages(numberofpages);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGenre method, of class Bookinventory.
     */
    @Test
    public void testGetGenre() {
        System.out.println("getGenre");
        Bookinventory instance = new Bookinventory();
        String expResult = "";
        String result = instance.getGenre();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of setGenre method, of class Bookinventory.
     */
    @Test
    public void testSetGenre() {
        System.out.println("setGenre");
        String genre = "";
        Bookinventory instance = new Bookinventory();
        instance.setGenre(genre);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBookimage method, of class Bookinventory.
     */
    @Test
    public void testGetBookimage() {
        System.out.println("getBookimage");
        Bookinventory instance = new Bookinventory();
        String expResult = "";
        String result = instance.getBookimage();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of setBookimage method, of class Bookinventory.
     */
    @Test
    public void testSetBookimage() {
        System.out.println("setBookimage");
        String bookimage = "";
        Bookinventory instance = new Bookinventory();
        instance.setBookimage(bookimage);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getWholesaleprice method, of class Bookinventory.
     */
    @Test
    public void testGetWholesaleprice() {
        System.out.println("getWholesaleprice");
        Bookinventory instance = new Bookinventory();
        BigDecimal expResult = null;
        BigDecimal result = instance.getWholesaleprice();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of setWholesaleprice method, of class Bookinventory.
     */
    @Test
    public void testSetWholesaleprice() {
        System.out.println("setWholesaleprice");
        BigDecimal wholesaleprice = null;
        Bookinventory instance = new Bookinventory();
        instance.setWholesaleprice(wholesaleprice);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getListprice method, of class Bookinventory.
     */
    @Test
    public void testGetListprice() {
        System.out.println("getListprice");
        Bookinventory instance = new Bookinventory();
        BigDecimal expResult = null;
        BigDecimal result = instance.getListprice();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of setListprice method, of class Bookinventory.
     */
    @Test
    public void testSetListprice() {
        System.out.println("setListprice");
        BigDecimal listprice = null;
        Bookinventory instance = new Bookinventory();
        instance.setListprice(listprice);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEntrydate method, of class Bookinventory.
     */
    @Test
    public void testGetEntrydate() {
        System.out.println("getEntrydate");
        Bookinventory instance = new Bookinventory();
        Date expResult = null;
        Date result = instance.getEntrydate();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEntrydate method, of class Bookinventory.
     */
    @Test
    public void testSetEntrydate() {
        System.out.println("setEntrydate");
        Date entrydate = null;
        Bookinventory instance = new Bookinventory();
        instance.setEntrydate(entrydate);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRemovalstatus method, of class Bookinventory.
     */
    @Test
    public void testGetRemovalstatus() {
        System.out.println("getRemovalstatus");
        Bookinventory instance = new Bookinventory();
        Boolean expResult = null;
        Boolean result = instance.getRemovalstatus();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRemovalstatus method, of class Bookinventory.
     */
    @Test
    public void testSetRemovalstatus() {
        System.out.println("setRemovalstatus");
        Boolean removalstatus = null;
        Bookinventory instance = new Bookinventory();
        instance.setRemovalstatus(removalstatus);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDescription method, of class Bookinventory.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        Bookinventory instance = new Bookinventory();
        String expResult = "";
        String result = instance.getDescription();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDescription method, of class Bookinventory.
     */
    @Test
    public void testSetDescription() {
        System.out.println("setDescription");
        String description = "";
        Bookinventory instance = new Bookinventory();
        instance.setDescription(description);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPublicationdate method, of class Bookinventory.
     */
    @Test
    public void testGetPublicationdate() {
        System.out.println("getPublicationdate");
        Bookinventory instance = new Bookinventory();
        Date expResult = null;
        Date result = instance.getPublicationdate();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPublicationdate method, of class Bookinventory.
     */
    @Test
    public void testSetPublicationdate() {
        System.out.println("setPublicationdate");
        Date publicationdate = null;
        Bookinventory instance = new Bookinventory();
        instance.setPublicationdate(publicationdate);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInvoicedetailsList method, of class Bookinventory.
     */
    @Test
    public void testGetInvoicedetailsList() {
        System.out.println("getInvoicedetailsList");
        Bookinventory instance = new Bookinventory();
        List<Invoicedetails> expResult = null;
        List<Invoicedetails> result = instance.getInvoicedetailsList();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of setInvoicedetailsList method, of class Bookinventory.
     */
    @Test
    public void testSetInvoicedetailsList() {
        System.out.println("setInvoicedetailsList");
        List<Invoicedetails> invoicedetailsList = null;
        Bookinventory instance = new Bookinventory();
        instance.setInvoicedetailsList(invoicedetailsList);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getReviewsList method, of class Bookinventory.
     */
    @Test
    public void testGetReviewsList() {
        System.out.println("getReviewsList");
        Bookinventory instance = new Bookinventory();
        List<Reviews> expResult = null;
        List<Reviews> result = instance.getReviewsList();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of setReviewsList method, of class Bookinventory.
     */
    @Test
    public void testSetReviewsList() {
        System.out.println("setReviewsList");
        List<Reviews> reviewsList = null;
        Bookinventory instance = new Bookinventory();
        instance.setReviewsList(reviewsList);
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class Bookinventory.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Bookinventory instance = new Bookinventory();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class Bookinventory.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object object = null;
        Bookinventory instance = new Bookinventory();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Bookinventory.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Bookinventory instance = new Bookinventory();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
    
}
