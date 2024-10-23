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
@WebServlet(name="ReservationList", urlPatterns={"/ReservationList"})
public class ReservationList extends HttpServlet {
   
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
            out.println("<title>Servlet ReservationList</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ReservationList at " + request.getContextPath () + "</h1>");
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
        ReservationDAO reservationDAO = new ReservationDAO();
        // Retrieve search parameters
        Date reservationDate = request.getParameter("reservationDate") != null ? 
                               Date.valueOf(request.getParameter("reservationDate")) : null;
        Time reservationTime = request.getParameter("reservationTime") != null ? 
                               Time.valueOf(request.getParameter("reservationTime")) : null;
        Integer numberOfGuests = request.getParameter("numberOfGuests") != null ? 
                                 Integer.valueOf(request.getParameter("numberOfGuests")) : null;
        String status = request.getParameter("status");
        String tableName = request.getParameter("tableName");
        String customerName = request.getParameter("customerName");
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        
        // Perform search
        List<TableReservation> reservations = reservationDAO.searchReservations(
            reservationDate, reservationTime, numberOfGuests, status, tableName, 
            customerName, phoneNumber, email);
        // Set attributes for the JSP
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
