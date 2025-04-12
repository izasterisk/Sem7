package vn.edu.fpt.jpos.repositories.entities.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import vn.edu.fpt.jpos.core.DBContext;
import vn.edu.fpt.jpos.repositories.entities.IDao;
import vn.edu.fpt.jpos.repositories.enums.ProductStatusEnum;

public class ProductDAO implements IDao<ProductDTO, ProductERROR> {

    private static final String GETS = "SELECT [id], [cate_id], [name], [description], [image], [price], [status] FROM [dbo].[Products]";
    private static final String GET = "SELECT [id], [cate_id], [name], [description], [image], [price], [status] FROM [dbo].[Products] WHERE [id] = ?";
    private static final String POST = "INSERT INTO [dbo].[Products] ([cate_id], [name], [description], [image], [price]) VALUES (?, ?, ?, ?, ?)";
    private static final String PUT = "UPDATE [dbo].[Products] SET [cate_id] = ?, [name] = ?, [description] = ?, [image] = ?, [price] = ?, [status] = ? WHERE [id] = ?";
    private static final String DELETE = "DELETE FROM [dbo].[Products] WHERE [id] = ?";
    private static ProductDAO instance;
    private DBContext context = DBContext.getInstance();

    private ProductDAO() {
    }

    public static ProductDAO getInstance() {
        if (instance == null) {
            instance = new ProductDAO();
        }
        return instance;
    }

    @Override
    public List<ProductDTO> gets() throws ProductERROR {
        List<ProductDTO> list = new ArrayList<>();
        try (Connection con = context.getConnection(); PreparedStatement stm = con.prepareStatement(GETS); ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                String id = rs.getString("id");
                String cate_id = rs.getString("cate_id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String image = rs.getString("image");
                float price = rs.getFloat("price");
                ProductStatusEnum status = ProductStatusEnum.getStatus(rs.getString("status"));
                list.add(new ProductDTO(id, cate_id, name, description, image, price, status));
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new ProductERROR(ex.getMessage());
        }
        return list;
    }

    @Override
    public ProductDTO get(String id) throws ProductERROR {
        ProductDTO item = null;
        try (Connection con = context.getConnection(); PreparedStatement stm = con.prepareStatement(GET)) {
            stm.setString(1, id);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    String cate_id = rs.getString("cate_id");
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    String image = rs.getString("image");
                    float price = rs.getFloat("price");
                    ProductStatusEnum status = ProductStatusEnum.getStatus(rs.getString("status"));
                    item = new ProductDTO(id, cate_id, name, description, image, price, status);
                } else {
                    throw new ProductERROR("Product does not exist.");
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new ProductERROR(ex.getMessage());
        }
        return item;
    }

    @Override
    public ProductDTO post(ProductDTO item) throws ProductERROR {
        try (Connection con = context.getConnection(); PreparedStatement stm = con.prepareStatement(POST)) {
            stm.setString(1, item.getCate_id());
            stm.setString(2, item.getName());
            stm.setString(3, item.getDescription());
            stm.setString(4, item.getImage());
            stm.setFloat(5, item.getPrice());
            if (stm.executeUpdate() > 0) {
                return item;
            } else {
                throw new ProductERROR("Cannot add new product");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new ProductERROR(ex.getMessage());
        }
    }

    @Override
    public ProductDTO put(ProductDTO item) throws ProductERROR {
        try (Connection con = context.getConnection(); PreparedStatement stm = con.prepareStatement(PUT)) {
            stm.setString(1, item.getCate_id());
            stm.setString(2, item.getName());
            stm.setString(3, item.getDescription());
            stm.setString(4, item.getImage());
            stm.setFloat(5, item.getPrice());
            stm.setString(6, item.getStatus().toString());
            stm.setString(7, item.getId());
            if (stm.executeUpdate() > 0) {
                return item;
            } else {
                throw new ProductERROR("Cannot update this product");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new ProductERROR(ex.getMessage());
        }
    }

    @Override
    public ProductDTO delete(ProductDTO item) throws ProductERROR {
        try (Connection con = context.getConnection(); PreparedStatement stm = con.prepareStatement(DELETE)) {
            stm.setString(1, item.getId());
            if (stm.executeUpdate() > 0) {
                return item;
            } else {
                throw new ProductERROR("Cannot delete this product");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new ProductERROR(ex.getMessage());
        }
    }
}
