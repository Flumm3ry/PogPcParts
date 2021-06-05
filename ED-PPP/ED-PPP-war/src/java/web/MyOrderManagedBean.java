/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import entity.CartItemDTO;
import entity.OrderDTO;
import entity.UserDTO;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import session.CartBeanRemote;
import session.OrderFacadeRemote;
import session.UserFacadeRemote;

/**
 *
 * @author jerem
 */
@Named(value = "myOrderManagedBean")
@RequestScoped
public class MyOrderManagedBean implements Serializable {

    @EJB
    private UserFacadeRemote userFacade;

    @EJB
    private OrderFacadeRemote orderFacade;
    @EJB
    private CartBeanRemote cartBean;

    private List<CartItemDTO> cartItemList;
    private int quantity;
    private List<OrderDTO> orderList;

    public List<OrderDTO> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderDTO> orderList) {
        this.orderList = orderList;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<CartItemDTO> getCartItemList() {
        return cartItemList;
    }

    public void setCartItemList(List<CartItemDTO> cartItemList) {
        this.cartItemList = cartItemList;
    }

    /**
     * Creates a new instance of MyCartManagedBean
     */
    public MyOrderManagedBean() {
    }
    
    public String checkoutCart() {
        String loggedInEmail = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
        
        UserDTO userDto = userFacade.getUserByEmail(loggedInEmail);
        if (userDto == null) return "fail";
        
        OrderDTO orderDto = new OrderDTO(null, userDto.getUserId(), new java.sql.Date(System.currentTimeMillis()), cartBean.getCart());

        boolean addOrderResult = orderFacade.addOrder(orderDto);

        if (addOrderResult) {
            return cartBean.clearCart() ? "success" : "fail";
        }

        return "failure";
    }
    
    public String removeCartItem(int productId) {
        return cartBean.removeCartItem(productId) ? "success" : "fail";
    }
    
    public String addCartItem(int productId, String productName) {
        CartItemDTO cartItemDto = new CartItemDTO(productId, productName, quantity);
        
        return cartBean.addCartItem(cartItemDto) ? "success" : "fail";
    }
    
    public String getCartItems() {
        try {
            cartItemList = cartBean.getCart();
            return "success";
        } catch (Exception e) {
        }
        
        return "fail";
    }
    
    public String getOrdersList() {
        String loggedInEmail = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
        
        UserDTO userDto = userFacade.getUserByEmail(loggedInEmail);
        
        if (userDto == null) return "fail";
        
        try {
            orderList = orderFacade.getOrdersByUserId(userDto.getUserId());
            return "success";
        } catch (Exception e) {
        }
        
        return "fail";
    }
}
