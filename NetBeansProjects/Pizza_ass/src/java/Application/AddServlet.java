package Application;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */



import dal.ProductsDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Categories;
import model.Products;
import model.Suppliers;

/**
 *
 * @author vipha
 */
@WebServlet(name = "AddServlet", urlPatterns = {"/AddServlet"})
public class AddServlet extends HttpServlet {
    private final String addSuccessfull= "ShopPizzaServlet?id=admin";
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String url = null;
        
        response.setContentType("text/html;charset=UTF-8");
        
        try {
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
            
            Products product = new Products(0, ProductName, supp, cate, QuantityPerUnit,
                    UnitPrice, ProductImage, Description);
                boolean check = productsDAO.addProducts(product);
                if (check) {
                    url = addSuccessfull;      
                }
        } catch (Exception e) {
            out.println("Error: " +e.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
        }
    }
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
