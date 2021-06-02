/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.ProductDTO;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author jerem
 */
@Remote
public interface ProductFacadeRemote {

    ProductDTO getProductById(int productId);

    List<ProductDTO> searchProducts(String searchTerm, boolean priceAscending, String category);

    List<ProductDTO> adminSearchProducts(String searchTerm, boolean priceAscending, String category);

    boolean addProduct(ProductDTO productDto);

    boolean updateProduct(ProductDTO productDto);
}
