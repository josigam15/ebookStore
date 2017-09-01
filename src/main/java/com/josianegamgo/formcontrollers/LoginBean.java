/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josianegamgo.formcontrollers;

import com.josianegamgo.ebooksstore.controllers.ClientrecordsJpaController;
import com.josianegamgo.ebooksstore.controllers.NavigationController;
import com.josianegamgo.ebooksstore.entities.Clientrecords;
import com.josianegamgo.ebooksstore.util.MessagesUtil;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;

/**
 *
 * @author Josiane Gamgo
 */


@Named("loginbean")
@SessionScoped
public class LoginBean implements Serializable{
    // Simple user database :)
   // private static final String[] users = {"ken:moose", "anna:qazwsx","kate:123456"};
    @Inject
    private ClientrecordsJpaController clientrecordsjpaController;
   
    private Clientrecords dbuser;

    private String username;
    private String password;
    private boolean loggedIn;
    @Inject
    private NavigationController navigationBean;
    private final ServletContext context;
//private final HttpSession context;
FacesMessage message;
    /**
     * Constructor initializes a ServletContext object so that the log can be
     * used
     */
    public LoginBean() {
        context = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        //context = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the loggedIn
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }

    /**
     * @param loggedIn the loggedIn to set
     */
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
   /**
     * Login operation.
     *
     * @return
     */
    public String doLogin() {
        
       // if the username is the same as in the db      
             dbuser = clientrecordsjpaController.findClientrecordsByUsernameAndPass(username, password);
            // Successful login
    
            if(dbuser.getUsername().equals(username) && dbuser.getPasswords().equals(password))
            {
                
                loggedIn = true;
                context.setAttribute("username", username); // .log(username);
                 message = MessagesUtil.getMessage(
                "com.josianegamgo.bundles.messages", "welcome", new Object[]{username});
                return navigationBean.showPage("6");//show profile
               
            }else{
            loggedIn=false;
            context.log("Unsuccessful login");
             // Set login ERROR
        message = MessagesUtil.getMessage("Login error!", "ERROR MSG",new Object[]{username});
        message.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().addMessage(null, message);
            return navigationBean.showPage("4");
            
            }
        
        

       

        // To  login page
       // return navigationBean.showPage("7");
    }

    /**
     * Logout operation.
     *
     * @return
     */
    public String doLogout() {
        // Set the parameter indicating that user is logged in to false
        loggedIn = false;

        // Set logout message
        FacesMessage msg = new FacesMessage("Logout success!", "INFO MSG");
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext.getCurrentInstance().addMessage(null, msg);

        return navigationBean.showPage("0");
    }  
    
}
