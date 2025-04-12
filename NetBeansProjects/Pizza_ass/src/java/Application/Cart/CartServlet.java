/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Application.Cart;


import dal.CartDAO;
import dal.ProductsDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Item;
import model.Products;

/**
 *
 * @author vipha
 */
@WebServlet(name = "CartServlet", urlPatterns = {"/CartServlet"})
public class CartServlet extends HttpServlet {

    private final String cartPage = "Cart.jsp";
    private final String HomePage = "ShopPizzaServlet";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String url = null;
        String productID = request.getParameter("productID");
        String quantity = request.getParameter("quantity");
        String action = request.getParameter("action");

        PrintWriter out = response.getWriter();
        try {

            ProductsDAO productDAO = new ProductsDAO();
            List<Products> productList = productDAO.getProductList();

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

            if (productID != null && quantity != null) {
                if (txtdecode.isEmpty()) {
                    txtdecode = productID + ":" + quantity;
                } else {
                    txtdecode = txtdecode + "," + productID + ":" + quantity;
                }
            }

            CartDAO cartDAO = new CartDAO(txtdecode, productList);
            List<Item> itemList = cartDAO.getListItems();
            
            
            if (itemList != null) {
                request.setAttribute("listItem1", itemList);
                request.setAttribute("size", itemList.size());
                double money = cartDAO.getTotalMoney();
                DecimalFormat decimalFormat = new DecimalFormat("#.##");
                String roundedMoney = decimalFormat.format(money);
                request.setAttribute("money", roundedMoney);
                double moneyVAT = money + money*10/100;
                String roundedMoneyVAT = decimalFormat.format(moneyVAT);
                request.setAttribute("roundedMoneyVAT", roundedMoneyVAT);
                request.setAttribute("txtdecode1", txtdecode);
            }
            String endCode = productDAO.encodeToBase64(txtdecode);
            Cookie c = new Cookie("cart", endCode);
            c.setMaxAge(24 * 60 * 60);
            response.addCookie(c);

            url = cartPage;
            if (action.equals("add")) {
                url = HomePage;
            }
        } catch (Exception e) {
            out.println(e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        String url = null;
        
        String action = request.getParameter("action");
        try {
            ProductsDAO productDAO = new ProductsDAO();
            List<Products> productList = productDAO.getProductList();

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

            String productID = request.getParameter("productID");
            String[] ids = txtdecode.split(",");
            String outString = "";
            for (int i = 0; i < ids.length; i++) {
                String[] s = ids[i].split(":");
                if (!s[0].equals(productID)) {
                    if (outString.isEmpty()) {
                        outString = ids[i];
                    } else {
                        outString += "," + ids[i];
                    }
                }
            }
            CartDAO cartDAO = new CartDAO(outString, productList);
            List<Item> itemList = cartDAO.getListItems();
            if (itemList != null) {
                request.setAttribute("listItem1", itemList);
                request.setAttribute("size", itemList.size());
                double money = cartDAO.getTotalMoney();
                DecimalFormat decimalFormat = new DecimalFormat("#.##");
                String roundedMoney = decimalFormat.format(money);
                request.setAttribute("money", roundedMoney);
                request.setAttribute("outString", outString);
            }
            if (!outString.isEmpty()) {
                String endCode = productDAO.encodeToBase64(outString);
                Cookie c = new Cookie("cart", endCode);
                c.setMaxAge(24 * 60 * 60);
                response.addCookie(c);
            }

            url = cartPage;
            if (action.equals("view")) {
                url = HomePage;
            }

        } catch (Exception e) {
            out.println(e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
