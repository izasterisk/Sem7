package vn.edu.fpt.jpos.repositories.entities.token;

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
public class TokenDTO {

    private String accessToken;
    private String userId;
    private String userRole;
}
