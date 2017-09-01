/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josianegamgo.formcontrollers;

import com.josianegamgo.ebooksstore.controllers.ClientrecordsJpaController;
import com.josianegamgo.ebooksstore.entities.Clientrecords;
import com.josianegamgo.ebooksstore.util.MessagesUtil;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jgamgo
 */
@Named("clientProfileController")
@ViewScoped
//@RequestScoped

public class ClientProfileController implements Serializable {
private final ServletContext context;
    @Inject
    ClientrecordsJpaController clientrecordsJpaController;
    private Clientrecords clientrecords;//add a new client if register is choose
    // Default logger is java.util.logging
    private static final Logger log = Logger.getLogger("ClientrecordsJpaController.class");

    public ClientProfileController() {
         context = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
    }

    /**
     * @return the clientrecords
     */

    public Clientrecords getClientrecords() {
        if (clientrecords == null) {
            clientrecords = new Clientrecords(); //initialise the client hier
        }

        return clientrecords;
    }

    /**
     * @param clientrecords the clientrecords to set
     */
    public void setClientrecords(Clientrecords clientrecords) {
        this.clientrecords = clientrecords;
    }

    private boolean isValidEmail() {
        boolean isvalidemail = false;
        String email = clientrecords.getEmail();
        if (email != null) {
            if (clientrecordsJpaController.findClientrecordsByEmail(email) == null) {
                isvalidemail = true;
            } else {
                isvalidemail = false;
                FacesMessage message = MessagesUtil.getMessage(
                        "com.josianegamgo.bundles.messages", "email.in.use", null);
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                FacesContext.getCurrentInstance().addMessage("registerForm:email", message);
            }
        }
        return isvalidemail;
    }
    //add a new client

    public String register() throws Exception {
        if (isValidEmail()) {
            clientrecordsJpaController.create(clientrecords);
           return "login";
        }else{
       String path= context.getRealPath("index");
       return path;
        }
       // return null;
    }

}
