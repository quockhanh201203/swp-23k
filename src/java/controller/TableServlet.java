/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.TableDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Table;

/**
 *
 * @author Admin
 */
@WebServlet(name = "TableServlet", urlPatterns = {"/TableServlet"})
public class TableServlet extends HttpServlet {

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
            out.println("<title>Servlet TableServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TableServlet at " + request.getContextPath() + "</h1>");
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
        String action = request.getParameter("action");
        TableDAO dao = new TableDAO();

        if (action == null || action.equals("list")) {
            // Hiển thị danh sách bảng
            ArrayList<Table> tableList = dao.getAllTables();
            request.setAttribute("tables", tableList);
            request.getRequestDispatcher("table.jsp").forward(request, response);
        } else if (action.equals("edit")) {
            // Chỉnh sửa bảng
            int id = Integer.parseInt(request.getParameter("tableID"));
            Table table = dao.getTableById(id);
            request.setAttribute("editTable", table);
            request.getRequestDispatcher("table.jsp").forward(request, response);
        } else if (action.equals("delete")) {
            // Xóa bảng
            int id = Integer.parseInt(request.getParameter("tableID"));
            dao.deleteTable(id);
            response.sendRedirect("TableServlet?action=list");
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
        String action = request.getParameter("action");
        TableDAO dao = new TableDAO();

        // Lưu hoặc cập nhật bảng
        String tableName = request.getParameter("tableName");
        String status = request.getParameter("status");

        if (action.equals("add")) {
            // Thêm mới
            Table newTable = new Table(0, tableName, status);
            dao.insertTable(newTable);
        } else if (action.equals("update")) {
            // Cập nhật bảng
            int tableID = Integer.parseInt(request.getParameter("tableID"));
            Table updatedTable = new Table(tableID, tableName, status);
            dao.updateTable(updatedTable);
        }
        response.sendRedirect("TableServlet?action=list");

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
