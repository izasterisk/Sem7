package vn.edu.fpt.jpos.repositories.entities.transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import vn.edu.fpt.jpos.core.DBContext;
import vn.edu.fpt.jpos.repositories.entities.IDao;

public class TransactionDAO implements IDao<TransactionDTO, TransactionERROR> {

    private static final String GETS = "SELECT [id], [order_id], [pay_date], [amount], [bank_code], [card_type], [status] FROM [dbo].[Transactions]";
    private static final String GET = "SELECT [id], [order_id], [pay_date], [amount], [bank_code], [card_type], [status] FROM [dbo].[Transactions] WHERE [id] = ?";
    private static final String POST = "INSERT INTO [dbo].[Transactions] ([id], [order_id], [pay_date], [amount], [bank_code], [card_type], [status]) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String PUT = "UPDATE [dbo].[Transactions] SET [order_id] = ?, [pay_date] = ?, [amount] = ?, [bank_code] = ?, [card_type] = ?, [status] = ? WHERE [id] = ?";
    private static final String DELETE = "DELETE FROM [dbo].[Transactions] WHERE [id] = ?";
    private static TransactionDAO instance;
    private DBContext context = DBContext.getInstance();

    private TransactionDAO() {
    }

    public static TransactionDAO getInstance() {
        if (instance == null) {
            instance = new TransactionDAO();
        }
        return instance;
    }

    @Override
    public List<TransactionDTO> gets() throws TransactionERROR {
        List<TransactionDTO> list = new ArrayList<>();
        try (Connection con = context.getConnection(); PreparedStatement stm = con.prepareStatement(GETS); ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                String id = rs.getString("id");
                String orderId = rs.getString("order_id");
                Timestamp payDate = rs.getTimestamp("pay_date");
                double amount = rs.getDouble("amount");
                String bankCode = rs.getString("bank_code");
                String cardType = rs.getString("card_type");
                String status = rs.getString("status");
                list.add(new TransactionDTO(id, orderId, payDate, amount, bankCode, cardType, status));
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new TransactionERROR(ex.getMessage());
        }
        return list;
    }

    @Override
    public TransactionDTO get(String id) throws TransactionERROR {
        TransactionDTO item = null;
        try (Connection con = context.getConnection(); PreparedStatement stm = con.prepareStatement(GET)) {
            stm.setString(1, id);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    String orderId = rs.getString("order_id");
                    Timestamp payDate = rs.getTimestamp("pay_date");
                    double amount = rs.getDouble("amount");
                    String bankCode = rs.getString("bank_code");
                    String cardType = rs.getString("card_type");
                    String status = rs.getString("status");
                    item = new TransactionDTO(id, orderId, payDate, amount, bankCode, cardType, status);
                } else {
                    throw new TransactionERROR("Transaction does not exist.");
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new TransactionERROR(ex.getMessage());
        }
        return item;
    }

    @Override
    public TransactionDTO post(TransactionDTO item) throws TransactionERROR {
        try (Connection con = context.getConnection(); PreparedStatement stm = con.prepareStatement(POST)) {
            stm.setString(1, item.getId());
            stm.setString(2, item.getOrderId());
            stm.setTimestamp(3, item.getPayDate());
            stm.setDouble(4, item.getAmount());
            stm.setString(5, item.getBankCode());
            stm.setString(6, item.getCardType());
            stm.setString(7, item.getStatus());
            if (stm.executeUpdate() > 0) {
                return item;
            } else {
                throw new TransactionERROR("Cannot add new transaction");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new TransactionERROR(ex.getMessage());
        }
    }

    @Override
    public TransactionDTO put(TransactionDTO item) throws TransactionERROR {
        try (Connection con = context.getConnection(); PreparedStatement stm = con.prepareStatement(PUT)) {
            stm.setString(1, item.getOrderId());
            stm.setTimestamp(2, item.getPayDate());
            stm.setDouble(3, item.getAmount());
            stm.setString(4, item.getBankCode());
            stm.setString(5, item.getCardType());
            stm.setString(6, item.getStatus());
            stm.setString(7, item.getId());
            if (stm.executeUpdate() > 0) {
                return item;
            } else {
                throw new TransactionERROR("Cannot update transaction");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new TransactionERROR(ex.getMessage());
        }
    }

    @Override
    public TransactionDTO delete(TransactionDTO item) throws TransactionERROR {
        try (Connection con = context.getConnection(); PreparedStatement stm = con.prepareStatement(DELETE)) {
            stm.setString(1, item.getId());
            if (stm.executeUpdate() > 0) {
                return item;
            } else {
                throw new TransactionERROR("Cannot delete transaction");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new TransactionERROR(ex.getMessage());
        }
    }
}
