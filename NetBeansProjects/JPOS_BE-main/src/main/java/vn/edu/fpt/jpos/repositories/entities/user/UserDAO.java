package vn.edu.fpt.jpos.repositories.entities.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import vn.edu.fpt.jpos.core.DBContext;
import vn.edu.fpt.jpos.repositories.entities.IDao;
import vn.edu.fpt.jpos.repositories.enums.UserRoleEnum;
import vn.edu.fpt.jpos.repositories.enums.UserStatusEnum;

public class UserDAO implements IDao<UserDTO, UserERROR> {

    private static final String GETS
            = "SELECT [id],[username],[fullname],[email],[password], "
            + "       [phone],[address],[role],[status] "
            + "FROM [dbo].[Users]";
    private static final String GET
            = "SELECT [id],[username],[fullname],[email],[password], "
            + "       [phone],[address],[role],[status] "
            + "FROM [dbo].[Users] "
            + "WHERE [id] = ?";
    private static final String POST
            = "INSERT INTO [dbo].[Users]"
            + "    (username, fullname, email, password, phone, address, role)"
            + "VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String PUT
            = "UPDATE [dbo].[Users]"
            + "   SET [username] = ?"
            + "      ,[fullname] = ?"
            + "      ,[email] = ?"
            + "      ,[password] = ?"
            + "      ,[phone] = ?"
            + "      ,[address] = ?"
            + "      ,[role] = ?"
            + "      ,[status] = ? "
            + " WHERE [id] = ?";
    private static final String DELETE
            = "UPDATE [dbo].[Users]"
            + "   SET [status] = \'Removed\'"
            + " WHERE [id] = ?";
    private static UserDAO instance;
    private DBContext context = DBContext.getInstance();

    private UserDAO() {
    }

    public static UserDAO getInstance() {
        if (instance == null) {
            instance = new UserDAO();
        }
        return instance;
    }

    @Override
    public List<UserDTO> gets() throws UserERROR {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<UserDTO> list = new ArrayList<>();
        try {
            con = context.getConnection();
            stm = con.prepareStatement(GETS);
            rs = stm.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                String username = rs.getString("username");
                String fullname = rs.getString("fullname");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                UserRoleEnum role = UserRoleEnum.getRole(rs.getString("role").trim());
                UserStatusEnum status = UserStatusEnum.getStatus(rs.getString("status"));
                list.add(new UserDTO(id, username, fullname, email, password, phone, address, role, status));
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new UserERROR(ex.getMessage());
        }
        return list;
    }

    @Override
    public UserDTO get(String id) throws UserERROR {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        UserDTO item = null;
        try {
            con = context.getConnection();
            stm = con.prepareCall(GET);
            stm.setString(1, id);
            rs = stm.executeQuery();
            if (rs.next()) {
                String username = rs.getString("username");
                String fullname = rs.getString("fullname");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                UserRoleEnum role = UserRoleEnum.valueOf(rs.getString("role"));
                UserStatusEnum status = UserStatusEnum.valueOf(rs.getString("status"));
                item = new UserDTO(id, username, fullname, email, password, phone, address, role, status);
            } else {
                throw new UserERROR("User does not exist.");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new UserERROR(ex.getMessage());
        }
        return item;
    }

    @Override
    public UserDTO post(UserDTO item) throws UserERROR {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = context.getConnection();
            stm = con.prepareCall(POST);
            stm.setString(1, item.getUsername());
            stm.setString(2, item.getFullname());
            stm.setString(3, item.getEmail());
            stm.setString(4, item.getPassword());
            stm.setString(5, item.getPhone());
            stm.setString(6, item.getAddress());
            stm.setString(7, item.getRole().toString());
            boolean res = stm.executeUpdate() > 0;
            if (res) {
                return item;
            } else {
                throw new UserERROR("Cannot register new user");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new UserERROR(ex.getMessage());
        }
    }

    @Override
    public UserDTO put(UserDTO item) throws UserERROR {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = context.getConnection();
            stm = con.prepareCall(PUT);
            stm.setString(1, item.getUsername());
            stm.setString(2, item.getFullname());
            stm.setString(3, item.getEmail());
            stm.setString(4, item.getPassword());
            stm.setString(5, item.getPhone());
            stm.setString(6, item.getAddress());
            stm.setString(7, item.getRole().toString());
            stm.setString(8, item.getStatus().toString());
            stm.setString(9, item.getId());
            boolean res = stm.executeUpdate() > 0;
            if (res) {
                return item;
            } else {
                throw new UserERROR("Cannot update this user");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new UserERROR(ex.getMessage());
        }
    }

    @Override
    public UserDTO delete(UserDTO item) throws UserERROR {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = context.getConnection();
            stm = con.prepareCall(DELETE);
            stm.setString(1, item.getId());
            boolean res = stm.executeUpdate() > 0;
            if (res) {
                return item;
            } else {
                throw new UserERROR("Cannot remove this user");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new UserERROR(ex.getMessage());
        }
    }
}
