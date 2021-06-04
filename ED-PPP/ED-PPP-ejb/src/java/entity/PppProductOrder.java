/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jerem
 */
@Entity
@Table(name = "PPP_PRODUCT_ORDER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PppProductOrder.findAll", query = "SELECT p FROM PppProductOrder p")
    , @NamedQuery(name = "PppProductOrder.findByOrderproductid", query = "SELECT p FROM PppProductOrder p WHERE p.orderproductid = :orderproductid")
    , @NamedQuery(name = "PppProductOrder.findByQuantity", query = "SELECT p FROM PppProductOrder p WHERE p.quantity = :quantity")})
public class PppProductOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ORDERPRODUCTID")
    private Integer orderproductid;
    @Column(name = "QUANTITY")
    private Integer quantity;
    @JoinColumn(name = "ORDERID", referencedColumnName = "ORDERID")
    @ManyToOne
    private PppOrders orderid;
    @JoinColumn(name = "PRODUCTID", referencedColumnName = "PRODUCTID")
    @ManyToOne
    private PppProducts productid;

    public PppProductOrder() {
    }

    public PppProductOrder(Integer orderproductid) {
        this.orderproductid = orderproductid;
    }

    public Integer getOrderproductid() {
        return orderproductid;
    }

    public void setOrderproductid(Integer orderproductid) {
        this.orderproductid = orderproductid;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public PppOrders getOrderid() {
        return orderid;
    }

    public void setOrderid(PppOrders orderid) {
        this.orderid = orderid;
    }

    public PppProducts getProductid() {
        return productid;
    }

    public void setProductid(PppProducts productid) {
        this.productid = productid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderproductid != null ? orderproductid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PppProductOrder)) {
            return false;
        }
        PppProductOrder other = (PppProductOrder) object;
        if ((this.orderproductid == null && other.orderproductid != null) || (this.orderproductid != null && !this.orderproductid.equals(other.orderproductid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.PppProductOrder[ orderproductid=" + orderproductid + " ]";
    }
    
}
