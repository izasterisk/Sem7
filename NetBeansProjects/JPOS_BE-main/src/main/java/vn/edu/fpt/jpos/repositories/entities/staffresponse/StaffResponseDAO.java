package vn.edu.fpt.jpos.repositories.entities.staffresponse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import vn.edu.fpt.jpos.core.DBContext;
import vn.edu.fpt.jpos.repositories.entities.IDao;
import vn.edu.fpt.jpos.repositories.enums.StaffResponseStatusEnum;

public class StaffResponseDAO implements IDao<StaffResponseDTO, StaffResponseERROR> {

    private static final String GETS = "SELECT [id], [request_id], [staff_id], [description], [time], [status] FROM [dbo].[StaffResponses]";
    private static final String GET = "SELECT [id], [request_id], [staff_id], [description], [time], [status] FROM [dbo].[StaffResponses] WHERE [id] = ?";
    private static final String POST = "INSERT INTO [dbo].[StaffResponses] ([request_id], [staff_id], [description]) VALUES (?, ?, ?)";
    private static final String PUT = "UPDATE [dbo].[StaffResponses] SET [request_id] = ?, [staff_id] = ?, [description] = ?, [time] = ?, [status] = ? WHERE [id] = ?";
    private static final String DELETE = "DELETE FROM [dbo].[StaffResponses] WHERE [id] = ?";
    private static StaffResponseDAO instance;
    private DBContext context = DBContext.getInstance();

    private StaffResponseDAO() {
    }

    public static StaffResponseDAO getInstance() {
        if (instance == null) {
            instance = new StaffResponseDAO();
        }
        return instance;
    }

    @Override
    public List<StaffResponseDTO> gets() throws StaffResponseERROR {
        List<StaffResponseDTO> list = new ArrayList<>();
        try (Connection con = context.getConnection(); PreparedStatement stm = con.prepareStatement(GETS); ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                String id = rs.getString("id");
                String requestId = rs.getString("request_id");
                String staffId = rs.getString("staff_id");
                String description = rs.getString("description");
                Timestamp time = rs.getTimestamp("time");
                StaffResponseStatusEnum status = StaffResponseStatusEnum.getStatus(rs.getString("status"));
                list.add(new StaffResponseDTO(id, requestId, staffId, description, time, status));
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new StaffResponseERROR(ex.getMessage());
        }
        return list;
    }

    @Override
    public StaffResponseDTO get(String id) throws StaffResponseERROR {
        StaffResponseDTO item = null;
        try (Connection con = context.getConnection(); PreparedStatement stm = con.prepareStatement(GET)) {
            stm.setString(1, id);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    String requestId = rs.getString("request_id");
                    String staffId = rs.getString("staff_id");
                    String description = rs.getString("description");
                    Timestamp time = rs.getTimestamp("time");
                    StaffResponseStatusEnum status = StaffResponseStatusEnum.getStatus(rs.getString("status"));
                    item = new StaffResponseDTO(id, requestId, staffId, description, time, status);
                } else {
                    throw new StaffResponseERROR("Staff response does not exist.");
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new StaffResponseERROR(ex.getMessage());
        }
        return item;
    }

    @Override
    public StaffResponseDTO post(StaffResponseDTO item) throws StaffResponseERROR {
        try (Connection con = context.getConnection(); PreparedStatement stm = con.prepareStatement(POST)) {
            stm.setString(1, item.getRequestId());
            stm.setString(2, item.getStaffId());
            stm.setString(3, item.getDescription());
            if (stm.executeUpdate() > 0) {
                return item;
            } else {
                throw new StaffResponseERROR("Cannot add new staff response");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new StaffResponseERROR(ex.getMessage());
        }
    }

    @Override
    public StaffResponseDTO put(StaffResponseDTO item) throws StaffResponseERROR {
        try (Connection con = context.getConnection(); PreparedStatement stm = con.prepareStatement(PUT)) {
            stm.setString(1, item.getRequestId());
            stm.setString(2, item.getStaffId());
            stm.setString(3, item.getDescription());
            stm.setTimestamp(4, new Timestamp(item.getTime().getTime()));
            stm.setString(5, item.getStatus().toString());
            stm.setString(6, item.getId());
            if (stm.executeUpdate() > 0) {
                return item;
            } else {
                throw new StaffResponseERROR("Cannot update this staff response");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new StaffResponseERROR(ex.getMessage());
        }
    }

    @Override
    public StaffResponseDTO delete(StaffResponseDTO item) throws StaffResponseERROR {
        try (Connection con = context.getConnection(); PreparedStatement stm = con.prepareStatement(DELETE)) {
            stm.setString(1, item.getId());
            if (stm.executeUpdate() > 0) {
                return item;
            } else {
                throw new StaffResponseERROR("Cannot delete this staff response");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new StaffResponseERROR(ex.getMessage());
        }
    }
}
