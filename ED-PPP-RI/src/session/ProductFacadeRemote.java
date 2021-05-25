/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.ProductDTO;
import javax.ejb.Remote;

/**
 *
 * @author jerem
 */
@Remote
public interface ProductFacadeRemote {

    ProductDTO getProductById(int productId);

    ProductDTO[] getActiveProducts(boolean priceAscending, String category);

    ProductDTO[] searchProducts(String searchTerm);

    void addProduct(ProductDTO productDto);

    void updateProduct(ProductDTO ProductDTO);

    void deleteProduct(int productId);
    
}
