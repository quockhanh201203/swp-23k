/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.AccountDAO;
import DAO.StaffDAO;
import DAO.UserDAO;
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
@WebServlet(name = "UpdateStaffSalary", urlPatterns = {"/UpdateStaffSalary"})
public class UpdateStaffSalary extends HttpServlet {

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
            out.println("<title>Servlet UpdateStaffSalary</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateStaffSalary at " + request.getContextPath() + "</h1>");
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
        String staffID = request.getParameter("staffID");
        String currentPage = request.getParameter("page");
        String accountID = request.getParameter("accountID");
        String search = request.getParameter("search");
        String sortColumn = request.getParameter("sortColumn");
        String sortOrder = request.getParameter("sortOrder");
        double salary = sd.findStaffByStaffID(Integer.parseInt(staffID)).getSalary();

        request.setAttribute("salary", salary);
        request.setAttribute("staffID", staffID);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("accountID", accountID);
        request.setAttribute("search", search);
        request.setAttribute("sortColumn", sortColumn);
        request.setAttribute("sortOrder", sortOrder);

        request.getRequestDispatcher("/UpdateStaffSalary.jsp").forward(request, response);
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
        // Retrieve parameters from the request
        String newSalaryRaw = request.getParameter("newSalary");
        String staffIDRaw = request.getParameter("staffID");
        String currentPage = request.getParameter("currentPage");
        String search = request.getParameter("search");
        String sortColumn = request.getParameter("sortColumn");
        String sortOrder = request.getParameter("sortOrder");
        String message = "";

        // Check if the staffID and newSalary are provided and valid
        if (staffIDRaw != null && !staffIDRaw.isEmpty() && newSalaryRaw != null && !newSalaryRaw.isEmpty()) {
            try {
                int staffID = Integer.parseInt(staffIDRaw);
                int newSalary = Integer.parseInt(newSalaryRaw);

                // Call the DAO method to update the staff salary
                StaffDAO staffDAO = new StaffDAO();
                String result = staffDAO.updateStaffSalary(staffID, newSalary);

                if ("Success".equals(result)) {
                    message = "Cập nhật lương thành công.";
                    String redirectUrl = String.format("StaffManage?page=%s&search=%s&sortColumn=%s&sortOrder=%s&message=%s",
                            currentPage != null ? currentPage : "1",
                            URLEncoder.encode(search != null ? search : "", "UTF-8"),
                            URLEncoder.encode(sortColumn != null ? sortColumn : "", "UTF-8"),
                            URLEncoder.encode(sortOrder != null ? sortOrder : "", "UTF-8"),
                            URLEncoder.encode(message, "UTF-8"));
                    response.sendRedirect(redirectUrl);
                    return;
                } else {
                    message = "Cập nhật lương thất bại.";
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                message = "Số ID nhân viên hoặc lương không hợp lệ.";
            } catch (Exception e) {
                e.printStackTrace();
                message = "Đã xảy ra lỗi trong khi cập nhật lương.";
            }

            // Construct the redirect URL with parameters
            String redirectUrl = String.format("?page=%s&search=%s&sortColumn=%s&sortOrder=%s&staffID=%s&message=%s",
                    currentPage != null ? currentPage : "1",
                    URLEncoder.encode(search != null ? search : "", "UTF-8"),
                    URLEncoder.encode(sortColumn != null ? sortColumn : "", "UTF-8"),
                    URLEncoder.encode(sortOrder != null ? sortOrder : "", "UTF-8"),
                    URLEncoder.encode(staffIDRaw != null ? staffIDRaw : "", "UTF-8"),
                    URLEncoder.encode(message, "UTF-8"));
            response.sendRedirect(redirectUrl);

        } else {
            // Redirect to the staff management page if parameters are missing
            response.sendRedirect("StaffManage?page=1");
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
