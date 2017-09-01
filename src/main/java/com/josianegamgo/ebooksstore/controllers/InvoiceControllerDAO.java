/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josianegamgo.ebooksstore.controllers;

/**
 *
 * @author Josiane Gamgo
 */
import com.josianegamgo.ebooksstore.entities.Bookinventory;
import com.josianegamgo.formcontrollers.BookinventoryBean;
import com.josianegamgo.formcontrollers.LoginBean;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

@Named
@RequestScoped
public class InvoiceControllerDAO {

    @Resource()
    private UserTransaction utx;

    @PersistenceContext(unitName = "ebooksStorePU")
    private EntityManager em;

    @Inject
    private BookinventoryBean books;
    @Inject
    private LoginBean login;
    @Inject
    private InvoiceJpaController invoicejpacontroller;
    @Inject
    private InvoicedetailsJpaController invoicedetailsjpacontroller;
    @Inject
    private BookinventoryJpaController bookinventoryjpacontroller;
    @Inject
    private ClientrecordsJpaController clientrecordsjpacontroller;

    private int saleId;

    public void createIncoives() {

        Query queryinvoice;
        Query queryinvoicedetails;

        try {
            utx.begin();
            queryinvoice = em.createNativeQuery("INSERT INTO Invoice (SALEID,GST,DATEOFSALE,CLIENTNUM,TOTALNETSALE,TOTALGROSSSALE)"
                    + "VALUES(?,?,?,?,?,?)");
            queryinvoicedetails = em.createNativeQuery("INSERT INTO Invoicedetails (INVOICEDETAILSID,SALEID,ISBNID,PRICE)"
                    + "VALUES(?,?,?,?)");

            queryinvoice.setParameter(1, invoicejpacontroller.getLastSaleId() + 1);
            saleId = invoicejpacontroller.getLastSaleId() + 1;
            queryinvoice.setParameter(2, BigDecimal.valueOf(0.149));
            queryinvoice.setParameter(3, new Date());
            queryinvoice.setParameter(4, clientrecordsjpacontroller.getMyPerson().get(0).getClientnum());
            queryinvoice.setParameter(5, BigDecimal.valueOf(books.getTotalPrice()));
            queryinvoice.setParameter(6, BigDecimal.valueOf(books.getTotalPrice() + (books.getTotalPrice() * 0.149)));
            queryinvoice.executeUpdate();

            for (Bookinventory b : books.getBooks()) {

                queryinvoicedetails.setParameter(1, invoicedetailsjpacontroller.getLastSaleId() + 1);
                queryinvoicedetails.setParameter(2, this.saleId);
                queryinvoicedetails.setParameter(3, b.getIsbn());
                queryinvoicedetails.setParameter(4, bookinventoryjpacontroller.getBookByIsbn(b.getIsbn()).get(0).getWholesaleprice());
                queryinvoicedetails.executeUpdate();

            }
            utx.commit();
            System.out.println("Le commit a marche");
        } catch (Exception e) {
            System.out.println("Le commit n a pas fonctionne, quelle merde");
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (IllegalStateException | SecurityException | SystemException exception) {

            }

        }
    }
    
    
    
    public List<Bookinventory> getInvoicedetails() throws SQLException{
     
         List<Bookinventory> booksInventory = new ArrayList<>();  
      Query  queryinvoicedetails = em.createNativeQuery("SELECT id.ISBNID FROM Invoicedetails id LEFT OUTER JOIN Invoice i ON id.SALEID=i.SALEID  WHERE i.clientnum="+clientrecordsjpacontroller.getMyPerson().get(0).getClientnum()+"");
       List<String>booklist =  queryinvoicedetails.getResultList();
       
       booklist.stream().forEach((isbn) -> {
           booksInventory.add(bookinventoryjpacontroller.getBookByIsbn(isbn).get(0));
        });
       
       return booksInventory;
}
    
    
}
