/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.CartItemDTO;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remove;
import javax.ejb.Stateful;

/**
 *
 * @author jerem
 */
@Stateful
public class CartBean implements CartBeanRemote {

    private List<CartItemDTO> cart;

    public CartBean() {
        cart = new ArrayList();
    }
    
    @Remove
    public void remove() {
        cart = null;
    }
    
    @Override
    public boolean addCartItem(CartItemDTO cartItemDto) {
        if (cartItemDto == null) return false;
        
        int index = cart.indexOf(cartItemDto);
        
        if (index == -1) {
            return cart.add(cartItemDto);
        }
        
        try {
            CartItemDTO itemToUpdate = cart.get(index);
            itemToUpdate.setQuantity(itemToUpdate.getQuantity() + cartItemDto.getQuantity());
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return false;
    }

    @Override
    public boolean removeCartItem(CartItemDTO cartItemDto) {
        try {
            // will return true if any items were deleted
            return cart.removeIf(ci -> ci.getProductId() == cartItemDto.getProductId());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public List<CartItemDTO> getCart() {
        return cart;
    }

    @Override
    public boolean clearCart() {
        cart = new ArrayList();
        
        return true;
    }
}
