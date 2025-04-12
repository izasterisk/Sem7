/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Application.OrderBuy.CRUD;



import dal.ProductsDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Products;

/**
 *
 * @author vipha
 */
@WebServlet(name = "DeleteServlet", urlPatterns = {"/DeleteServlet"})
public class Delete extends HttpServlet {

    private final String deleteSuccessfull = "ShopPizzaServlet?id=admin";
    private final String deletePage = "Delete.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String url = null;

        response.setContentType("text/html;charset=UTF-8");
        try {
            int ProductID = Integer.parseInt(request.getParameter("ProductID"));
            ProductsDAO productsDAO = new ProductsDAO();
            boolean check = productsDAO.deleteProducts(ProductID);
            if (check) {
                url = deleteSuccessfull;
            }
            response.sendRedirect(url);
        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String url = deletePage;        
        try {
            int Id = Integer.parseInt(request.getParameter("ProductID"));
            ProductsDAO productsDAO = new ProductsDAO();
            Products products = productsDAO.getProductByID(Id);
            request.setAttribute("products", products);
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
