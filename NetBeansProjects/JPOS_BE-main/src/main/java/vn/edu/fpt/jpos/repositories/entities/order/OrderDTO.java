package vn.edu.fpt.jpos.repositories.entities.order;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import vn.edu.fpt.jpos.repositories.enums.OrderStatusEnum;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderDTO {

    private String id;
    private String customerId;
    private double totalPrice;
    private Timestamp time;
    private OrderStatusEnum status;
}
