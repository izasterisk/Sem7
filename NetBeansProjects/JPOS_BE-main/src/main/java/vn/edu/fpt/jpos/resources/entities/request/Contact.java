package vn.edu.fpt.jpos.resources.entities.request;

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
public class Contact {

    private String contactId;
    private String fullname;
    private String email;
    private String phone;
    private String message;
    private Timestamp time;
    private CustomerRequestStatusEnum status;
}
