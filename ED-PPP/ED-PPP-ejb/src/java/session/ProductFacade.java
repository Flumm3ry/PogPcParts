/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.PppProducts;
import entity.ProductDTO;
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

    @PersistenceContext
    private EntityManager em;

    public ProductFacade() {
    }

    private void create(PppProducts entity) {
        em.persist(entity);
    }

    private void edit(PppProducts entity) {
        em.merge(entity);
    }

    private PppProducts find(int id) {
        return em.find(PppProducts.class, id);
    }

    private PppProducts DTO2DAO(ProductDTO productDto) {
        if (productDto == null) {
            return null;
        }

        PppProducts product = new PppProducts();
        product.setProductid(productDto.getProductId());
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setImage(productDto.getImage());
        product.setPrice(productDto.getPrice());
        product.setActive(productDto.isActive());
        product.setCategory(productDto.getCategory());

        return product;
    }

    private ProductDTO DAO2DTO(PppProducts product) {
        if (product == null) {
            return null;
        }

        return new ProductDTO(product.getProductid(), product.getName(), product.getDescription(), product.getImage(), product.getActive(), product.getPrice(), product.getCategory());
    }

    private List<PppProducts> getSearchedProducts(String searchTerm, String category) {
        if (category == null) {
            category = "";
        }
        if (searchTerm == null) {
            searchTerm = "";
        }
        category = "%" + category + "%";
        searchTerm = "%" + searchTerm + "%";

        return em.createQuery("SELECT p FROM PppProducts p WHERE p.name LIKE :searchTerm AND p.category LIKE :category", PppProducts.class)
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
                .sorted(priceAscending ? Comparator.comparingDouble(PppProducts::getPrice) : Comparator.comparingDouble(PppProducts::getPrice).reversed())
                .map(p -> DAO2DTO(p))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> adminSearchProducts(String searchTerm, boolean priceAscending, String category) {
        return getSearchedProducts(searchTerm, category)
                .stream()
                .sorted(priceAscending ? Comparator.comparingDouble(PppProducts::getPrice) : Comparator.comparingDouble(PppProducts::getPrice).reversed())
                .map(p -> DAO2DTO(p))
                .collect(Collectors.toList());
    }

    @Override
    public boolean addProduct(ProductDTO productDto) {
        if (productDto == null) {
            return false;
        }
        if (productDto.getProductId() != null) {
            return false;
        }

        create(DTO2DAO(productDto));

        return true;
    }

    @Override
    public boolean updateProduct(ProductDTO productDto) {
        if (productDto == null || find(productDto.getProductId()) == null) {
            return false;
        }

        edit(DTO2DAO(productDto));

        return true;
    }
}
