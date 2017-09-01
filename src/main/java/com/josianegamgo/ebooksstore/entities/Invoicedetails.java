/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josianegamgo.ebooksstore.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Josiane Gamgo
 */
@Entity
@Table(name = "invoicedetails", catalog = "ebooksstore", schema = "")
@NamedQueries({
    @NamedQuery(name = "Invoicedetails.findAll", query = "SELECT i FROM Invoicedetails i"),
    @NamedQuery(name = "Invoicedetails.findByInvoicedetailsid", query = "SELECT i FROM Invoicedetails i WHERE i.invoicedetailsid = :invoicedetailsid"),
    @NamedQuery(name = "Invoicedetails.findByPrice", query = "SELECT i FROM Invoicedetails i WHERE i.price = :price")})
public class Invoicedetails implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "INVOICEDETAILSID")
    private Integer invoicedetailsid;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PRICE")
    private BigDecimal price;
    @JoinColumn(name = "ISBNID", referencedColumnName = "ISBN")
    @ManyToOne(optional = false)
    private Bookinventory isbnid;
    @JoinColumn(name = "SALEID", referencedColumnName = "SALEID")
    @ManyToOne(optional = false)
    private Invoice saleid;

    public Invoicedetails() {
    }

    public Invoicedetails(Integer invoicedetailsid) {
        this.invoicedetailsid = invoicedetailsid;
    }

    public Integer getInvoicedetailsid() {
        return invoicedetailsid;
    }

    public void setInvoicedetailsid(Integer invoicedetailsid) {
        this.invoicedetailsid = invoicedetailsid;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Bookinventory getIsbnid() {
        return isbnid;
    }

    public void setIsbnid(Bookinventory isbnid) {
        this.isbnid = isbnid;
    }

    public Invoice getSaleid() {
        return saleid;
    }

    public void setSaleid(Invoice saleid) {
        this.saleid = saleid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (invoicedetailsid != null ? invoicedetailsid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Invoicedetails)) {
            return false;
        }
        Invoicedetails other = (Invoicedetails) object;
        if ((this.invoicedetailsid == null && other.invoicedetailsid != null) || (this.invoicedetailsid != null && !this.invoicedetailsid.equals(other.invoicedetailsid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.josianegamgo.ebooksstore.entities.Invoicedetails[ invoicedetailsid=" + invoicedetailsid + " ]";
    }
    
}
