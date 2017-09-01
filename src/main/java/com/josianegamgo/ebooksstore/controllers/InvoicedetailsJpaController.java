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
import com.josianegamgo.ebooksstore.entities.Invoice;
import com.josianegamgo.ebooksstore.entities.Invoicedetails;
import com.josianegamgo.formcontrollers.LoginBean;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.transaction.UserTransaction;

/**
 *
 * @author jgamgo
 */
@Named
@RequestScoped
public class InvoicedetailsJpaController implements Serializable {

  @Resource
private UserTransaction utx;
@PersistenceContext(unitName="ebooksStorePU")
private EntityManager em;
@Inject
private ClientrecordsJpaController clientrecordsjpacontroller;

public InvoicedetailsJpaController(){

}

    public void create(Invoicedetails invoicedetails) throws RollbackFailureException, Exception {
         
        try {
            utx.begin();
            
            Bookinventory isbnid = invoicedetails.getIsbnid();
            if (isbnid != null) {
                isbnid = em.getReference(isbnid.getClass(), isbnid.getId());
                invoicedetails.setIsbnid(isbnid);
            }
            Invoice saleid = invoicedetails.getSaleid();
            if (saleid != null) {
                saleid = em.getReference(saleid.getClass(), saleid.getSaleid());
                invoicedetails.setSaleid(saleid);
            }
            em.persist(invoicedetails);
            if (isbnid != null) {
                isbnid.getInvoicedetailsList().add(invoicedetails);
                isbnid = em.merge(isbnid);
            }
            if (saleid != null) {
                saleid.getInvoicedetailsList().add(invoicedetails);
                saleid = em.merge(saleid);
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

    public void edit(Invoicedetails invoicedetails) throws NonexistentEntityException, RollbackFailureException, Exception {
         
        try {
            utx.begin();
            
            Invoicedetails persistentInvoicedetails = em.find(Invoicedetails.class, invoicedetails.getInvoicedetailsid());
            Bookinventory isbnidOld = persistentInvoicedetails.getIsbnid();
            Bookinventory isbnidNew = invoicedetails.getIsbnid();
            Invoice saleidOld = persistentInvoicedetails.getSaleid();
            Invoice saleidNew = invoicedetails.getSaleid();
            if (isbnidNew != null) {
                isbnidNew = em.getReference(isbnidNew.getClass(), isbnidNew.getId());
                invoicedetails.setIsbnid(isbnidNew);
            }
            if (saleidNew != null) {
                saleidNew = em.getReference(saleidNew.getClass(), saleidNew.getSaleid());
                invoicedetails.setSaleid(saleidNew);
            }
            invoicedetails = em.merge(invoicedetails);
            if (isbnidOld != null && !isbnidOld.equals(isbnidNew)) {
                isbnidOld.getInvoicedetailsList().remove(invoicedetails);
                isbnidOld = em.merge(isbnidOld);
            }
            if (isbnidNew != null && !isbnidNew.equals(isbnidOld)) {
                isbnidNew.getInvoicedetailsList().add(invoicedetails);
                isbnidNew = em.merge(isbnidNew);
            }
            if (saleidOld != null && !saleidOld.equals(saleidNew)) {
                saleidOld.getInvoicedetailsList().remove(invoicedetails);
                saleidOld = em.merge(saleidOld);
            }
            if (saleidNew != null && !saleidNew.equals(saleidOld)) {
                saleidNew.getInvoicedetailsList().add(invoicedetails);
                saleidNew = em.merge(saleidNew);
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
                Integer id = invoicedetails.getInvoicedetailsid();
                if (findInvoicedetails(id) == null) {
                    throw new NonexistentEntityException("The invoicedetails with id " + id + " no longer exists.");
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
            
            Invoicedetails invoicedetails;
            try {
                invoicedetails = em.getReference(Invoicedetails.class, id);
                invoicedetails.getInvoicedetailsid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The invoicedetails with id " + id + " no longer exists.", enfe);
            }
            Bookinventory isbnid = invoicedetails.getIsbnid();
            if (isbnid != null) {
                isbnid.getInvoicedetailsList().remove(invoicedetails);
                isbnid = em.merge(isbnid);
            }
            Invoice saleid = invoicedetails.getSaleid();
            if (saleid != null) {
                saleid.getInvoicedetailsList().remove(invoicedetails);
                saleid = em.merge(saleid);
            }
            em.remove(invoicedetails);
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

    public List<Invoicedetails> findInvoicedetailsEntities() {
        return findInvoicedetailsEntities(true, -1, -1);
    }

    public List<Invoicedetails> findInvoicedetailsEntities(int maxResults, int firstResult) {
        return findInvoicedetailsEntities(false, maxResults, firstResult);
    }

    private List<Invoicedetails> findInvoicedetailsEntities(boolean all, int maxResults, int firstResult) {
         
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Invoicedetails.class));
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

    public Invoicedetails findInvoicedetails(Integer id) {
         
        try {
            return em.find(Invoicedetails.class, id);
        } finally {
            em.close();
        }
    }

    public int getInvoicedetailsCount() {
         
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Invoicedetails> rt = cq.from(Invoicedetails.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
   
     //other queries
      
   public List<Invoicedetails> getAll() throws SQLException{
   CriteriaBuilder cb = em.getCriteriaBuilder();
   CriteriaQuery<Invoicedetails> cq = cb.createQuery(Invoicedetails.class);
   Root<Invoicedetails> invoicedetails = cq.from(Invoicedetails.class);
   cq.select(invoicedetails);
   TypedQuery<Invoicedetails> query =em.createQuery(cq);
   List<Invoicedetails> allinvoice = query.getResultList();
   
   return allinvoice;
   } 
     //get invoice details of this invoice
   
// public List<Invoicedetails> getAllUserInvoice() throws SQLException{
// TypedQuery<Invoicedetails> tq = em.createQuery("SELECT id,i FROM Invoicedetails id LEFT OUTER JOIN Invoice i ON id.SALEID=i.SALEID  WHERE i.clientnum="+clientrecordsjpacontroller.getMyPerson().get(0).getClientnum()+"",Invoicedetails.class) ;
//
// List<Invoicedetails> allinvoicedetails = tq.getResultList();
//   return allinvoicedetails;
//   }
 
 //IvoicedetailsController
public int getLastSaleId(){
        int sale_id=0;
        
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery cq = cb.createQuery(Integer.class);
            Root<Invoicedetails> ri = cq.from(Invoicedetails.class);
            cq.select(cb.max(ri.<Long>get("invoicedetailsid")));
            Object result = em.createQuery(cq).getSingleResult();
            
            sale_id=(int)result;                    
            System.out.println("sale_id  work");        
        }catch(Exception e){
            System.out.println("DONT work");
        }
        
        return sale_id;
    }


}
