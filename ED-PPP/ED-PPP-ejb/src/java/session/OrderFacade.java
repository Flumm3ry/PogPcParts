/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.OrderDTO;
import javax.ejb.Stateless;

/**
 *
 * @author jerem
 */
@Stateless
public class OrderFacade implements OrderFacadeRemote {

    @Override
    public void addOrder(OrderDTO orderDto) {
    }

    @Override
    public OrderDTO[] getOrdersByUserId(int userId) {
        return null;
    }
}
