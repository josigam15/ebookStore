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
import javax.inject.Named;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author jgamgo
 */
@Entity

@Table(name = "bookinventory", catalog = "ebooksstore", schema = "")
@NamedQueries({
    @NamedQuery(name = "Bookinventory.findAll", query = "SELECT b FROM Bookinventory b"),
    @NamedQuery(name = "Bookinventory.findById", query = "SELECT b FROM Bookinventory b WHERE b.id = :id"),
    @NamedQuery(name = "Bookinventory.findByIsbn", query = "SELECT b FROM Bookinventory b WHERE b.isbn =?1"),
    @NamedQuery(name = "Bookinventory.findByTitle", query = "SELECT b FROM Bookinventory b WHERE b.title = :title"),
    @NamedQuery(name = "Bookinventory.findByAuthor", query = "SELECT b FROM Bookinventory b WHERE b.author = :author"),
    @NamedQuery(name = "Bookinventory.findByPublisher", query = "SELECT b FROM Bookinventory b WHERE b.publisher = :publisher"),
    @NamedQuery(name = "Bookinventory.findByNumberofpages", query = "SELECT b FROM Bookinventory b WHERE b.numberofpages = :numberofpages"),
    @NamedQuery(name = "Bookinventory.findByGenre", query = "SELECT b FROM Bookinventory b WHERE b.genre = ?1"),
    @NamedQuery(name = "Bookinventory.findByBookimage", query = "SELECT b FROM Bookinventory b WHERE b.bookimage = :bookimage"),
    @NamedQuery(name = "Bookinventory.findByWholesaleprice", query = "SELECT b FROM Bookinventory b WHERE b.wholesaleprice = :wholesaleprice"),
    @NamedQuery(name = "Bookinventory.findByPrice", query = "SELECT b FROM Bookinventory b WHERE b.listprice = ?1 or b.wholesaleprice = ?2"),
    @NamedQuery(name = "Bookinventory.findByEntrydate", query = "SELECT b FROM Bookinventory b WHERE b.entrydate = ?1"),
    @NamedQuery(name = "Bookinventory.findByRemovalstatus", query = "SELECT b FROM Bookinventory b WHERE b.removalstatus = :removalstatus"),
    @NamedQuery(name = "Bookinventory.findByDescription", query = "SELECT b FROM Bookinventory b WHERE b.description = :description"),
    @NamedQuery(name = "Bookinventory.findByPublicationdate", query = "SELECT b FROM Bookinventory b WHERE b.publicationdate = :publicationdate")})
public class Bookinventory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 13, message = "{length}")
    @Column(name = "ISBN")
    private String isbn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200, message = "{length}")
    @Column(name = "TITLE")
    private String title;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200, message = "{length}")
    @Column(name = "AUTHOR")
    private String author;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200, message = "{length}")
    @Column(name = "PUBLISHER")
    private String publisher;
    @Column(name = "NUMBEROFPAGES")
    private Integer numberofpages;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100, message = "{length}")
    @Column(name = "GENRE")
    private String genre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500, message = "{length}")
    @Column(name = "BOOKIMAGE")
    private String bookimage;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "WHOLESALEPRICE")
    private BigDecimal wholesaleprice;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LISTPRICE")
    private BigDecimal listprice;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ENTRYDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date entrydate;
    @Column(name = "REMOVALSTATUS")
    private Boolean removalstatus;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500, message = "{length}")
    @Column(name = "DESCRIPTION")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PUBLICATIONDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date publicationdate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "isbnid")
    private List<Invoicedetails> invoicedetailsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bookisbn")
    private List<Reviews> reviewsList;

    public Bookinventory() {
    }

    public Bookinventory(Integer id) {
        this.id = id;
    }

    public Bookinventory(Integer id, String isbn, String title, String author, String publisher, String genre, String bookimage, BigDecimal wholesaleprice, BigDecimal listprice, Date entrydate, String description, Date publicationdate) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.genre = genre;
        this.bookimage = bookimage;
        this.wholesaleprice = wholesaleprice;
        this.listprice = listprice;
        this.entrydate = entrydate;
        this.description = description;
        this.publicationdate = publicationdate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Integer getNumberofpages() {
        return numberofpages;
    }

    public void setNumberofpages(Integer numberofpages) {
        this.numberofpages = numberofpages;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getBookimage() {
        return bookimage;
    }

    public void setBookimage(String bookimage) {
        this.bookimage = bookimage;
    }

    public BigDecimal getWholesaleprice() {
        return wholesaleprice;
    }

    public void setWholesaleprice(BigDecimal wholesaleprice) {
        this.wholesaleprice = wholesaleprice;
    }

    public BigDecimal getListprice() {
        return listprice;
    }

    public void setListprice(BigDecimal listprice) {
        this.listprice = listprice;
    }

    public Date getEntrydate() {
        return entrydate;
    }

    public void setEntrydate(Date entrydate) {
        this.entrydate = entrydate;
    }

    public Boolean getRemovalstatus() {
        return removalstatus;
    }

    public void setRemovalstatus(Boolean removalstatus) {
        this.removalstatus = removalstatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getPublicationdate() {
        return publicationdate;
    }

    public void setPublicationdate(Date publicationdate) {
        this.publicationdate = publicationdate;
    }

    public List<Invoicedetails> getInvoicedetailsList() {
        return invoicedetailsList;
    }

    public void setInvoicedetailsList(List<Invoicedetails> invoicedetailsList) {
        this.invoicedetailsList = invoicedetailsList;
    }

    public List<Reviews> getReviewsList() {
        return reviewsList;
    }

    public void setReviewsList(List<Reviews> reviewsList) {
        this.reviewsList = reviewsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bookinventory)) {
            return false;
        }
        Bookinventory other = (Bookinventory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.josianegamgo.ebooksstore.entities.Bookinventory[ id=" + id + " ]";
    }
    
}
