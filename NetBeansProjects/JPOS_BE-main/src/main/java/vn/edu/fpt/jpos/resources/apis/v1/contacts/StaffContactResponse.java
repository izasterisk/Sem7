package vn.edu.fpt.jpos.resources.apis.v1.contacts;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import vn.edu.fpt.jpos.repositories.entities.customerrequest.CustomerRequestDTO;
import vn.edu.fpt.jpos.repositories.entities.customerrequest.CustomerRequestERROR;
import vn.edu.fpt.jpos.repositories.entities.staffresponse.StaffResponseDTO;
import vn.edu.fpt.jpos.repositories.entities.staffresponse.StaffResponseERROR;
import vn.edu.fpt.jpos.repositories.entities.token.TokenDTO;
import vn.edu.fpt.jpos.repositories.entities.token.TokenERROR;
import vn.edu.fpt.jpos.repositories.enums.CustomerRequestStatusEnum;
import vn.edu.fpt.jpos.resources.entities.http.EntityBaseResponse;
import vn.edu.fpt.jpos.resources.entities.http.HttpStatusCode;
import vn.edu.fpt.jpos.resources.entities.request.Contact;
import vn.edu.fpt.jpos.services.CustomerRequestService;
import vn.edu.fpt.jpos.services.StaffResponseService;
import vn.edu.fpt.jpos.services.TokenService;

@Path("v1/staff")
public class StaffContactResponse {

    @POST
    @Path("contacts-response")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response sendResponse(
            @HeaderParam("authToken") String tokenString,
            Contact contact) {
        TokenService tokenService = TokenService.getInstance();
        CustomerRequestService reqService = CustomerRequestService.getInstance();
        StaffResponseService resService = StaffResponseService.getIntsance();
        try {
            TokenDTO staffToken = tokenService.getToken(tokenString);
            CustomerRequestDTO req = reqService.getById(contact.getContactId());
            req.setStatus(CustomerRequestStatusEnum.CONFIRMED);
            req = reqService.update(req);
            StaffResponseDTO res = resService.add(
                    new StaffResponseDTO(null, req.getId(),
                            staffToken.getUserId(),
                            contact.getMessage(),
                            null, null)
            );
            EntityBaseResponse resp = new EntityBaseResponse<>(
                    HttpStatusCode.ACCEPTED,
                    "Successfully send to manager",
                    res);
            return Response.ok(resp).build();
        } catch (TokenERROR ex) {
            EntityBaseResponse res = new EntityBaseResponse<>(
                    HttpStatusCode.UNAUTHORIED,
                    ex.getMessage(),
                    ex);
            return Response.ok(res).build();
        } catch (CustomerRequestERROR ex) {
            EntityBaseResponse res = new EntityBaseResponse<>(
                    HttpStatusCode.NOT_FOUND,
                    ex.getMessage(),
                    ex);
            return Response.ok(res).build();
        } catch (StaffResponseERROR ex) {
            EntityBaseResponse res = new EntityBaseResponse<>(
                    HttpStatusCode.INTERNAL_SERVER_ERROR,
                    ex.getMessage(),
                    ex);
            return Response.ok(res).build();
        }
    }
}
