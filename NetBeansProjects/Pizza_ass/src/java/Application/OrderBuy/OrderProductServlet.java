/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Application.OrderBuy;

import dal.CartDAO;
import dal.CustomerDAO;
import dal.OrderDAO;
import dal.ProductsDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.Categories;
import model.Customers;
import model.Item;
import model.Orders;
import model.Products;
import model.Suppliers;

/**
 *
 * @author vipha
 */
@WebServlet(name = "OrderProductServlet", urlPatterns = {"/OrderProductServlet"})
public class OrderProductServlet extends HttpServlet {

    private final String pageLogin = "Login.jsp";
    private final String home = "HomeShop.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String url = null;

        String contactName = request.getParameter("contactName");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        try {
            ProductsDAO productDAO = new ProductsDAO();
            List<Products> productList = productDAO.getProductList();
            request.setAttribute("productList", productList);
            List<Categories> cateList = productDAO.getCateList();
            request.setAttribute("cateList", cateList);
            List<Suppliers> suppList = productDAO.getSuppliersList();
            request.setAttribute("suppList", suppList);

            String txt = "";
            Cookie[] arr = request.getCookies();
            if (arr != null) {
                for (Cookie o : arr) {
                    if (o.getName().equals("cart")) {
                        txt += o.getValue();
                        o.setMaxAge(0);
                        response.addCookie(o);
                    }
                }
            }
            String txtdecode;
            if (!txt.isEmpty()) {
                txtdecode = productDAO.decodeFromBase64(txt);
            } else {
                txtdecode = "";
            }

            CartDAO cartDAO = new CartDAO(txtdecode, productList);
            List<Item> itemList = cartDAO.getListItems();
            HttpSession session = request.getSession();
            Account account = (Account) session.getAttribute("account");
            if (account == null) {
                url = pageLogin;
            } else {
                Customers customers = new Customers(0, account, contactName, address, phone);
                CustomerDAO customerDAO = new CustomerDAO();
                boolean check = customerDAO.addCustomers(customers);
                OrderDAO orderDAO = new OrderDAO();

                Customers customerAc = customerDAO.getCustomersByAccount(account);
                orderDAO.addOrder(customerAc);
                Orders order = orderDAO.OrderLast();
                orderDAO.addOrderDetails(order, itemList);
                request.setAttribute("size", 0);
                
                
                url = home;
            }
        } catch (Exception e) {
            out.println(e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
