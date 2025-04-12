package vn.edu.fpt.jpos.repositories.entities.transaction;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TransactionDTO {

    private String id;
    private String orderId;
    private Timestamp payDate;
    private double amount;
    private String bankCode;
    private String cardType;
    private String status;
}
