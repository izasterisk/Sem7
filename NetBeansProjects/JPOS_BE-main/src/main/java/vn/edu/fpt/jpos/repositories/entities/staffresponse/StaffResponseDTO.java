package vn.edu.fpt.jpos.repositories.entities.staffresponse;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import vn.edu.fpt.jpos.repositories.enums.StaffResponseStatusEnum;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class StaffResponseDTO {

    private String id;
    private String requestId;
    private String staffId;
    private String description;
    private Timestamp time;
    private StaffResponseStatusEnum status;
}
