/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import DAO.VoucherDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import Model.Voucher;

/**
 *
 * @author Admin
 */
@WebServlet(name="VoucherServlet", urlPatterns={"/voucher"})
public class VoucherServlet extends HttpServlet {
   
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
            out.println("<title>Servlet VoucherServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet VoucherServlet at " + request.getContextPath () + "</h1>");
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
          VoucherDAO voucherDAO = new VoucherDAO();
            String action = request.getParameter("action");

        // Xử lý hành động delete
        if (action != null && action.equals("delete")) {
            int id = Integer.parseInt(request.getParameter("id"));
            voucherDAO.deleteVoucher(id);
            response.sendRedirect("voucher");
            return;  // Thoát ra để không thực hiện tiếp code hiển thị danh sách
        }

        // Xử lý hành động tìm kiếm
        if (action != null && action.equals("search")) {
            String searchTerm = request.getParameter("searchTerm");
            List<Voucher> vouchers = voucherDAO.searchVouchersByName(searchTerm);
            request.setAttribute("vouchers", vouchers);
            request.getRequestDispatcher("vouchers.jsp").forward(request, response);
            return;  // Thoát ra để không thực hiện tiếp code hiển thị danh sách
        }

        // Mặc định: Hiển thị danh sách tất cả voucher
        List<Voucher> vouchers = voucherDAO.getAllVouchers();
        request.setAttribute("vouchers", vouchers);
        request.getRequestDispatcher("vouchers.jsp").forward(request, response);
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
                  VoucherDAO voucherDAO = new VoucherDAO();
 String action = request.getParameter("action");

        // Xử lý thêm mới voucher
        if (action.equals("add")) {
            String name = request.getParameter("voucherName");
            double value = Double.parseDouble(request.getParameter("value"));

            Voucher voucher = new Voucher();
            voucher.setVoucherName(name);
            voucher.setValue(value);
            voucherDAO.insertVoucher(voucher);

            response.sendRedirect("voucher");
        } 
        // Xử lý cập nhật voucher
        else if (action.equals("update")) {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("voucherName");
            double value = Double.parseDouble(request.getParameter("value"));

            Voucher voucher = new Voucher();
            voucher.setVoucherID(id);
            voucher.setVoucherName(name);
            voucher.setValue(value);
            voucherDAO.updateVoucher(voucher);

            response.sendRedirect("voucher");
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
