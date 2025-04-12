package vn.edu.fpt.jpos.resources.apis.v1.orders;

import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import vn.edu.fpt.jpos.repositories.entities.order.OrderDTO;
import vn.edu.fpt.jpos.repositories.entities.order.OrderERROR;
import vn.edu.fpt.jpos.resources.entities.http.EntityBaseResponse;
import vn.edu.fpt.jpos.resources.entities.http.HttpStatusCode;
import vn.edu.fpt.jpos.resources.entities.http.ListBaseResponse;
import vn.edu.fpt.jpos.services.OrderService;

@Path("v1/orders")
public class Orders {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrderList(
            @DefaultValue("1") @QueryParam("page") int page,
            @DefaultValue("20") @QueryParam("size") int size) {
        OrderService orderService = OrderService.getInstance();
        try {
            List<OrderDTO> list = orderService.getAll();
            ListBaseResponse<OrderDTO> res
                    = new ListBaseResponse<>(HttpStatusCode.ACCEPTED,
                            "Successfully get order list.",
                            page, size, list);
            return Response.ok(res).build();
        } catch (OrderERROR ex) {
            EntityBaseResponse res = new EntityBaseResponse<>(
                    HttpStatusCode.INTERNAL_SERVER_ERROR,
                    ex.getMessage(),
                    ex);
            return Response.ok(res).build();
        }
    }

    @GET
    @Path("total")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrdersTotal(
            @DefaultValue("1") @QueryParam("page") int page,
            @DefaultValue("20") @QueryParam("size") int size) {
        OrderService orderService = OrderService.getInstance();
        try {
            OrderDTO order = orderService.getOrdersTotal();
            EntityBaseResponse res = new EntityBaseResponse<>(
                    HttpStatusCode.ACCEPTED,
                    "Successfully get orders' total price.",
                    order);
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
