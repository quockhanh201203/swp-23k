/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.CustomerDAO;
import DAO.FeedbackDAO;
import DAO.UserDAO;
import Model.Feedback;
import Model.Responde;
import Model.*;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "myFeedBack", urlPatterns = {"/myFeedBack"})
public class myFeedBack extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
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
            out.println("<title>Servlet myFeedBack</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet myFeedBack at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        FeedbackDAO fbd = new FeedbackDAO();
        CustomerDAO cd = new CustomerDAO();
        HttpSession session = request.getSession();
        UserDAO ud = new UserDAO();
        try {
            Integer accid = (Integer) session.getAttribute("id");
            List<Feedback> feedbackList = fbd.getAllFeedbackByCustomerID(cd.getCustomerByAccountID(accid).getCustomerID());
            // Set the result in request attribute
            request.setAttribute("feedbackList", feedbackList);
            request.getRequestDispatcher("myFeedback.jsp").forward(request, response);
        } catch (Exception e) {
            response.sendRedirect("Login.jsp");
        }

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
        FeedbackDAO fbd = new FeedbackDAO();
        CustomerDAO cd = new CustomerDAO();
        String feedbackNote = request.getParameter("feedbackNote");

        HttpSession session = request.getSession();
        try {
            Integer accid = (Integer) session.getAttribute("id");

            // Check if the user is logged in
            if (accid == null) {
                throw new Exception("Account not Login");
            }

            Customer c = cd.getCustomerByAccountID(accid);

            if (c == null) {
                throw new Exception("Account not Login");
            } else {
                Feedback f = new Feedback();
                f.setFeedbackNote(feedbackNote);
                f.setCustomerID(c.getCustomerID());
                fbd.addFeedback(f);
                response.sendRedirect("myFeedBack");
            }
        } catch (Exception e) {
            response.sendRedirect("Login.jsp");
        }
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
