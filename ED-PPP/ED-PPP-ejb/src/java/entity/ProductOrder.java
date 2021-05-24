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
    @NamedQuery(name = "ProductOrder.findAll", query = "SELECT p FROM ProductOrder p")
    , @NamedQuery(name = "ProductOrder.findByOrderproductid", query = "SELECT p FROM ProductOrder p WHERE p.orderproductid = :orderproductid")
    , @NamedQuery(name = "ProductOrder.findByQuantity", query = "SELECT p FROM ProductOrder p WHERE p.quantity = :quantity")})
public class ProductOrder implements Serializable {

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
    private Orders orderid;
    @JoinColumn(name = "PRODUCTID", referencedColumnName = "PRODUCTID")
    @ManyToOne
    private Products productid;

    public ProductOrder() {
    }

    public ProductOrder(Integer orderproductid) {
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

    public Orders getOrderid() {
        return orderid;
    }

    public void setOrderid(Orders orderid) {
        this.orderid = orderid;
    }

    public Products getProductid() {
        return productid;
    }

    public void setProductid(Products productid) {
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
        if (!(object instanceof ProductOrder)) {
            return false;
        }
        ProductOrder other = (ProductOrder) object;
        if ((this.orderproductid == null && other.orderproductid != null) || (this.orderproductid != null && !this.orderproductid.equals(other.orderproductid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ProductOrder[ orderproductid=" + orderproductid + " ]";
    }
    
}
