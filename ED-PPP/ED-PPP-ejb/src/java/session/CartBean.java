/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.CartItemDTO;
import javax.ejb.Stateful;

/**
 *
 * @author jerem
 */
@Stateful
public class CartBean implements CartBeanRemote {

    @Override
    public void addCartItem(CartItemDTO cartItemDto) {
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public void removeCartItem(CartItemDTO cartItemDto) {
    }

    @Override
    public CartItemDTO[] getCart() {
        return null;
    }
    
    
}
