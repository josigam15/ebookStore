/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josianegamgo.ebooksstore.controllers;

import com.josianegamgo.ebooksstore.controllers.exceptions.IllegalOrphanException;
import com.josianegamgo.ebooksstore.controllers.exceptions.NonexistentEntityException;
import com.josianegamgo.ebooksstore.controllers.exceptions.RollbackFailureException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.josianegamgo.ebooksstore.entities.Clientrecords;
import com.josianegamgo.ebooksstore.entities.Invoice;
import com.josianegamgo.ebooksstore.entities.Invoicedetails;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import static javax.persistence.criteria.JoinType.LEFT;
import javax.transaction.UserTransaction;

/**
 *
 * @author jgamgo
 */
@Named
@RequestScoped
public class InvoiceJpaController implements Serializable {
    
@Resource 
private UserTransaction utx;
@PersistenceContext(unitName="ebooksStorePU")
private EntityManager em ;

public InvoiceJpaController(){
}

    public void create(Invoice invoice) throws RollbackFailureException, Exception {
        if (invoice.getInvoicedetailsList() == null) {
            invoice.setInvoicedetailsList(new ArrayList<Invoicedetails>());
        }
            
        try {
            utx.begin();
               
            Clientrecords clientnum = invoice.getClientnum();
            if (clientnum != null) {
                clientnum = em.getReference(clientnum.getClass(), clientnum.getClientnum());
                invoice.setClientnum(clientnum);
            }
            List<Invoicedetails> attachedInvoicedetailsList = new ArrayList<Invoicedetails>();
            for (Invoicedetails invoicedetailsListInvoicedetailsToAttach : invoice.getInvoicedetailsList()) {
                invoicedetailsListInvoicedetailsToAttach = em.getReference(invoicedetailsListInvoicedetailsToAttach.getClass(), invoicedetailsListInvoicedetailsToAttach.getInvoicedetailsid());
                attachedInvoicedetailsList.add(invoicedetailsListInvoicedetailsToAttach);
            }
            invoice.setInvoicedetailsList(attachedInvoicedetailsList);
            em.persist(invoice);
            if (clientnum != null) {
                clientnum.getInvoiceList().add(invoice);
                clientnum = em.merge(clientnum);
            }
            for (Invoicedetails invoicedetailsListInvoicedetails : invoice.getInvoicedetailsList()) {
                Invoice oldSaleidOfInvoicedetailsListInvoicedetails = invoicedetailsListInvoicedetails.getSaleid();
                invoicedetailsListInvoicedetails.setSaleid(invoice);
                invoicedetailsListInvoicedetails = em.merge(invoicedetailsListInvoicedetails);
                if (oldSaleidOfInvoicedetailsListInvoicedetails != null) {
                    oldSaleidOfInvoicedetailsListInvoicedetails.getInvoicedetailsList().remove(invoicedetailsListInvoicedetails);
                    oldSaleidOfInvoicedetailsListInvoicedetails = em.merge(oldSaleidOfInvoicedetailsListInvoicedetails);
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

    public void edit(Invoice invoice) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
            
        try {
            utx.begin();
               
            Invoice persistentInvoice = em.find(Invoice.class, invoice.getSaleid());
            Clientrecords clientnumOld = persistentInvoice.getClientnum();
            Clientrecords clientnumNew = invoice.getClientnum();
            List<Invoicedetails> invoicedetailsListOld = persistentInvoice.getInvoicedetailsList();
            List<Invoicedetails> invoicedetailsListNew = invoice.getInvoicedetailsList();
            List<String> illegalOrphanMessages = null;
            for (Invoicedetails invoicedetailsListOldInvoicedetails : invoicedetailsListOld) {
                if (!invoicedetailsListNew.contains(invoicedetailsListOldInvoicedetails)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Invoicedetails " + invoicedetailsListOldInvoicedetails + " since its saleid field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (clientnumNew != null) {
                clientnumNew = em.getReference(clientnumNew.getClass(), clientnumNew.getClientnum());
                invoice.setClientnum(clientnumNew);
            }
            List<Invoicedetails> attachedInvoicedetailsListNew = new ArrayList<Invoicedetails>();
            for (Invoicedetails invoicedetailsListNewInvoicedetailsToAttach : invoicedetailsListNew) {
                invoicedetailsListNewInvoicedetailsToAttach = em.getReference(invoicedetailsListNewInvoicedetailsToAttach.getClass(), invoicedetailsListNewInvoicedetailsToAttach.getInvoicedetailsid());
                attachedInvoicedetailsListNew.add(invoicedetailsListNewInvoicedetailsToAttach);
            }
            invoicedetailsListNew = attachedInvoicedetailsListNew;
            invoice.setInvoicedetailsList(invoicedetailsListNew);
            invoice = em.merge(invoice);
            if (clientnumOld != null && !clientnumOld.equals(clientnumNew)) {
                clientnumOld.getInvoiceList().remove(invoice);
                clientnumOld = em.merge(clientnumOld);
            }
            if (clientnumNew != null && !clientnumNew.equals(clientnumOld)) {
                clientnumNew.getInvoiceList().add(invoice);
                clientnumNew = em.merge(clientnumNew);
            }
            for (Invoicedetails invoicedetailsListNewInvoicedetails : invoicedetailsListNew) {
                if (!invoicedetailsListOld.contains(invoicedetailsListNewInvoicedetails)) {
                    Invoice oldSaleidOfInvoicedetailsListNewInvoicedetails = invoicedetailsListNewInvoicedetails.getSaleid();
                    invoicedetailsListNewInvoicedetails.setSaleid(invoice);
                    invoicedetailsListNewInvoicedetails = em.merge(invoicedetailsListNewInvoicedetails);
                    if (oldSaleidOfInvoicedetailsListNewInvoicedetails != null && !oldSaleidOfInvoicedetailsListNewInvoicedetails.equals(invoice)) {
                        oldSaleidOfInvoicedetailsListNewInvoicedetails.getInvoicedetailsList().remove(invoicedetailsListNewInvoicedetails);
                        oldSaleidOfInvoicedetailsListNewInvoicedetails = em.merge(oldSaleidOfInvoicedetailsListNewInvoicedetails);
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
                Integer id = invoice.getSaleid();
                if (findInvoice(id) == null) {
                    throw new NonexistentEntityException("The invoice with id " + id + " no longer exists.");
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
               
            Invoice invoice;
            try {
                invoice = em.getReference(Invoice.class, id);
                invoice.getSaleid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The invoice with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Invoicedetails> invoicedetailsListOrphanCheck = invoice.getInvoicedetailsList();
            for (Invoicedetails invoicedetailsListOrphanCheckInvoicedetails : invoicedetailsListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Invoice (" + invoice + ") cannot be destroyed since the Invoicedetails " + invoicedetailsListOrphanCheckInvoicedetails + " in its invoicedetailsList field has a non-nullable saleid field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Clientrecords clientnum = invoice.getClientnum();
            if (clientnum != null) {
                clientnum.getInvoiceList().remove(invoice);
                clientnum = em.merge(clientnum);
            }
            em.remove(invoice);
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

    public List<Invoice> findInvoiceEntities() {
        return findInvoiceEntities(true, -1, -1);
    }

    public List<Invoice> findInvoiceEntities(int maxResults, int firstResult) {
        return findInvoiceEntities(false, maxResults, firstResult);
    }

    private List<Invoice> findInvoiceEntities(boolean all, int maxResults, int firstResult) {
            
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Invoice.class));
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

    public Invoice findInvoice(Integer id) {
            
        try {
            return em.find(Invoice.class, id);
        } finally {
            em.close();
        }
    }

    public int getInvoiceCount() {
            
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Invoice> rt = cq.from(Invoice.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    //other queries
      
   public List<Invoice> getAll() throws SQLException{
   CriteriaBuilder cb = em.getCriteriaBuilder();
   CriteriaQuery<Invoice> cq = cb.createQuery(Invoice.class);
   Root<Invoice> invoice = cq.from(Invoice.class);
   cq.select(invoice);
   TypedQuery<Invoice> query =em.createQuery(cq);
   List<Invoice> allinvoice = query.getResultList();
   
   return allinvoice;
   } 
    //get invoice of this user
 public Invoice getAllUserInvoice(int userid) throws SQLException{
        TypedQuery<Invoice> tq = em.createQuery("SELECT i,c.name FROM Invoice i LEFT OUTER JOIN Clientrecords c WHERE i.clientnum=?1",Invoice.class) ;
        tq.setParameter(1, userid);

        List<Invoice> alluserinvoice = tq.getResultList();

        return alluserinvoice.get(0);
   }
     //get all invoice isbn
 public List<Invoice> getAllBooksinInvoice(int saleid) throws SQLException{
 TypedQuery<Invoice> tq = em.createQuery("SELECT i,b.isbn FROM Invoice i LEFT OUTER JOIN Bookinventory b WHERE i.saleid=?1",Invoice.class) ;
  tq.setParameter(1, saleid);
 List<Invoice> allbookinvoice = tq.getResultList();
   return allbookinvoice;
   }
 
 //InvoiceJpaController 
public int getLastSaleId(){
        int sale_id=0;
        
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery cq = cb.createQuery(Integer.class);
            Root<Invoice> ri = cq.from(Invoice.class);
            cq.select(cb.max(ri.<Long>get("saleid")));
            Object result = em.createQuery(cq).getSingleResult();
            
            sale_id=(int)result;                    
            System.out.println("sale_id  work");        
        }catch(Exception e){
            System.out.println("DONT work");
        }
        
        return sale_id;
    }


 
}
