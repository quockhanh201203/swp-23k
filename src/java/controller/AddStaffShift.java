/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.ShiftDAO;
import DAO.StaffDAO;
import Model.Staff;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import Model.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "AddStaffShift", urlPatterns = {"/AddStaffShift"})
public class AddStaffShift extends HttpServlet {

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
            out.println("<title>Servlet AddStaff</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddStaff at " + request.getContextPath() + "</h1>");
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
        StaffDAO sd = new StaffDAO();
        ShiftDAO shd = new ShiftDAO();
        String shiftID = request.getParameter("shiftID");
        String weekParam = request.getParameter("week");
        String searchStaff = request.getParameter("searchStaff");

        String search = request.getParameter("search");
        String sortColumn = request.getParameter("sortColumn");
        String sortOrder_raw = request.getParameter("sortOrder");
        String page_raw = request.getParameter("page");
        int page = 1;
        if (page_raw != null && !page_raw.trim().isEmpty()) {
            page = Integer.parseInt(page_raw);
        }
        int recordsPerPage = 10;
        boolean sortOrder = true;
        if (sortOrder_raw != null) {
            if (sortOrder_raw.equals("desc")) {
                sortOrder = false;
            }
        }

        // Fetch filtered, sorted, paginated staff list
        List<Staff> staffList = sd.findStaffByPage(page, recordsPerPage, search, sortColumn, sortOrder);
        //List<Staff> staffList = sd.findStaffByPage(page, 1, search, sortColumn, sortOrder);
        int totalRecords = sd.getTotalPages(recordsPerPage, search);
        int totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);

        Shift shift = new Shift();

        try {
            shift = shd.getShiftByShiftID(Integer.parseInt(shiftID));
        } catch (Exception ex) {
        }

        request.setAttribute("shift", shift);
        request.setAttribute("shiftID", shiftID);
        request.setAttribute("weekParam", weekParam);
        request.setAttribute("searchStaff", searchStaff);
        request.setAttribute("staffList", staffList);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("sortColumn", sortColumn);
        request.setAttribute("sortOrder", sortOrder);
        request.setAttribute("search", search);
        // Forward to JSP
        request.getRequestDispatcher("AddStaffShift.jsp").forward(request, response);
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
        // Retrieve form parameters
        String staffID = request.getParameter("staffID");
        String currentPage = request.getParameter("page");
        String shiftID = request.getParameter("shiftID");
        String weekParam = request.getParameter("weekParam");
        String searchStaff = request.getParameter("searchStaff");
        String search = request.getParameter("search");
        String sortColumn = request.getParameter("sortColumn");
        String sortOrder = request.getParameter("sortOrder");
        
        ShiftDAO shd = new ShiftDAO();
        ShiftDAO sd = new ShiftDAO();

        sd.addShiftStaff(Integer.parseInt(shiftID), Integer.parseInt(staffID), "future");

        // Set the retrieved values as request attributes to forward to the JSP
        Shift shift = new Shift();
        try {
            shift = shd.getShiftByShiftID(Integer.parseInt(shiftID));
        } catch (Exception ex) {
        }

        request.setAttribute("shift", shift);
        request.setAttribute("staffID", staffID);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("shiftID", shiftID);
        request.setAttribute("weekParam", weekParam);
        request.setAttribute("searchStaff", searchStaff);
        request.setAttribute("search", search);
        request.setAttribute("sortColumn", sortColumn);
        request.setAttribute("sortOrder", sortOrder);

        // Forward to AddStaffShift.jsp
        request.getRequestDispatcher("AddStaffShift.jsp").forward(request, response);
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
