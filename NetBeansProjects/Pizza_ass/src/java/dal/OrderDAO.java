/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import model.Customers;
import model.Item;
import model.Orders;
import model.OrdersDetails;
import model.Products;

public class OrderDAO {

    // <editor-fold defaultstate="collapsed" desc="getConnection Method">
    public Connection getConnection() throws Exception {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionString = "jdbc:sqlserver://localhost:1433;database=PizzaStore;instanceName=SQL2014";
            //SQL Server Authentication
            Connection cnn = DriverManager.getConnection(connectionString, "sa", "12345");
            return cnn;
        } catch (ClassNotFoundException | SQLException ex) {
            throw ex;
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="getListOrder Method">
    public List<Orders> getListOrder() throws SQLException, Exception {
        List<Orders> listOrder = new ArrayList<>();
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        CustomerDAO customerDAO = new CustomerDAO();
        try {
            cnn = getConnection();
            String sql = "Select * from Orders";
            ps = cnn.prepareCall(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int OrderID = rs.getInt(1);
                Customers customer = customerDAO.getCustomersByID(rs.getInt(2));
                Date OrderDate = rs.getDate(3);
                Date ShippedDate = rs.getDate(4);
                float Fre = rs.getFloat(5);
                String ShipAddress = rs.getNString(6);
                boolean status = rs.getBoolean(7);
                Orders order = new Orders(OrderID, customer, OrderDate, ShippedDate,
                        Fre, ShipAddress, status);
                listOrder.add(order);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
        if (listOrder == null) {
            return null;
        }
        return listOrder;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="getListOrdersDetailsByID Method">
    public List<OrdersDetails> getListOrdersDetailsByID(int OrderID) throws SQLException, Exception {
        List<OrdersDetails> listOrderDetails = new ArrayList<>();
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        CustomerDAO customerDAO = new CustomerDAO();
        ProductsDAO productsDAO = new ProductsDAO();
        try {
            cnn = getConnection();
            String sql = "Select * from OrdersDetails where OrderID =? ";
            ps = cnn.prepareCall(sql);
            ps.setInt(1, OrderID);
            rs = ps.executeQuery();
            while (rs.next()) {
                Orders order = getOrdersByID(rs.getInt(1));
                Products products = productsDAO.getProductByID(rs.getInt(2));
                float price = rs.getFloat(3);
                int quantity = rs.getInt(4);
                OrdersDetails ordersDetails = new OrdersDetails(order, products, price, quantity);
                listOrderDetails.add(ordersDetails);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
        if (listOrderDetails == null) {
            return null;
        }
        return listOrderDetails;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="getOrdersByID Method">
    public Orders getOrdersByID(int OrderID) throws Exception {
        Orders order = null;
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        CustomerDAO customerDAO = new CustomerDAO();
        try {
            cnn = getConnection();
            String sql = "Select * from Orders where OrderID = ?";
            ps = cnn.prepareStatement(sql);
            ps.setInt(1, OrderID);
            rs = ps.executeQuery();
            if (rs.next()) {
                OrderID = rs.getInt(1);
                Customers customer = customerDAO.getCustomersByID(rs.getInt(2));
                java.util.Date OrderDate = new java.util.Date(rs.getTimestamp(3).getTime());
                java.util.Date ShippedDate = new java.util.Date(rs.getTimestamp(4).getTime());
                float Fre = rs.getFloat(5);
                String Address = rs.getNString(6);
                boolean status = rs.getBoolean(7);
                order = new Orders(OrderID, customer, OrderDate, ShippedDate,
                        Fre, Address, status);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
        return order;
    }
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="getOrdersByID Method">
    public OrdersDetails getOrdersDetailsByID(int OrderID) throws Exception {
        OrdersDetails orderDetails = null;
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ProductsDAO productsDAO = new ProductsDAO();
        try {
            cnn = getConnection();
            String sql = "Select * from OrdersDetails where OrderID = ?";
            ps = cnn.prepareStatement(sql);
            ps.setInt(1, OrderID);
            rs = ps.executeQuery();
            if (rs.next()) {
                Orders order = getOrdersByID(rs.getInt(1));
                Products product = productsDAO.getProductByID(rs.getInt(2));
                float unitPrice = rs.getFloat(3);
                int quantity = rs.getInt(4);
                orderDetails = new OrdersDetails(order, product, unitPrice, quantity);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
        return orderDetails;
    }
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="addOrder Method">
    public void addOrder(Customers customer) throws Exception {
        Orders order = null;
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        LocalDateTime currentDateTime = LocalDateTime.now();

        LocalDateTime ShippedDateTime = currentDateTime.plusHours(1);

        // Chuyển đổi thành Timestamp để có thể sử dụng cho cột kiểu DateTime
        Timestamp Orderdate = Timestamp.valueOf(currentDateTime);
        Timestamp Shippeddate = Timestamp.valueOf(ShippedDateTime);
        float Freight = (float) 3.2;
        try {
            cnn = getConnection();
            String sql = "INSERT INTO [dbo].[Orders] ([CustomerID],[OrderDate],[ShippedDate],[Freight],[ShipAddress],[Status]) "
                    + "VALUES( ?, ?, ?, ?, ?, ?)";
            ps = cnn.prepareStatement(sql);
            ps.setInt(1, customer.getCustomerID());
            ps.setTimestamp(2, Orderdate);
            ps.setTimestamp(3, Shippeddate);
            ps.setFloat(4, Freight);
            ps.setNString(5, customer.getAddress());
            ps.setBoolean(6, false);
            ps.executeUpdate();

        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
    }
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="OrderLast Method">
    public Orders OrderLast() throws Exception {
        Orders order = null;
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            cnn = getConnection();
            String sql = "select top 1 OrderID from Orders order by OrderID desc";
            ps = cnn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                int orderID = rs.getInt(1);
                order = getOrdersByID(orderID);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
        return order;
    }
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="addOrderDetails Method">
    public void addOrderDetails(Orders order, List<Item> listItem) throws Exception {
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            cnn = getConnection();
            for (Item item : listItem) {
                String sql = "INSERT INTO [dbo].[OrdersDetails]([OrderID],[ProductID],[UnitPrice],[Quantity]) VALUES(?,?,?,?)";
                ps = cnn.prepareStatement(sql);
                ps.setInt(1, order.getOrderID());
                ps.setInt(2, item.getProducts().getProductID());
                ps.setFloat(3, item.getUnitPrice());
                ps.setInt(4, item.getQuantity());
                ps.executeUpdate();
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
    }
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="getTotalMoney Method">
    public double getTotalMoney(int OrderID) throws Exception {
        double price = 0;
        for (OrdersDetails o : getListOrdersDetailsByID(OrderID)) {
            price += o.getQuantity() * o.getUnitPrice();
        }
        return price;
    }
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="updateOrderStatus Method">
    public void updateOrderStatus(int orderId, boolean status) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection(); // Phương thức để lấy kết nối đến cơ sở dữ liệu
            String sql = "UPDATE Orders SET status = ? WHERE orderID = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setBoolean(1, status);
            pstmt.setInt(2, orderId);

            pstmt.executeUpdate();
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="getTotalMoney Method">
    public double getTotalMoneyAll() throws Exception {
        double price = 0;
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            cnn = getConnection();
            String sql = "SELECT SUM(UnitPrice * Quantity) AS TotalRevenue "
                    + "FROM OrdersDetails";
            ps = cnn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                price = rs.getDouble("TotalRevenue");
            }
        } catch (Exception e) {
            throw e;
        }
        return price;
    }
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="getProductSales Method">
    public double getProductSales(Date startDate, Date endDate) throws Exception {
        double totalAll = 0;
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            cnn = getConnection();
            String sql = "SELECT\n"
                    + "    SUM(OD.UnitPrice * Quantity) AS TotalRevenue\n"
                    + "FROM\n"
                    + "    Orders O\n"
                    + "INNER JOIN OrdersDetails OD ON O.OrderID = OD.OrderID\n"
                    + "INNER JOIN Products P ON OD.ProductID = P.ProductID\n"
                    + "WHERE\n"
                    + "    O.OrderDate >= ? AND O.OrderDate <= ?";
            ps = cnn.prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(startDate.getTime()));
            ps.setDate(2, new java.sql.Date(endDate.getTime()));
            rs = ps.executeQuery();
            if (rs.next()) {
                totalAll = rs.getDouble(1);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
        return totalAll;
    }
//</editor-fold>

}
