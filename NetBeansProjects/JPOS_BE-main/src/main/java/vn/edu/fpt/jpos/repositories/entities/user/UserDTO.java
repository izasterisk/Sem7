package vn.edu.fpt.jpos.repositories.entities.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import vn.edu.fpt.jpos.repositories.enums.UserRoleEnum;
import vn.edu.fpt.jpos.repositories.enums.UserStatusEnum;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserDTO {

    private String id;
    private String username;
    private String fullname;
    private String email;
    private String password;
    private String phone;
    private String address;
    private UserRoleEnum role;
    private UserStatusEnum status;
}
