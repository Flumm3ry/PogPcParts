/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.ProductDTO;
import javax.ejb.Stateless;

/**
 *
 * @author jerem
 */
@Stateless
public class ProductFacade implements ProductFacadeRemote {

    @Override
    public ProductDTO getProductById(int productId) {
        return null;
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
    public void addProduct(ProductDTO productDto) {
    }

    @Override
    public void updateProduct(ProductDTO ProductDTO) {
    }

    @Override
    public void deleteProduct(int productId) {
    }
    
    
}
