/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josianegamgo.ebooksstore.controllers;

import java.io.Serializable;
import javax.inject.Named;

import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Josiane Gamgo
 */
@RequestScoped
@Named("navigationController")
public class NavigationController implements Serializable {
//@ManagedProperty(value="#{param.pageId}")
   
    
    private String pageId;
//navigate on pages otherwise show home
    public String showPage(String p) {
      // this.pageId= p;
        if (p == null || p.isEmpty()) {
            return "index";
        }
        switch (p) {
            case "1":
                return "singleBookView";

            case "2":
                return "myshoppingCart";
            case "3":
                return "checkout";
            case "4":
                return "login";
            case "5":
                return "register";
                case "6":
               return "profile";
                case "7":
                    return "searchResults";
                case "8":
                    return "finalize";
                case "9":
                    return "category";
                case "10":
                    return "searchResults";
                case "11":
                    return "download";
            default:
                return "index";
        }
    }

    /**
     * @return the pageId
     */
    public String getPageId() {
        return pageId;
    }

    /**
     * @param pageId the pageId to set
     */
    public void setPageId(String pageId) {
        this.pageId = pageId;
    }
}
