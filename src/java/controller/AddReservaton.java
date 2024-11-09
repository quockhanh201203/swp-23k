/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.AccountDAO;
import DAO.CustomerDAO;
import DAO.ReservationDAO;
import Model.TableReservation;
import java.sql.Date;
import java.sql.Time;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import Model.*;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "AddReservaton", urlPatterns = {"/AddReservation"})
public class AddReservaton extends HttpServlet {

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
            out.println("<title>Servlet AddReservaton</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddReservaton at " + request.getContextPath() + "</h1>");
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
        ReservationDAO rd = new ReservationDAO();
        AccountDAO ad = new AccountDAO();
        CustomerDAO cd = new CustomerDAO();
        HttpSession session = request.getSession();
        Integer accountID = (Integer) session.getAttribute("id");

        if (accountID == null) {
            response.sendRedirect("error.jsp");
            return;
        }

        // Tìm tài khoản bằng ID
        Account account = ad.findAccountByID(accountID);
        if (account == null) {
            response.sendRedirect("error.jsp");
            return;
        }

        int roleID = account.getRoleID();
        List<Table> tables = rd.getAllTables();

        if (roleID == 1) {
            // Role ID 1: Sử dụng accountID làm customerID
            request.setAttribute("customerID", cd.getCustomerByAccountID(accountID).getCustomerID());
            request.setAttribute("showCustomerSelect", false);
        } else if (roleID == 2 || roleID == 3) {
            // Role ID 2: Hiển thị lựa chọn khách hàng
            List<Customer> customers = rd.getAllCustomers();
            request.setAttribute("customerList", customers);
            request.setAttribute("showCustomerSelect", true);
        } else {
            response.sendRedirect("error.jsp");
            return;
        }
        request.setAttribute("myreservation", request.getAttribute("myreservation"));
        request.setAttribute("tableList", tables);
        request.getRequestDispatcher("AddReservation.jsp").forward(request, response);
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
        ReservationDAO rd = new ReservationDAO();
        AccountDAO ad = new AccountDAO();
        CustomerDAO cd = new CustomerDAO();
        HttpSession session = request.getSession();
        Integer accountID = (Integer) session.getAttribute("id");

        if (accountID == null) {
            response.sendRedirect("error.jsp");
            return;
        }

        Account account = ad.findAccountByID(accountID);
        int customerID;
        if (account.getRoleID() == 1) {
            customerID = cd.getCustomerByAccountID(accountID).getCustomerID();
        } else {
            customerID = Integer.parseInt(request.getParameter("customerID"));
        }

        Date reservationDate = Date.valueOf(request.getParameter("reservationDate"));
        String timeParam = request.getParameter("reservationTime");
        Time reservationTime = null;

        if (timeParam != null) {
            if (timeParam.matches("\\d{2}:\\d{2}")) {
                timeParam += ":00"; // Thêm giây
            }
            if (timeParam.matches("\\d{2}:\\d{2}:\\d{2}")) {
                reservationTime = Time.valueOf(timeParam);
            } else {
                throw new IllegalArgumentException("Invalid time format. Expected hh:mm:ss.");
            }
        }

        int numberOfGuests = Integer.parseInt(request.getParameter("numberOfGuests"));
        int tableID = Integer.parseInt(request.getParameter("tableID"));
        String status = request.getParameter("status");
        String notes = request.getParameter("notes");

        TableReservation reservation = new TableReservation();
        reservation.setReservationDate(reservationDate);
        reservation.setReservationTime(reservationTime);
        reservation.setNumberOfGuests(numberOfGuests);
        reservation.setCustomerID(customerID);
        reservation.setTableID(tableID);
        reservation.setStatus(status);
        reservation.setNotes(notes);

        String resultMessage = rd.addReservation(reservation);
        request.setAttribute("message", resultMessage);
        request.getRequestDispatcher("AddReservation.jsp").forward(request, response);
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
