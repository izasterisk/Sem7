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
import javax.servlet.http.HttpSession;
import model.Item;
import model.Products;

/**
 *
 * @author vipha
 */
@WebServlet(name = "EditQuantity", urlPatterns = {"/EditQuantity"})
public class EditQuantity extends HttpServlet {

    private final String cartPage = "Cart.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
       
        String num = request.getParameter("num");
        String productID = request.getParameter("productID");
        
        
        String url = null;
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
            
            CartDAO cartList = new CartDAO(txtdecode, productList);
            int numInt = Integer.parseInt(num);
            Products product = productDAO.getProductByID(Integer.parseInt(productID));
            int numShop = product.getQuantityPerUnit();
            if (numInt == -1 && (cartList.getQuantityById(Integer.parseInt(productID))) <= 1) {
                cartList.removeItem(Integer.parseInt(productID));
            }else{
                if ((numInt == 1) && cartList.getQuantityById(Integer.parseInt(productID))  >= numShop) {
                    numInt = 0;
                }else{
                    float price =  product.getUnitPrice()*2;
                    Item item = new Item(product, numInt, price);
                    cartList.addItem(item);
                }
            }
            
            List<Item> item = cartList.getListItems();
            txtdecode = "";
            if (item.size()>0) {
                txtdecode = item.get(0).getProducts().getProductID()+ ":" + item.get(0).getQuantity(); 
                for (int i = 1; i < item.size(); i++) {
                    txtdecode += "," + item.get(i).getProducts().getProductID() + ":" + item.get(i).getQuantity() ;
                }
            }
            
            
 
            if (item != null) {
                request.setAttribute("listItem1", item);
                request.setAttribute("size", item.size());
                double money = cartList.getTotalMoney();
                DecimalFormat decimalFormat = new DecimalFormat("#.##");
                String roundedMoney = decimalFormat.format(money);
                request.setAttribute("money", roundedMoney);
                double moneyVAT = money + money*10/100;
                String roundedMoneyVAT = decimalFormat.format(moneyVAT);
                request.setAttribute("roundedMoneyVAT", roundedMoneyVAT);
            }
                String endCode = productDAO.encodeToBase64(txtdecode);
                Cookie c = new Cookie("cart", endCode);
                c.setMaxAge(24 * 60 * 60);
                response.addCookie(c);
            url = cartPage;
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

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
