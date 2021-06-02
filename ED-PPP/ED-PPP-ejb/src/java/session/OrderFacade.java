/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.CartItemDTO;
import entity.OrderDTO;
import entity.PppOrder;
import entity.Product;
import entity.ProductOrder;
import entity.User;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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

    private User getUserById(int userId) {
        return em.find(User.class, userId);
    }

    private Product getProductById(int productId) {
        return em.find(Product.class, productId);
    }
    
    private List<PppOrder> findAllByUserId(int userId) {
        User user = em.find(User.class, userId);
        
        if (user == null) return new ArrayList();
        
        return user.getPppOrderCollection().stream().collect(Collectors.toList());
    }

    private OrderDTO DAO2DTO(PppOrder order) {
        ArrayList<CartItemDTO> items = new ArrayList();

        order.getProductOrderCollection().forEach(po -> {
            items.add(new CartItemDTO(po.getProductid().getProductid(), po.getProductid().getName(), po.getQuantity()));
        });

        return new OrderDTO(order.getOrderid(), order.getUserid().getUserid(), order.getDate(), items);
    }

    private PppOrder DTO2DAO(OrderDTO orderDto) {

        ArrayList<ProductOrder> products = new ArrayList();

        orderDto.getItems().forEach((i) -> {
            ProductOrder productOrder = new ProductOrder();

            productOrder.setProductid(getProductById(i.getProductId()));
            productOrder.setQuantity(i.getQuantity());

            products.add(productOrder);
        });

        PppOrder order = new PppOrder();

        order.setDate(orderDto.getOrderDate());
        order.setOrderid(orderDto.getOrderId());
        order.setUserid(getUserById(orderDto.getUserId()));

        order.setProductOrderCollection(products);

        return order;
    }

    @Override
    public boolean addOrder(OrderDTO orderDto) {
        if (orderDto == null || find(orderDto.getOrderId()) != null || orderDto.getItems().isEmpty()) {
            return false;
        }

        create(DTO2DAO(orderDto));

        return true;
    }

    @Override
    public List<OrderDTO> getOrdersByUserId(int userId) {
        return findAllByUserId(userId)
            .stream()
            .map(o -> DAO2DTO(o))
            .collect(Collectors.toList());
    }
}
