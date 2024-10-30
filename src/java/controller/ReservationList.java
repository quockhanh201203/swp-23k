/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.ReservationDAO;
import Model.TableReservation;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "ReservationList", urlPatterns = {"/ReservationList"})
public class ReservationList extends HttpServlet {

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
            out.println("<title>Servlet ReservationList</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ReservationList at " + request.getContextPath() + "</h1>");
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
        ReservationDAO reservationDAO = new ReservationDAO();

        // Retrieve search parameters
        Date reservationDate = null;
        try {
            String reservationDateStr = request.getParameter("reservationDate");
            if (reservationDateStr != null && !reservationDateStr.isEmpty()) {
                reservationDate = Date.valueOf(reservationDateStr); // Converts to java.sql.Date
            }
        } catch (IllegalArgumentException e) {
            // Handle the case where the date is invalid
            reservationDate = null; // or set to a default date if needed
            System.err.println("Invalid reservation date: " + e.getMessage());
        }

        Time reservationTime = null;
        if (request.getParameter("reservationTime") != null) {
            try {
                reservationTime = Time.valueOf(request.getParameter("reservationTime")); // Converts to java.sql.Time
            } catch (IllegalArgumentException e) {
                reservationTime = null; // Handle invalid time format
                System.err.println("Invalid reservation time: " + e.getMessage());
            }
        }

        Integer numberOfGuests = null;
        String numberOfGuestsStr = request.getParameter("numberOfGuests");
        if (numberOfGuestsStr != null && !numberOfGuestsStr.trim().isEmpty()) {
            try {
                numberOfGuests = Integer.valueOf(numberOfGuestsStr);
            } catch (NumberFormatException e) {
                // Handle the case where the string is not a valid integer
                numberOfGuests = null; // or set to a default value if needed
                System.err.println("Invalid number of guests: " + e.getMessage());
            }
        }
        String status = request.getParameter("status");
        String tableName = request.getParameter("tableName");
        String customerName = request.getParameter("customerName");
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");

        int recordsPerPage = 5;
        int currentPage = 1;
        if (request.getParameter("page") != null) {
            currentPage = Integer.parseInt(request.getParameter("page"));
        }
        int totalRecords = reservationDAO.getTotalRecords(
                reservationDate, reservationTime, numberOfGuests, status, tableName,
                customerName, phoneNumber, email);
        int totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);
        
        // Perform search
        List<TableReservation> reservations = reservationDAO.searchReservations(
                reservationDate, reservationTime, numberOfGuests, status, tableName,
                customerName, phoneNumber, email, currentPage, recordsPerPage);
        // Set attributes for the JSP

        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("reservations", reservations);
        request.setAttribute("reservationDate", reservationDate);
        request.setAttribute("reservationTime", reservationTime);
        request.setAttribute("numberOfGuests", numberOfGuests);
        request.setAttribute("status", status);
        request.setAttribute("tableName", tableName);
        request.setAttribute("customerName", customerName);
        request.setAttribute("phoneNumber", phoneNumber);
        request.setAttribute("email", email);

        // Forward to JSP
        request.getRequestDispatcher("reservationList.jsp").forward(request, response);
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
