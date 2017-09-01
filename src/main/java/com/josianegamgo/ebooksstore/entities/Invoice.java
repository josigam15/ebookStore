/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josianegamgo.ebooksstore.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Josiane Gamgo
 */
@Entity
@Table(name = "invoice", catalog = "ebooksstore", schema = "")
@NamedQueries({
    @NamedQuery(name = "Invoice.findAll", query = "SELECT i FROM Invoice i"),
    @NamedQuery(name = "Invoice.findBySaleid", query = "SELECT i FROM Invoice i WHERE i.saleid = ?1"),
    @NamedQuery(name = "Invoice.findByGst", query = "SELECT i FROM Invoice i WHERE i.gst = :gst"),
    @NamedQuery(name = "Invoice.findByDateofsale", query = "SELECT i FROM Invoice i WHERE i.dateofsale = :dateofsale"),
    @NamedQuery(name = "Invoice.findByTotalnetsale", query = "SELECT i FROM Invoice i WHERE i.totalnetsale = :totalnetsale"),
    @NamedQuery(name = "Invoice.findByTotalgrosssale", query = "SELECT i FROM Invoice i WHERE i.totalgrosssale = :totalgrosssale")})
public class Invoice implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SALEID")
    private Integer saleid;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "GST")
    private BigDecimal gst;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATEOFSALE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateofsale;
    @Column(name = "TOTALNETSALE")
    private BigDecimal totalnetsale;
    @Column(name = "TOTALGROSSSALE")
    private BigDecimal totalgrosssale;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "saleid")
    private List<Invoicedetails> invoicedetailsList;
    @JoinColumn(name = "CLIENTNUM", referencedColumnName = "CLIENTNUM")
    @ManyToOne(optional = false)
    private Clientrecords clientnum;

    public Invoice() {
    }

    public Invoice(Integer saleid) {
        this.saleid = saleid;
    }

    public Invoice(Integer saleid, Date dateofsale) {
        this.saleid = saleid;
        this.dateofsale = dateofsale;
    }

    public Integer getSaleid() {
        return saleid;
    }

    public void setSaleid(Integer saleid) {
        this.saleid = saleid;
    }

    public BigDecimal getGst() {
        return gst;
    }

    public void setGst(BigDecimal gst) {
        this.gst = gst;
    }

    public Date getDateofsale() {
        return dateofsale;
    }

    public void setDateofsale(Date dateofsale) {
        this.dateofsale = dateofsale;
    }

    public BigDecimal getTotalnetsale() {
        return totalnetsale;
    }

    public void setTotalnetsale(BigDecimal totalnetsale) {
        this.totalnetsale = totalnetsale;
    }

    public BigDecimal getTotalgrosssale() {
        return totalgrosssale;
    }

    public void setTotalgrosssale(BigDecimal totalgrosssale) {
        this.totalgrosssale = totalgrosssale;
    }

    public List<Invoicedetails> getInvoicedetailsList() {
        return invoicedetailsList;
    }

    public void setInvoicedetailsList(List<Invoicedetails> invoicedetailsList) {
        this.invoicedetailsList = invoicedetailsList;
    }

    public Clientrecords getClientnum() {
        return clientnum;
    }

    public void setClientnum(Clientrecords clientnum) {
        this.clientnum = clientnum;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (saleid != null ? saleid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Invoice)) {
            return false;
        }
        Invoice other = (Invoice) object;
        if ((this.saleid == null && other.saleid != null) || (this.saleid != null && !this.saleid.equals(other.saleid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.josianegamgo.ebooksstore.entities.Invoice[ saleid=" + saleid + " ]";
    }
    
}
