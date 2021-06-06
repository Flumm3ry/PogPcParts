/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jerem
 */
@Entity
@Table(name = "PPP_ORDERS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PppOrders.findAll", query = "SELECT p FROM PppOrders p")
    , @NamedQuery(name = "PppOrders.findByOrderid", query = "SELECT p FROM PppOrders p WHERE p.orderid = :orderid")
    , @NamedQuery(name = "PppOrders.findByDate", query = "SELECT p FROM PppOrders p WHERE p.date = :date")})
public class PppOrders implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ORDERID")
    private Integer orderid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATE")
    @Temporal(TemporalType.DATE)
    private Date date;
    @JoinColumn(name = "USERID", referencedColumnName = "USERID")
    @ManyToOne(optional = false)
    private PppUsers userid;
    @OneToMany(mappedBy = "orderid", cascade =  CascadeType.PERSIST)
    private Collection<PppProductOrder> pppProductOrderCollection;

    public PppOrders() {
    }

    public PppOrders(Integer orderid) {
        this.orderid = orderid;
    }

    public PppOrders(Integer orderid, Date date) {
        this.orderid = orderid;
        this.date = date;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public PppUsers getUserid() {
        return userid;
    }

    public void setUserid(PppUsers userid) {
        this.userid = userid;
    }

    @XmlTransient
    public Collection<PppProductOrder> getPppProductOrderCollection() {
        return pppProductOrderCollection;
    }

    public void setPppProductOrderCollection(Collection<PppProductOrder> pppProductOrderCollection) {
        this.pppProductOrderCollection = pppProductOrderCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderid != null ? orderid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PppOrders)) {
            return false;
        }
        PppOrders other = (PppOrders) object;
        if ((this.orderid == null && other.orderid != null) || (this.orderid != null && !this.orderid.equals(other.orderid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.PppOrders[ orderid=" + orderid + " ]";
    }
    
}
