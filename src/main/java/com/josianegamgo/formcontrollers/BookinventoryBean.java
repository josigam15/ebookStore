/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josianegamgo.formcontrollers;

import com.josianegamgo.ebooksstore.controllers.BookinventoryJpaController;
import com.josianegamgo.ebooksstore.controllers.InvoiceJpaController;
import com.josianegamgo.ebooksstore.controllers.InvoicedetailsJpaController;
import com.josianegamgo.ebooksstore.controllers.NavigationController;
import com.josianegamgo.ebooksstore.entities.Bookinventory;
import com.josianegamgo.ebooksstore.entities.Clientrecords;
import com.josianegamgo.ebooksstore.entities.Invoice;
import com.josianegamgo.ebooksstore.entities.Invoicedetails;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Josiane Gamgo
 */
@Named("bookinventorybean")
@SessionScoped
public class BookinventoryBean implements Serializable, Filter {
private ServletContext contextServelet;
    private FacesContext context = FacesContext.getCurrentInstance();
    private HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
    //
    private List<Bookinventory> books= new ArrayList<>();
    private String sessionid;    
    private Bookinventory lastesViewed;
   private  Bookinventory actualinView ;
    private String actualView ;
    private String addToCart;
    private boolean isActualView;
    String action;
    private final double GST=0.050;
    private final double PST =0.099;
    
    @Inject
    private NavigationController navigation;
        
    @Inject
    private BookinventoryJpaController bookjpa;
   
    
    
    @Inject
    private InvoicedetailsJpaController invoicedetailsjpa;
    
    private Invoice invoice;
    private Invoicedetails invoicedetails;
    
    @Inject
    private LoginBean login;
    private Clientrecords clientrecords;
    
    public void addToCart(){
        //
       
        session.setAttribute("addTocart", this.getAddToCart());
        session.setAttribute("actualView", this.getActualView());
        
        //contextServelet.setAttribute("addTocart", getAddToCart());
      //  System.out.println("Session attribut= "+session.getAttribute("addTocart"));
      //  System.out.println("contextServelet attribut= "+session.getAttribute("addTocart"));
   
        
        setAddToCart(getActualView()); 
    }
    
    public Bookinventory getLatestViewed(String s){
        
        return lastesViewed;
    }
      
    public int getCartSize(){
        
        //context.getExternalContext().getSessionMap().get("addTocart");        
        return books.size();
        
    }


    public void setBooks(List<Bookinventory> books) {
        this.books = books;
    }

    public List<Bookinventory> getBooks() {
        return books;
    }
     
    public double getTotalPrice(){
        NumberFormat nf = NumberFormat.getCurrencyInstance();
         nf.setMaximumFractionDigits(3);//3 digits after the dot
        double price=0.0;   
        try{
          for(int i=0;i<books.size();i++){           
            
               price += books.get(i).getWholesaleprice().doubleValue();
             
                         
        }       
            
        }catch(Exception e){
            e.getMessage();
        }
           
        return  price;
    }
    
  

    @Override
    public void init(FilterConfig config) throws ServletException {
         //To change body of generated methods, choose Tools | Templates.
   contextServelet = config.getServletContext();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
            throws IOException, ServletException {
     
    
        
        AddToCart addTocart = (AddToCart) ((HttpServletRequest) request)
                .getSession().getAttribute("addTocart");

        System.out.println("addTocart= "+ addTocart);
        
        if (addTocart == null ) {
            
           // System.out.println("User not logged in");
            // creer la session
            contextServelet.getAttribute("addTocart");
            contextServelet.getInitParameterNames();
           // System.out.println("Context sessionid= "+contextServelet.getContext(sessionid));
                
        }else{
            
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
//the getters and setters
    /**
     * @return the addToCart
     */
    public String getAddToCart() {
        return addToCart;
    }
    
    /**
     *
     * @param actualView isbn
     */
    public void setActualView(String actualView) {
        this.actualView=actualView;
       this.setIsActualView(true); //set the bool to true
     
    }

    /**
     * @return the actualView isbn
     */
    public String getActualView() {
        return actualView;
    }
    
    /**
     * @param addToCart the addToCart to set
     */
    public void setAddToCart(String addToCart) {
        
        this.addToCart = addToCart;
        setActualView(addToCart);
        Bookinventory b = bookjpa.getBookByIsbn(addToCart).get(0);
        books.add(b); //add the book in the list of book cart

        this.setIsActualView(true);
        
    }
    
    

  public void removeFromcart(String s)
  { 
      try{ 
          books.remove(bookjpa.getBookByIsbn(s).get(0));
      }catch(Exception e){
          e.getMessage();
      }
  }  
    
    /**
     * @return the isActualView
     */
    public boolean isIsActualView() {
        return isActualView;
    }

    /**
     * @param isActualView the isActualView to set
     */
    public void setIsActualView(boolean isActualView) {
        this.isActualView = isActualView;
    }

    public Bookinventory getActualinView(){
       
        if(this.getActualView()== null){
         this.setActualinView(bookjpa.getBookByIsbn("9781608458691").get(0));
             //set this book as default when nothing is inside the actual
           
        }else{
            this.setActualinView(actualinView);
        actualinView =  bookjpa.getBookByIsbn(getActualView()).get(0);
        this.setActualView(actualinView.getIsbn()); 
        }
        return actualinView;
    }

    /**
     * @param actualinView the actualinView to set
     */
    public void setActualinView(Bookinventory actualinView) {
        this.actualinView = actualinView;
    }
    

    
    //get value from "f:param"
    public String getActualViewParam(FacesContext fc){
		
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
                //take the actual view in the parameter
		return params.get("actualView");
		
	}
   

    /**
     *
     * @return total price with taxes
     */
          public double getTotalPriceWithTaxes(){
           
                    double totalprice =this.getTotalPrice();
                    totalprice =  (totalprice*GST)+(totalprice*PST)+totalprice;

            return totalprice;
            }
   
          
    /**
     *format  all price with 3 digits after the dot
     * @param price
     * @return
     */
    public String totalFormattedPrice(double price){
         NumberFormat nf = NumberFormat.getCurrencyInstance();
         nf.setMaximumFractionDigits(3);//3 digits after the dot
         
         
         
         
        return nf.format(price);   
    
    }
          
 

}
