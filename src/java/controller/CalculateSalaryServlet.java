/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.StaffSalaryDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import Model.Salary;

/**
 *
 * @author Admin
 */
@WebServlet(name = "CalculateSalaryServlet", urlPatterns = {"/CalculateSalary"})
public class CalculateSalaryServlet extends HttpServlet {

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
            out.println("<title>Servlet CalculateSalaryServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CalculateSalaryServlet at " + request.getContextPath() + "</h1>");
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
         String staffIDParam = request.getParameter("staffID");
        StaffSalaryDAO salaryDAO = new StaffSalaryDAO();

        // Nếu staffID không nhập hoặc rỗng, cung cấp giá trị mặc định cho JSP
        if (staffIDParam == null || staffIDParam.trim().isEmpty()) {
            request.setAttribute("staffID", "-");
            request.setAttribute("fixedSalary", "-");
            request.setAttribute("salaryPlus", "-");
            request.setAttribute("salaryMinus", "-");
            request.setAttribute("totalSalary", "-");
        } else {
            try {
                int staffID = Integer.parseInt(staffIDParam);
                int fixedSalary = salaryDAO.getFixedSalary(staffID);
                int totalSalary = salaryDAO.calculateTotalSalary(staffID);
                int salaryPlus = salaryDAO.getTotalSalaryPlus(staffID);
                int salaryMinus = salaryDAO.getTotalSalaryMinus(staffID);

                request.setAttribute("staffID", String.valueOf(staffID));
                request.setAttribute("fixedSalary", String.valueOf(fixedSalary));
                request.setAttribute("salaryPlus", String.valueOf(salaryPlus));
                request.setAttribute("salaryMinus", String.valueOf(salaryMinus));
                request.setAttribute("totalSalary", String.valueOf(totalSalary));
            } catch (NumberFormatException e) {
                request.setAttribute("staffID", "Không hợp lệ");
                request.setAttribute("fixedSalary", "-");
                request.setAttribute("salaryPlus", "-");
                request.setAttribute("salaryMinus", "-");
                request.setAttribute("totalSalary", "-");
            }
        }

        request.getRequestDispatcher("salaryResult.jsp").forward(request, response);


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
