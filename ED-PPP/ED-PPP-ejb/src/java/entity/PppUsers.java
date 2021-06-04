/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jerem
 */
@Entity
@Table(name = "PPP_USERS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PppUsers.findAll", query = "SELECT p FROM PppUsers p")
    , @NamedQuery(name = "PppUsers.findByUserid", query = "SELECT p FROM PppUsers p WHERE p.userid = :userid")
    , @NamedQuery(name = "PppUsers.findByName", query = "SELECT p FROM PppUsers p WHERE p.name = :name")
    , @NamedQuery(name = "PppUsers.findByPhone", query = "SELECT p FROM PppUsers p WHERE p.phone = :phone")
    , @NamedQuery(name = "PppUsers.findByAddress", query = "SELECT p FROM PppUsers p WHERE p.address = :address")
    , @NamedQuery(name = "PppUsers.findByEmail", query = "SELECT p FROM PppUsers p WHERE p.email = :email")
    , @NamedQuery(name = "PppUsers.findByPassword", query = "SELECT p FROM PppUsers p WHERE p.password = :password")
    , @NamedQuery(name = "PppUsers.findByAppgroup", query = "SELECT p FROM PppUsers p WHERE p.appgroup = :appgroup")
    , @NamedQuery(name = "PppUsers.findByActive", query = "SELECT p FROM PppUsers p WHERE p.active = :active")})
public class PppUsers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "USERID")
    private Integer userid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "NAME")
    private String name;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "PHONE")
    private String phone;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "ADDRESS")
    private String address;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "EMAIL")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 120)
    @Column(name = "PASSWORD")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "APPGROUP")
    private String appgroup;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ACTIVE")
    private Boolean active;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userid")
    private Collection<PppOrders> pppOrdersCollection;

    public PppUsers() {
    }

    public PppUsers(Integer userid) {
        this.userid = userid;
    }

    public PppUsers(Integer userid, String name, String phone, String address, String email, String password, String appgroup, Boolean active) {
        this.userid = userid;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.password = password;
        this.appgroup = appgroup;
        this.active = active;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAppgroup() {
        return appgroup;
    }

    public void setAppgroup(String appgroup) {
        this.appgroup = appgroup;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @XmlTransient
    public Collection<PppOrders> getPppOrdersCollection() {
        return pppOrdersCollection;
    }

    public void setPppOrdersCollection(Collection<PppOrders> pppOrdersCollection) {
        this.pppOrdersCollection = pppOrdersCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userid != null ? userid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PppUsers)) {
            return false;
        }
        PppUsers other = (PppUsers) object;
        if ((this.userid == null && other.userid != null) || (this.userid != null && !this.userid.equals(other.userid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.PppUsers[ userid=" + userid + " ]";
    }
    
}
