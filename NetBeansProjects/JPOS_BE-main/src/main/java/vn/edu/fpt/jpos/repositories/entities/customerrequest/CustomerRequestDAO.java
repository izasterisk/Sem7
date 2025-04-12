package vn.edu.fpt.jpos.repositories.entities.customerrequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import vn.edu.fpt.jpos.core.DBContext;
import vn.edu.fpt.jpos.repositories.entities.IDao;
import vn.edu.fpt.jpos.repositories.enums.CustomerRequestStatusEnum;

public class CustomerRequestDAO implements IDao<CustomerRequestDTO, CustomerRequestERROR> {

    private static final String GETS = "SELECT [id], [cus_id], [description], [time], [status] FROM [dbo].[CustomerRequests]";
    private static final String GET = "SELECT [id], [cus_id], [description], [time], [status] FROM [dbo].[CustomerRequests] WHERE [id] = ?";
    private static final String POST = "INSERT INTO [dbo].[CustomerRequests] ([cus_id], [description]) VALUES (?, ?)";
    private static final String PUT = "UPDATE [dbo].[CustomerRequests] SET [cus_id] = ?, [description] = ?, [time] = ?, [status] = ? WHERE [id] = ?";
    private static final String DELETE = "DELETE FROM [dbo].[CustomerRequests] WHERE [id] = ?";
    private static CustomerRequestDAO instance;
    private DBContext context = DBContext.getInstance();

    private CustomerRequestDAO() {
    }

    public static CustomerRequestDAO getInstance() {
        if (instance == null) {
            instance = new CustomerRequestDAO();
        }
        return instance;
    }

    @Override
    public List<CustomerRequestDTO> gets() throws CustomerRequestERROR {
        List<CustomerRequestDTO> list = new ArrayList<>();
        try (Connection con = context.getConnection(); PreparedStatement stm = con.prepareStatement(GETS); ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                String id = rs.getString("id");
                String cusId = rs.getString("cus_id");
                String description = rs.getString("description");
                Timestamp time = rs.getTimestamp("time");
                CustomerRequestStatusEnum status = CustomerRequestStatusEnum.getStatus(rs.getString("status"));
                list.add(new CustomerRequestDTO(id, cusId, description, time, status));
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new CustomerRequestERROR(ex.getMessage());
        }
        return list;
    }

    @Override
    public CustomerRequestDTO get(String id) throws CustomerRequestERROR {
        CustomerRequestDTO item = null;
        try (Connection con = context.getConnection(); PreparedStatement stm = con.prepareStatement(GET)) {
            stm.setString(1, id);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    String cusId = rs.getString("cus_id");
                    String description = rs.getString("description");
                    Timestamp time = rs.getTimestamp("time");
                    CustomerRequestStatusEnum status = CustomerRequestStatusEnum.getStatus(rs.getString("status"));
                    item = new CustomerRequestDTO(id, cusId, description, time, status);
                } else {
                    throw new CustomerRequestERROR("Customer request does not exist.");
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new CustomerRequestERROR(ex.getMessage());
        }
        return item;
    }

    @Override
    public CustomerRequestDTO post(CustomerRequestDTO item) throws CustomerRequestERROR {
        try (Connection con = context.getConnection(); PreparedStatement stm = con.prepareStatement(POST)) {
            stm.setString(1, item.getCusId().trim());
            stm.setString(2, item.getDescription());
            if (stm.executeUpdate() > 0) {
                return item;
            } else {
                throw new CustomerRequestERROR("Cannot add new customer request");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new CustomerRequestERROR(ex.getMessage());
        }
    }

    @Override
    public CustomerRequestDTO put(CustomerRequestDTO item) throws CustomerRequestERROR {
        try (Connection con = context.getConnection(); PreparedStatement stm = con.prepareStatement(PUT)) {
            stm.setString(1, item.getCusId());
            stm.setString(2, item.getDescription());
            stm.setTimestamp(3, new Timestamp(item.getTime().getTime()));
            stm.setString(4, item.getStatus().toString());
            stm.setString(5, item.getId());
            if (stm.executeUpdate() > 0) {
                return item;
            } else {
                throw new CustomerRequestERROR("Cannot update this customer request");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new CustomerRequestERROR(ex.getMessage());
        }
    }

    @Override
    public CustomerRequestDTO delete(CustomerRequestDTO item) throws CustomerRequestERROR {
        try (Connection con = context.getConnection(); PreparedStatement stm = con.prepareStatement(DELETE)) {
            stm.setString(1, item.getId());
            if (stm.executeUpdate() > 0) {
                return item;
            } else {
                throw new CustomerRequestERROR("Cannot delete this customer request");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new CustomerRequestERROR(ex.getMessage());
        }
    }
}
