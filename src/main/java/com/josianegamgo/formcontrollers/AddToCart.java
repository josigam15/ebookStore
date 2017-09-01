/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josianegamgo.formcontrollers;

import com.josianegamgo.ebooksstore.entities.Bookinventory;
import java.io.Serializable;
import java.util.Objects;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;

/**
 *
 * @author Josiane gamgo
 */
@Named
@SessionScoped
public class AddToCart implements Serializable{
    private String isbn;
    private boolean addedtoCart;
    private double unitprix;
    private int numberOfItems;
    private final ServletContext context;
   
    private Bookinventory bookinventory;
    
    public AddToCart(){
    context = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
    
    }

    /**
     * @return the isbn
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * @param isbn the isbn to set
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * @return the addedtoCart
     */
    public boolean isAddedtoCart() {
        return addedtoCart;
    }

    /**
     * @param addedtoCart to set
     * 
     */
    public void setAddedtoCart(boolean addedtoCart) {
        
        this.addedtoCart = addedtoCart;
         
    }

    /**
     * @return the unitprix
     */
    public double getUnitprix() {
        return unitprix;
    }

    /**
     * @param unitprix the unitprix to set
     */
    public void setUnitprix(double unitprix) {
        this.unitprix = unitprix;
    }

    /**
     * @return the numberOfItems
     */
    public int getNumberOfItems() {
        return numberOfItems;
    }

    /**
     * @param numberOfItems the numberOfItems to set
     */
    public void setNumberOfItems(int numberOfItems) {
        this.numberOfItems = numberOfItems;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.isbn);
        hash = 29 * hash + (this.addedtoCart ? 1 : 0);
        hash = 29 * hash + (int) (Double.doubleToLongBits(this.unitprix) ^ (Double.doubleToLongBits(this.unitprix) >>> 32));
        hash = 29 * hash + this.numberOfItems;
        hash = 29 * hash + Objects.hashCode(this.context);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AddToCart other = (AddToCart) obj;
        if (this.addedtoCart != other.addedtoCart) {
            return false;
        }
        if (Double.doubleToLongBits(this.unitprix) != Double.doubleToLongBits(other.unitprix)) {
            return false;
        }
        if (this.numberOfItems != other.numberOfItems) {
            return false;
        }
        if (!Objects.equals(this.isbn, other.isbn)) {
            return false;
        }
        if (!Objects.equals(this.context, other.context)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AddToCart{" + "isbn=" + isbn + ", addtoCart=" + addedtoCart + ", unitprix=" + unitprix + ", numberOfItems=" + numberOfItems + ", context=" + context + '}';
    }
    
}
