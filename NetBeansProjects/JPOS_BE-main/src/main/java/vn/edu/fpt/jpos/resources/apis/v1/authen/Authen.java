package vn.edu.fpt.jpos.resources.apis.v1.authen;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import vn.edu.fpt.jpos.resources.entities.http.HttpStatusCode;
import vn.edu.fpt.jpos.repositories.entities.token.TokenDTO;
import vn.edu.fpt.jpos.repositories.entities.token.TokenERROR;
import vn.edu.fpt.jpos.repositories.entities.user.UserDTO;
import vn.edu.fpt.jpos.repositories.entities.user.UserERROR;
import vn.edu.fpt.jpos.resources.entities.http.EntityBaseResponse;
import vn.edu.fpt.jpos.services.TokenService;

@Path("v1/authen")
public class Authen {

    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkLogin(UserDTO login) {
        TokenService authenService = TokenService.getInstance();
        TokenDTO token = null;
        try {
            token = authenService.checkLogin(login.getEmail(), login.getPassword());
        } catch (UserERROR ex) {
            return Response.ok(new EntityBaseResponse<>(
                    HttpStatusCode.FORBIDDEN, ex.getMessage(), token)).build();
        } catch (TokenERROR ex) {
            return Response.ok(new EntityBaseResponse<>(
                    HttpStatusCode.INTERNAL_SERVER_ERROR, ex.getMessage(), token)).build();
        }
        return token != null
                ? Response.ok(new EntityBaseResponse<>(
                        HttpStatusCode.OK,
                        "Login successfully", token)).build()
                : Response.ok(new EntityBaseResponse<>(
                        HttpStatusCode.NOT_ACCEPTABLE,
                        "Login failed", token)).build();
    }

    @POST
    @Path("register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(UserDTO userInfo) {
        TokenService authenService = TokenService.getInstance();
        TokenDTO token = null;
        try {
            token = authenService.register(userInfo);
        } catch (UserERROR ex) {
            return Response.ok(new EntityBaseResponse<>(
                    HttpStatusCode.FORBIDDEN, ex.getMessage(), token)).build();
        } catch (TokenERROR ex) {
            return Response.ok(new EntityBaseResponse<>(
                    HttpStatusCode.INTERNAL_SERVER_ERROR, ex.getMessage(), token)).build();
        }
        return token != null
                ? Response.ok(new EntityBaseResponse<>(
                        HttpStatusCode.OK,
                        "Register successfully", token)).build()
                : Response.ok(new EntityBaseResponse<>(
                        HttpStatusCode.FORBIDDEN,
                        "Register failed", token)).build();
    }

    @POST
    @Path("logout")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response logout(@HeaderParam("authToken") String tokenString) {
        TokenService authenService = TokenService.getInstance();
        TokenDTO token = new TokenDTO(tokenString, "", "");
        try {
            token = authenService.logout(token);
        } catch (UserERROR ex) {
            return Response.ok(new EntityBaseResponse<>(
                    HttpStatusCode.UNAUTHORIED, ex.getMessage(), token)).build();
        } catch (TokenERROR ex) {
            return Response.ok(new EntityBaseResponse<>(
                    HttpStatusCode.INTERNAL_SERVER_ERROR, ex.getMessage(), token)).build();
        }
        return token != null
                ? Response.ok(new EntityBaseResponse<>(
                        HttpStatusCode.NO_CONTENT,
                        "Logout successfully", null)).build()
                : Response.ok(new EntityBaseResponse<>(
                        HttpStatusCode.NOT_ACCEPTABLE,
                        "Logout failed", token)).build();
    }
}
