/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author jerem
 */
public class OrderDTO {
    private int orderId;
    private int userId;
    private Date orderDate;
    private ArrayList<CartItemDTO> items = new ArrayList<>();

    public OrderDTO(int orderId, int userId, Date orderDate, ArrayList<CartItemDTO> items) {
        this.orderId = orderId;
        this.userId = userId;
        this.orderDate = orderDate;
        this.items = items;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public ArrayList<CartItemDTO> getItems() {
        return items;
    }

    public void setItems(ArrayList<CartItemDTO> items) {
        this.items = items;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
