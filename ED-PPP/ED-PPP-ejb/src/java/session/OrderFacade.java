/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.OrderDTO;
import entity.PppOrder;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jerem
 */
@Stateless
public class OrderFacade implements OrderFacadeRemote {
    
    @PersistenceContext(unitName = "ED-PPP-ejbOrder")
    private EntityManager em;

    private void create(PppOrder entity) {
        em.persist(entity);
    }
    
    private PppOrder find(int id) {
        return em.find(PppOrder.class, id);
    }
    
    private PppOrder DTO2DAO(OrderDTO orderDto) {
        PppOrder order = new PppOrder();
        
        return order;
    } 

    @Override
    public boolean addOrder(OrderDTO orderDto) {
        if (orderDto == null || find(orderDto.getOrderId()) != null) return false;
        
        em.persist(DTO2DAO(orderDto));
        
        return true;
    }

    @Override
    public OrderDTO[] getOrdersByUserId(int userId) {
        return null;
    }
}
