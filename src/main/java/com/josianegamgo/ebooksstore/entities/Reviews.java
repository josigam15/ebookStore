/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josianegamgo.ebooksstore.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Josiane Gamgo
 */
@Entity
@Table(name = "reviews", catalog = "ebooksstore", schema = "")
@NamedQueries({
    @NamedQuery(name = "Reviews.findAll", query = "SELECT r FROM Reviews r"),
    @NamedQuery(name = "Reviews.findByReviewsid", query = "SELECT r FROM Reviews r WHERE r.reviewsid = ?1"),
    @NamedQuery(name = "Reviews.findByRating", query = "SELECT r FROM Reviews r WHERE r.rating = :rating"),
    @NamedQuery(name = "Reviews.findBySubmitdate", query = "SELECT r FROM Reviews r WHERE r.submitdate = ?1"),
    @NamedQuery(name = "Reviews.findByApprovalstatus", query = "SELECT r FROM Reviews r WHERE r.approvalstatus =?1")})
public class Reviews implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "REVIEWSID")
    private Integer reviewsid;
    @Column(name = "RATING")
    private Integer rating;
    @Lob
    @Size(max = 65535)
    @Column(name = "REVIEWTEXT")
    private String reviewtext;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SUBMITDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date submitdate;
    @Column(name = "APPROVALSTATUS")
    private Boolean approvalstatus;
    @JoinColumn(name = "BOOKISBN", referencedColumnName = "ISBN")
    @ManyToOne(optional = false)
    private Bookinventory bookisbn;
    @JoinColumn(name = "CLIENTID", referencedColumnName = "CLIENTNUM")
    @ManyToOne(optional = false)
    private Clientrecords clientid;

    public Reviews() {
    }

    public Reviews(Integer reviewsid) {
        this.reviewsid = reviewsid;
    }

    public Reviews(Integer reviewsid, Date submitdate) {
        this.reviewsid = reviewsid;
        this.submitdate = submitdate;
    }

    public Integer getReviewsid() {
        return reviewsid;
    }

    public void setReviewsid(Integer reviewsid) {
        this.reviewsid = reviewsid;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getReviewtext() {
        return reviewtext;
    }

    public void setReviewtext(String reviewtext) {
        this.reviewtext = reviewtext;
    }

    public Date getSubmitdate() {
        return submitdate;
    }

    public void setSubmitdate(Date submitdate) {
        this.submitdate = submitdate;
    }

    public Boolean getApprovalstatus() {
        return approvalstatus;
    }

    public void setApprovalstatus(Boolean approvalstatus) {
        this.approvalstatus = approvalstatus;
    }

    public Bookinventory getBookisbn() {
        return bookisbn;
    }

    public void setBookisbn(Bookinventory bookisbn) {
        this.bookisbn = bookisbn;
    }

    public Clientrecords getClientid() {
        return clientid;
    }

    public void setClientid(Clientrecords clientid) {
        this.clientid = clientid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reviewsid != null ? reviewsid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reviews)) {
            return false;
        }
        Reviews other = (Reviews) object;
        if ((this.reviewsid == null && other.reviewsid != null) || (this.reviewsid != null && !this.reviewsid.equals(other.reviewsid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.josianegamgo.ebooksstore.entities.Reviews[ reviewsid=" + reviewsid + " ]";
    }
    
}
