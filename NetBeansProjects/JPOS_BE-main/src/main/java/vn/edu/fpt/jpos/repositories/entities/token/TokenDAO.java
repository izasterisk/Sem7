package vn.edu.fpt.jpos.repositories.entities.token;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import vn.edu.fpt.jpos.core.DBContext;
import vn.edu.fpt.jpos.repositories.entities.IDao;

public class TokenDAO implements IDao<TokenDTO, TokenERROR> {

    private static final String GETS = "SELECT [access_token], [user_id], [user_role] FROM [dbo].[Tokens]";
    private static final String GET = "SELECT [access_token], [user_id], [user_role] FROM [dbo].[Tokens] WHERE [access_token] = ?";
    private static final String POST = "INSERT INTO [dbo].[Tokens] ([access_token], [user_id], [user_role]) VALUES (?, ?, ?)";
    private static final String PUT = "UPDATE [dbo].[Tokens] SET [user_id] = ?, [user_role] = ? WHERE [access_token] = ?";
    private static final String DELETE = "DELETE FROM [dbo].[Tokens] WHERE [access_token] = ?";
    private static TokenDAO instance;
    private DBContext context = DBContext.getInstance();

    private TokenDAO() {
    }

    public static TokenDAO getInstance() {
        if (instance == null) {
            instance = new TokenDAO();
        }
        return instance;
    }

    @Override
    public List<TokenDTO> gets() throws TokenERROR {
        List<TokenDTO> list = new ArrayList<>();
        try (Connection con = context.getConnection(); PreparedStatement stm = con.prepareStatement(GETS); ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                String accessToken = rs.getString("access_token");
                String userId = rs.getString("user_id");
                String userRole = rs.getString("user_role");
                list.add(new TokenDTO(accessToken, userId, userRole));
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new TokenERROR(ex.getMessage());
        }
        return list;
    }

    @Override
    public TokenDTO get(String accessToken) throws TokenERROR {
        TokenDTO item = null;
        try (Connection con = context.getConnection(); PreparedStatement stm = con.prepareStatement(GET)) {
            stm.setString(1, accessToken);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    String userId = rs.getString("user_id");
                    String userRole = rs.getString("user_role");
                    item = new TokenDTO(accessToken, userId, userRole);
                } else {
                    throw new TokenERROR("Token does not exist.");
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new TokenERROR(ex.getMessage());
        }
        return item;
    }

    @Override
    public TokenDTO post(TokenDTO item) throws TokenERROR {
        try (Connection con = context.getConnection(); PreparedStatement stm = con.prepareStatement(POST)) {
            stm.setString(1, item.getAccessToken());
            stm.setString(2, item.getUserId());
            stm.setString(3, item.getUserRole());
            if (stm.executeUpdate() > 0) {
                return item;
            } else {
                throw new TokenERROR("Cannot add new token");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new TokenERROR(ex.getMessage());
        }
    }

    @Override
    public TokenDTO put(TokenDTO item) throws TokenERROR {
        try (Connection con = context.getConnection(); PreparedStatement stm = con.prepareStatement(PUT)) {
            stm.setString(1, item.getUserId());
            stm.setString(2, item.getUserRole());
            stm.setString(3, item.getAccessToken());
            if (stm.executeUpdate() > 0) {
                return item;
            } else {
                throw new TokenERROR("Cannot update this token");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new TokenERROR(ex.getMessage());
        }
    }

    @Override
    public TokenDTO delete(TokenDTO item) throws TokenERROR {
        try (Connection con = context.getConnection(); PreparedStatement stm = con.prepareStatement(DELETE)) {
            stm.setString(1, item.getAccessToken());
            if (stm.executeUpdate() > 0) {
                return item;
            } else {
                throw new TokenERROR("Cannot delete this token");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new TokenERROR(ex.getMessage());
        }
    }
}
