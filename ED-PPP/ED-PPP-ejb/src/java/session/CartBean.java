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
        cart.add(new CartItemDTO(2, "Ant", 6000));
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
    public boolean removeCartItem(int productId) {
        try {
            // will return true if any items were deleted
            return cart.removeIf(ci -> ci.getProductId() == productId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public List<CartItemDTO> getCartItems() {
        return cart;
    }

    public List<CartItemDTO> getCart() {
        return cart;
    }

    public void setCart(List<CartItemDTO> cart) {
        this.cart = cart;
    }

    @Override
    public boolean clearCart() {
        cart = new ArrayList();
        
        return true;
    }
}
