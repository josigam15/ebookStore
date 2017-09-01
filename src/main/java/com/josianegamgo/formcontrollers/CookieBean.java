/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josianegamgo.formcontrollers;

import com.josianegamgo.ebooksstore.controllers.BookinventoryJpaController;
import com.josianegamgo.ebooksstore.entities.Bookinventory;
import com.josianegamgo.filters.BookPropertiesFilter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Josiane Gamgo
 */

@Named("cookiebean")
@RequestScoped
public class CookieBean {
    
    
    /**
     * Look for a cookie
     */
     
    @Inject
    private BookinventoryJpaController bookinventoryjpacontroller;
    private Bookinventory book;
    private String value;
    
    public void checkCookies() {       
        
       FacesContext context = FacesContext.getCurrentInstance();//get the current instance
     Map<String, Object> cookieMap = context.getExternalContext().getRequestCookieMap();//read the cookies
       
        // Retrieve all cookies
        if (!ifCookie()) {
            System.out.println("No cookies");
        } else {
            ArrayList<Object> ac = new ArrayList<>(cookieMap.values());

            // Streams coding to print out the contenst of the cookies found
            ac.stream().map((c) -> {
                ((Cookie) c).getName();
                System.out.println("name of the cookie = "+ ((Cookie) c).getName());
                return c;
            }).forEach((c) -> {
               ((Cookie) c).getValue();
                System.out.println("Value of the cookie "+((Cookie) c).getValue());
            });
            System.out.println("map= "+ cookieMap.keySet().toString());
            System.out.println("map values= "+ cookieMap.values().toString());
             System.out.println("map size= "+ cookieMap.size());
        }  

        // Retrieve a specific cookie
        Object my_cookie = cookieMap.get("actualView");
        if (my_cookie != null) {
            String cookiename = ((Cookie) my_cookie).getName();
            System.out.println(cookiename);
            String cookievalue = ((Cookie) my_cookie).getValue();
            System.out.println(cookievalue);
        }
        writeCookie();
    }
//    
//    @RequestScoped
    public boolean ifCookie(){
          
       FacesContext context = FacesContext.getCurrentInstance();//get the current instance
     Map<String, Object> cookieMap = context.getExternalContext().getRequestCookieMap();//read the cookies
        return !(cookieMap == null || cookieMap.isEmpty());
    }
    
   
    
    public String  getSpecificCookie(String s){
         FacesContext context = FacesContext.getCurrentInstance();//get the current instance
         Map<String, Object> cookieMap = context.getExternalContext().getRequestCookieMap();//read the cookies
        String  var;

        
        Object my_cookie;
        my_cookie = cookieMap.get(s);
        
        if (my_cookie != null) {
            ((Cookie)my_cookie).getValue();
            var=(String)((Cookie)my_cookie).getValue();
            System.out.println("specific cookie name= "+((Cookie) my_cookie).getName());
            System.out.println("specific cookie value= "+((Cookie) my_cookie).getValue());
            this.getAllCookies();
        }else{
            var="nothing inside";
            //write the cookie if it's null
            writeCookie();
        }
        return var;
    }
    
    
    public void writeCookie() {
        FacesContext facescontext = FacesContext.getCurrentInstance();
        Map<String, Object> cookiemap = facescontext.getExternalContext().getRequestCookieMap();
        facescontext.getExternalContext().addResponseCookie("actualView", this.getValue(), null);
      //  facescontext.getExternalContext().addResponseCookie("addToCart",book.getIsbn(),cookiemap);
        
    }
    public List  getAllCookies(){
       FacesContext facescontext = FacesContext.getCurrentInstance();
         Map<String, Object> cookiemap = facescontext.getExternalContext().getRequestCookieMap();
         List cookies = new ArrayList(cookiemap.values());
         System.out.println("cookies="+ cookies.toString());
    return cookies;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        FacesContext context = FacesContext.getCurrentInstance();//get the current instance
         
        value = (String)( (Cookie) context.getExternalContext().getRequestCookieMap().get("actualView") ).getValue() ;//get the title from the cookie
         
        this.value = value;
//        Date entrydate= new Date();
//      book.setEntrydate(entrydate);
//      value= book.getEntrydate().toString();

        
    }
    
}
