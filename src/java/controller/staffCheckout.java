/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import DAO.LoginDAO;
import DAO.tableDAOt;
import DAO.voucherCheckoutDAO;
import Model.tableT;
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
 * @author tran tung
 */
@WebServlet(name="staffCheckout", urlPatterns={"/staffcheckout"})
public class staffCheckout extends HttpServlet {
   
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
            out.println("<title>Servlet staffCheckout</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet staffCheckout at " + request.getContextPath () + "</h1>");
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
      tableDAOt td = new tableDAOt();

        List<tableT> tableList = td.getTableList();
        request.setAttribute("tableList", tableList);
        request.getRequestDispatcher("tableCheckout.jsp").forward(request, response);

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
                String customerName = request.getParameter("customerName");
                LoginDAO ld = new LoginDAO();
                int customerID = ld.getCustomerIdbyName(customerName);
                String action = request.getParameter("action");
                switch (action){
                    case "searchvoucher":
                         voucherCheckoutDAO vd = new voucherCheckoutDAO();
              List<Model.dao.voucherCustomer> voucherList = vd.getCustomerVoucher(customerID);
              request.setAttribute("voucherList", voucherList);
request.getRequestDispatcher("staffCheckout.jsp").forward(request, response);       
break;
               
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
