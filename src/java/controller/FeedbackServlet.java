/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.FeedbackDAO;
import DAO.StaffDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import Model.*;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "FeedbackServlet", urlPatterns = {"/FeedbackList"})
public class FeedbackServlet extends HttpServlet {

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
            out.println("<title>Servlet FeedbackServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FeedbackServlet at " + request.getContextPath() + "</h1>");
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
        // Get search parameters
        String customerName = request.getParameter("customerName");
        String feedbackNote = request.getParameter("feedbackNote");
        String hasResponseParam = request.getParameter("hasResponse");
        boolean onlyWithResponse = hasResponseParam != null && hasResponseParam.equals("on");

        // Get pagination parameters
        int page = 1;
        try{
            page = Integer.parseInt(request.getParameter("page")); 
        }catch(Exception e){
            
        }
                
        int pageSize = 10; // Number of items per page
        int offset = (page - 1) * pageSize;

        // Call the searchFeedback function
        FeedbackDAO fbd = new FeedbackDAO();
        List<Feedback> feedbackList = fbd.searchFeedback(customerName, feedbackNote, onlyWithResponse, offset, pageSize);

        // Set the result in request attribute
        request.setAttribute("feedbackList", feedbackList);
        request.setAttribute("currentPage", page);
        request.setAttribute("pageSize", pageSize);

        // Calculate total pages (you should implement a method in FeedbackDAO to get count)
        int totalFeedbackCount = fbd.getFeedbackCount(customerName, feedbackNote, onlyWithResponse);
        int totalPages = (int) Math.ceil((double) totalFeedbackCount / pageSize);
        request.setAttribute("totalPages", totalPages);

        request.getRequestDispatcher("FeedbackList.jsp").forward(request, response);
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
        StaffDAO std = new StaffDAO();
        FeedbackDAO fbd = new FeedbackDAO();
        String feedbackId = request.getParameter("feedbackId");
        String responseNote = request.getParameter("responseNote");

        HttpSession session = request.getSession();
        try {
            Integer accid = (Integer) session.getAttribute("id");

            // Check if the user is logged in
            if (accid == null) {
                throw new Exception("Account not Login");
            }

            Staff staff = std.findStaffByAccountID(accid);

            if (staff == null) {
                throw new Exception("Account not Login");
            } else {
                Responde r = new Responde();
                r.setFeedBackID(Integer.parseInt(feedbackId));
                r.setRespondeNote(responseNote);
                r.setStaffID(staff.getStaffID());
                fbd.addResponde(r);
                response.sendRedirect("FeedbackList");
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
