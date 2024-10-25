/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.SalaryDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.List;
import model.Salary;

/**
 *
 * @author Admin
 */
@WebServlet(name="SalaryServlet", urlPatterns={"/salaryList"})
public class SalaryServlet extends HttpServlet {
   
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
            out.println("<title>Servlet SalaryServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SalaryServlet at " + request.getContextPath () + "</h1>");
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
       SalaryDAO dao = new SalaryDAO();
        List<Salary> salaryList = dao.getAllSalariesWithStaff();
        
        request.setAttribute("salaryList", salaryList);
        request.getRequestDispatcher("salary.jsp").forward(request, response);
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
           // Handle form submission for adding a salary
        try {
            String salaryPlus = request.getParameter("salaryPlus");
            String salaryMinus = request.getParameter("salaryMinus");
            String date = request.getParameter("date");
            String note = request.getParameter("note");
            String staffID = request.getParameter("staffID");

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsedDate = formatter.parse(date);

            Salary salary = new Salary();
            salary.setSalaryPlus(Integer.parseInt(salaryPlus));
            salary.setSalaryMinus(Integer.parseInt(salaryMinus));
            salary.setDate(parsedDate);
            salary.setNote(note);
            salary.setStaffID(Integer.parseInt(staffID));

            SalaryDAO dao = new SalaryDAO();
            boolean result = dao.addSalary(salary);

            if (result) {
                request.setAttribute("message", "Salary record added successfully!");
            } else {
                request.setAttribute("message", "Error adding salary record.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "Error processing request: " + e.getMessage());
        }

        response.sendRedirect("salaryList");
    
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
