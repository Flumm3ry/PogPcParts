/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.OrderDTO;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author jerem
 */
@Remote
public interface OrderFacadeRemote {

    boolean addOrder(OrderDTO orderDto);

    List<OrderDTO> getOrdersByUserId(int userId);
    
}
