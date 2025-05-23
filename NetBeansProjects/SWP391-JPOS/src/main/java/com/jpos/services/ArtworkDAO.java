package com.jpos.services;

import com.cowards.onlyarts.core.DBContext;
import com.cowards.onlyarts.repositories.artwork.ArtworkDTO;
import com.cowards.onlyarts.repositories.artwork.ArtworkERROR;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class provides data access methods for interacting with artwork data in
 * the database.
 */
public class ArtworkDAO {

    private static final DBContext context = DBContext.getInstance();
    private static ArtworkDAO instance;
        private static final String GET_ARTWORKS
            = "SELECT [artwork_id], [owner_id], [cate_id], [name], [description], "
            + "[artwork_image], [price], [released_date], [status]"
            + " FROM [dbo].[Artworks]";
    private static final String GET_TOP_10_ARTWORKS
            = "SELECT TOP (10) [artwork_id], [owner_id], [cate_id], [name], [description], "
            + "[artwork_image], [price], [released_date], [status]"
            + " FROM [dbo].[Artworks]"
            + " ORDER BY [released_date] DESC";
    private static final String ADD_ARTWORK = "INSERT INTO [dbo].[Artworks]"
            + "(artwork_id, owner_id, cate_id, name, "
            + "description, artwork_image, price, status) "
            + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_ARTWORK_PRICE
            = "UPDATE [dbo].[Artworks]"
            + "SET [price] = ?, [status] = 0 "
            + "WHERE [artwork_id] = ?";
    private static final String REMOVE_ARTWORK = "UPDATE Artworks "
            + "SET status = 1"
            + "WHERE artwork_id = ?";
    private static final String GET_ARTWORK
            = "SELECT [artwork_id], [owner_id], [cate_id], [name], [description], "
            + "[artwork_image], [price], [released_date], [status]"
            + " FROM [dbo].[Artworks] WHERE [artwork_id] = ?";
    private static final String UPDATE
            = "UPDATE [dbo].[Artworks]"
            + " SET [cate_id] = ?, [name] = ?, [description] = ?,"
            + " [artwork_image] = ?, [price] = ?, [status] = ?"
            + " WHERE [artwork_id] = ?";
    private static final String SEARCH_BY_TYPE = "SELECT aw.artwork_id, u.first_name, u.last_name, c.cate_name,\n"
            + "aw.name, aw.description, aw.artwork_image, aw.price, aw.released_date, aw.status FROM Artworks aw\n"
            + "LEFT JOIN Users u on aw.owner_id = u.user_id\n"
            + "LEFT JOIN Categories c on aw.cate_id = c.cate_id\n"
            + "WHERE c.cate_name like ? ";
    private static final String SEARCH_BY_NAME = "SELECT aw.artwork_id, u.first_name, u.last_name, c.cate_name, \n"
            + "aw.name, aw.description, aw.artwork_image, aw.price, aw.released_date, aw.status FROM Artworks aw\n"
            + "LEFT JOIN Users u on aw.owner_id = u.user_id\n"
            + "LEFT JOIN Categories c on aw.cate_id = c.cate_id\n"
            + "WHERE aw.name like ?";
    private static final String SEARCH_BY_CREATOR_NAME = "SELECT aw.artwork_id, u.first_name, u.last_name, c.cate_name,\n"
            + "aw.name, aw.description, aw.artwork_image, aw.price, aw.released_date, aw.status FROM Artworks aw\n"
            + "LEFT JOIN Users u on aw.owner_id = u.user_id\n"
            + "LEFT JOIN Categories c on aw.cate_id = c.cate_id\n"
            + "WHERE CONCAT(u.first_name, ' ', u.last_name) like ?";
    private static final String UPDATE_STATUS
            = "UPDATE [dbo].[Artworks]"
            + "SET [status] = ?"
            + " WHERE [artwork_id] = ?";

    private ArtworkDAO() {
    }

    private void logError(String message, Exception ex) {
        Logger.getLogger(ArtworkDAO.class.getName())
                .log(Level.SEVERE, message, ex);
    }

    public static ArtworkDAO getInstance() {
        if (instance == null) {
            instance = new ArtworkDAO();
        }
        return instance;
    }

    /**
     * Retrieves all artworks from the database.
     *
     * @return a list of ArtworkDTO objects representing the artworks.
     */
    public List<ArtworkDTO> getAll() {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<ArtworkDTO> artworks = new ArrayList<>();
        try {
            conn = context.getConnection();
            stm = conn.prepareStatement(GET_ARTWORKS + " ORDER BY [released_date] DESC");
            rs = stm.executeQuery();
            ArtworkDTO artwork = null;
            while (rs.next()) {
                artwork = new ArtworkDTO();
                artwork.setArtworkId(rs.getString(1));
                artwork.setOwnerId(rs.getString(2));
                artwork.setCateId(rs.getString(3));
                artwork.setName(rs.getString(4));
                artwork.setDescription(rs.getString(5));
                artwork.setArtworkImage(rs.getString(6));
                artwork.setPrice(rs.getFloat(7));
                artwork.setReleasedDate(rs.getDate(8));
                artwork.setStatus(rs.getInt(9));
                artworks.add(artwork);
            }
        } catch (SQLException ex) {
            logError("Exception found on getAll() method", ex);
        } finally {
            context.closeResultSet(rs);
            context.closeStatement(stm);
        }
        return artworks;
    }

    /**
     * Retrieves artworks owned by a specific user.
     *
     * @param ownerId the ID of the owner.
     * @return a list of ArtworkDTO objects representing the artworks owned by
     * the user.
     */
    public List<ArtworkDTO> getArtworkByOwner(String ownerId) {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<ArtworkDTO> artworks = new ArrayList<>();
        try {
            conn = context.getConnection();
            stm = conn.prepareStatement(GET_ARTWORKS + " WHERE [owner_id] = ? "
                    + "ORDER BY [released_date] DESC");
            stm.setString(1, ownerId);
            rs = stm.executeQuery();
            ArtworkDTO artwork = null;
            while (rs.next()) {
                artwork = new ArtworkDTO();
                artwork.setArtworkId(rs.getString(1));
                artwork.setOwnerId(rs.getString(2));
                artwork.setCateId(rs.getString(3));
                artwork.setName(rs.getString(4));
                artwork.setDescription(rs.getString(5));
                artwork.setArtworkImage(rs.getString(6));
                artwork.setPrice(rs.getFloat(7));
                artwork.setReleasedDate(rs.getDate(8));
                artwork.setStatus(rs.getInt(9));
                artworks.add(artwork);
            }
        } catch (SQLException ex) {
            logError("Exception found on getArtworkByOwner() method", ex);
        } finally {
            context.closeResultSet(rs);
            context.closeStatement(stm);
        }
        return artworks;
    }

    /**
     * Retrieves an artwork by its ID.
     *
     * @param artworkId the ID of the artwork.
     * @return the ArtworkDTO object representing the artwork.
     * @throws ArtworkERROR if the artwork with the specified ID does not exist.
     */
    public ArtworkDTO getArtwork(String artworkId) throws ArtworkERROR {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ArtworkDTO artwork = null;
        try {
            conn = context.getConnection();
            stm = conn.prepareStatement(GET_ARTWORK);
            stm.setString(1, artworkId);
            rs = stm.executeQuery();
            if (rs.next()) {
                artwork = new ArtworkDTO();
                artwork.setArtworkId(rs.getString(1));
                artwork.setOwnerId(rs.getString(2));
                artwork.setCateId(rs.getString(3));
                artwork.setName(rs.getString(4));
                artwork.setDescription(rs.getString(5));
                artwork.setArtworkImage(rs.getString(6));
                artwork.setPrice(rs.getFloat(7));
                artwork.setReleasedDate(rs.getDate(8));
                artwork.setStatus(rs.getInt(9));
            } else {
                throw new ArtworkERROR("This artowrk does not exist");
            }
        } catch (SQLException ex) {
            logError("Exception found on getArtwork() method", ex);
        } finally {
            context.closeResultSet(rs);
            context.closeStatement(stm);
        }
        return artwork;
    }

    /**
     * Adds a new artwork to the database.
     *
     * @param artwork the ArtworkDTO object representing the artwork to be
     * added.
     * @return true if the artwork is successfully added, false otherwise.
     */
    public boolean addArtwork(ArtworkDTO artwork) {
        Connection conn = null;
        PreparedStatement stm = null;
        boolean check = false;
        try {
            conn = context.getConnection();
            stm = conn.prepareStatement(ADD_ARTWORK);
            stm.setString(1, artwork.getArtworkId());
            stm.setString(2, artwork.getOwnerId());
            stm.setString(3, artwork.getCateId());
            stm.setString(4, artwork.getName());
            stm.setString(5, artwork.getDescription());
            stm.setString(6, artwork.getArtworkImage());
            stm.setDouble(7, artwork.getPrice());
            stm.setInt(8, artwork.getStatus());
            check = stm.executeUpdate() > 0;
        } catch (SQLException ex) {
            logError("Exception found on addArtwork() method", ex);
        } finally {
            context.closeStatement(stm);
        }
        return check;
    }

    /**
     * Updates the price of an existing artwork in the database.
     *
     * @param artwork the ArtworkDTO object representing the artwork to be
     * updated.
     * @return true if the artwork price is successfully updated, false
     * otherwise.
     */
    public boolean updateArtworkPrice(ArtworkDTO artwork) {
        Connection conn = null;
        PreparedStatement stm = null;
        boolean res = false;
        try {
            conn = context.getConnection();
            stm = conn.prepareStatement(UPDATE_ARTWORK_PRICE);
            stm.setFloat(1, artwork.getPrice());
            stm.setString(2, artwork.getArtworkId());
            res = stm.executeUpdate() > 0;
        } catch (SQLException ex) {
            logError("Exception found on updateArtworkPrice() method", ex);
            res = false;
        } finally {
            context.closeStatement(stm);
        }
        return res;
    }

    /**
     * Deletes an artwork from the database.
     *
     * @param artworkId the ID of the artwork to be deleted.
     * @return true if the artwork is successfully deleted, false otherwise.
     * @throws ArtworkERROR if the artwork with the specified ID does not exist.
     */
    public boolean delete(String artworkId) throws ArtworkERROR {
        Connection conn = null;
        boolean check = false;
        PreparedStatement stm = null;
        try {
            conn = context.getConnection();
            stm = conn.prepareStatement(REMOVE_ARTWORK);
            stm.setString(1, artworkId);
            if (stm.executeUpdate() > 0) {
                check = true;
            } else {
                throw new ArtworkERROR("This id does not exist in the system");
            }
        } catch (SQLException e) {
            logError("Exception found on delete(String artworkId) method", e);
        } finally {
            context.closeStatement(stm);
        }
        return check;
    }

    /**
     * Updates the details of an existing artwork in the database.
     *
     * @param artworkDTO the ArtworkDTO object representing the updated details
     * of the artwork.
     * @return true if the artwork details are successfully updated, false
     * otherwise.
     * @throws ArtworkERROR if the artwork with the specified ID does not exist.
     */
    public boolean update(ArtworkDTO artworkDTO) throws ArtworkERROR {
        Connection conn = null;
        PreparedStatement stm = null;
        boolean check = false;
        try {
            conn = context.getConnection();
            stm = conn.prepareStatement(UPDATE);
            stm.setString(1, artworkDTO.getCateId());
            stm.setString(2, artworkDTO.getName());
            stm.setString(3, artworkDTO.getDescription());
            stm.setString(4, artworkDTO.getArtworkImage());
            stm.setFloat(5, artworkDTO.getPrice());
            stm.setInt(6, artworkDTO.getStatus());
            stm.setString(7, artworkDTO.getArtworkId());

            if (stm.executeUpdate() > 0) {
                check = true;
            } else {
                throw new ArtworkERROR("This id does not exist in the system");
            }
        } catch (SQLException e) {
            logError("Exception found on update(ArtworkDTO artworkDTO) method", e);
        } finally {
            context.closeStatement(stm);
        }
        return check;
    }

    /**
     * Retrieves a list of artworks filtered by category type.
     *
     * @param typeInput the category type to filter artworks by.
     * @return a list of ArtworkDTO objects representing the filtered artworks.
     */
    public List<ArtworkDTO> getListArtworkWithType(String typeInput) {
        List<ArtworkDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = context.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(SEARCH_BY_TYPE);
                stm.setString(1, "%" + typeInput + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String artworkId = rs.getString("artwork_id");
                    String ownerId = rs.getString("first_name").concat(" ").concat(rs.getString("last_name"));
                    String cateId = rs.getString("cate_name");
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    String artworkImage = rs.getString("artwork_image");
                    float price = rs.getFloat("price");
                    Date releaseDate = rs.getDate("released_date");
                    int status = rs.getInt("status");
                    list.add(new ArtworkDTO(artworkId, ownerId, cateId, name, description, artworkImage, price, releaseDate, status));
                }
            }
        } catch (SQLException e) {
            logError("Exception found on getListArtworkWithType() method", e);
        } finally {
            context.closeResultSet(rs);
            context.closeStatement(stm);
        }
        return list;
    }

    /**
     * Retrieves a list of artworks filtered by artwork name.
     *
     * @param titleInput the artwork name to filter artworks by.
     * @return a list of ArtworkDTO objects representing the filtered artworks.
     */
    public List<ArtworkDTO> getListArtworkWithName(String titleInput) {
        List<ArtworkDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = context.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(SEARCH_BY_NAME);
                stm.setString(1, "%" + titleInput + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String artworkId = rs.getString("artwork_id");
                    String ownerId = rs.getString("first_name").concat(" ").concat(rs.getString("last_name"));
                    String cateId = rs.getString("cate_name");
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    String artworkImage = rs.getString("artwork_image");
                    float price = rs.getFloat("price");
                    Date releaseDate = rs.getDate("released_date");
                    int status = rs.getInt("status");
                    list.add(new ArtworkDTO(artworkId, ownerId, cateId, name, description, artworkImage, price, releaseDate, status));
                }
            }
        } catch (SQLException e) {
            logError("Exception found on getListArtworkWithName() method", e);
        } finally {
            context.closeResultSet(rs);
            context.closeStatement(stm);
        }
        return list;
    }

    /**
     * Retrieves a list of artworks filtered by creator name.
     *
     * @param creatorInput the creator name to filter artworks by.
     * @return a list of ArtworkDTO objects representing the filtered artworks.
     */
    public List<ArtworkDTO> getListArtworkWithNameOfCreator(String creatorInput) {
        List<ArtworkDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = context.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(SEARCH_BY_CREATOR_NAME);
                stm.setString(1, "%" + creatorInput + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String artworkId = rs.getString("artwork_id");
                    String ownerId = rs.getString("first_name").concat(" ").concat(rs.getString("last_name"));
                    String cateId = rs.getString("cate_name");
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    String artworkImage = rs.getString("artwork_image");
                    float price = rs.getFloat("price");
                    Date releaseDate = rs.getDate("released_date");
                    int status = rs.getInt("status");
                    list.add(new ArtworkDTO(artworkId, ownerId, cateId, name, description, artworkImage, price, releaseDate, status));
                }
            }
        } catch (SQLException e) {
            logError("Exception found on getListArtworkWithNameOfCreator() method", e);
        } finally {
            context.closeResultSet(rs);
            context.closeStatement(stm);
        }
        return list;
    }

    /**
     * Retrieves the top 10 artworks from the database, ordered by release date.
     *
     * @return a list of ArtworkDTO objects representing the top 10 artworks.
     */
    public List<ArtworkDTO> getTop10() {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<ArtworkDTO> artworks = new ArrayList<>();
        try {
            conn = context.getConnection();
            stm = conn.prepareStatement(GET_TOP_10_ARTWORKS);
            rs = stm.executeQuery();
            ArtworkDTO artwork = null;
            while (rs.next()) {
                artwork = new ArtworkDTO();
                artwork.setArtworkId(rs.getString(1));
                artwork.setOwnerId(rs.getString(2));
                artwork.setCateId(rs.getString(3));
                artwork.setName(rs.getString(4));
                artwork.setDescription(rs.getString(5));
                artwork.setArtworkImage(rs.getString(6));
                artwork.setPrice(rs.getFloat(7));
                artwork.setReleasedDate(rs.getDate(8));
                artwork.setStatus(rs.getInt(9));
                artworks.add(artwork);
            }
        } catch (SQLException ex) {
            logError("Exception found on getTop10() method", ex);
        } finally {
            context.closeResultSet(rs);
            context.closeStatement(stm);
        }
        return artworks;
    }

    public boolean changeStatus(String artworkId, int status, int state) {
        Connection conn = null;
        PreparedStatement stm = null;
        boolean check = false;
        try {
            conn = context.getConnection();
            stm = conn.prepareStatement(UPDATE_STATUS);
            stm.setInt(1, status ^ state);
            stm.setString(2, artworkId);
            check = stm.executeUpdate() > 0;
        } catch (SQLException e) {
            logError("Exception found on update(ArtworkDTO artworkDTO) method", e);
        } finally {
            context.closeStatement(stm);
        }
        return check;
    }
}
