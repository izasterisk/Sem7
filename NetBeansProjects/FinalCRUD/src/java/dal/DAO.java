/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import Model.Category;
import Model.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class DAO extends DBContext {

    public List<Category> getAll() {
        List<Category> list = new ArrayList<>();
        String sql = "select * from Categories";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Category c = new Category(rs.getInt("id"), rs.getString("name"), rs.getString("describe"));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public Category getCategoryByID(int id) {
        String sql = "select * from Categories where id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Category c = new Category(rs.getInt("id"), rs.getString("name"),
                        rs.getString("describe"));
                return c;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Product> getNewProducts() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT TOP 3 [id]\n"
                + ",[name]\n"
                + ",[quantity]\n"
                + ",[price]\n"
                + ",[releaseDate]\n"
                + ",[describe]\n"
                + ",[image]\n"
                + ",[cid]\n"
                + " FROM [dbo].[Products]\n"
                + " order by releaseDate desc";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getString("id"));
                p.setName(rs.getString("name"));
                p.setQuantity(rs.getInt("quantity"));
                p.setPrice(rs.getDouble("price"));
                p.setReleaseDate(rs.getString("releaseDate"));
                p.setDescribe(rs.getString("describe"));
                p.setImage(rs.getString("image"));
                Category c = getCategoryByID(rs.getInt("cid"));
                p.setCategory(c);
                list.add(p);
            }
        }catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    
    public List<Product> getOldProducts() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT TOP 3 [id]\n"
                + ",[name]\n"
                + ",[quantity]\n"
                + ",[price]\n"
                + ",[releaseDate]\n"
                + ",[describe]\n"
                + ",[image]\n"
                + ",[cid]\n"
                + " FROM [dbo].[Products]\n"
                + " order by releaseDate";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getString("id"));
                p.setName(rs.getString("name"));
                p.setQuantity(rs.getInt("quantity"));
                p.setPrice(rs.getDouble("price"));
                p.setReleaseDate(rs.getString("releaseDate"));
                p.setDescribe(rs.getString("describe"));
                p.setImage(rs.getString("image"));
                Category c = getCategoryByID(rs.getInt("cid"));
                p.setCategory(c);
                list.add(p);
            }
        }catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    
    
}
