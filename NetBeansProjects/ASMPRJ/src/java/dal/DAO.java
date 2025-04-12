/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import Model.Account;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Account
 */
public class DAO extends DBContext {

    public Account check(String username, String password) {
        String sql = "SELECT TOP (1000) [userID]\n"
                + "      ,[username]\n"
                + "      ,[password]\n"
                + "      ,[fullName]\n"
                + "      ,[email]\n"
                + "      ,[role]\n"
                + "  FROM [PianoMusicCenter].[dbo].[Users] where username=? and password=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Account a = new Account(rs.getInt("userID"), rs.getString("username"),
                        rs.getString("password"), rs.getString("fullName"),
                        rs.getString("email"), rs.getInt("role"));
                return a;
            }
        } catch (SQLException e) {
        }
        return null;
    }
}
