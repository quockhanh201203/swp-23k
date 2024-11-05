/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import DAO.OrderDAO;
import Model.orderDto;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author ADMIN
 */
@WebServlet(name="OrderList", urlPatterns={"/OrderList"})
public class OrderList extends HttpServlet {
   
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
            out.println("<title>Servlet OrderList</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OrderList at " + request.getContextPath () + "</h1>");
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
        OrderDAO orderDAO = new OrderDAO();
        // Get parameters for search and pagination
        String guestNote = request.getParameter("guestNote");
        String customerName = request.getParameter("customerName");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String productName = request.getParameter("productName");
        
        int currentPage = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;
        int limit = 10; // Set your limit for pagination
        int offset = (currentPage - 1) * limit;

        // Fetch orders from the database
        List <orderDto> orders = orderDAO.searchOrders(null, guestNote, customerName, email, phoneNumber, productName, productName, productName, offset, limit);
        
        // Set attributes for the JSP
        request.setAttribute("orders", orders);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPages", calculateTotalPages(null, guestNote, customerName, email, phoneNumber, productName, limit)); 

        // Forward to the JSP page
        request.getRequestDispatcher("OrderHistory.jsp").forward(request, response);
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

    
    private int calculateTotalPages(Integer customerID, String guestNote, String customerName, 
                                 String email, String phoneNumber, String productName, int limit) {
        OrderDAO o = new OrderDAO();
    int totalOrders = o.countOrders(customerID, guestNote, customerName, email, phoneNumber, productName);
    return (totalOrders + limit - 1) / limit; // This calculates the total pages
}
}
