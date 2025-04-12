/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Application.OrderBuy;

import dal.OrderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Orders;

/**
 *
 * @author vipha
 */
@WebServlet(name = "OrderShowServlet", urlPatterns = {"/OrderShowServlet"})
public class OrderShowServlet extends HttpServlet {

    private final String OderManager = "ManagerOrder.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet OrderShowServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OrderShowServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = null;
        PrintWriter out = response.getWriter();
        
        try {
            OrderDAO orderDAO = new OrderDAO();
            List<Orders> orderList = orderDAO.getListOrder();
            request.setAttribute("orderList", orderList);
            url = OderManager;
            
        } catch (Exception e) {
            out.println(e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String url = null;  
        try {
            int orderId = Integer.parseInt(request.getParameter("orderId"));
            boolean status = request.getParameter("status") != null; // Checkbox checked gửi "on", không check gửi null
            
            OrderDAO orderDAO = new OrderDAO();
            orderDAO.updateOrderStatus(orderId, status);
            List<Orders> orderList = orderDAO.getListOrder();
            request.setAttribute("orderList", orderList);
            url = OderManager;
             
        } catch (Exception e) {
            out.println(e.getMessage());
        }finally{
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
