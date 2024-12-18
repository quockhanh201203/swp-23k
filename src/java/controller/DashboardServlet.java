/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.DashBoardDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "DashboardServlet", urlPatterns = {"/DashboardServlet"})
public class DashboardServlet extends HttpServlet {

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
            out.println("<title>Servlet DashboardServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DashboardServlet at " + request.getContextPath() + "</h1>");
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
        // Trong DashboardServlet.java
        DashBoardDAO db = new DashBoardDAO();
        try {
            // Doanh thu theo ngày
            List<String> revenueDates = db.getRevenueDates();
            List<Double> revenueValues = db.getRevenueByDate();
            request.setAttribute("revenueDates", revenueDates);
            request.setAttribute("revenueValues", revenueValues);

            // Doanh thu theo loại sản phẩm
            List<String> productTypes = db.getProductTypes();
            List<Double> productTotals = db.getProductRevenue();
            request.setAttribute("productTypes", productTypes);
            request.setAttribute("productTotals", productTotals);

            // Số lượng đơn hàng theo trạng thái
            List<String> orderStatuses = db.getOrderStatuses();
            List<Integer> orderCounts = db.getOrderCountsByStatus();
            request.setAttribute("orderStatuses", orderStatuses);
            request.setAttribute("orderCounts", orderCounts);

            // Lịch sử lương
            List<String> salaryDates = db.getSalaryDates();
            List<Double> salaryValues = db.getSalaryHistory();
            request.setAttribute("salaryDates", salaryDates);
            request.setAttribute("salaryValues", salaryValues);

            // Chuyển tiếp đến trang JSP
            RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
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
