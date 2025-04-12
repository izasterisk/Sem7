package vn.edu.fpt.jpos.resources.apis.v1.products;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import vn.edu.fpt.jpos.resources.entities.http.HttpStatusCode;
import vn.edu.fpt.jpos.repositories.entities.product.ProductDTO;
import vn.edu.fpt.jpos.repositories.entities.product.ProductERROR;
import vn.edu.fpt.jpos.resources.entities.http.EntityBaseResponse;
import vn.edu.fpt.jpos.resources.entities.http.ListBaseResponse;
import vn.edu.fpt.jpos.services.ProductService;

/**
 * RESTful API resource class for managing products.
 *
 * This class provides an API endpoint to fetch a list of products. It supports
 * pagination through query parameters.
 *
 * <p>
 * Path: {@code /v1/products}</p>
 * <p>
 * Produces: {@link MediaType#APPLICATION_JSON}</p>
 *
 * Example usage:
 * <pre>{@code
 * GET /v1/products?page=1&size=20
 * }</pre>
 *
 * This will return a paginated list of products in JSON format.
 */
@Path("v1/products")
public class Products {

    /**
     * Retrieves a paginated list of products.
     *
     * @param page the page number to retrieve, defaults to 1 if not specified.
     * @param size the number of products per page, defaults to 20 if not
     * specified.
     * @return a {@link Response} object containing the paginated list of
     * products in JSON format.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductList(
            @DefaultValue("1") @QueryParam("page") int page,
            @DefaultValue("20") @QueryParam("size") int size) {
        ProductService service = ProductService.getInstance();
        try {
            List<ProductDTO> list = service.getAllProducts();
            ListBaseResponse<ProductDTO> res = new ListBaseResponse<>();
            res.setStatus(HttpStatusCode.ACCEPTED);
            res.setMessage("Successfully get product list.");
            res.setPage(page);
            res.setSize(size);
            res.setTotalPages(Math.floorDiv(list.size(), size) + 1);
            list = list.subList((page - 1) * size, Math.min(list.size(), page * size));
            res.setTotal(list.size());
            res.setItems(list);
            return Response.ok(res).build();
        } catch (ProductERROR ex) {
            EntityBaseResponse res = new EntityBaseResponse<>(
                    HttpStatusCode.INTERNAL_SERVER_ERROR,
                    ex.getMessage(),
                    null);
            return Response.ok(res).build();
        }
    }

    /**
     * Retrieves a product by its ID.
     *
     * @param id the ID of the product to retrieve.
     * @return a {@link Response} object containing the product in JSON format,
     * or an error message if not found.
     */
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAProduct(@PathParam("id") String id) {
        ProductService service = ProductService.getInstance();
        try {
            ProductDTO product = service.getProduct(id);
            EntityBaseResponse<ProductDTO> res = new EntityBaseResponse<>(
                    HttpStatusCode.OK,
                    "Successfully get product has id " + id,
                    product);
            return Response.ok(res).build();
        } catch (ProductERROR ex) {
            EntityBaseResponse<ProductDTO> res = new EntityBaseResponse<>(
                    HttpStatusCode.NOT_FOUND,
                    ex.getMessage(),
                    null);
            return Response.ok(res).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addNewProduct(ProductDTO product) {
        ProductService service = ProductService.getInstance();
        try {
            ProductDTO p = service.addNewProduct(product);
            EntityBaseResponse<ProductDTO> res = new EntityBaseResponse<>(
                    HttpStatusCode.ACCEPTED,
                    "Successfully add product",
                    p);
            return Response.ok(res).build();
        } catch (ProductERROR ex) {
            EntityBaseResponse<ProductDTO> res = new EntityBaseResponse<>(
                    HttpStatusCode.INTERNAL_SERVER_ERROR,
                    ex.getMessage(),
                    null);
            return Response.ok(res).build();
        }
    }
}
