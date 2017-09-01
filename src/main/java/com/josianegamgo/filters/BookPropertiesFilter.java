/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josianegamgo.filters;

import com.josianegamgo.ebooksstore.controllers.BookinventoryJpaController;
import com.josianegamgo.ebooksstore.controllers.NavigationController;
import com.josianegamgo.ebooksstore.entities.Bookinventory;
import com.josianegamgo.formcontrollers.AddToCart;
import java.io.IOException;
import java.io.Serializable;
import java.util.Enumeration;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Josiane Gamgo
 */
@Named
@SessionScoped
public class BookPropertiesFilter implements Serializable , Filter{
    private  ServletContext servletcontext;
    //private FacesContext context = FacesContext.getCurrentInstance();
   // private HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
   @Inject
   private BookinventoryJpaController bookinventoryjpacontroller;
   
   @Inject
   NavigationController navigationcontroller;
    private String sessionid;    
    private Bookinventory lastesViewed;
    private String actualView ;
    private boolean addedToCart;
    private String actualViewPage;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
       servletcontext= filterConfig.getServletContext();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
       servletcontext.log("Inside the book filter");
     AddToCart  addToCart = (AddToCart)((HttpServletRequest)request).getSession().getAttribute("addToCart");
       if(addToCart==null || addToCart.isAddedtoCart()==false){
       servletcontext.log("Nothing to add to the cart");
          
            String contextPath = ((HttpServletRequest) request)
                    .getContextPath();
            ((HttpServletResponse) response).sendRedirect(contextPath + "/faces/index.xhtml");
            
           Enumeration<String> initParameterNames = servletcontext.getInitParameterNames();
           System.out.println("initParametersName: "+initParameterNames.nextElement());
           System.out.println("contextPath: "+ servletcontext.getContextPath());
       }else{
         isAddedToCart();
           chain.doFilter(request, response);
       }
       
    }

    /**
     * @return the bookinventoryjpacontroller
     */
    public BookinventoryJpaController getBookinventoryjpacontroller() {
        return bookinventoryjpacontroller;
    }

    /**
     * @param bookinventoryjpacontroller the bookinventoryjpacontroller to set
     */
    public void setBookinventoryjpacontroller(BookinventoryJpaController bookinventoryjpacontroller) {
        this.bookinventoryjpacontroller = bookinventoryjpacontroller;
    }

 
    /**
     * @return the sessionid
     */
    public String getSessionid() {
        return sessionid;
    }

    /**
     * @return the lastesViewed
     */
    public Bookinventory getLastesViewed() {
        return lastesViewed;
    }

    /**
     * @param lastesViewed the lastesViewed to set
     */
    public void setLastesViewed(Bookinventory lastesViewed) {
       
        this.lastesViewed = lastesViewed;
    }

    /**
     * @return the actualView
     */
    public String getActualView() {
        return actualView;
    }

    /**
     * @param actualView the actualView to set
     */
    public void setActualView(String actualView) {
        this.actualView = actualView;
    }

    /**
     * @return the addedToCart
     */
    public boolean isAddedToCart() {
        return addedToCart;
    }

    /**
     * @param addedToCart the addToCart to set
     */
    public void setAddedToCart(boolean addedToCart) {
       
        this.addedToCart = addedToCart;
    }

    /**
     * @return the actualViewPage
     */
    public String getActualViewPage() {
        return actualViewPage;
    }

    /**
     * @param actualViewPage the actualViewPage to set
     */
    public void setActualViewPage(String actualViewPage) {
        this.actualViewPage = actualViewPage;
    }

    
    @Override
    public void destroy() {
        //nothing to destroy
    }
    
}
