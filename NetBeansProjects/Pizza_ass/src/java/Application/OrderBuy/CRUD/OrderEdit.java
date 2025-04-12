/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Application.OrderBuy.CRUD;



import dal.OrderDAO;
import dal.ProductsDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Categories;
import model.Orders;
import model.OrdersDetails;
import model.Products;
import model.Suppliers;

/**
 *
 * @author vipha
 */
@WebServlet(name = "OrderEdit", urlPatterns = {"/OrderEdit"})
public class OrderEdit extends HttpServlet {

    private final String EditSuccessfull = "ShopPizzaServlet?id=admin";
    private final String EditOrderSuccessfull = "ShopPizzaServlet?id=admin";
    
    private final String EditPage = "OrderEdit.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String url = null;
        
         
       
        try {
            int ProductID = Integer.parseInt(request.getParameter("txtProductID"));
            String ProductName = request.getParameter("txtProductName");
            int SupplierID = Integer.parseInt(request.getParameter("txtSupplierID"));
            String CategoryID = request.getParameter("txtCategoryID");
            int QuantityPerUnit = Integer.parseInt(request.getParameter("txtQuantityPerUnit"));
            float UnitPrice = Float.parseFloat(request.getParameter("txtUnitPrice"));
            String ProductImage = request.getParameter("txtProductImage");
            String Description = request.getParameter("txtDescription");
            
            ProductsDAO productsDAO = new ProductsDAO();
            Categories cate = productsDAO.getCateById(CategoryID);
            Suppliers supp = productsDAO.getSupplierByID(SupplierID);
            
            Products product = new Products(ProductID, ProductName, supp, cate, QuantityPerUnit,
                    UnitPrice, ProductImage, Description);
            boolean check = productsDAO.updateProducts(product);
                if (check) {
                    url = EditSuccessfull;
                } else {
                    String megasse = "<H4>Edit product faile. Please try again!!!<h4>";
                    request.setAttribute("products", product);
                    request.setAttribute("megasse", megasse);
                    url = EditPage;
                }
        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
        }finally{
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        PrintWriter out = response.getWriter();
        String url = null;
        try {
            int orderID = Integer.parseInt(request.getParameter("orderID"));
            OrderDAO orderDAO = new OrderDAO();
            List<OrdersDetails> listOrderDetails = orderDAO.getListOrdersDetailsByID(orderID);
            Orders order = orderDAO.getOrdersByID(orderID);
            double price = orderDAO.getTotalMoney(orderID);
            
            request.setAttribute("listOrderDetails", listOrderDetails);
            request.setAttribute("order", order);
            request.setAttribute("price", price);
            url = EditPage;
        } catch (Exception e) {
            out.println(e.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
