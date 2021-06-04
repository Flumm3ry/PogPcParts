/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
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
@Table(name = "PPP_PRODUCTS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PppProducts.findAll", query = "SELECT p FROM PppProducts p")
    , @NamedQuery(name = "PppProducts.findByProductid", query = "SELECT p FROM PppProducts p WHERE p.productid = :productid")
    , @NamedQuery(name = "PppProducts.findByName", query = "SELECT p FROM PppProducts p WHERE p.name = :name")
    , @NamedQuery(name = "PppProducts.findByDescription", query = "SELECT p FROM PppProducts p WHERE p.description = :description")
    , @NamedQuery(name = "PppProducts.findByPrice", query = "SELECT p FROM PppProducts p WHERE p.price = :price")
    , @NamedQuery(name = "PppProducts.findByImage", query = "SELECT p FROM PppProducts p WHERE p.image = :image")
    , @NamedQuery(name = "PppProducts.findByCategory", query = "SELECT p FROM PppProducts p WHERE p.category = :category")
    , @NamedQuery(name = "PppProducts.findByActive", query = "SELECT p FROM PppProducts p WHERE p.active = :active")})
public class PppProducts implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PRODUCTID")
    private Integer productid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "NAME")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "DESCRIPTION")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRICE")
    private double price;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "IMAGE")
    private String image;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "CATEGORY")
    private String category;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ACTIVE")
    private Boolean active;
    @OneToMany(mappedBy = "productid")
    private Collection<PppProductOrder> pppProductOrderCollection;

    public PppProducts() {
    }

    public PppProducts(Integer productid) {
        this.productid = productid;
    }

    public PppProducts(Integer productid, String name, String description, double price, String image, String category, Boolean active) {
        this.productid = productid;
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
        this.category = category;
        this.active = active;
    }

    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
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
        hash += (productid != null ? productid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PppProducts)) {
            return false;
        }
        PppProducts other = (PppProducts) object;
        if ((this.productid == null && other.productid != null) || (this.productid != null && !this.productid.equals(other.productid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.PppProducts[ productid=" + productid + " ]";
    }
    
}
