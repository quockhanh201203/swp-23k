/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import DAO.ReservationDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
@WebServlet(name="UpdateReservationStatus", urlPatterns={"/UpdateReservationStatus"})
public class UpdateReservationStatus extends HttpServlet {
   
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
            out.println("<title>Servlet UpdateReservationStatus</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateReservationStatus at " + request.getContextPath () + "</h1>");
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
        int reservationID = Integer.parseInt(request.getParameter("reservationID"));
        String newStatus = request.getParameter("newstatus");

        // Update reservation status in the database
        ReservationDAO reservationDAO = new ReservationDAO();
        reservationDAO.changeReservationStatus(reservationID, newStatus);

        // Get all the query parameters to redirect back to the same page
        String reservationDate = request.getParameter("reservationDate");
        String reservationTime = request.getParameter("reservationTime");
        String numberOfGuests = request.getParameter("numberOfGuests");
        String status = request.getParameter("status");
        String tableName = request.getParameter("tableName");
        String customerName = request.getParameter("customerName");
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        String page = request.getParameter("page");
        String myreservation = request.getParameter("myreservation");

        if(myreservation == null){
        // Build the redirect URL with all parameters
        String redirectURL = "ReservationList?page=" + page +
                "&reservationDate=" + reservationDate +
                "&reservationTime=" + reservationTime +
                "&numberOfGuests=" + numberOfGuests +
                "&status=" + status +
                "&tableName=" + tableName +
                "&customerName=" + customerName +
                "&phoneNumber=" + phoneNumber +
                "&email=" + email;
        response.sendRedirect(redirectURL);
        return;
        }else{
            String redirectURL = "myReservation?page=" + page +
                "&reservationDate=" + reservationDate +
                "&reservationTime=" + reservationTime +
                "&numberOfGuests=" + numberOfGuests +
                "&status=" + status +
                "&tableName=" + tableName +
                "&customerName=" + customerName +
                "&phoneNumber=" + phoneNumber +
                "&email=" + email;
            response.sendRedirect(redirectURL);
            return;
        }
        // Redirect back to the reservation list
       
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
