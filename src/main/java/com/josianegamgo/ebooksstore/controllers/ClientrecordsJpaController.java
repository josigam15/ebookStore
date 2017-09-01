/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josianegamgo.ebooksstore.controllers;

import com.josianegamgo.ebooksstore.controllers.exceptions.IllegalOrphanException;
import com.josianegamgo.ebooksstore.controllers.exceptions.NonexistentEntityException;
import com.josianegamgo.ebooksstore.controllers.exceptions.RollbackFailureException;
import com.josianegamgo.ebooksstore.entities.Clientrecords;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.josianegamgo.ebooksstore.entities.Reviews;
import java.util.ArrayList;
import java.util.List;
import com.josianegamgo.ebooksstore.entities.Invoice;
import com.josianegamgo.formcontrollers.LoginBean;
import java.sql.SQLException;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
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
public class ClientrecordsJpaController implements Serializable {

 @Resource(name="jdbc/ebooksstore")
    private UserTransaction utx;
    @PersistenceContext(unitName = "ebooksStorePU")
    private EntityManager em;
    @Inject
    private LoginBean loginbean;
    
    public ClientrecordsJpaController(){
    }
    public void create(Clientrecords clientrecords) throws RollbackFailureException, Exception {
        if (clientrecords.getReviewsList() == null) {
            clientrecords.setReviewsList(new ArrayList<Reviews>());
        }
        if (clientrecords.getInvoiceList() == null) {
            clientrecords.setInvoiceList(new ArrayList<Invoice>());
        }
          
        try {
            utx.begin();
              
            List<Reviews> attachedReviewsList = new ArrayList<Reviews>();
            for (Reviews reviewsListReviewsToAttach : clientrecords.getReviewsList()) {
                reviewsListReviewsToAttach = em.getReference(reviewsListReviewsToAttach.getClass(), reviewsListReviewsToAttach.getReviewsid());
                attachedReviewsList.add(reviewsListReviewsToAttach);
            }
            clientrecords.setReviewsList(attachedReviewsList);
            List<Invoice> attachedInvoiceList = new ArrayList<Invoice>();
            for (Invoice invoiceListInvoiceToAttach : clientrecords.getInvoiceList()) {
                invoiceListInvoiceToAttach = em.getReference(invoiceListInvoiceToAttach.getClass(), invoiceListInvoiceToAttach.getSaleid());
                attachedInvoiceList.add(invoiceListInvoiceToAttach);
            }
            clientrecords.setInvoiceList(attachedInvoiceList);
            em.persist(clientrecords);
            for (Reviews reviewsListReviews : clientrecords.getReviewsList()) {
                Clientrecords oldClientidOfReviewsListReviews = reviewsListReviews.getClientid();
                reviewsListReviews.setClientid(clientrecords);
                reviewsListReviews = em.merge(reviewsListReviews);
                if (oldClientidOfReviewsListReviews != null) {
                    oldClientidOfReviewsListReviews.getReviewsList().remove(reviewsListReviews);
                    oldClientidOfReviewsListReviews = em.merge(oldClientidOfReviewsListReviews);
                }
            }
            for (Invoice invoiceListInvoice : clientrecords.getInvoiceList()) {
                Clientrecords oldClientnumOfInvoiceListInvoice = invoiceListInvoice.getClientnum();
                invoiceListInvoice.setClientnum(clientrecords);
                invoiceListInvoice = em.merge(invoiceListInvoice);
                if (oldClientnumOfInvoiceListInvoice != null) {
                    oldClientnumOfInvoiceListInvoice.getInvoiceList().remove(invoiceListInvoice);
                    oldClientnumOfInvoiceListInvoice = em.merge(oldClientnumOfInvoiceListInvoice);
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

    public void edit(Clientrecords clientrecords) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
          
        try {
            utx.begin();
              
            Clientrecords persistentClientrecords = em.find(Clientrecords.class, clientrecords.getClientnum());
            List<Reviews> reviewsListOld = persistentClientrecords.getReviewsList();
            List<Reviews> reviewsListNew = clientrecords.getReviewsList();
            List<Invoice> invoiceListOld = persistentClientrecords.getInvoiceList();
            List<Invoice> invoiceListNew = clientrecords.getInvoiceList();
            List<String> illegalOrphanMessages = null;
            for (Reviews reviewsListOldReviews : reviewsListOld) {
                if (!reviewsListNew.contains(reviewsListOldReviews)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Reviews " + reviewsListOldReviews + " since its clientid field is not nullable.");
                }
            }
            for (Invoice invoiceListOldInvoice : invoiceListOld) {
                if (!invoiceListNew.contains(invoiceListOldInvoice)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Invoice " + invoiceListOldInvoice + " since its clientnum field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Reviews> attachedReviewsListNew = new ArrayList<Reviews>();
            for (Reviews reviewsListNewReviewsToAttach : reviewsListNew) {
                reviewsListNewReviewsToAttach = em.getReference(reviewsListNewReviewsToAttach.getClass(), reviewsListNewReviewsToAttach.getReviewsid());
                attachedReviewsListNew.add(reviewsListNewReviewsToAttach);
            }
            reviewsListNew = attachedReviewsListNew;
            clientrecords.setReviewsList(reviewsListNew);
            List<Invoice> attachedInvoiceListNew = new ArrayList<Invoice>();
            for (Invoice invoiceListNewInvoiceToAttach : invoiceListNew) {
                invoiceListNewInvoiceToAttach = em.getReference(invoiceListNewInvoiceToAttach.getClass(), invoiceListNewInvoiceToAttach.getSaleid());
                attachedInvoiceListNew.add(invoiceListNewInvoiceToAttach);
            }
            invoiceListNew = attachedInvoiceListNew;
            clientrecords.setInvoiceList(invoiceListNew);
            clientrecords = em.merge(clientrecords);
            for (Reviews reviewsListNewReviews : reviewsListNew) {
                if (!reviewsListOld.contains(reviewsListNewReviews)) {
                    Clientrecords oldClientidOfReviewsListNewReviews = reviewsListNewReviews.getClientid();
                    reviewsListNewReviews.setClientid(clientrecords);
                    reviewsListNewReviews = em.merge(reviewsListNewReviews);
                    if (oldClientidOfReviewsListNewReviews != null && !oldClientidOfReviewsListNewReviews.equals(clientrecords)) {
                        oldClientidOfReviewsListNewReviews.getReviewsList().remove(reviewsListNewReviews);
                        oldClientidOfReviewsListNewReviews = em.merge(oldClientidOfReviewsListNewReviews);
                    }
                }
            }
            for (Invoice invoiceListNewInvoice : invoiceListNew) {
                if (!invoiceListOld.contains(invoiceListNewInvoice)) {
                    Clientrecords oldClientnumOfInvoiceListNewInvoice = invoiceListNewInvoice.getClientnum();
                    invoiceListNewInvoice.setClientnum(clientrecords);
                    invoiceListNewInvoice = em.merge(invoiceListNewInvoice);
                    if (oldClientnumOfInvoiceListNewInvoice != null && !oldClientnumOfInvoiceListNewInvoice.equals(clientrecords)) {
                        oldClientnumOfInvoiceListNewInvoice.getInvoiceList().remove(invoiceListNewInvoice);
                        oldClientnumOfInvoiceListNewInvoice = em.merge(oldClientnumOfInvoiceListNewInvoice);
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
                Integer id = clientrecords.getClientnum();
                if (findClientrecords(id) == null) {
                    throw new NonexistentEntityException("The clientrecords with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
          
        try {
            utx.begin();
              
            Clientrecords clientrecords;
            try {
                clientrecords = em.getReference(Clientrecords.class, id);
                clientrecords.getClientnum();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The clientrecords with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Reviews> reviewsListOrphanCheck = clientrecords.getReviewsList();
            for (Reviews reviewsListOrphanCheckReviews : reviewsListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Clientrecords (" + clientrecords + ") cannot be destroyed since the Reviews " + reviewsListOrphanCheckReviews + " in its reviewsList field has a non-nullable clientid field.");
            }
            List<Invoice> invoiceListOrphanCheck = clientrecords.getInvoiceList();
            for (Invoice invoiceListOrphanCheckInvoice : invoiceListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Clientrecords (" + clientrecords + ") cannot be destroyed since the Invoice " + invoiceListOrphanCheckInvoice + " in its invoiceList field has a non-nullable clientnum field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(clientrecords);
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

    public List<Clientrecords> findClientrecordsEntities() {
        return findClientrecordsEntities(true, -1, -1);
    }

    public List<Clientrecords> findClientrecordsEntities(int maxResults, int firstResult) {
        return findClientrecordsEntities(false, maxResults, firstResult);
    }

    private List<Clientrecords> findClientrecordsEntities(boolean all, int maxResults, int firstResult) {
             
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Clientrecords.class));
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

    public Clientrecords findClientrecords(Integer id) {
             
        try {
            return em.find(Clientrecords.class, id);
        } finally {
            em.close();
        }
    }
   //add other named query
    public Clientrecords findClientrecordsByEmail(String email){
        TypedQuery<Clientrecords> tq = em.createNamedQuery("Clientrecords.findByEmail",Clientrecords.class);
        tq.setParameter(1, email);
        List<Clientrecords> clientrecordlist = tq.getResultList();
        if(!clientrecordlist.isEmpty()){
            return clientrecordlist.get(0);
        }
       return null; 
    }
     //add a named query to find the logged in user
    public Clientrecords findClientrecordsByUsernameAndPass(String username,String password){
        TypedQuery<Clientrecords> tq = em.createNamedQuery("Clientrecords.findByUsernameAndPassword",Clientrecords.class);
        tq.setParameter(1, username);
        tq.setParameter(2, password);
        List<Clientrecords> clientrecordlist = tq.getResultList();
        if(!clientrecordlist.isEmpty()){
            return clientrecordlist.get(0);
        }
       return null; 
    }
    
    //find client by username for the reviews
     public Clientrecords findClientrecordsByUsername(String username){
         CriteriaBuilder cb = em.getCriteriaBuilder();
         CriteriaQuery cq = cb.createQuery();
         Root<Clientrecords>rootquery = cq.from(Clientrecords.class);//from client
        cq.select(rootquery);//select
        cq.where(cb.equal(rootquery.get("username"), username));//where
        Query q = em.createQuery(cq);
       List<Clientrecords> clientrecordlist = q.getResultList();
       return clientrecordlist.get(0) ;
    }
    
    public int getClientrecordsCount() {
             
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Clientrecords> rt = cq.from(Clientrecords.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    //get the actual username
     public List<Clientrecords> getMyPerson() throws SQLException {
        //
        // Criteria for Select all records
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Clientrecords> cq = cb.createQuery(Clientrecords.class);
        Root<Clientrecords> person = cq.from(Clientrecords.class);
        cq.select(person); 
        Predicate loggedUsername;
        loggedUsername = cb.equal(person.get("username"),loginbean.getUsername());
        cq.where(loggedUsername);        
        TypedQuery<Clientrecords> query = em.createQuery(cq);
        List<Clientrecords> personinfo = query.getResultList();
        return personinfo;       
    }
    
}
