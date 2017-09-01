/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josianegamgo.filters;

import com.josianegamgo.formcontrollers.AddToCart;
import com.josianegamgo.formcontrollers.LoginBean;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Josiane Gamgo
 */
//@WebFilter(filterName = "LoginFilter", urlPatterns = {"/secured/*", "/faces/secured/*"})
public class LoginFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //init
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
       //define a request and response and store the context path for the redirection
       //Also need to read the cookie to display the related books
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        LoginBean session = (LoginBean)req.getSession().getAttribute("loginbean");
        AddToCart actualview = (AddToCart)req.getSession().getAttribute("actualView");
        String url = req.getRequestURI();
        String contextpath = req.getServletContext().getContextPath();
        Cookie[] cookies = req.getCookies();
        
        
       /**
        *configure the redirect in case of login or not
        * if the user is not logged and coming from the register page or the logout page or the book page
        * redirect him to the loggin page
        * If the user just registered send him to to the current page
         
        */
        
        if(session==null || ! session.isLoggedIn()){
        
            if((url.contains("register.xhtml")) ||( url.contains("logout.xhtml"))){
            resp.sendRedirect(contextpath+"/login.xhtml");
        
            }else{
            chain.doFilter(request, response);
            //System.out.println("Les cookies= "+cookies.toString());
            }
            
        }else{
            if(url.contains("register.xhtml") ){
                resp.sendRedirect(contextpath+"/login.xhtml");
        
            }else if(url.contains("register.xhtml")||url.contains("logout.xhtml")){
                req.getSession().removeAttribute("loginbean");//if user log out remove the session
            
            }else if( url.contains("login.xhtml")){
         
                if(actualview.isAddedtoCart()==true){
                resp.sendRedirect(contextpath+"/checkout.xhtml");
                }else{ 

                    resp.sendRedirect(contextpath+"/profile.xhtml");
                }
       
            }else{
                chain.doFilter(request, response);
            }
        
        
        }
        
        
        
    }

    @Override
    public void destroy() {
       //nothing to destroy
    }

}
