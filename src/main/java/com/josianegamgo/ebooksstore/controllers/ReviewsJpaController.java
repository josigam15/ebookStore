/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josianegamgo.ebooksstore.controllers;

import com.josianegamgo.ebooksstore.controllers.exceptions.NonexistentEntityException;
import com.josianegamgo.ebooksstore.controllers.exceptions.RollbackFailureException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.josianegamgo.ebooksstore.entities.Bookinventory;
import com.josianegamgo.ebooksstore.entities.Clientrecords;
import com.josianegamgo.ebooksstore.entities.Reviews;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.UserTransaction;

/**
 *
 * @author jgamgo
 */
@Named
@RequestScoped
public class ReviewsJpaController implements Serializable {
  @Resource
private UserTransaction utx;
@PersistenceContext(unitName="ebooksStorePU")
private EntityManager em;
public ReviewsJpaController(){

}
    
    public void create(Reviews reviews) throws RollbackFailureException, Exception {
         
        try {
            utx.begin();
             
            Bookinventory bookisbn = reviews.getBookisbn();
            if (bookisbn != null) {
                bookisbn = em.getReference(bookisbn.getClass(), bookisbn.getId());
                reviews.setBookisbn(bookisbn);
            }
            Clientrecords clientid = reviews.getClientid();
            if (clientid != null) {
                clientid = em.getReference(clientid.getClass(), clientid.getClientnum());
                reviews.setClientid(clientid);
            }
            em.persist(reviews);
            if (bookisbn != null) {
                bookisbn.getReviewsList().add(reviews);
                bookisbn = em.merge(bookisbn);
            }
            if (clientid != null) {
                clientid.getReviewsList().add(reviews);
                clientid = em.merge(clientid);
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

    public void edit(Reviews reviews) throws NonexistentEntityException, RollbackFailureException, Exception {
         
        try {
            utx.begin();
             
            Reviews persistentReviews = em.find(Reviews.class, reviews.getReviewsid());
            Bookinventory bookisbnOld = persistentReviews.getBookisbn();
            Bookinventory bookisbnNew = reviews.getBookisbn();
            Clientrecords clientidOld = persistentReviews.getClientid();
            Clientrecords clientidNew = reviews.getClientid();
            if (bookisbnNew != null) {
                bookisbnNew = em.getReference(bookisbnNew.getClass(), bookisbnNew.getId());
                reviews.setBookisbn(bookisbnNew);
            }
            if (clientidNew != null) {
                clientidNew = em.getReference(clientidNew.getClass(), clientidNew.getClientnum());
                reviews.setClientid(clientidNew);
            }
            reviews = em.merge(reviews);
            if (bookisbnOld != null && !bookisbnOld.equals(bookisbnNew)) {
                bookisbnOld.getReviewsList().remove(reviews);
                bookisbnOld = em.merge(bookisbnOld);
            }
            if (bookisbnNew != null && !bookisbnNew.equals(bookisbnOld)) {
                bookisbnNew.getReviewsList().add(reviews);
                bookisbnNew = em.merge(bookisbnNew);
            }
            if (clientidOld != null && !clientidOld.equals(clientidNew)) {
                clientidOld.getReviewsList().remove(reviews);
                clientidOld = em.merge(clientidOld);
            }
            if (clientidNew != null && !clientidNew.equals(clientidOld)) {
                clientidNew.getReviewsList().add(reviews);
                clientidNew = em.merge(clientidNew);
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
                Integer id = reviews.getReviewsid();
                if (findReviews(id) == null) {
                    throw new NonexistentEntityException("The reviews with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
         
        try {
            utx.begin();
             
            Reviews reviews;
            try {
                reviews = em.getReference(Reviews.class, id);
                reviews.getReviewsid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The reviews with id " + id + " no longer exists.", enfe);
            }
            Bookinventory bookisbn = reviews.getBookisbn();
            if (bookisbn != null) {
                bookisbn.getReviewsList().remove(reviews);
                bookisbn = em.merge(bookisbn);
            }
            Clientrecords clientid = reviews.getClientid();
            if (clientid != null) {
                clientid.getReviewsList().remove(reviews);
                clientid = em.merge(clientid);
            }
            em.remove(reviews);
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

    public List<Reviews> findReviewsEntities() {
        return findReviewsEntities(true, -1, -1);
    }

    public List<Reviews> findReviewsEntities(int maxResults, int firstResult) {
        return findReviewsEntities(false, maxResults, firstResult);
    }

    private List<Reviews> findReviewsEntities(boolean all, int maxResults, int firstResult) {
         
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Reviews.class));
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

    public Reviews findReviews(Integer id) {
         
        try {
            return em.find(Reviews.class, id);
        } finally {
            em.close();
        }
    }

    public int getReviewsCount() {
         
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Reviews> rt = cq.from(Reviews.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
  
   public List<Reviews> getAll() throws SQLException{
   CriteriaBuilder cb = em.getCriteriaBuilder();
   CriteriaQuery<Reviews> cq = cb.createQuery(Reviews.class);
   Root<Reviews> reviews = cq.from(Reviews.class);
   cq.select(reviews);
   TypedQuery<Reviews> query =em.createQuery(cq);
   List<Reviews> allreviews = query.getResultList();
   
   return allreviews;
   }
   
       //add a named query to find the logged in user
    public Reviews findReviewsByDate(Date submitdate){
       
        TypedQuery<Reviews> tq = em.createNamedQuery("Reviews.findBySubmitdate",Reviews.class);
        tq.setParameter(1, submitdate);
        
        List<Reviews> reviewslist = tq.getResultList();
        if(!reviewslist.isEmpty()){
            return reviewslist.get(0);
        }
       return null; 
    }
    
    
}
