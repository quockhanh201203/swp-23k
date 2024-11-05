/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import DAO.SalaryDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import Model.Salary;

/**
 *
 * @author Admin
 */
@WebServlet(name="UpdateSalaryServlet", urlPatterns={"/updateSalary"})
public class UpdateSalaryServlet extends HttpServlet {
   
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
            out.println("<title>Servlet UpdateSalaryServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateSalaryServlet at " + request.getContextPath () + "</h1>");
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
        processRequest(request, response);
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
            SalaryDAO d = new SalaryDAO();
           int salaryID = Integer.parseInt(request.getParameter("salaryID"));
int salaryPlus = Integer.parseInt(request.getParameter("salaryPlus"));
int salaryMinus = Integer.parseInt(request.getParameter("salaryMinus"));
String note = request.getParameter("note");

// Chuyển đổi ngày từ request
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
java.sql.Date sqlDate = null;
try {
    java.util.Date utilDate = sdf.parse(request.getParameter("date"));  // Chuyển đổi chuỗi sang java.util.Date
    sqlDate = new java.sql.Date(utilDate.getTime());  // Chuyển đổi java.util.Date sang java.sql.Date
} catch (ParseException e) {
    e.printStackTrace();
}
boolean isUpdated = d.updateSalary(salaryPlus, salaryMinus, sqlDate, note, salaryID);

// Phản hồi lại kết quả cho người dùng
if (isUpdated) {
    response.sendRedirect("salaryList");
} else {
    response.getWriter().println("Failed to update salary.");
}
    
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
