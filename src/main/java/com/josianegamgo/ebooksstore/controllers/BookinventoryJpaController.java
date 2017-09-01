/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josianegamgo.ebooksstore.controllers;

import com.josianegamgo.ebooksstore.controllers.exceptions.IllegalOrphanException;
import com.josianegamgo.ebooksstore.controllers.exceptions.NonexistentEntityException;
import com.josianegamgo.ebooksstore.controllers.exceptions.RollbackFailureException;
import com.josianegamgo.ebooksstore.entities.Bookinventory;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.josianegamgo.ebooksstore.entities.Invoicedetails;
import java.util.ArrayList;
import java.util.List;
import com.josianegamgo.ebooksstore.entities.Reviews;
import com.josianegamgo.formcontrollers.BookinventoryBean;
import com.josianegamgo.formcontrollers.CookieBean;
import java.sql.SQLException;
import java.util.Date;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.transaction.UserTransaction;

/**
 *
 * @author jgamgo
 */
@Named
@RequestScoped
public class BookinventoryJpaController implements Serializable {

@Resource
private UserTransaction utx;
@PersistenceContext(unitName="ebooksStorePU")
private EntityManager em;

@Inject
private CookieBean cookie;

@Inject
private BookinventoryBean bookinventorybean;

public BookinventoryJpaController(){
}
    public void create(Bookinventory bookinventory) throws RollbackFailureException, Exception {
        if (bookinventory.getInvoicedetailsList() == null) {
            bookinventory.setInvoicedetailsList(new ArrayList<Invoicedetails>());
        }
        if (bookinventory.getReviewsList() == null) {
            bookinventory.setReviewsList(new ArrayList<Reviews>());
        }
         
        try {
            utx.begin();
            
            List<Invoicedetails> attachedInvoicedetailsList = new ArrayList<Invoicedetails>();
            for (Invoicedetails invoicedetailsListInvoicedetailsToAttach : bookinventory.getInvoicedetailsList()) {
                invoicedetailsListInvoicedetailsToAttach = em.getReference(invoicedetailsListInvoicedetailsToAttach.getClass(), invoicedetailsListInvoicedetailsToAttach.getInvoicedetailsid());
                attachedInvoicedetailsList.add(invoicedetailsListInvoicedetailsToAttach);
            }
            bookinventory.setInvoicedetailsList(attachedInvoicedetailsList);
            List<Reviews> attachedReviewsList = new ArrayList<Reviews>();
            for (Reviews reviewsListReviewsToAttach : bookinventory.getReviewsList()) {
                reviewsListReviewsToAttach = em.getReference(reviewsListReviewsToAttach.getClass(), reviewsListReviewsToAttach.getReviewsid());
                attachedReviewsList.add(reviewsListReviewsToAttach);
            }
            bookinventory.setReviewsList(attachedReviewsList);
            em.persist(bookinventory);
            for (Invoicedetails invoicedetailsListInvoicedetails : bookinventory.getInvoicedetailsList()) {
                Bookinventory oldIsbnidOfInvoicedetailsListInvoicedetails = invoicedetailsListInvoicedetails.getIsbnid();
                invoicedetailsListInvoicedetails.setIsbnid(bookinventory);
                invoicedetailsListInvoicedetails = em.merge(invoicedetailsListInvoicedetails);
                if (oldIsbnidOfInvoicedetailsListInvoicedetails != null) {
                    oldIsbnidOfInvoicedetailsListInvoicedetails.getInvoicedetailsList().remove(invoicedetailsListInvoicedetails);
                    oldIsbnidOfInvoicedetailsListInvoicedetails = em.merge(oldIsbnidOfInvoicedetailsListInvoicedetails);
                }
            }
            for (Reviews reviewsListReviews : bookinventory.getReviewsList()) {
                Bookinventory oldBookisbnOfReviewsListReviews = reviewsListReviews.getBookisbn();
                reviewsListReviews.setBookisbn(bookinventory);
                reviewsListReviews = em.merge(reviewsListReviews);
                if (oldBookisbnOfReviewsListReviews != null) {
                    oldBookisbnOfReviewsListReviews.getReviewsList().remove(reviewsListReviews);
                    oldBookisbnOfReviewsListReviews = em.merge(oldBookisbnOfReviewsListReviews);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } 
    }

    public void edit(Bookinventory bookinventory) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
         
        try {
            utx.begin();
            
            Bookinventory persistentBookinventory = em.find(Bookinventory.class, bookinventory.getId());
            List<Invoicedetails> invoicedetailsListOld = persistentBookinventory.getInvoicedetailsList();
            List<Invoicedetails> invoicedetailsListNew = bookinventory.getInvoicedetailsList();
            List<Reviews> reviewsListOld = persistentBookinventory.getReviewsList();
            List<Reviews> reviewsListNew = bookinventory.getReviewsList();
            List<String> illegalOrphanMessages = null;
            for (Invoicedetails invoicedetailsListOldInvoicedetails : invoicedetailsListOld) {
                if (!invoicedetailsListNew.contains(invoicedetailsListOldInvoicedetails)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Invoicedetails " + invoicedetailsListOldInvoicedetails + " since its isbnid field is not nullable.");
                }
            }
            for (Reviews reviewsListOldReviews : reviewsListOld) {
                if (!reviewsListNew.contains(reviewsListOldReviews)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Reviews " + reviewsListOldReviews + " since its bookisbn field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Invoicedetails> attachedInvoicedetailsListNew = new ArrayList<Invoicedetails>();
            for (Invoicedetails invoicedetailsListNewInvoicedetailsToAttach : invoicedetailsListNew) {
                invoicedetailsListNewInvoicedetailsToAttach = em.getReference(invoicedetailsListNewInvoicedetailsToAttach.getClass(), invoicedetailsListNewInvoicedetailsToAttach.getInvoicedetailsid());
                attachedInvoicedetailsListNew.add(invoicedetailsListNewInvoicedetailsToAttach);
            }
            invoicedetailsListNew = attachedInvoicedetailsListNew;
            bookinventory.setInvoicedetailsList(invoicedetailsListNew);
            List<Reviews> attachedReviewsListNew = new ArrayList<Reviews>();
            for (Reviews reviewsListNewReviewsToAttach : reviewsListNew) {
                reviewsListNewReviewsToAttach = em.getReference(reviewsListNewReviewsToAttach.getClass(), reviewsListNewReviewsToAttach.getReviewsid());
                attachedReviewsListNew.add(reviewsListNewReviewsToAttach);
            }
            reviewsListNew = attachedReviewsListNew;
            bookinventory.setReviewsList(reviewsListNew);
            bookinventory = em.merge(bookinventory);
            for (Invoicedetails invoicedetailsListNewInvoicedetails : invoicedetailsListNew) {
                if (!invoicedetailsListOld.contains(invoicedetailsListNewInvoicedetails)) {
                    Bookinventory oldIsbnidOfInvoicedetailsListNewInvoicedetails = invoicedetailsListNewInvoicedetails.getIsbnid();
                    invoicedetailsListNewInvoicedetails.setIsbnid(bookinventory);
                    invoicedetailsListNewInvoicedetails = em.merge(invoicedetailsListNewInvoicedetails);
                    if (oldIsbnidOfInvoicedetailsListNewInvoicedetails != null && !oldIsbnidOfInvoicedetailsListNewInvoicedetails.equals(bookinventory)) {
                        oldIsbnidOfInvoicedetailsListNewInvoicedetails.getInvoicedetailsList().remove(invoicedetailsListNewInvoicedetails);
                        oldIsbnidOfInvoicedetailsListNewInvoicedetails = em.merge(oldIsbnidOfInvoicedetailsListNewInvoicedetails);
                    }
                }
            }
            for (Reviews reviewsListNewReviews : reviewsListNew) {
                if (!reviewsListOld.contains(reviewsListNewReviews)) {
                    Bookinventory oldBookisbnOfReviewsListNewReviews = reviewsListNewReviews.getBookisbn();
                    reviewsListNewReviews.setBookisbn(bookinventory);
                    reviewsListNewReviews = em.merge(reviewsListNewReviews);
                    if (oldBookisbnOfReviewsListNewReviews != null && !oldBookisbnOfReviewsListNewReviews.equals(bookinventory)) {
                        oldBookisbnOfReviewsListNewReviews.getReviewsList().remove(reviewsListNewReviews);
                        oldBookisbnOfReviewsListNewReviews = em.merge(oldBookisbnOfReviewsListNewReviews);
                    }
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = bookinventory.getId();
                if (findBookinventory(id) == null) {
                    throw new NonexistentEntityException("The bookinventory with id " + id + " no longer exists.");
                }
            }
            throw ex;
        }finally {
            if (em != null) {
                em.close();
            }
        } 
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
         
        try {
            utx.begin();
            
            Bookinventory bookinventory;
            try {
                bookinventory = em.getReference(Bookinventory.class, id);
                bookinventory.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The bookinventory with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Invoicedetails> invoicedetailsListOrphanCheck = bookinventory.getInvoicedetailsList();
            for (Invoicedetails invoicedetailsListOrphanCheckInvoicedetails : invoicedetailsListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Bookinventory (" + bookinventory + ") cannot be destroyed since the Invoicedetails " + invoicedetailsListOrphanCheckInvoicedetails + " in its invoicedetailsList field has a non-nullable isbnid field.");
            }
            List<Reviews> reviewsListOrphanCheck = bookinventory.getReviewsList();
            for (Reviews reviewsListOrphanCheckReviews : reviewsListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Bookinventory (" + bookinventory + ") cannot be destroyed since the Reviews " + reviewsListOrphanCheckReviews + " in its reviewsList field has a non-nullable bookisbn field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(bookinventory);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Bookinventory> findBookinventoryEntities() {
        return findBookinventoryEntities(true, -1, -1);
    }

    public List<Bookinventory> findBookinventoryEntities(int maxResults, int firstResult) {
        return findBookinventoryEntities(false, maxResults, firstResult);
    }

    private List<Bookinventory> findBookinventoryEntities(boolean all, int maxResults, int firstResult) {
           
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Bookinventory.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Bookinventory findBookinventory(Integer id) {
           
        try {
            return em.find(Bookinventory.class, id);
        } finally {
            em.close();
        }
    }

    public int getBookinventoryCount() {
           
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Bookinventory> rt = cq.from(Bookinventory.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Bookinventory> getAll() throws SQLException{
   CriteriaBuilder cb = em.getCriteriaBuilder();
   CriteriaQuery<Bookinventory> cq = cb.createQuery(Bookinventory.class);
   Root<Bookinventory> books = cq.from(Bookinventory.class);
   cq.select(books);
   TypedQuery<Bookinventory> query =em.createQuery(cq);
   List<Bookinventory> allbooks = query.getResultList();
   
   return allbooks;
   } 
    
    //find all genre
     public List<String> getAllgenre() throws SQLException{
   CriteriaBuilder cb = em.getCriteriaBuilder();
   CriteriaQuery<String> cq = cb.createQuery(String.class);
   Root<Bookinventory> books = cq.from(Bookinventory.class);
   cq.select(books.get("genre")).distinct(true);
   
   TypedQuery<String> query =em.createQuery(cq);
   List<String> allgenre = query.getResultList();
   
   return allgenre;
   }  
    
    /** Finding specific books by genre,price,entrydate,isbn
     * 
     * 
     * @param genre
     * @return 
*/
   public List<Bookinventory> getBookByGenre(String genre){
    TypedQuery<Bookinventory> query =em.createNamedQuery("Bookinventory.findByGenre",Bookinventory.class);
    query.setParameter(1, genre);//set the selected genre to show
    List<Bookinventory> booksBygenre = query.getResultList();
    if(!(booksBygenre.isEmpty())){
    return booksBygenre;
    }
       return null;
    }
   /**
    * 
    * @param isbn
    * @return 
    */
   public List<Bookinventory> getBookByIsbn(String isbn){
    TypedQuery<Bookinventory> query =em.createNamedQuery("Bookinventory.findByIsbn",Bookinventory.class);
    query.setParameter(1,isbn);
    List<Bookinventory> booksByisbn = query.getResultList();
    if(!(booksByisbn.isEmpty())){
    return booksByisbn;
    }
       return null;
    }
 //special books are the less expensive one
   
    public List<Bookinventory> getBookByPrice(double listprice,double wholesaleprice){
    TypedQuery<Bookinventory> query =em.createNamedQuery("Bookinventory.findByPrice",Bookinventory.class);
    query.setParameter(1, listprice);
    query.setParameter(2, wholesaleprice);
    List<Bookinventory> booksByprice = query.getResultList();
    if(!(booksByprice.isEmpty())){
    return booksByprice;
    }
       return null;
    }
    
    public List<Bookinventory> getBookByDate(Date entrydate){
    //Date today = new Date();
    TypedQuery<Bookinventory> query =em.createNamedQuery("Bookinventory.findByEntrydate",Bookinventory.class);
    query.setParameter(1, entrydate);
    
    List<Bookinventory> booksBydate = query.getResultList();
    if(!(booksBydate.isEmpty())){
    return booksBydate;
    }
   
       return null;
    } 
    
       public List<Bookinventory>  getBookByauthor(String author) {
        
       // Criteria for Select all records
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Bookinventory> cq = cb.createQuery(Bookinventory.class);
        Root<Bookinventory> book = cq.from(Bookinventory.class);
       
       Predicate bookparam=cb.like(book.get("author"), "%"+author+"%");
        CriteriaQuery<Bookinventory> select = cq.select(book).having(bookparam);//books having this genre
         TypedQuery<Bookinventory> queryselect = em.createQuery(select);
        List<Bookinventory> bookselected = queryselect.getResultList();
        
        return bookselected;  
    }
           public List<Bookinventory>  getBookBytitle(String title) {
        
       // Criteria for Select all records
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Bookinventory> cq = cb.createQuery(Bookinventory.class);
        Root<Bookinventory> book = cq.from(Bookinventory.class);
       
       Predicate bookparam=cb.like(book.get("title"), "%"+title+"%");
        CriteriaQuery<Bookinventory> select = cq.select(book).having(bookparam);//books having this genre
         TypedQuery<Bookinventory> queryselect = em.createQuery(select);
        List<Bookinventory> bookselected = queryselect.getResultList();
        
        return bookselected;  
    }
       
           public List<Bookinventory>  getBookBypublisher(String publisher) {
        
       // Criteria for Select all records
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Bookinventory> cq = cb.createQuery(Bookinventory.class);
        Root<Bookinventory> book = cq.from(Bookinventory.class);
        // TypedQuery<Bookinventory> query = em.createQuery(cq);
        //List<Bookinventory> bookinfo = query.getResultList();
       Predicate bookparam=cb.like(book.get("publisher"), "%"+publisher+"%");
        CriteriaQuery<Bookinventory> select = cq.select(book).having(bookparam);//books having this genre
         TypedQuery<Bookinventory> queryselect = em.createQuery(select);
        List<Bookinventory> bookselected = queryselect.getResultList();
        
        return bookselected;  
    }
    
       /**
     * 
     * take all  information comes from the cookies 
     * @return 
  //get the books tracked in the cookie 
     */ 
    public List<Bookinventory>  getTrackedBook() {
        
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Bookinventory> cq = cb.createQuery(Bookinventory.class);
        Root<Bookinventory> book = cq.from(Bookinventory.class);
        cq.select(book);
        cq.orderBy(cb.desc(book.get("author")));      
        Predicate bookparam = cb.like(book.get("isbn"), "%"+cookie.getSpecificCookie("actualView")+"%");
        
        CriteriaQuery<Bookinventory>cqWithPredicate = cq.select(book).having(bookparam);
        TypedQuery<Bookinventory> query = em.createQuery(cqWithPredicate);
        List<Bookinventory> trackedBooks = query.setFirstResult(0).setMaxResults(5).getResultList();//show maximum 5 results

        return trackedBooks;
    }  
       /**
     * 
     * take all  information comes from the cookies 
     * @return latest visited books
     * @throws SQLException 
     */
    public List<Bookinventory> getLatestBooks() throws SQLException {
        // TODO
        // Criteria for Select all records
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Bookinventory> cq = cb.createQuery(Bookinventory.class);
        Root<Bookinventory> book = cq.from(Bookinventory.class);
        cq.select(book);
         cq.orderBy(cb.desc(book.get("title")));
         Predicate bookparam = cb.like(book.get("isbn"), "%"+cookie.getSpecificCookie("actualView")+"%");//get latest viewed isbn
        
         CriteriaQuery<Bookinventory>cqWithPredicate = cq.select(book).having(bookparam);
        TypedQuery<Bookinventory> query = em.createQuery(cqWithPredicate);
        List<Bookinventory> bookinfo = query.setFirstResult(0).setMaxResults(5).getResultList();

        return bookinfo;
    } 
    
        /**
     * 
     * @return the most 5 recent books
     * @throws SQLException 
     */
    public List<Bookinventory> getRecentBooks() throws SQLException {
        //
        // Criteria for Select all records
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Bookinventory> cq = cb.createQuery(Bookinventory.class);
        Root<Bookinventory> book = cq.from(Bookinventory.class);
        cq.select(book);
        cq.orderBy(cb.desc(book.get("entrydate")));
        List<Bookinventory> bookinfo = em.createQuery(cq).setFirstResult(0).setMaxResults(5).getResultList(); 

        return bookinfo;
    }
    
      public List<Bookinventory>  getBookInCart(){
        
        return bookinventorybean.getBooks();//get the books from the shopping cart
    }
    /**
     * Retourne le livre actuellement en View, initialisé par l appel setActualView
     * @return 
     */
    public Bookinventory getActualinView(){
        
        return bookinventorybean.getActualinView();
        
       
    }
  //get the book in the cart
      //get the actual username
     public List<Bookinventory> getMyBook() throws SQLException {
        //
        // Criteria for Select all records
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Bookinventory> cq = cb.createQuery(Bookinventory.class);
        Root<Bookinventory> book = cq.from(Bookinventory.class);
        cq.select(book); 
        Predicate cartisbn;
        cartisbn = cb.equal(book.get("isbn"), bookinventorybean.getAddToCart());  //.getActualView());      
        cq.where(cartisbn);        
        TypedQuery<Bookinventory> query = em.createQuery(cq);
        List<Bookinventory> bookinfo = query.getResultList();
        return bookinfo;       
    }
   
}
