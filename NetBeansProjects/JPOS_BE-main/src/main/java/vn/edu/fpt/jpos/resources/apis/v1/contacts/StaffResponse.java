package vn.edu.fpt.jpos.resources.apis.v1.contacts;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import vn.edu.fpt.jpos.repositories.entities.customerrequest.CustomerRequestDTO;
import vn.edu.fpt.jpos.repositories.entities.customerrequest.CustomerRequestERROR;
import vn.edu.fpt.jpos.repositories.entities.order.OrderDTO;
import vn.edu.fpt.jpos.repositories.entities.order.OrderERROR;
import vn.edu.fpt.jpos.repositories.entities.staffresponse.StaffResponseDTO;
import vn.edu.fpt.jpos.repositories.entities.staffresponse.StaffResponseERROR;
import vn.edu.fpt.jpos.repositories.entities.user.UserDTO;
import vn.edu.fpt.jpos.repositories.enums.CustomerRequestStatusEnum;
import vn.edu.fpt.jpos.repositories.enums.OrderStatusEnum;
import vn.edu.fpt.jpos.repositories.enums.StaffResponseStatusEnum;
import vn.edu.fpt.jpos.resources.entities.http.EntityBaseResponse;
import vn.edu.fpt.jpos.resources.entities.http.HttpStatusCode;
import vn.edu.fpt.jpos.resources.entities.http.ListBaseResponse;
import vn.edu.fpt.jpos.resources.entities.request.ContactRes;
import vn.edu.fpt.jpos.services.CustomerRequestService;
import vn.edu.fpt.jpos.services.OrderService;
import vn.edu.fpt.jpos.services.StaffResponseService;
import vn.edu.fpt.jpos.services.UserService;

@Path("v1/contacts-response")
public class StaffResponse {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getResponseList(
            @DefaultValue("1") @QueryParam("page") int page,
            @DefaultValue("20") @QueryParam("size") int size,
            @DefaultValue("NewAcceptedRejecttedDesigningProducingCompletedComplete DesigningComplete Producing")
            @QueryParam("status") String status) {
        StaffResponseService resService = StaffResponseService.getIntsance();
        CustomerRequestService reqService = CustomerRequestService.getInstance();
        UserService userService = UserService.getInstance();
        try {
            List<StaffResponseDTO> list = resService.getListByStatus(status);
            List<ContactRes> resList = new ArrayList<>();
            list.forEach(res -> {
                CustomerRequestDTO req = reqService.getById(res.getRequestId());
                UserDTO cus = userService.getUserById(req.getCusId());
                resList.add(
                        new ContactRes(
                                res.getId(),
                                cus.getFullname(),
                                cus.getEmail(),
                                cus.getPhone(),
                                res.getDescription(),
                                res.getTime(),
                                res.getStatus())
                );
            });
            ListBaseResponse<ContactRes> res
                    = new ListBaseResponse<>(HttpStatusCode.ACCEPTED,
                            "Successfully get contact list.",
                            page, size, resList);
            return Response.ok(res).build();
        } catch (StaffResponseERROR ex) {
            EntityBaseResponse res = new EntityBaseResponse<>(
                    HttpStatusCode.INTERNAL_SERVER_ERROR,
                    ex.getMessage(),
                    ex);
            return Response.ok(res).build();
        }
    }

    @PATCH
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateContactRequest(
            @PathParam("id") String id,
            @QueryParam("status") String status) {
        CustomerRequestService reqService = CustomerRequestService.getInstance();
        StaffResponseService resService = StaffResponseService.getIntsance();
        try {
            StaffResponseDTO res = resService.getById(id);
            CustomerRequestDTO req = reqService.getById(res.getRequestId());
            res.setStatus(StaffResponseStatusEnum.getStatus(status));
            req.setStatus(CustomerRequestStatusEnum.getStatus(status));
            req = reqService.update(req);
            res = resService.update(res);
            EntityBaseResponse resp = new EntityBaseResponse<>(
                    HttpStatusCode.ACCEPTED,
                    "Successfully updated",
                    res);
            return Response.ok(resp).build();
        } catch (CustomerRequestERROR | StaffResponseERROR ex) {
            EntityBaseResponse res = new EntityBaseResponse<>(
                    HttpStatusCode.NOT_FOUND,
                    ex.getMessage(),
                    ex);
            return Response.ok(res).build();
        }
    }

    @PATCH
    @Path("reject/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response rejectContactRequest(
            @PathParam("id") String id,
            @QueryParam("status") String status) {
        CustomerRequestService reqService = CustomerRequestService.getInstance();
        StaffResponseService resService = StaffResponseService.getIntsance();
        try {
            StaffResponseDTO res = resService.getById(id);
            CustomerRequestDTO req = reqService.getById(res.getRequestId());
            res.setStatus(StaffResponseStatusEnum.REJECTED);
            req.setStatus(CustomerRequestStatusEnum.REJECTED);
            req = reqService.update(req);
            res = resService.update(res);
            EntityBaseResponse resp = new EntityBaseResponse<>(
                    HttpStatusCode.ACCEPTED,
                    "Successfully rejected",
                    res);
            return Response.ok(resp).build();
        } catch (CustomerRequestERROR | StaffResponseERROR ex) {
            EntityBaseResponse res = new EntityBaseResponse<>(
                    HttpStatusCode.NOT_FOUND,
                    ex.getMessage(),
                    ex);
            return Response.ok(res).build();
        }
    }

    @POST
    @Path("complete/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response completeContactRequest(
            @PathParam("id") String id,
            @QueryParam("status") String status,
            OrderDTO order) {
        CustomerRequestService reqService = CustomerRequestService.getInstance();
        StaffResponseService resService = StaffResponseService.getIntsance();
        OrderService orderService = OrderService.getInstance();
        try {
            StaffResponseDTO res = resService.getById(id);
            CustomerRequestDTO req = reqService.getById(res.getRequestId());
            res.setStatus(StaffResponseStatusEnum.COMPLETED);
            req.setStatus(CustomerRequestStatusEnum.COMPLETED);
            req = reqService.update(req);
            res = resService.update(res);
            order.setCustomerId(req.getCusId());
            order.setStatus(OrderStatusEnum.COMPLETED);
            order = orderService.add(order);
            EntityBaseResponse resp = new EntityBaseResponse<>(
                    HttpStatusCode.ACCEPTED,
                    "Successfully complete and created new order.",
                    order);
            return Response.ok(resp).build();
        } catch (CustomerRequestERROR | StaffResponseERROR ex) {
            EntityBaseResponse res = new EntityBaseResponse<>(
                    HttpStatusCode.NOT_FOUND,
                    ex.getMessage(),
                    ex);
            return Response.ok(res).build();
        } catch (OrderERROR ex) {
            EntityBaseResponse res = new EntityBaseResponse<>(
                    HttpStatusCode.INTERNAL_SERVER_ERROR,
                    ex.getMessage(),
                    ex);
            return Response.ok(res).build();
        }
    }
}
