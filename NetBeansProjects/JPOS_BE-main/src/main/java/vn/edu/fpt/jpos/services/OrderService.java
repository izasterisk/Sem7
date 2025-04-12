package vn.edu.fpt.jpos.services;

import java.util.List;
import vn.edu.fpt.jpos.repositories.OrderRepository;
import vn.edu.fpt.jpos.repositories.entities.order.OrderDTO;
import vn.edu.fpt.jpos.repositories.entities.order.OrderERROR;

public class OrderService {

    private static OrderService instance;

    private OrderService() {
    }

    public static OrderService getInstance() {
        if (instance == null) {
            instance = new OrderService();
        }
        return instance;
    }

    public OrderDTO add(OrderDTO order) throws OrderERROR {
        OrderRepository repo = OrderRepository.getInstance();
        return repo.addNew(order);
    }

    public List<OrderDTO> getAll() throws OrderERROR {
        OrderRepository repo = OrderRepository.getInstance();
        return repo.getAll();
    }

    public OrderDTO getOrdersTotal() throws OrderERROR {
        OrderRepository repo = OrderRepository.getInstance();
        List<OrderDTO> list = repo.getAll();
        OrderDTO order = new OrderDTO();
        list.forEach(o -> order.setTotalPrice(o.getTotalPrice() + order.getTotalPrice()));
        return order;
    }
}
