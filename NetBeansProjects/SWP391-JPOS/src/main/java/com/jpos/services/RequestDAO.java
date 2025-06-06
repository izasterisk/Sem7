package com.jpos.services;

import com.cowards.onlyarts.core.DBContext;
import com.cowards.onlyarts.repositories.request.RequestDTO;
import com.cowards.onlyarts.repositories.request.RequestERROR;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class provides data access operations for managing requests related to artworks.
 */
public class RequestDAO {

    private static RequestDAO instance;
    private static final DBContext DB = DBContext.getInstance();
    private static final String GET_ALL_REQUESTS_BY_CR = "SELECT [customer_id],[publisher_id],"
            + "[request_time],[description],[status],[request_id] FROM [dbo].[Requests] "
            + "WHERE [publisher_id] = ?";
    private static final String GET_ALL_REQUESTS_BY_CT = "SELECT [customer_id],[publisher_id],"
            + "[request_time],[description],[status],[request_id] FROM [dbo].[Requests] "
            + "WHERE [customer_id] = ?";
    private static final String GET_REQUEST_BY_ID = "SELECT [customer_id],[publisher_id],"
            + "[request_time],[description],[status],[request_id] FROM [dbo].[Requests] "
            + "WHERE [request_id] = ?";
    private static final String ADD_REQUEST = "INSERT INTO [dbo].[Requests] "
            + "([request_id],[customer_id],[publisher_id],[description],[status]) "
            + "VALUES (?,?,?,?,?)";
    private static final String UPDATE_REQUEST = "UPDATE [dbo].[Requests] "
            + "SET [description] = ?, [status] = ? WHERE request_id = ?";
    private static final String REMOVE_REQUEST = "DELETE FROM [dbo].[Requests] "
            + "WHERE request_id = ?";
    private static final String CHANGE_STATUS = "UPDATE [dbo].[Requests] "
            + "SET [status] = ? WHERE request_id = ?";

    /**
     * Gets the instance of RequestDAO using the singleton pattern. If the
     * instance is null, a new instance is created.
     *
     * @return The instance of RequestDAO.
     */
    public static RequestDAO getInstance() {
        if (instance == null) {
            instance = new RequestDAO();
        }
        return instance;
    }

    private void logError(String message, Exception ex) {
        Logger.getLogger(UserDAO.class.getName())
                .log(Level.SEVERE, message, ex);
    }

    /**
     * Retrieves all requests associated with a user based on their role.
     *
     * @param userId The ID of the user.
     * @param roleID The role of the user ("CR" for customer, "CT" for publisher).
     * @return A list of RequestDTO objects containing information about each request.
     */
    public List<RequestDTO> getAllRequest(String userId, String roleID) {
        List<RequestDTO> requestList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DB.getConnection();
            if ("CR".equals(roleID)) {
                stm = conn.prepareStatement(GET_ALL_REQUESTS_BY_CR);
            } else if ("CT".equalsIgnoreCase(roleID)) {
                stm = conn.prepareStatement(GET_ALL_REQUESTS_BY_CT);
            } else {
                return null;
            }
            stm.setString(1, userId);
            rs = stm.executeQuery();
            while (rs.next()) {
                RequestDTO request = new RequestDTO();
                request.setRequestId(rs.getString("request_id"));
                request.setCustomerID(rs.getString("customer_id"));
                request.setPublisherId(rs.getString("publisher_id"));
                request.setRequestTime(new Date(rs.getTimestamp("request_time").getTime()));
                request.setDescription(rs.getString("description"));
                request.setStatus(rs.getInt("status"));
                requestList.add(request);
            }
        } catch (SQLException ex) {
            logError("Exception found on getAllRequest() method", ex);
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(stm);
        }
        return requestList;
    }

    /**
     * Retrieves a request by its ID.
     *
     * @param requestId The ID of the request to retrieve.
     * @return The RequestDTO object representing the request.
     * @throws RequestERROR if the request ID does not exist in the system.
     */
    public RequestDTO getRequestById(String requestId) throws RequestERROR {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        RequestDTO request = null;
        try {
            conn = DB.getConnection();
            stm = conn.prepareStatement(GET_REQUEST_BY_ID);
            stm.setString(1, requestId);
            rs = stm.executeQuery();
            if (rs.next()) {
                request = new RequestDTO();
                request.setRequestId(rs.getString("request_id"));
                request.setCustomerID(rs.getString("customer_id"));
                request.setPublisherId(rs.getString("publisher_id"));
                request.setRequestTime(rs.getDate("request_time"));
                request.setDescription(rs.getString("description"));
                request.setStatus(rs.getInt("status"));
            } else {
                throw new RequestERROR("This id does not exist in the system");
            }
        } catch (SQLException ex) {
            logError("Exception found on getUserById() method", ex);
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(stm);
        }
        return request;
    }

    /**
     * Adds a new request to the system.
     *
     * @param request The RequestDTO object representing the request to add.
     * @return True if the request is added successfully, otherwise false.
     */
    public boolean addRequest(RequestDTO request) {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DB.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(ADD_REQUEST);
                stm.setString(1, request.getRequestId());
                stm.setString(2, request.getCustomerID());
                stm.setString(3, request.getPublisherId());
                stm.setString(4, request.getDescription());
                stm.setInt(5, 0);
                check = stm.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            logError("Exception found on addRequest() method", e);
        } finally {
            DB.closeStatement(stm);
        }
        return check;

    }

    /**
     * Updates an existing request in the system.
     *
     * @param request The RequestDTO object representing the request to update.
     * @return True if the request is updated successfully, otherwise false.
     */
    public boolean updateRequest(RequestDTO request) {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DB.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(UPDATE_REQUEST);
                stm.setString(1, request.getDescription());
                if (request.isSeen()) {
                    stm.setInt(2, request.getStatus() ^ 0b1000);
                } else {
                    stm.setInt(2, request.getStatus());
                }
                stm.setString(3, request.getRequestId());

                check = stm.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            logError("Exception found on updateRequest() method", e);
        } finally {
            DB.closeStatement(stm);
        }
        return check;
    }

    /**
     * Removes a request from the system.
     *
     * @param requestId The ID of the request to remove.
     * @return True if the request is removed successfully, otherwise false.
     */
    public boolean removeRequest(String requestId) {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DB.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(REMOVE_REQUEST);
                stm.setString(1, requestId);
                check = stm.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            logError("Exception found on removeRequest() method", e);
        } finally {
            DB.closeStatement(stm);
        }
        return check;
    }

    /**
     * Changes the status of a request in the system.
     *
     * @param request The RequestDTO object representing the request.
     * @param state The state to set for the request status.
     * @return True if the status is changed successfully, otherwise false.
     */
    public boolean changeStatus(RequestDTO request, int state) {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DB.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(CHANGE_STATUS);
                stm.setInt(1, request.getStatus() ^ state);
                stm.setString(2, request.getRequestId());
                check = stm.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            logError("Exception found on changStatus() method", e);
        } finally {
            DB.closeStatement(stm);
        }
        return check;
    }

}
