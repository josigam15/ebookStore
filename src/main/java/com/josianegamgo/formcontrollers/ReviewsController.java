/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josianegamgo.formcontrollers;

import com.josianegamgo.ebooksstore.controllers.BookinventoryJpaController;
import com.josianegamgo.ebooksstore.controllers.ClientrecordsJpaController;
import com.josianegamgo.ebooksstore.controllers.ReviewsJpaController;
import com.josianegamgo.ebooksstore.entities.Reviews;
import com.josianegamgo.ebooksstore.util.MessagesUtil;
import java.util.logging.Logger;
import java.io.Serializable;
import java.sql.Date;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;



/**
 *
 * @author Josiane Gamgo
 */
@Named("reviewscontroller")
@ViewScoped
public class ReviewsController implements Serializable{
 @Inject
 private ReviewsJpaController reviewsjpacontroller;
 @Inject
 private ClientrecordsJpaController clientrecordsjpacontroller;
 @Inject
 BookinventoryJpaController bookinventoryjpacontroller;
 @Inject
 private LoginBean loginbean;
 private Reviews reviews;
 private String clientname; //had to put it in the db?
 private String clientemail;
 
private final ServletContext servletcontext;
private static final Logger log = Logger.getLogger("ReviewsJpaController.class");
//constructor
public ReviewsController(){
servletcontext = (ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext();
}
//getters and setters

    public Reviews getReviews() {
        return reviews;
    }

    public void setReviews(Reviews reviews) {
        this.reviews = reviews;
    }

    public String getClientname() {
        return clientname;
    }

    public void setClientname(String clientname) {
     
        this.clientname = clientname;
    }

    public String getClientemail() {
        return clientemail;
    }

    public void setClientemail(String clientemail) {
        this.clientemail = clientemail;
    }
    
    
    
//verify user email
       private boolean isValidEmail() {
        boolean isvalidemail;
        
            if (clientrecordsjpacontroller.findClientrecordsByEmail(this.getClientemail()) != null) {
                isvalidemail = true;
                log.info(getClientemail() + "is valid");
            } else {
                isvalidemail = false;
                FacesMessage message = MessagesUtil.getMessage(
                        "com.josianegamgo.bundles.messages", "email.in.use", null);
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                FacesContext.getCurrentInstance().addMessage("registerForm:email", message);
            }
        
        return isvalidemail;
    }
       //verify username
      private boolean isValidUsername() {
        boolean isvalidusername;
      
            if (clientrecordsjpacontroller.findClientrecordsByUsername(this.getClientname()) != null) {
                isvalidusername = true;
            } else {
                isvalidusername = false;
                FacesMessage message = MessagesUtil.getMessage(
                        "com.josianegamgo.bundles.messages", "email.in.use", null);
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                FacesContext.getCurrentInstance().addMessage("registerForm:email", message);
            }
        
        return isvalidusername;
    } 
        //add a new client

    public String createReview() throws Exception {
        if (isValidEmail() && isValidUsername()) {
            reviewsjpacontroller.create(reviews); 
            return "reviews";
        }
        return null;
    }

}
