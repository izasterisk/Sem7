/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccountManager;


import dal.AccountDAO;
import model.Account;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author vipha
 */
@WebServlet(urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    private final String homePage = "ShopPizzaServlet";
    private final String Login = "Login.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String url = Login;
        try {
            Cookie arr[] = request.getCookies();
            for (Cookie cookie : arr) {
                if (cookie.getName().equals("userC")) {
                    request.setAttribute("userName", cookie.getValue());
                }if ( cookie.getName().equals("passwordC")) {
                    request.setAttribute("password", cookie.getValue());
                }
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
        String userName = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String remember = request.getParameter("remember");

        String url = null;
        PrintWriter out = response.getWriter();
        try {

            AccountDAO userDao = new AccountDAO();
            Account account = userDao.login(userName, password);

            if (account != null) {
                HttpSession session = request.getSession();
                session.setAttribute("account", account);
                url = homePage;
                if (remember.equalsIgnoreCase("ON")) {
                    Cookie userC = new Cookie("userC", userName);
                    Cookie passwordC = new Cookie("passwordC", password);
                    userC.setMaxAge(60*5);
                    passwordC.setMaxAge(60*5);
                }
            } else {
                String message = "Account don't exits. Please try again!!!";
                request.setAttribute("message", message);
                url = Login;
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
