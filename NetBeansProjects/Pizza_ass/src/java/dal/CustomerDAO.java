/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.Customers;

/**
 *
 * @author vipha
 */
public class CustomerDAO {

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

    // <editor-fold defaultstate="collapsed" desc="getListCustomers Method">
    public List<Customers> getListCustomers() throws SQLException {
        List<Customers> listCustomer = new ArrayList<>();
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        AccountDAO accountDAO = new AccountDAO();

        int CustomerID;
        Account Account;
        String ContactName;
        String Address;
        String Phone;

        try {
            cnn = getConnection();
            String sql = "Select * from Customers";
            ps = cnn.prepareCall(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                CustomerID = rs.getInt(1);
                Account account = accountDAO.getAccountByID(rs.getInt(2));
                ContactName = rs.getNString(3);
                Address = rs.getNString(3);
                Phone = rs.getString(4);
                int role = rs.getInt(5);
                Customers customer = new Customers(CustomerID, account, ContactName, Address, Phone);
                listCustomer.add(customer);
            }
        } catch (Exception e) {
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
        if (listCustomer == null) {
            return null;
        }

        return listCustomer;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="getCustomersByID Method">
    public Customers getCustomersByID(int CustomerID) throws Exception {
        Customers customer = null;
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        AccountDAO accountDAO = new AccountDAO();
        try {
            cnn = getConnection();
            String sql = "Select * from Customers where CustomerID = ?";
            ps = cnn.prepareStatement(sql);
            ps.setInt(1, CustomerID);
            rs = ps.executeQuery();
            if (rs.next()) {
                CustomerID = rs.getInt(1);
                Account account = accountDAO.getAccountByID(rs.getInt(2));
                String ContactName = rs.getNString(3);
                String Address = rs.getNString(4);
                String Phone = rs.getString(5);
                customer = new Customers(CustomerID, account, ContactName, Address, Phone);
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
        return customer;
    }
    //</editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="getCustomersByAccount Method">
    public Customers getCustomersByAccount(Account account) throws Exception {
        Customers customer = null;
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        AccountDAO accountDAO = new AccountDAO();
        try {
            cnn = getConnection();
            String sql = "Select CustomerID, ContactName, [Address], Phone  from Customers where AccountID = ?";
            ps = cnn.prepareStatement(sql);
            ps.setInt(1, account.getAccountID());
            rs = ps.executeQuery();
            if (rs.next()) {
                int CustomerID = rs.getInt(1);
                String ContactName = rs.getNString(2);
                String Address = rs.getNString(3);
                String Phone = rs.getString(4);
                customer = new Customers(CustomerID, account, ContactName, Address, Phone);
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
        return customer;
    }
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="addCustomers Method"> 
    public boolean addCustomers(Customers customer) throws Exception {
        Connection cnn = null; //Khai báo một đối tượng Connection để kết nối đến cơ sở dữ liệu.
        PreparedStatement preStm = null; //khai bao doi tuong để thực hiện các truy vấn SQL có tham số.
        ResultSet rs = null;
        try {

            cnn = getConnection(); //Lấy kết nối đến cơ sở dữ liệu bằng cách sử dụng một
            String check = "Select * from Customers where AccountID = ?";
            preStm = cnn.prepareStatement(check);
            preStm.setInt(1, customer.getAccount().getAccountID());
            rs = preStm.executeQuery();
            
            if (!rs.next()) {
                String sql = "INSERT INTO Customers VALUES (?, ?, ?, ?)";
                preStm = cnn.prepareStatement(sql);
                preStm.setInt(1, customer.getAccount().getAccountID());
                preStm.setNString(2, customer.getContactName());
                preStm.setNString(3, customer.getAddress());
                preStm.setString(4, customer.getPhone());
                return preStm.executeUpdate() > 0;
            }else{
                return false;
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (preStm != null) {
                preStm.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
    }
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="ChangeProfile Method">
    public boolean ChangeProfile(Customers customers) throws Exception {
        Connection cnn = null;
        PreparedStatement ps = null;

        try {
            cnn = getConnection();
            String sql = "Update Customers Set ContactName = ? ,[Address] = ? ,Phone = ? where CustomerID = ?";
            ps = cnn.prepareStatement(sql);
            ps.setNString(1, customers.getContactName());
            ps.setNString(2, customers.getAddress());
            ps.setString(3, customers.getPhone());
            ps.setInt(4, customers.getCustomerID());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }

    }//end getUserByID
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="DeleteCustomer Method">
    public boolean DeleteCustomer(Customers customers) throws Exception {
        Connection cnn = null;
        PreparedStatement ps = null;

        try {
            cnn = getConnection();
            String sql = "Delete Customers where CustomerID = ?";
            ps = cnn.prepareStatement(sql);
            ps.setInt(1, customers.getCustomerID());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }

    }//end getUserByID
    //</editor-fold>

}
