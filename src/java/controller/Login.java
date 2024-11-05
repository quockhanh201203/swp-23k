/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.LoginDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import model.Account;
import model.guest;

/**
 *
 * @author tran tung
 */
@WebServlet(name="Login", urlPatterns={"/login"})
public class Login extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Login</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Login at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
            








    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        LoginDAO ld = new LoginDAO();
        String loginType= request.getParameter("loginType");
        
        if(  loginType.equals("wl") ){
            String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        Account ac = ld.login(username,hashPassword(password) );
        Account account = ld.getId(username);
             
            if (ac == null || ac.equals(ac.getUsername())) {
                String error = "Incorrect username or password";
                request.setAttribute("error", error);
                request.getRequestDispatcher("Login.jsp").forward(request, response);
              
                 
                
            }else{
                
                 HttpSession session = request.getSession();
                session.setAttribute("id", account.getAccountID());
                session.setAttribute("username", username);
                session.setAttribute("password", password);
                   String goc = "customer";
            session.setAttribute("goc", goc);
            System.out.println("Session ID attribute: " + session.getAttribute("id"));
              System.out.println("Session goc attribute: " + session.getAttribute("goc"));

            System.out.println("Session Username attribute: " + session.getAttribute("username"));
            System.out.println("Session Password attribute: " + session.getAttribute("password"));
               
                    response.sendRedirect("homepage");
                }
        }else{
            
            HttpSession session = request.getSession();

            String guestname = request.getParameter("guestname");
            String phone = request.getParameter("phone");   
            ld.newGuest(guestname, phone);
           int guestID = ld.getGuestId(guestname);
            session.setAttribute("guestID", guestID);

            session.setAttribute("guestname", guestname);
                   String goc = "guest";
            session.setAttribute("goc", goc);
            System.out.println("Session goc attribute: " + session.getAttribute("goc"));
            System.out.println("Session ID attribute: " + session.getAttribute("guestID"));
            System.out.println("Session Username attribute: " + session.getAttribute("guestname"));

                
        response.sendRedirect("homepage");

            
        }
        
    }
      public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(password.getBytes());

            // Chuyển đổi kết quả từ byte thành chuỗi hexa
            StringBuilder sb = new StringBuilder();
            for (byte b : messageDigest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
