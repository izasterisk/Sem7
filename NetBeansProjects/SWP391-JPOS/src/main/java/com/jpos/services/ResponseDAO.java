package com.jpos.services;

import com.cowards.onlyarts.core.DBContext;
import com.cowards.onlyarts.repositories.response.ResponseDTO;
import com.cowards.onlyarts.repositories.response.ResponseERROR;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class provides data access operations for managing responses related to requests.
 */
public class ResponseDAO {

    private static ResponseDAO instance;
    private static final DBContext DB = DBContext.getInstance();
    private static final String ADD_RESPONSE = "INSERT INTO [dbo].[Responses] ([request_id] ,"
            + "[response_id] ,[image] ,[description],[status]) "
            + "VALUES (?,?,?,?,?)";
    private static final String GET_RESPONSE_BY_ID = "SELECT [request_id] ,[response_id] ,"
            + "[image] ,[description] ,[response_time] ,[status] "
            + "FROM [dbo].[Responses] WHERE [response_id] = ?";
    private static final String GET_ALL_RESPONSE_BY_ID = "SELECT tb1.[request_id] ,"
            + "[response_id] ,[image] ,tb1.[description] ,[response_time] ,tb1.[status] "
            + "FROM [dbo].[Responses] tb1 LEFT JOIN  [dbo].[Requests] tb2 "
            + "ON tb1.request_id = tb2.request_id "
            + "WHERE tb2.publisher_id = ?";
    private static final String CHANGE_STATUS_RESPONSE = "UPDATE [dbo].[Responses] "
            + "SET [status] = ? WHERE response_id = ?";
    private static final String GET_ALL_RESPONSE_BY_CUSTOMER_ID = "SELECT tb1.[request_id] ,"
            + "[response_id] ,[image] ,tb1.[description] ,[response_time] ,tb1.[status] "
            + "FROM [dbo].[Responses] tb1 RIGHT JOIN  [dbo].[Requests] tb2 "
            + "ON tb1.request_id = tb2.request_id "
            + "WHERE tb2.customer_id = ?";

    /**
     * Private constructor for the ResponseDAO class. Prevents instantiation from
     * outside the class.
     */
    private ResponseDAO() {

    }

    /**
     * Gets the instance of ResponseDAO using the singleton pattern. If the
     * instance is null, a new instance is created.
     *
     * @return The instance of ResponseDAO.
     */
    public static ResponseDAO getInstance() {
        if (instance == null) {
            instance = new ResponseDAO();
        }
        return instance;
    }

    private void logError(String message, Exception ex) {
        Logger.getLogger(ResponseDAO.class.getName())
                .log(Level.SEVERE, message, ex);
    }

    /**
     * Adds a new response to the system.
     *
     * @param responseDTO The ResponseDTO object representing the response to add.
     * @return True if the response is added successfully, otherwise false.
     */
    public boolean addResponse(ResponseDTO responseDTO) {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DB.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(ADD_RESPONSE);
                stm.setString(1, responseDTO.getRequestId());
                stm.setString(2, responseDTO.getResponseId());
                stm.setString(3, responseDTO.getImage());
                stm.setString(4, responseDTO.getDescription());
                stm.setInt(5, responseDTO.getStatus());
                check = stm.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            logError("Exception found on addResponse() method", e);
        } finally {
            DB.closeStatement(stm);
        }
        return check;
    }

    /**
     * Retrieves a response by its ID.
     *
     * @param responseId The ID of the response to retrieve.
     * @return The ResponseDTO object representing the response.
     * @throws ResponseERROR if the response ID does not exist in the system.
     */
    public ResponseDTO getResponseById(String responseId) throws ResponseERROR {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ResponseDTO response = null;
        try {
            conn = DB.getConnection();
            stm = conn.prepareStatement(GET_RESPONSE_BY_ID);
            stm.setString(1, responseId);
            rs = stm.executeQuery();
            if (rs.next()) {
                response = new ResponseDTO();
                response.setRequestId(rs.getString("request_id"));
                response.setResponseId(rs.getString("response_id"));
                response.setImage(rs.getString("image"));
                response.setDescription(rs.getString("description"));
                response.setResponseTime(rs.getDate("response_time"));
                response.setStatus(rs.getInt("status"));
            } else {
                throw new ResponseERROR("This id does not exist in the system");
            }
        } catch (SQLException ex) {
            logError("Exception found on getUserById() method", ex);
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(stm);
        }
        return response;
    }

    /**
     * Retrieves all responses associated with a publisher based on their ID.
     *
     * @param userId The ID of the publisher.
     * @return A list of ResponseDTO objects containing information about each response.
     */
    public List<ResponseDTO> getAllResponseById(String userId) {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<ResponseDTO> responseList = new ArrayList<>();
        try {
            conn = DB.getConnection();
            stm = conn.prepareStatement(GET_ALL_RESPONSE_BY_ID);
            stm.setString(1, userId);
            rs = stm.executeQuery();
            while (rs.next()) {
                ResponseDTO response = new ResponseDTO();
                response.setRequestId(rs.getString("request_id"));
                response.setResponseId(rs.getString("response_id"));
                response.setImage(rs.getString("image"));
                response.setDescription(rs.getString("description"));
                response.setResponseTime(rs.getDate("response_time"));
                response.setStatus(rs.getInt("status"));
                responseList.add(response);
            }
        } catch (SQLException ex) {
            logError("Exception found on getAllResponseById() method", ex);
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(stm);
        }
        return responseList;
    }

    /**
     * Changes the status of a response in the system.
     *
     * @param response The ResponseDTO object representing the response.
     * @param state The state to set for the response status.
     * @return True if the status is changed successfully, otherwise false.
     */
    public boolean changeStatus(ResponseDTO response, int state) {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DB.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(CHANGE_STATUS_RESPONSE);
                stm.setInt(1, response.getStatus() ^ state);
                stm.setString(2, response.getResponseId());
                check = stm.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            logError("Exception found on changeStatus() method", e);
        } finally {
            DB.closeStatement(stm);
        }
        return check;

    }

    /**
     * Retrieves all responses associated with a customer based on their ID.
     *
     * @param userId The ID of the customer.
     * @return A list of ResponseDTO objects containing information about each response.
     */
    public List<ResponseDTO> getAllResponseByCustomerId(String userId) {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<ResponseDTO> responseList = new ArrayList<>();
        try {
            conn = DB.getConnection();
            stm = conn.prepareStatement(GET_ALL_RESPONSE_BY_CUSTOMER_ID);
            stm.setString(1, userId);
            rs = stm.executeQuery();
            while (rs.next()) {
                ResponseDTO response = new ResponseDTO();
                response.setRequestId(rs.getString("request_id"));
                response.setResponseId(rs.getString("response_id"));
                response.setImage(rs.getString("image"));
                response.setDescription(rs.getString("description"));
                response.setResponseTime(rs.getDate("response_time"));
                response.setStatus(rs.getInt("status"));
                responseList.add(response);
            }
        } catch (SQLException ex) {
            logError("Exception found on getAllResponseByCustomerId() method", ex);
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(stm);
        }
        return responseList;
    }

}
