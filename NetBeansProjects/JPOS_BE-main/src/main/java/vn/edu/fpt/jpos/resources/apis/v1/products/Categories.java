package vn.edu.fpt.jpos.resources.apis.v1.products;

import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import vn.edu.fpt.jpos.repositories.entities.category.CategoryDTO;
import vn.edu.fpt.jpos.repositories.entities.category.CategoryERROR;
import vn.edu.fpt.jpos.repositories.entities.product.ProductDTO;
import vn.edu.fpt.jpos.repositories.entities.product.ProductERROR;
import vn.edu.fpt.jpos.resources.entities.http.EntityBaseResponse;
import vn.edu.fpt.jpos.resources.entities.http.HttpStatusCode;
import vn.edu.fpt.jpos.resources.entities.http.ListBaseResponse;
import vn.edu.fpt.jpos.services.CategoryService;
import vn.edu.fpt.jpos.services.ProductService;

@Path("v1/categories")
public class Categories {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCategories(
            @DefaultValue("1") @QueryParam("page") int page,
            @DefaultValue("20") @QueryParam("size") int size) {
        CategoryService service = CategoryService.getInstance();
        try {
            List<CategoryDTO> list = service.getAllCategories();
            ListBaseResponse<CategoryDTO> res = new ListBaseResponse<>();
            res.setStatus(HttpStatusCode.ACCEPTED);
            res.setMessage("Successfully get product list.");
            res.setPage(page);
            res.setSize(size);
            res.setTotalPages(Math.floorDiv(list.size(), size) + 1);
            list = list.subList((page - 1) * size, Math.min(list.size(), page * size));
            res.setTotal(list.size());
            res.setItems(list);
            return Response.ok(res).build();
        } catch (CategoryERROR ex) {
            EntityBaseResponse res = new EntityBaseResponse<>(
                    HttpStatusCode.INTERNAL_SERVER_ERROR,
                    ex.getMessage(),
                    null);
            return Response.ok(res).build();
        }
    }

    @GET
    @Path("{id}/products")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductsByCategory(
            @DefaultValue("1") @QueryParam("page") int page,
            @DefaultValue("20") @QueryParam("size") int size,
            @PathParam("id") String cateId) {
        ProductService service = ProductService.getInstance();
        try {
            List<ProductDTO> list = service.getProductsByCategoryId(cateId);
            ListBaseResponse<ProductDTO> res = new ListBaseResponse<>();
            res.setStatus(HttpStatusCode.ACCEPTED);
            res.setMessage("Successfully get product list.");
            res.setPage(page);
            res.setSize(size);
            res.setTotalPages(Math.floorDiv(list.size(), size) + 1);
            list = list.subList((page - 1) * size, Math.min(list.size(), page * size));
            res.setTotal(list.size());
            res.setItems(list);
            System.out.println(res);
            return Response.ok(res).build();
        } catch (ProductERROR ex) {
            EntityBaseResponse res = new EntityBaseResponse<>(
                    HttpStatusCode.INTERNAL_SERVER_ERROR,
                    ex.getMessage(),
                    null);
            return Response.ok(res).build();
        } catch (CategoryERROR ex) {
            EntityBaseResponse res = new EntityBaseResponse<>(
                    HttpStatusCode.NOT_FOUND,
                    ex.getMessage(),
                    null);
            return Response.ok(res).build();
        }
    }
}
