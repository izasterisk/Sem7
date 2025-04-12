package vn.edu.fpt.jpos.repositories.entities.order;

import static com.fasterxml.jackson.databind.jsonFormatVisitors.JsonValueFormat.UUID;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import vn.edu.fpt.jpos.core.DBContext;
import vn.edu.fpt.jpos.repositories.entities.IDao;
import vn.edu.fpt.jpos.repositories.enums.OrderStatusEnum;

public class OrderDAO implements IDao<OrderDTO, OrderERROR> {

    private static final String GETS = "SELECT [id], [cus_id], [total_price], [time], [status] FROM [dbo].[Orders]";
    private static final String GET = "SELECT [id], [cus_id], [total_price], [time], [status] FROM [dbo].[Orders] WHERE [id] = ?";
    private static final String POST = "INSERT INTO [dbo].[Orders] ( [cus_id], [total_price],  [status]) VALUES (?, ?, ?)";
    private static final String PUT = "UPDATE [dbo].[Orders] SET [cus_id] = ?, [total_price] = ?, [time] = ?, [status] = ? WHERE [id] = ?";
    private static final String DELETE = "DELETE FROM [dbo].[Orders] WHERE [id] = ?";
    private static OrderDAO instance;
    private DBContext context = DBContext.getInstance();

    private OrderDAO() {
    }

    public static OrderDAO getInstance() {
        if (instance == null) {
            instance = new OrderDAO();
        }
        return instance;
    }

    @Override
    public List<OrderDTO> gets() throws OrderERROR {
        List<OrderDTO> list = new ArrayList<>();
        try (Connection con = context.getConnection(); PreparedStatement stm = con.prepareStatement(GETS); ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                String id = rs.getString("id");
                String customerId = rs.getString("cus_id");
                double totalPrice = rs.getDouble("total_price");
                Timestamp time = rs.getTimestamp("time");
                OrderStatusEnum status = OrderStatusEnum.getStatus(rs.getString("status"));
                list.add(new OrderDTO(id, customerId, totalPrice, time, status));
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new OrderERROR(ex.getMessage());
        }
        return list;
    }

    @Override
    public OrderDTO get(String id) throws OrderERROR {
        OrderDTO item = null;
        try (Connection con = context.getConnection(); PreparedStatement stm = con.prepareStatement(GET)) {
            stm.setString(1, id);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    String customerId = rs.getString("cus_id");
                    double totalPrice = rs.getDouble("total_price");
                    Timestamp time = rs.getTimestamp("time");
                    OrderStatusEnum status = OrderStatusEnum.getStatus(rs.getString("status"));
                    item = new OrderDTO(id, customerId, totalPrice, time, status);
                } else {
                    throw new OrderERROR("Order does not exist.");
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new OrderERROR(ex.getMessage());
        }
        return item;
    }

    @Override
    public OrderDTO post(OrderDTO item) throws OrderERROR {
        try (Connection con = context.getConnection(); PreparedStatement stm = con.prepareStatement(POST)) {
            stm.setString(1, item.getCustomerId());
            stm.setDouble(2, item.getTotalPrice());
            stm.setString(3, item.getStatus().toString());
            if (stm.executeUpdate() > 0) {
                return item;
            } else {
                throw new OrderERROR("Cannot create new order");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new OrderERROR(ex.getMessage());
        }
    }

    @Override
    public OrderDTO put(OrderDTO item) throws OrderERROR {
        try (Connection con = context.getConnection(); PreparedStatement stm = con.prepareStatement(PUT)) {
            stm.setString(1, item.getCustomerId());
            stm.setDouble(2, item.getTotalPrice());
            stm.setTimestamp(3, new Timestamp(item.getTime().getTime()));
            stm.setString(4, item.getStatus().toString());
            stm.setString(5, item.getId());
            if (stm.executeUpdate() > 0) {
                return item;
            } else {
                throw new OrderERROR("Cannot update this order");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new OrderERROR(ex.getMessage());
        }
    }

    @Override
    public OrderDTO delete(OrderDTO item) throws OrderERROR {
        try (Connection con = context.getConnection(); PreparedStatement stm = con.prepareStatement(DELETE)) {
            stm.setString(1, item.getId());
            if (stm.executeUpdate() > 0) {
                return item;
            } else {
                throw new OrderERROR("Cannot delete this order");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new OrderERROR(ex.getMessage());
        }
    }
}
