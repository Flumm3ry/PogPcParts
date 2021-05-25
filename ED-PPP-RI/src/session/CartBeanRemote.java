/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.CartItemDTO;
import javax.ejb.Remote;

/**
 *
 * @author jerem
 */
@Remote
public interface CartBeanRemote {

    void addCartItem(CartItemDTO cartItemDto);

    void removeCartItem(CartItemDTO cartItemDto);

    CartItemDTO[] getCart();
    
}
