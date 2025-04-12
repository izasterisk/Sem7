package vn.edu.fpt.jpos.repositories.entities.orderdetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import vn.edu.fpt.jpos.core.DBContext;
import vn.edu.fpt.jpos.repositories.entities.IDao;

public class OrderDetailDAO implements IDao<OrderDetailDTO, OrderDetailERROR> {

    private static final String GETS = "SELECT [order_id], [product_id], [description], [quantity], [price] FROM [dbo].[OrderDetails]";
    private static final String GET = "SELECT [order_id], [product_id], [description], [quantity], [price] FROM [dbo].[OrderDetails] WHERE [order_id] = ?";
    private static final String POST = "INSERT INTO [dbo].[OrderDetails] ([order_id], [product_id], [description], [quantity], [price]) VALUES (?, ?, ?, ?, ?)";
    private static final String PUT = "UPDATE [dbo].[OrderDetails] SET [product_id] = ?, [description] = ?, [quantity] = ?, [price] = ? WHERE [order_id] = ?";
    private static final String DELETE = "DELETE FROM [dbo].[OrderDetails] WHERE [order_id] = ?";
    private static OrderDetailDAO instance;
    private DBContext context = DBContext.getInstance();

    private OrderDetailDAO() {
    }

    public static OrderDetailDAO getInstance() {
        if (instance == null) {
            instance = new OrderDetailDAO();
        }
        return instance;
    }

    @Override
    public List<OrderDetailDTO> gets() throws OrderDetailERROR {
        List<OrderDetailDTO> list = new ArrayList<>();
        try (Connection con = context.getConnection(); PreparedStatement stm = con.prepareStatement(GETS); ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                String orderId = rs.getString("order_id");
                String productId = rs.getString("product_id");
                String description = rs.getString("description");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");
                list.add(new OrderDetailDTO(orderId, productId, description, quantity, price));
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new OrderDetailERROR(ex.getMessage());
        }
        return list;
    }

    @Override
    public OrderDetailDTO get(String orderId) throws OrderDetailERROR {
        OrderDetailDTO item = null;
        try (Connection con = context.getConnection(); PreparedStatement stm = con.prepareStatement(GET)) {
            stm.setString(1, orderId);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    String productId = rs.getString("product_id");
                    String description = rs.getString("description");
                    int quantity = rs.getInt("quantity");
                    double price = rs.getDouble("price");
                    item = new OrderDetailDTO(orderId, productId, description, quantity, price);
                } else {
                    throw new OrderDetailERROR("Order details do not exist.");
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new OrderDetailERROR(ex.getMessage());
        }
        return item;
    }

    @Override
    public OrderDetailDTO post(OrderDetailDTO item) throws OrderDetailERROR {
        try (Connection con = context.getConnection(); PreparedStatement stm = con.prepareStatement(POST)) {
            stm.setString(1, item.getOrderId());
            stm.setString(2, item.getProductId());
            stm.setString(3, item.getDescription());
            stm.setInt(4, item.getQuantity());
            stm.setDouble(5, item.getPrice());
            if (stm.executeUpdate() > 0) {
                return item;
            } else {
                throw new OrderDetailERROR("Cannot add new order details");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new OrderDetailERROR(ex.getMessage());
        }
    }

    @Override
    public OrderDetailDTO put(OrderDetailDTO item) throws OrderDetailERROR {
        try (Connection con = context.getConnection(); PreparedStatement stm = con.prepareStatement(PUT)) {
            stm.setString(1, item.getProductId());
            stm.setString(2, item.getDescription());
            stm.setInt(3, item.getQuantity());
            stm.setDouble(4, item.getPrice());
            stm.setString(5, item.getOrderId());
            if (stm.executeUpdate() > 0) {
                return item;
            } else {
                throw new OrderDetailERROR("Cannot update order details");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new OrderDetailERROR(ex.getMessage());
        }
    }

    @Override
    public OrderDetailDTO delete(OrderDetailDTO item) throws OrderDetailERROR {
        try (Connection con = context.getConnection(); PreparedStatement stm = con.prepareStatement(DELETE)) {
            stm.setString(1, item.getOrderId());
            if (stm.executeUpdate() > 0) {
                return item;
            } else {
                throw new OrderDetailERROR("Cannot delete order details");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new OrderDetailERROR(ex.getMessage());
        }
    }
}
