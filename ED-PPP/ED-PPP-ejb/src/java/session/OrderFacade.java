/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.CartItemDTO;
import entity.OrderDTO;
import entity.PppOrders;
import entity.PppProductOrder;
import entity.PppProducts;
import entity.PppUsers;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jerem
 */
@DeclareRoles({"ADMIN", "USER"})
@Stateless
public class OrderFacade implements OrderFacadeRemote {

    @PersistenceContext
    private EntityManager em;

    public OrderFacade() {
    }

    private void create(PppOrders entity) {
        em.persist(entity);
    }

    private PppOrders find(int id) {
        return em.find(PppOrders.class, id);
    }

    private PppUsers getUserById(int userId) {
        return em.find(PppUsers.class, userId);
    }

    private PppProducts getProductById(int productId) {
        return em.find(PppProducts.class, productId);
    }
    
    private List<PppOrders> findAllByUserId(int userId) {
        PppUsers user = em.find(PppUsers.class, userId);
        
        if (user == null) return new ArrayList();
        
        return user.getPppOrdersCollection().stream().collect(Collectors.toList());
    }

    private OrderDTO DAO2DTO(PppOrders order) {
        ArrayList<CartItemDTO> items = new ArrayList();

        order.getPppProductOrderCollection().forEach(po -> {
            items.add(new CartItemDTO(po.getProductid().getProductid(), po.getProductid().getName(), po.getQuantity()));
        });

        return new OrderDTO(order.getOrderid(), order.getUserid().getUserid(), order.getDate(), items);
    }

    private PppOrders DTO2DAO(OrderDTO orderDto) {

        ArrayList<PppProductOrder> products = new ArrayList();

        orderDto.getItems().forEach((i) -> {
            PppProductOrder productOrder = new PppProductOrder();

            productOrder.setProductid(getProductById(i.getProductId()));
            productOrder.setQuantity(i.getQuantity());

            products.add(productOrder);
        });

        PppOrders order = new PppOrders();

        order.setDate(orderDto.getOrderDate());
        order.setOrderid(orderDto.getOrderId());
        order.setUserid(getUserById(orderDto.getUserId()));

        order.setPppProductOrderCollection(products);

        return order;
    }

    @Override
    @RolesAllowed({"ADMIN", "USER"})
    public boolean addOrder(OrderDTO orderDto) {
        if (orderDto == null || orderDto.getOrderId() != null || orderDto.getItems().isEmpty()) {
            return false;
        }

        create(DTO2DAO(orderDto));

        return true;
    }

    @Override
    @RolesAllowed({"ADMIN", "USER"})
    public List<OrderDTO> getOrdersByUserId(int userId) {
        return findAllByUserId(userId)
            .stream()
            .map(o -> DAO2DTO(o))
            .collect(Collectors.toList());
    }
}
