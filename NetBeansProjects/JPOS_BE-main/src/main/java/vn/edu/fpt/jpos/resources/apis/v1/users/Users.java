package vn.edu.fpt.jpos.resources.apis.v1.users;

import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import vn.edu.fpt.jpos.repositories.entities.user.UserDTO;
import vn.edu.fpt.jpos.repositories.entities.user.UserERROR;
import vn.edu.fpt.jpos.resources.entities.http.EntityBaseResponse;
import vn.edu.fpt.jpos.resources.entities.http.HttpStatusCode;
import vn.edu.fpt.jpos.resources.entities.http.ListBaseResponse;
import vn.edu.fpt.jpos.services.UserService;

@Path("v1/users")
public class Users {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUser(
            @DefaultValue("1") @QueryParam("page") int page,
            @DefaultValue("20") @QueryParam("size") int size) {
        UserService service = UserService.getInstance();
        try {
            List<UserDTO> list = service.getAll();
            ListBaseResponse<UserDTO> res
                    = new ListBaseResponse<>(HttpStatusCode.ACCEPTED,
                            "Successfully get user list.",
                            page, size, list);
            return Response.ok(res).build();
        } catch (UserERROR ex) {
            EntityBaseResponse res = new EntityBaseResponse<>(
                    HttpStatusCode.INTERNAL_SERVER_ERROR,
                    ex.getMessage(),
                    null);
            return Response.ok(res).build();
        }
    }
}
