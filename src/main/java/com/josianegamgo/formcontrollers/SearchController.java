/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josianegamgo.formcontrollers;

import com.josianegamgo.ebooksstore.controllers.BookinventoryJpaController;
import com.josianegamgo.ebooksstore.controllers.NavigationController;
import com.josianegamgo.ebooksstore.entities.Bookinventory;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;


/**
 *
 * @author Josiane Gamgo
 */
@Named("searchcontroller")
@SessionScoped
public class SearchController implements Serializable{
 private String title;
 private String isbn;
 private String author;
 private Bookinventory bookinventory;
 @Inject
 private BookinventoryJpaController bookinventoryjpacontroller;
 @Inject
 private NavigationController navigationcontroller;
 private BookinventoryBean bookinventorybean;
 
 private  List<Bookinventory>  bookresults;
 private String genre;

 
  private final ServletContext context;  
  
  
    public SearchController(){
         context = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
  }  

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<Bookinventory> getBookresults() {
        return bookresults;
    }

    public void setBookresults(List<Bookinventory> bookresults) {
        this.bookresults = bookresults;
    }
   public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

  
   //
    public void search(){
 //search by title
        if(!this.getTitle().isEmpty()){
        
            this.setBookresults(bookinventoryjpacontroller.getBookBytitle(this.getTitle()));
        }
  //search by author
        else if (!this.getAuthor().isEmpty()){
        
            this.setBookresults(bookinventoryjpacontroller.getBookByauthor(this.getAuthor()));
        }
        //search by isbn
        else if (!this.getIsbn().isEmpty()){
        
            this.setBookresults(bookinventoryjpacontroller.getBookByIsbn(this.getIsbn()));
        }
       //search by genre
        //search by isbn
        else if (!this.getGenre().isEmpty()){
        
            this.setBookresults(bookinventoryjpacontroller.getBookByGenre(this.getGenre()));
        }
        
  //if the search has only one result show single book
        if(this.getBookresults().size()==1){
       String searchisbn= this.getBookresults().get(0).getIsbn();
        bookinventorybean.setAddToCart(searchisbn);
        navigationcontroller.showPage("1");
       
        }
        else{
        
         navigationcontroller.showPage("7");
         
        }
  
    
   
    }
  
}
