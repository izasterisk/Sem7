/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import Model.Admin;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class DAO extends DBContext {

    public Admin check(String username, String password) {
        String sql = "SELECT [Username]\n"
                + "      ,[Password]\n"
                + "      ,[role]\n"
                + "  FROM [Trading2024].[dbo].[Admin] where username=? and password=?";
        try {
            PreparedStatement st= connection.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, password);
            ResultSet rs= st.executeQuery();
            if(rs.next()){
                Admin a = new Admin(rs.getString("Username"),
                        rs.getString("Password"), rs.getInt("role"));
                return a;
            }
        } catch (SQLException e) {
        }
        return null;
    }
}
