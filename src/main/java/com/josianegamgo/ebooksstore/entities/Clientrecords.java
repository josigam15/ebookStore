/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josianegamgo.ebooksstore.entities;

import java.io.Serializable;
import java.util.List;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author jgamgo
 */
@Entity
@Table(name = "clientrecords", catalog = "ebooksstore", schema = "")
@NamedQueries({
    @NamedQuery(name = "Clientrecords.findAll", query = "SELECT c FROM Clientrecords c"),
    @NamedQuery(name = "Clientrecords.findByClientnum", query = "SELECT c FROM Clientrecords c WHERE c.clientnum = :clientnum"),
    @NamedQuery(name = "Clientrecords.findByUsernameAndPassword", query = "SELECT c FROM Clientrecords c WHERE c.username = ?1 and c.passwords = ?2"),
    @NamedQuery(name = "Clientrecords.findByTitle", query = "SELECT c FROM Clientrecords c WHERE c.title = :title"),
    @NamedQuery(name = "Clientrecords.findByLastname", query = "SELECT c FROM Clientrecords c WHERE c.lastname = :lastname"),
    @NamedQuery(name = "Clientrecords.findByFirstname", query = "SELECT c FROM Clientrecords c WHERE c.firstname = :firstname"),
    
    @NamedQuery(name = "Clientrecords.findByCompanyname", query = "SELECT c FROM Clientrecords c WHERE c.companyname = :companyname"),
    @NamedQuery(name = "Clientrecords.findByAdress1", query = "SELECT c FROM Clientrecords c WHERE c.adress1 = :adress1"),
    @NamedQuery(name = "Clientrecords.findByAdress2", query = "SELECT c FROM Clientrecords c WHERE c.adress2 = :adress2"),
    @NamedQuery(name = "Clientrecords.findByCity", query = "SELECT c FROM Clientrecords c WHERE c.city = :city"),
    @NamedQuery(name = "Clientrecords.findByProvince", query = "SELECT c FROM Clientrecords c WHERE c.province = :province"),
    @NamedQuery(name = "Clientrecords.findByCountry", query = "SELECT c FROM Clientrecords c WHERE c.country = :country"),
    @NamedQuery(name = "Clientrecords.findByPostalcode", query = "SELECT c FROM Clientrecords c WHERE c.postalcode = :postalcode"),
    @NamedQuery(name = "Clientrecords.findByHomephone", query = "SELECT c FROM Clientrecords c WHERE c.homephone = :homephone"),
    @NamedQuery(name = "Clientrecords.findByCellphone", query = "SELECT c FROM Clientrecords c WHERE c.cellphone = :cellphone"),
    @NamedQuery(name = "Clientrecords.findByEmail", query = "SELECT c FROM Clientrecords c WHERE c.email = ?1")})
public class Clientrecords implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    //@NotNull
    @Column(name = "CLIENTNUM")
    private Integer clientnum;
    
    @Basic(optional=false)
  //@Null
   @Size(min=1,max = 30, message = "{length}" )
    @Column(name = "USERNAME")
    private String username;
    
    @Basic(optional= true)
  //@Null
  //  @Size(min=1,max = 10, message = "{length}" )
    @Column(name = "TITLE")
    private String title;
    
    @Basic(optional = false)
//@NotNull
 //   @Size(min = 1, max = 30, message = "{length}" )
    @Column(name = "LASTNAME")
    private String lastname;
    
    @Basic(optional = false)
//@NotNull
   @Size(min = 1, max = 30, message = "{length}" )
    @Column(name = "FIRSTNAME")
    private String firstname;
    
    @Basic(optional = false)
//@NotNull
    @Size(min = 1, max = 8, message = "{length}" )
    @Column(name = "PASSWORDS")
    private String passwords;
    
    @Basic(optional = false)
//@NotNull
    @Size(min = 1, max = 50, message = "{length}" )
    @Column(name = "COMPANYNAME")
    private String companyname;
    
    @Basic(optional = false)
//@NotNull
    @Size(min = 1, max = 50, message = "{length}" )
    @Column(name = "ADRESS1")
    private String adress1;
    
    @Basic(optional = false)
//@NotNull
    @Size(min = 1, max = 50, message = "{length}" )
    @Column(name = "ADRESS2")
    private String adress2;
    
    @Basic(optional = false)
//@NotNull
    @Size(min = 1, max = 30, message = "{length}" )
    @Column(name = "CITY")
    private String city;
    
    @Basic(optional = false)
//@NotNull
    @Size(min = 1, max = 30, message = "{length}" )
    @Column(name = "PROVINCE")
    private String province;
    
    @Basic(optional = false)
//@NotNull
    @Size(min = 1, max = 30, message = "{length}" )
    @Column(name = "COUNTRY")
    private String country;
    
    @Basic(optional = false)
//@NotNull
    @Size(min = 1, max = 7, message = "{length}" )
    @Column(name = "POSTALCODE")
    private String postalcode;
    
    @Basic(optional = true)
//@NotNull
    @Size(min = 1, max = 14, message = "{fixedlength}")
    @Column(name = "HOMEPHONE")
    private String homephone;
    
    @Basic(optional = true)
//@NotNull
    @Size(min = 1, max = 14, message = "{fixedlength}")
    @Column(name = "CELLPHONE")
    private String cellphone;
    @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
////@NotNull
    @Size(min = 1, max = 60, message = "{length}")
    @Column(name = "EMAIL")
    private String email;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clientid")
    private List<Reviews> reviewsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clientnum")
    private List<Invoice> invoiceList;

    public Clientrecords() {
    }

    public Clientrecords(Integer clientnum) {
        this.clientnum = clientnum;
    }

    public Clientrecords(Integer clientnum, String lastname, String firstname, String passwords, String companyname, String adress1, String adress2, String city, String province, String country, String postalcode, String homephone, String cellphone, String email) {
        this.clientnum = clientnum;
        this.lastname = lastname;
        this.firstname = firstname;
        this.passwords = passwords;
        this.companyname = companyname;
        this.adress1 = adress1;
        this.adress2 = adress2;
        this.city = city;
        this.province = province;
        this.country = country;
        this.postalcode = postalcode;
        this.homephone = homephone;
        this.cellphone = cellphone;
        this.email = email;
    }

    public Integer getClientnum() {
        return clientnum;
    }

    public void setClientnum(Integer clientnum) {
        this.clientnum = clientnum;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getPasswords() {
        return passwords;
    }

    public void setPasswords(String passwords) {
        this.passwords = passwords;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getAdress1() {
        return adress1;
    }

    public void setAdress1(String adress1) {
        this.adress1 = adress1;
    }

    public String getAdress2() {
        return adress2;
    }

    public void setAdress2(String adress2) {
        this.adress2 = adress2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getHomephone() {
        return homephone;
    }

    public void setHomephone(String homephone) {
        this.homephone = homephone;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Reviews> getReviewsList() {
        return reviewsList;
    }

    public void setReviewsList(List<Reviews> reviewsList) {
        this.reviewsList = reviewsList;
    }

    public List<Invoice> getInvoiceList() {
        return invoiceList;
    }

    public void setInvoiceList(List<Invoice> invoiceList) {
        this.invoiceList = invoiceList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clientnum != null ? clientnum.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Clientrecords)) {
            return false;
        }
        Clientrecords other = (Clientrecords) object;
        if ((this.clientnum == null && other.clientnum != null) || (this.clientnum != null && !this.clientnum.equals(other.clientnum))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.josianegamgo.ebooksstore.entities.Clientrecords[ clientnum=" + clientnum + " ]";
    }
    
}
