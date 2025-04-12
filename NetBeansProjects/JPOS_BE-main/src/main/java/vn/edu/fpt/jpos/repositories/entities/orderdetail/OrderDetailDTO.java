package vn.edu.fpt.jpos.repositories.entities.orderdetail;

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
public class OrderDetailDTO {

    private String orderId;
    private String productId;
    private String description;
    private int quantity;
    private double price;
}
