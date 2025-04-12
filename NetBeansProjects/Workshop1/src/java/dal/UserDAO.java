/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.User;

/**
 *
 * @author Admin
 */
public class UserDAO extends DBContext {

    public List<User> getAll() {
        List<User> list = new ArrayList<>();
        String sql = "select * from [dbo].[Users]";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                User u = new User(rs.getInt("id"), rs.getString("firstName"), rs.getString("lastName"),
                        rs.getString("email"),
                        rs.getString("username"), rs.getString("password"));
                list.add(u);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public User checkLogin(String username, String password) {
        String sql = "SELECT firstName, lastName, email "
                + "FROM [dbo].[Users] "
                + "WHERE username= ? "
                + "AND password= ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                User u = new User(rs.getInt("id"), rs.getString("firstName"), rs.getString("lastName"),
                        rs.getString("email"),
                        rs.getString("username"), rs.getString("password"));
                return u;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public User getUserByLName(String lastName) {
        String sql = "select * from Users where lastName=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, lastName);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                User u = new User(rs.getInt("id"), rs.getString("firstName"), rs.getString("lastName"),
                        rs.getString("email"),
                        rs.getString("username"), rs.getString("password"));
                return u;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public User getUserByID(int id) {
        String sql = "select * from Users where id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                User u = new User(rs.getInt("id"), rs.getString("firstName"), rs.getString("lastName"),
                        rs.getString("email"),
                        rs.getString("username"), rs.getString("password"));
                return u;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public void delete(int id) {
        String sql = "DELETE FROM [dbo].[Users] WHERE id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void update(User u) {
        String sql = "UPDATE [dbo].[Users]\n"
                + "   SET [firstName] = ?\n"
                + "      ,[lastName] = ?\n"
                + "      ,[Email] = ?\n"
                + " WHERE ID=?";
        try {
            PreparedStatement st = connection.prepareCall(sql);
            st.setInt(4, u.getId());
            st.setString(1, u.getFirstName());
            st.setString(2, u.getLastName());
            st.setString(3, u.getEmail());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

}
