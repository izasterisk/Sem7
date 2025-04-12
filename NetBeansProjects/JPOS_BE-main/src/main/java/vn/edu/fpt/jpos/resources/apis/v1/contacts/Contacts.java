package vn.edu.fpt.jpos.resources.apis.v1.contacts;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import vn.edu.fpt.jpos.repositories.entities.customerrequest.CustomerRequestDTO;
import vn.edu.fpt.jpos.repositories.entities.customerrequest.CustomerRequestERROR;
import vn.edu.fpt.jpos.repositories.entities.user.UserDTO;
import vn.edu.fpt.jpos.repositories.entities.user.UserERROR;
import vn.edu.fpt.jpos.repositories.enums.UserRoleEnum;
import vn.edu.fpt.jpos.repositories.enums.UserStatusEnum;
import vn.edu.fpt.jpos.resources.entities.http.EntityBaseResponse;
import vn.edu.fpt.jpos.resources.entities.http.HttpStatusCode;
import vn.edu.fpt.jpos.resources.entities.http.ListBaseResponse;
import vn.edu.fpt.jpos.resources.entities.request.Contact;
import vn.edu.fpt.jpos.services.CustomerRequestService;
import vn.edu.fpt.jpos.services.UserService;

@Path("v1/contacts")
public class Contacts {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response sendContact(Contact contact) {
        UserService userService = UserService.getInstance();
        CustomerRequestService cusReqService = CustomerRequestService.getInstance();
        try {
            UserDTO customer = new UserDTO(null, contact.getFullname(),
                    contact.getFullname(), contact.getEmail(),
                    "1234", contact.getPhone(),
                    null, UserRoleEnum.CUSTOMER, UserStatusEnum.ACTIVED);
            customer = userService.addNewUser(customer);
            customer = userService.getByPhone(customer.getPhone());
            CustomerRequestDTO req = new CustomerRequestDTO(null,
                    customer.getId(), contact.getMessage(),
                    null, null);
            req = cusReqService.addContact(req);
            EntityBaseResponse res = new EntityBaseResponse<>(
                    HttpStatusCode.ACCEPTED,
                    "Succesfully add new contact",
                    req);
            return Response.ok(res).build();
        } catch (UserERROR ex) {
            EntityBaseResponse res = new EntityBaseResponse<>(
                    HttpStatusCode.FORBIDDEN,
                    ex.getMessage(),
                    null);
            return Response.ok(res).build();
        } catch (CustomerRequestERROR ex) {
            EntityBaseResponse res = new EntityBaseResponse<>(
                    HttpStatusCode.INTERNAL_SERVER_ERROR,
                    ex.getMessage(),
                    null);
            return Response.ok(res).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllContacts(
            @DefaultValue("1") @QueryParam("page") int page,
            @DefaultValue("20") @QueryParam("size") int size,
            @DefaultValue("NewAcceptedRejecttedDesigningProducingCompletedComplete DesigningComplete Producing")
            @QueryParam("status") String status) {
        CustomerRequestService cusReqService = CustomerRequestService.getInstance();
        UserService userService = UserService.getInstance();
        try {
            List<CustomerRequestDTO> reqList = cusReqService.getByStatus(status);
            List<Contact> contactList = new ArrayList<>();
            reqList.forEach(req -> {
                UserDTO cus = userService.getUserById(req.getCusId());
                contactList.add(
                        new Contact(
                                req.getId(),
                                cus.getFullname(),
                                cus.getEmail(),
                                cus.getPhone(),
                                req.getDescription(),
                                req.getTime(),
                                req.getStatus())
                );
            });
            ListBaseResponse<Contact> res
                    = new ListBaseResponse<>(HttpStatusCode.ACCEPTED,
                            "Successfully get contact list.",
                            page, size, contactList);
            return Response.ok(res).build();
        } catch (CustomerRequestERROR ex) {
            EntityBaseResponse res = new EntityBaseResponse<>(
                    HttpStatusCode.UNAUTHORIED,
                    ex.getMessage(),
                    ex);
            return Response.ok(res).build();
        } catch (UserERROR ex) {
            EntityBaseResponse res = new EntityBaseResponse<>(
                    HttpStatusCode.INTERNAL_SERVER_ERROR,
                    ex.getMessage(),
                    ex);
            return Response.ok(res).build();
        }
    }
}
