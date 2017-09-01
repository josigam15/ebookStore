/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josianegamgo.ebooksstore.util;

import java.util.Locale;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Josiane Gamgo
 */
public class MessagesUtilTest {
    
    public MessagesUtilTest() {
    }

    /**
     * Test of getMessage method, of class MessagesUtil.
     */
    @Test
    public void testGetMessage() {
        System.out.println("getMessage");
        String bundleName = "";
        String resourceId = "";
        Object[] params = null;
        FacesMessage expResult = null;
        FacesMessage result = MessagesUtil.getMessage(bundleName, resourceId, params);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getString method, of class MessagesUtil.
     */
    @Test
    public void testGetString_3args() {
        System.out.println("getString");
        String bundle = "";
        String resourceId = "";
        Object[] params = null;
        String expResult = "";
        String result = MessagesUtil.getString(bundle, resourceId, params);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getString method, of class MessagesUtil.
     */
    @Test
    public void testGetString_6args() {
        System.out.println("getString");
        String bundle1 = "";
        String bundle2 = "";
        String resourceId = "";
        Locale locale = null;
        ClassLoader loader = null;
        Object[] params = null;
        String expResult = "";
        String result = MessagesUtil.getString(bundle1, bundle2, resourceId, locale, loader, params);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLocale method, of class MessagesUtil.
     */
    @Test
    public void testGetLocale() {
        System.out.println("getLocale");
        FacesContext context = null;
        Locale expResult = null;
        Locale result = MessagesUtil.getLocale(context);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getClassLoader method, of class MessagesUtil.
     */
    @Test
    public void testGetClassLoader() {
        System.out.println("getClassLoader");
        ClassLoader expResult = null;
        ClassLoader result = MessagesUtil.getClassLoader();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
    
}
