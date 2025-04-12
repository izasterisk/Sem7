package AccountManager;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */




import dal.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;


/**
 *
 * @author vipha
 */
@WebServlet(name = "SignUpServlet", urlPatterns = {"/SignUpServlet"})
public class SignUpServlet extends HttpServlet {

    private final String homePage = "ShopPizzaServlet";
    private final String SignUpPage = "Login.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String url = null;

        try {
            String fullName = request.getParameter("fullName");
            String userId = request.getParameter("userId");
            String password = request.getParameter("password");
            String passwordCheck = request.getParameter("passwordConfirm");

            if (password.equals(passwordCheck)) {
                AccountDAO accountDAO = new AccountDAO();
                Account account = new Account(0, userId, password, fullName, 2);
                boolean check = accountDAO.Register(account);
                if (check) {
                    HttpSession session = request.getSession();
                    session.setAttribute("account", account);
                    url = homePage;
                } else {
                    String message = "Có vẻ như userId đã có. Vui lòng thử lại.";
                    request.setAttribute("message", message);
                    request.setAttribute("showSignup", "true"); 
                    url = SignUpPage;
                }
            } else {
                String message = "Có vẻ như bạn đã nhập sai mật khẩu xác nhận. Vui lòng kiểm tra lại và thử lại.";
                request.setAttribute("message", message);

                request.setAttribute("showSignup", "true"); 
                url = SignUpPage;
            }

        } catch (Exception e) {
            out.println(e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
