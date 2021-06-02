/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import com.sun.xml.ws.util.StringUtils;
import entity.Product;
import entity.ProductDTO;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
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
        product.setCategory(productDto.getCategory());
        
        return product;
    }
    
    private ProductDTO DAO2DTO(Product product) {
        if (product == null) return null;
        
        return new ProductDTO(product.getProductid(), product.getName(), product.getDescription(), product.getImage(), product.getActive(), product.getPrice(), product.getCategory());
    }
    
    private List<Product> getSearchedProducts(String searchTerm, String category) {
        if (category == null) category = "";
        if (searchTerm == null) searchTerm = "";
        category = "%" + category + "%";
        searchTerm = "%" + searchTerm + "%";
        
        return em.createQuery("SELECT p FROM Products p WHERE p.name LIKE :searchTerm AND p.category LIKE :category", Product.class)
                .setParameter("searchTerm", searchTerm)
                .setParameter("category", category)
                .getResultList();
    }
   

    @Override
    public ProductDTO getProductById(int productId) {
        return DAO2DTO(find(productId));
    }

    @Override
    public List<ProductDTO> searchProducts(String searchTerm, boolean priceAscending, String category) {
        return getSearchedProducts(searchTerm, category)
                .stream()
                .filter(p -> p.getActive())
                .sorted(priceAscending ? Comparator.comparingDouble(Product::getPrice) : Comparator.comparingDouble(Product::getPrice).reversed())
                .map(p -> DAO2DTO(p))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> adminSearchProducts(String searchTerm, boolean priceAscending, String category) {
        return getSearchedProducts(searchTerm, category)
                .stream()
                .sorted(priceAscending ? Comparator.comparingDouble(Product::getPrice) : Comparator.comparingDouble(Product::getPrice).reversed())
                .map(p -> DAO2DTO(p))
                .collect(Collectors.toList());
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
