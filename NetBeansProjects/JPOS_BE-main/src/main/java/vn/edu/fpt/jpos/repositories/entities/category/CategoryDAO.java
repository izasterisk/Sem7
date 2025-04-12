package vn.edu.fpt.jpos.repositories.entities.category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import vn.edu.fpt.jpos.core.DBContext;
import vn.edu.fpt.jpos.repositories.entities.IDao;

public class CategoryDAO implements IDao<CategoryDTO, CategoryERROR> {

    private static final String GETS
            = "SELECT [id], [name] FROM [dbo].[Categories]";
    private static final String GET
            = "SELECT [id], [name] FROM [dbo].[Categories] WHERE [id] = ?";
    private static final String POST
            = "INSERT INTO [dbo].[Categories] ([name]) VALUES (?)";
    private static final String PUT
            = "UPDATE [dbo].[Categories] SET [name] = ? WHERE [id] = ?";
    private static final String DELETE
            = "DELETE FROM [dbo].[Categories] WHERE [id] = ?";
    private static CategoryDAO instance;
    private DBContext context = DBContext.getInstance();

    private CategoryDAO() {
    }

    public static CategoryDAO getInstance() {
        if (instance == null) {
            instance = new CategoryDAO();
        }
        return instance;
    }

    @Override
    public List<CategoryDTO> gets() throws CategoryERROR {
        List<CategoryDTO> list = new ArrayList<>();
        try (Connection con = context.getConnection(); PreparedStatement stm = con.prepareStatement(GETS); ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                list.add(new CategoryDTO(id, name));
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new CategoryERROR(ex.getMessage());
        }
        return list;
    }

    @Override
    public CategoryDTO get(String id) throws CategoryERROR {
        CategoryDTO item = null;
        try (Connection con = context.getConnection(); PreparedStatement stm = con.prepareStatement(GET)) {
            stm.setString(1, id);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("name");
                    item = new CategoryDTO(id, name);
                } else {
                    throw new CategoryERROR("Category does not exist.");
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new CategoryERROR(ex.getMessage());
        }
        return item;
    }

    @Override
    public CategoryDTO post(CategoryDTO item) throws CategoryERROR {
        try (Connection con = context.getConnection(); PreparedStatement stm = con.prepareStatement(POST)) {
            stm.setString(1, item.getName());
            if (stm.executeUpdate() > 0) {
                return item;
            } else {
                throw new CategoryERROR("Cannot add new category");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new CategoryERROR(ex.getMessage());
        }
    }

    @Override
    public CategoryDTO put(CategoryDTO item) throws CategoryERROR {
        try (Connection con = context.getConnection(); PreparedStatement stm = con.prepareStatement(PUT)) {
            stm.setString(1, item.getName());
            stm.setString(2, item.getId());
            if (stm.executeUpdate() > 0) {
                return item;
            } else {
                throw new CategoryERROR("Cannot update this category");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new CategoryERROR(ex.getMessage());
        }
    }

    @Override
    public CategoryDTO delete(CategoryDTO item) throws CategoryERROR {
        try (Connection con = context.getConnection(); PreparedStatement stm = con.prepareStatement(DELETE)) {
            stm.setString(1, item.getId());
            if (stm.executeUpdate() > 0) {
                return item;
            } else {
                throw new CategoryERROR("Cannot delete this category");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new CategoryERROR(ex.getMessage());
        }
    }
}
