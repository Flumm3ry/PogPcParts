/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import entity.ProductDTO;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import session.ProductFacadeRemote;

/**
 *
 * @author jerem
 */
@Named(value = "myProductManagedBean")
@SessionScoped
public class MyProductManagedBean implements Serializable {

    @EJB
    private ProductFacadeRemote productFacade;
    
    private List<ProductDTO> productList;
    private String searchTerm;
    private boolean isAscending;
    private String filterCategory;
    
    private int productId;
    private String name;
    private String description;
    private String image;
    private boolean active;
    private String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<ProductDTO> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductDTO> productList) {
        this.productList = productList;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public boolean isIsAscending() {
        return isAscending;
    }

    public void setIsAscending(boolean isAscending) {
        this.isAscending = isAscending;
    }

    public String getFilterCategory() {
        return filterCategory;
    }

    public void setFilterCategory(String filterCategory) {
        this.filterCategory = filterCategory;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    private double price;

    /**
     * Creates a new instance of MyProductManagedBean
     */
    public MyProductManagedBean() {
    }
    
    public String addProduct() {
        ProductDTO productDto = new ProductDTO(null, name, description, image, active, price, category);
        
        return productFacade.addProduct(productDto) ? "success" : "fail";
    }
    
    public String updateProduct() {
        ProductDTO productDto = new ProductDTO(productId, name, description, image, active, price, category);
        
        return productFacade.updateProduct(productDto) ? "success" : "fail";
    }
    
    public String searchProducts() {
        try {
            setProductList(productFacade.searchProducts(searchTerm, isAscending, filterCategory));
            return "success";
        } catch (Exception e) {
        }
        
        return "fail";
    }
    
    public String adminSearchProducts() {
        try {
            setProductList(productFacade.adminSearchProducts(searchTerm, isAscending, filterCategory));
            return "success";
        } catch (Exception e) {
        }
        
        return "fail";
    }
    
    public String getProduct(Integer productIdToGet) {
        ProductDTO productDto = productFacade.getProductById(productIdToGet);
        
        if (productDto == null) return "fail";
        
        setProductId(productDto.getProductId());
        setName(productDto.getName());
        setDescription(productDto.getDescription());
        setImage(productDto.getImage());
        setActive(productDto.isActive());
        setCategory(productDto.getCategory());
        
        return "success";
    }
    
}
