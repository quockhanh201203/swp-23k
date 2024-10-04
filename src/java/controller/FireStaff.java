/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.*;
import Model.*;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import utils.EmailUtility;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "FireStaff", urlPatterns = {"/FireStaff"})
public class FireStaff extends HttpServlet {

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
            out.println("<title>Servlet FireStaff</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FireStaff at " + request.getContextPath() + "</h1>");
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
        // Retrieve parameters from the request
        String staffID = request.getParameter("staffID");
        String currentPage = request.getParameter("page");
        String accountID = request.getParameter("accountID");
        String search = request.getParameter("search");
        String sortColumn = request.getParameter("sortColumn");
        String sortOrder = request.getParameter("sortOrder");

        // Push the retrieved staff details and parameters to FireStaff.jsp
        request.setAttribute("staffID", staffID);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("accountID", accountID);
        request.setAttribute("search", search);
        request.setAttribute("sortColumn", sortColumn);
        request.setAttribute("sortOrder", sortOrder);

        // Forward the request to FireStaff.jsp
        request.getRequestDispatcher("/FireStaff.jsp").forward(request, response);
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
        String reason = request.getParameter("reason");
        String staffID_raw = request.getParameter("staffID");
        String currentPage = request.getParameter("currentPage");
        String accountID_raw = request.getParameter("accountID");
        String search = request.getParameter("search");
        String sortColumn = request.getParameter("sortColumn");
        String sortOrder = request.getParameter("sortOrder");
        String message = "";

        if (staffID_raw != null && !staffID_raw.isEmpty()) {
            int staffID = Integer.parseInt(staffID_raw);
            int accountID = Integer.parseInt(accountID_raw);

            // Call the DAO method to update the staff's AccountID to null (or fire them)
            try {
                StaffDAO sd = new StaffDAO();
                AccountDAO ad = new AccountDAO();
                EmailUtility eu = new EmailUtility();
                String staffEmail = ad.findEmailByAccountID(accountID);
                boolean success = sd.FireStaff(accountID); // Modify this method to accept a reason

                if (success) {
                    eu.sendEmail(staffEmail, "Bạn đã bị sa thải", reason); // Implement email sending function

                    message = "Staff member fired successfully.";
                } else {
                    message = "Failed to fire staff member.";
                }
            } catch (Exception e) {
                e.printStackTrace();
                message = "An error occurred while firing staff member.";
            }

            String redirectUrl = String.format("StaffManage?page=%s&search=%s&sortColumn=%s&sortOrder=%s&message=%s",
                    currentPage,
                    URLEncoder.encode(search, "UTF-8"),
                    URLEncoder.encode(sortColumn, "UTF-8"),
                    URLEncoder.encode(sortOrder, "UTF-8"),
                    URLEncoder.encode(message, "UTF-8"));
            response.sendRedirect(redirectUrl);
        } else {
            response.sendRedirect("StaffManage?page=1"); // Default to page 1 if staffID is not valid
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
