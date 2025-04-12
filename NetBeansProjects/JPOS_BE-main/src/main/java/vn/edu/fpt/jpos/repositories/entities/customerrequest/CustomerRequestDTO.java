package vn.edu.fpt.jpos.repositories.entities.customerrequest;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import vn.edu.fpt.jpos.repositories.enums.CustomerRequestStatusEnum;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CustomerRequestDTO {

    private String id;
    private String cusId;
    private String description;
    private Timestamp time;
    private CustomerRequestStatusEnum status;
}
