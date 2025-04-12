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

/**
 *
 * @author vipha
 */
public class AccountDAO {

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

    // <editor-fold defaultstate="collapsed" desc="login Method"> 
    public Account login(String userName, String password) throws Exception {
        Account account = null;
        Connection cnn = null; //Khai báo một đối tượng Connection để kết nối đến cơ sở dữ liệu.
        PreparedStatement preStm = null; //khai bao doi tuong để thực hiện các truy vấn SQL có tham số.
        ResultSet rs = null; //Khai báo một đối tượng ResultSet để lưu trữ kết quả của truy vấn SQL.
        int AccountID, role;
        String fullName;
        
        try {
            cnn = getConnection(); //Lấy kết nối đến cơ sở dữ liệu bằng cách sử dụng một
            String sql = "select AccountID, fullName, [role] from Account where userName= ? and [Password]=?";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, userName);
            preStm.setString(2, password);
            rs = preStm.executeQuery();
            while (rs.next()) {
                AccountID = rs.getInt(1);
                fullName = rs.getString(2);
                role = rs.getInt(3);
                account = new Account(AccountID, userName, password, fullName, role);
            }//end while
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStm != null) {
                preStm.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
        return account;
    }//end login
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Register Method"> 
    public boolean Register(Account account) throws Exception {
        Connection cnn = null; //Khai báo một đối tượng Connection để kết nối đến cơ sở dữ liệu.
        PreparedStatement preStm = null; //khai bao doi tuong để thực hiện các truy vấn SQL có tham số.
        ResultSet rs = null;
        try {
            cnn = getConnection(); //Lấy kết nối đến cơ sở dữ liệu bằng cách sử dụng một
            String sqlRegister = "INSERT INTO Account VALUES (?, ?, ?, ?)";

            String sqlCheck = "Select * from Account where userName = ?";
            preStm = cnn.prepareStatement(sqlCheck);
            preStm.setString(1, account.getUserName());
            rs = preStm.executeQuery();
            if (!rs.next()) {
                preStm = cnn.prepareStatement(sqlRegister);
                preStm.setString(1, account.getUserName());
                preStm.setString(2, account.getPassword());
                preStm.setNString(3, account.getFullName());
                preStm.setInt(4, account.getRole());
                return preStm.executeUpdate() > 0;
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
        return false;
    }//end Register
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="ChangePassword Method">
    public boolean ChangePassword(Account account, String newPassword) throws Exception {
        PreparedStatement preStm = null;
        Connection cnn = null;
        try {
            cnn = getConnection();
            String sql = "Update Account Set [password]= ? where userName= ? And [password]=?";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, newPassword);
            preStm.setString(2, account.getUserName());
            preStm.setString(3, account.getPassword());
            return preStm.executeUpdate() > 0;
        } catch (Exception e) {
            throw e;
        } finally {
            if (preStm != null) {
                preStm.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
    }//end ChangePassword
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="getAccountList Method">
    public List<Account> getAccountList() throws Exception {
        String userName, password, fullName;
        int AccountID, role;
        Connection cnn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        List<Account> accountList = new ArrayList<>();
        try {
            cnn = getConnection();
            String sql = "select * from Account";
            preStm = cnn.prepareStatement(sql);
            rs = preStm.executeQuery();
            while (rs.next()) {
                AccountID = rs.getInt(1);
                userName = rs.getString(2);
                password = rs.getString(3);
                fullName = rs.getString(4);
                role = rs.getInt(5);
                Account account = new Account(AccountID, userName, password, fullName, role);
                accountList.add(account);
            }//end While
        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStm != null) {
                preStm.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
        if (accountList.isEmpty()) {
            return null;
        }
        return accountList;
    }//end getUsersList
    //</editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="getAccountByID Method">
    public Account getAccountByID(int AccountID) throws Exception {
        Account account = null;
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            cnn = getConnection();
            String sql = "SELECT * FROM Account where AccountID = ?";
            ps = cnn.prepareStatement(sql);
            ps.setInt(1, AccountID);
            rs = ps.executeQuery();
            if (rs.next()) {
                AccountID = rs.getInt(1);
                String userName = rs.getString(2);
                String password = rs.getString(3);
                String fullName = rs.getString(4);
                int role = rs.getInt(5);
                account = new Account(AccountID, userName, password, fullName, role);
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
        return account;
    }//end getUserByID
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="getAccountByName Method">
    public Account getAccountByName(String userName) throws Exception {
        Account account = null;
        Connection cnn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            cnn = getConnection();
            String sql = "SELECT AccountID ,[password], fullName, [role] FROM Account where userName = ?";
            ps = cnn.prepareStatement(sql);
            ps.setString(1, userName);
            rs = ps.executeQuery();
            if (rs.next()) {
                int AccountID = rs.getInt(1);
                userName = rs.getString(2);
                String password = rs.getString(3);
                String fullName = rs.getString(4);
                int role = rs.getInt(5);
                account = new Account(AccountID, userName, password, fullName, role);
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
        return account;
    }//end getUserByID
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="ChangeProfile Method">
    public boolean ChangeProfile(Account account) throws Exception {
        Connection cnn = null;
        PreparedStatement ps = null;

        try {
            cnn = getConnection();
            String sql = "Update Account Set fullName= ?, [password]= ?  where userName= ?";
            ps = cnn.prepareStatement(sql);
            ps.setString(1, account.getUserName());
            ps.setString(2, account.getFullName());
            ps.setString(3, account.getPassword());

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
