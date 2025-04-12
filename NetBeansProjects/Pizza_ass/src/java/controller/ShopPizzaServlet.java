
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CartDAO;
import dal.ProductsDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.Categories;
import model.Item;
import model.Products;
import model.Suppliers;

/**
 *
 * @author vipha
 */
@WebServlet(name = "ShopPizzaServlet", urlPatterns = {"/ShopPizzaServlet"})
public class ShopPizzaServlet extends HttpServlet {

    private final String homePage = "HomeShop.jsp";
    private final String ManagerProduct = "ManagerProduct.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String url = null;

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");

        String id = request.getParameter("id");

        try {

            ProductsDAO productsDAO = new ProductsDAO();
            List<Products> productList = productsDAO.getProductList();
            request.setAttribute("productList", productList);

            List<Categories> cateList = productsDAO.getCateList();
            request.setAttribute("cateList", cateList);
            List<Suppliers> suppList = productsDAO.getSuppliersList();
            request.setAttribute("suppList", suppList);

            String txt = "";
            String txtdecode = null;
            String txtdecode1 = (String)request.getAttribute("txtdecode1");
            String outString = (String)request.getAttribute("outString");
            if (txtdecode1 != null) {
                txtdecode = txtdecode1;
            }else if (outString != null) {
                txtdecode = outString;
            } 
            if (txtdecode == null) {
                Cookie[] arr = request.getCookies();
                if (arr != null) {
                    for (Cookie o : arr) {
                        if (o.getName().equals("cart")) {
                            txt += o.getValue();
                        }
                    }
                }
                if (!txt.isEmpty()) {
                    txtdecode = productsDAO.decodeFromBase64(txt);
                } else {
                    txtdecode = "";
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
                double moneyVAT = money + money * 10 / 100;
                String roundedMoneyVAT = decimalFormat.format(moneyVAT);
                request.setAttribute("roundedMoneyVAT", roundedMoneyVAT);
            }
            String endCode = productsDAO.encodeToBase64(txtdecode);
            Cookie c = new Cookie("cart", endCode);
            c.setMaxAge(24 * 60 * 60);
            response.addCookie(c);

            url = homePage;
            if (account.getRole() == 1 && id.equalsIgnoreCase("admin")) {
                url = ManagerProduct;
            }

        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
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
