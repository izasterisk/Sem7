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
import java.util.Base64;
import java.util.List;
import model.Categories;
import model.Products;
import model.Suppliers;

/**
 *
 * @author vipha
 */
public class ProductsDAO {

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

    // <editor-fold defaultstate="collapsed" desc="getProductList Method">
    public List<Products> getProductList() throws Exception {
        int ProductID, SupplierID, QuantityPerUnit;
        String ProductName, CategoryID, ProductImage, Description;
        float UnitPrice;
        
        Connection cnn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;

        List<Products> productList = new ArrayList<>();
        try {
            cnn = getConnection();
            String sql = "select * from Products";
            preStm = cnn.prepareStatement(sql);
            rs = preStm.executeQuery();
            while (rs.next()) {
                ProductID = rs.getInt(1);
                ProductName = rs.getString(2);
                SupplierID = rs.getInt(3);
                CategoryID =  rs.getString(4);
                QuantityPerUnit = rs.getInt(5);
                UnitPrice = rs.getFloat(6);
                ProductImage = rs.getString(7);
                Description = rs.getString(8);
                
                Suppliers supp = getSupplierByID(SupplierID);
                Categories cate = getCateById(CategoryID);
                Products product = new Products(ProductID, ProductName, supp,
                        cate, QuantityPerUnit, UnitPrice, ProductImage, Description);
                productList.add(product);
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
        if (productList.isEmpty()) {
            return null;
        }
        return productList;
    }//end getCarList
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="addProducts Method">
    public boolean addProducts(Products product) throws Exception {
        PreparedStatement ps = null;
        Connection cnn = null;
        try {
            cnn = getConnection();
            String sql = "INSERT INTO [Products] ([ProductName],[SupplierID],[CategoryID]"
                    + ",[QuantityPerUnit],[UnitPrice],[ProductImage],[Description])"
                    + " VALUES (?,?,?,?,?,?,?)";
            ps = cnn.prepareStatement(sql);
            ps.setString(1, product.getProductName());
            ps.setInt(2, product.getSupplier().getSupplierID());
            ps.setString(3, product.getCategory().getCategoryID());
            ps.setInt(4, product.getQuantityPerUnit());
            ps.setFloat(5, product.getUnitPrice());
            ps.setString(6, product.getProductImage());
            ps.setString(7, product.getDescription());
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
    }//end addMobies
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="updateProducts Method">
    public boolean updateProducts(Products product) throws Exception {
        PreparedStatement preStm = null;
        Connection cnn = null;

        try {
            cnn = getConnection();
            String sql = "UPDATE [Products]"
                    + "   SET "
                    + "      [ProductName] = ?"
                    + "      ,[SupplierID] = ?"
                    + "      ,[CategoryID] = ?"
                    + "      ,[QuantityPerUnit] = ?"
                    + "      ,[UnitPrice] = ?"
                    + "      ,[ProductImage] = ?"
                    + "      ,[Description] = ?"
                    + " WHERE [ProductID] = ?";
            preStm = cnn.prepareStatement(sql);
            preStm.setNString(1, product.getProductName());
            preStm.setInt(2, product.getSupplier().getSupplierID());
            preStm.setString(3, product.getCategory().getCategoryID());
            preStm.setInt(4, product.getQuantityPerUnit());
            preStm.setFloat(5, product.getUnitPrice());
            preStm.setString(6, product.getProductImage());
            preStm.setNString(7, product.getDescription());
            preStm.setInt(8, product.getProductID());   

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
    }//end updateMobies
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="deleteProducts Method">
    public boolean deleteProducts(int productId) throws Exception {
        PreparedStatement preStm = null;
        Connection cnn = null;
        try {
            cnn = getConnection();
            String sql = "delete from [Products] Where ProductID=?";
            preStm = cnn.prepareStatement(sql);
            preStm.setInt(1, productId);
            return preStm.executeUpdate() > 0;
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
    }//end deleteProducts
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="getProductByID Method">
    public Products getProductByID(int ProductID) throws Exception {
        int SupplierID, QuantityPerUnit;
        String ProductName, CategoryID, ProductImage, Description;
        float UnitPrice;
        Connection cnn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;

        Products product = null;
        try {
            cnn = getConnection();
            String sql = "select * from Products where ProductID = ?";
            preStm = cnn.prepareStatement(sql);
            preStm.setInt(1, ProductID);
            rs = preStm.executeQuery();
            while (rs.next()) {
                ProductID = rs.getInt(1);
                ProductName = rs.getString(2);
                SupplierID = rs.getInt(3);
                CategoryID =  rs.getString(4);
                QuantityPerUnit = rs.getInt(5);
                UnitPrice = rs.getFloat(6);
                ProductImage = rs.getString(7);
                Description = rs.getString(8);
                
                Suppliers supp = getSupplierByID(SupplierID);
                Categories cate = getCateById(CategoryID);
                product = new Products(ProductID, ProductName, supp,
                        cate, QuantityPerUnit, UnitPrice, ProductImage, Description);
                
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
        return product;
    }//end getProductByID
    //</editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="getCateById Method">
    public Categories getCateById(String CategoryID) throws Exception {
        String CategoryName, Description;
        Connection cnn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        Categories cate = null;
        
        try {
            cnn = getConnection();
            String sql = "select * from Categories where CategoryID =?";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, CategoryID);
            rs = preStm.executeQuery();
            while (rs.next()) {
                CategoryID = rs.getString(1);
                CategoryName = rs.getString(2);
                Description = rs.getString(3);

                cate = new Categories(CategoryID, CategoryName, Description);
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
        
        return cate;
    }//end getCateList
    //</editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="getSupplierByID Method">
    public Suppliers getSupplierByID(int SupplierID) throws Exception {
        
        String CompanyName, Address, Phone;
        Connection cnn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        
        Suppliers suppliers = null;

        
        try {
            cnn = getConnection();
            String sql = "select * from Suppliers where SupplierID = ?";
            preStm = cnn.prepareStatement(sql);
            preStm.setInt(1, SupplierID);
            rs = preStm.executeQuery();
            while (rs.next()) {
                SupplierID = rs.getInt(1);
                CompanyName = rs.getString(2);
                Address = rs.getString(3);
                Phone = rs.getString(4);

                suppliers = new Suppliers(SupplierID, CompanyName, Address, Phone);
                
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
       
        return suppliers;
    }//end getSuppliersList
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="getProductListByCateID Method">
    public List<Products> getProductListByCateID(String CateID) throws Exception {
        int ProductID, SupplierID, QuantityPerUnit;
        String ProductName, CategoryID, ProductImage, Description;
        float UnitPrice;
        Connection cnn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        List<Products> listProducts = new ArrayList<>();

        try {
            cnn = getConnection();
            String sql = "select * from Products where CategoryID = ?";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, CateID);
            rs = preStm.executeQuery();
            while (rs.next()) {
                ProductID = rs.getInt(1);
                ProductName = rs.getString(2);
                SupplierID = rs.getInt(3);
                CategoryID =  rs.getString(4);
                QuantityPerUnit = rs.getInt(5);
                UnitPrice = rs.getFloat(6);
                ProductImage = rs.getString(7);
                Description = rs.getString(8);
                
                Suppliers supp = getSupplierByID(SupplierID);
                Categories cate = getCateById(CategoryID);
                Products product = new Products(ProductID, ProductName, supp,
                        cate, QuantityPerUnit, UnitPrice, ProductImage, Description);
                listProducts.add(product);
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
        if (listProducts == null) {
            return null;
        }
        return listProducts;
    }//end getProductListByCateID
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="getCateList Method">
    public List<Categories> getCateList() throws Exception {
        String CategoryID, CategoryName, Description;
        Connection cnn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;

        List<Categories> cateList = new ArrayList<>();
        try {
            cnn = getConnection();
            String sql = "select * from Categories";
            preStm = cnn.prepareStatement(sql);
            rs = preStm.executeQuery();
            while (rs.next()) {
                CategoryID = rs.getString(1);
                CategoryName = rs.getString(2);
                Description = rs.getString(3);

                Categories cate = new Categories(CategoryID, CategoryName, Description);
                cateList.add(cate);
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
        if (cateList.isEmpty()) {
            return null;
        }
        return cateList;
    }//end getCateList
    //</editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="getSuppliersList Method">
    public List<Suppliers> getSuppliersList() throws Exception {
        int SupplierID;
        String CompanyName, Address, Phone;
        Connection cnn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;

        List<Suppliers> supplierList = new ArrayList<>();
        try {
            cnn = getConnection();
            String sql = "select * from Suppliers";
            preStm = cnn.prepareStatement(sql);
            rs = preStm.executeQuery();
            while (rs.next()) {
                SupplierID = rs.getInt(1);
                CompanyName = rs.getString(2);
                Address = rs.getString(3);
                Phone = rs.getString(4);

                Suppliers suppliers = new Suppliers(SupplierID, CompanyName, Address, Phone);
                supplierList.add(suppliers);
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
        if (supplierList.isEmpty()) {
            return null;
        }
        return supplierList;
    }//end getSuppliersList
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="SearchProducts Method">
    public List<Products> SearchProducts(String search) throws Exception {
        Products product = null;
        List<Products> searchList = new ArrayList<>();
        PreparedStatement preStm = null;
        Connection cnn = null;
        ResultSet rs = null;
        int ProductID, SupplierID, QuantityPerUnit;
        String ProductName, CategoryID, ProductImage, Description;
        float UnitPrice;

        try {
            cnn = getConnection();
            String sql = "select * from Products where ProductName like ?";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, "%" + search + "%");
            rs = preStm.executeQuery();
            while (rs.next()) {
                ProductID = rs.getInt(1);
                ProductName = rs.getString(2);
                SupplierID = rs.getInt(3);
                CategoryID =  rs.getString(4);
                QuantityPerUnit = rs.getInt(5);
                UnitPrice = rs.getFloat(6);
                ProductImage = rs.getString(7);
                Description = rs.getString(8);
                
                Suppliers supp = getSupplierByID(SupplierID);
                Categories cate = getCateById(CategoryID);
                product = new Products(ProductID, ProductName, supp,
                        cate, QuantityPerUnit, UnitPrice, ProductImage, Description);
                searchList.add(product);
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
        return searchList;
    }//end SearchMoblie
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="UpOrDowSortMoblieID Method">
    public List<Products> UpOrDowSortMoblieID(String sort, int id, String search, int low, int higt) throws Exception {
        Products product = null;
        List<Products> sortList = new ArrayList<>();
        PreparedStatement preStm = null;
        Connection cnn = null;
        ResultSet rs = null;
        int ProductID, SupplierID, QuantityPerUnit;
        String ProductName, CategoryID, ProductImage, Description;
        float UnitPrice;
        
        if (search.isEmpty()) {
            search = null;
        }

        try {
            cnn = getConnection();
            String up = "SELECT * FROM Products m Where m.ProductID = ? or m.ProductName like ? ORDER BY m.UnitPrice";
            String dow = "SELECT * FROM Products m Where m.ProductID = ? or m.ProductName like ? ORDER BY m.UnitPrice DESC ";
            String sql_Low_High = "SELECT * FROM Products m "
                    + "WHERE (m.ProductID = ? OR m.ProductName like ? )  AND m.UnitPrice > ? AND m.UnitPrice < ?";

            String sql;

            if (sort.equalsIgnoreCase("Price")) {
                sql = sql_Low_High;
                preStm = cnn.prepareStatement(sql);
                preStm.setInt(1, id);
                preStm.setString(2, "%" + search + "%");
                preStm.setInt(3, low);
                preStm.setInt(4, higt);

            } else if (sort.equalsIgnoreCase("up")) {
                if (sort.equalsIgnoreCase("up") && higt != 0) {
                    sql = sql_Low_High + "ORDER BY price";
                    preStm = cnn.prepareStatement(sql);
                    preStm.setInt(1, id);
                    preStm.setString(2, "%" + search + "%");
                    preStm.setInt(3, low);
                    preStm.setInt(4, higt);
                }else  {
                    sql = up;
                    preStm = cnn.prepareStatement(sql);
                    preStm.setInt(1, id);
                    preStm.setString(2, "%" + search + "%");
                   
                }
            } else if (sort.equalsIgnoreCase("dow")) {
                if (sort.equalsIgnoreCase("dow") && low != 0) {
                    sql = sql_Low_High + "ORDER BY price DESC";
                    preStm = cnn.prepareStatement(sql);
                    preStm.setInt(1, id);
                    preStm.setString(2, "%" + search + "%");
                    preStm.setInt(3, low);
                    preStm.setInt(4, higt);
                } 
                else {
                    if (sort.equalsIgnoreCase("dow") && higt != 0) {
                        sql = sql_Low_High + "ORDER BY price DESC";
                        preStm = cnn.prepareStatement(sql);
                        preStm.setInt(1, id);
                        preStm.setString(2, "%" + search + "%");
                        preStm.setInt(3, low);
                        preStm.setInt(4, higt);
                    }else{
                        sql = dow;
                        preStm = cnn.prepareStatement(sql);
                        preStm.setInt(1, id);
                        preStm.setString(2, "%" + search + "%");
                    }
                }
            }

            rs = preStm.executeQuery();
            while (rs.next()) {
                ProductID = rs.getInt(1);
                ProductName = rs.getString(2);
                SupplierID = rs.getInt(3);
                CategoryID =  rs.getString(4);
                QuantityPerUnit = rs.getInt(5);
                UnitPrice = rs.getFloat(6);
                ProductImage = rs.getString(7);
                Description = rs.getString(8);
                
                Suppliers supp = getSupplierByID(SupplierID);
                Categories cate = getCateById(CategoryID);
                product = new Products(ProductID, ProductName, supp,
                        cate, QuantityPerUnit, UnitPrice, ProductImage, Description);
                sortList.add(product);
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
        return sortList;
    }//end SearchMoblie
    //</editor-fold>
    
     public String encodeToBase64(String input) {
        // Chuyển đổi chuỗi thành mảng bytes
        byte[] inputBytes = input.getBytes();

        // Mã hóa mảng bytes thành chuỗi Base64
        String encodedString = Base64.getEncoder().encodeToString(inputBytes);

        return encodedString;
    }

    public String decodeFromBase64(String encodedString) {
        // Giải mã chuỗi Base64 thành mảng bytes
        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);

        // Chuyển đổi mảng bytes thành chuỗi
        String decodedString = new String(decodedBytes);

        return decodedString;
    }

}
