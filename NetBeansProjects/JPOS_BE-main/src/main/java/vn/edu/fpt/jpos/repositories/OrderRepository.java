package vn.edu.fpt.jpos.repositories;

import java.util.List;
import vn.edu.fpt.jpos.repositories.entities.order.OrderDAO;
import vn.edu.fpt.jpos.repositories.entities.order.OrderDTO;
import vn.edu.fpt.jpos.repositories.entities.order.OrderERROR;

public class OrderRepository {

    private static OrderRepository instance;

    private OrderRepository() {
    }

    public static OrderRepository getInstance() {
        if (instance == null) {
            instance = new OrderRepository();
        }
        return instance;
    }

    public OrderDTO addNew(OrderDTO order) throws OrderERROR {
        OrderDAO dao = OrderDAO.getInstance();
        return dao.post(order);
    }

    public List<OrderDTO> getAll() throws OrderERROR {
        OrderDAO dao = OrderDAO.getInstance();
        return dao.gets();
    }
}
