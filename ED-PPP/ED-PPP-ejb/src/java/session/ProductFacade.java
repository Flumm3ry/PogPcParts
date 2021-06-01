/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Product;
import entity.ProductDTO;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jerem
 */
@Stateless
public class ProductFacade implements ProductFacadeRemote {
    
    @PersistenceContext(unitName = "ED-PPP-ejbPRODUCT")
    private EntityManager em;

    private void create(Product entity) {
        em.persist(entity);
    }

    private void edit(Product entity) {
        em.merge(entity);
    }
    
    private Product find(int id) {
        return em.find(Product.class, id);
    }
    
    private Product DTO2DAO(ProductDTO productDto) {
        if (productDto == null) return null;
        
        Product product = new Product();
        product.setProductid(productDto.getProductId());
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setImage(productDto.getImage());
        product.setPrice(productDto.getPrice());
        product.setActive(productDto.isActive());
        
        return product;
    }
    
    private ProductDTO DAO2DTO(Product product) {
        if (product == null) return null;
        
        return new ProductDTO(product.getProductid(), product.getName(), product.getDescription(), product.getImage(), product.getActive(), product.getPrice());
    }

    @Override
    public ProductDTO getProductById(int productId) {
        return DAO2DTO(find(productId));
    }

    @Override
    public ProductDTO[] getActiveProducts(boolean priceAscending, String category) {
        return null;
    }

    @Override
    public ProductDTO[] searchProducts(String searchTerm) {
        return null;
    }

    @Override
    public boolean addProduct(ProductDTO productDto) {
        if (productDto == null || find(productDto.getProductId()) != null) return false;
        
        create(DTO2DAO(productDto));
        
        return true;
    }

    @Override
    public boolean updateProduct(ProductDTO productDto) {
        if (productDto == null || find(productDto.getProductId()) == null) return false;
        
        edit(DTO2DAO(productDto));
        
        return true;
    }
}
