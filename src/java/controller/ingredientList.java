/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.MenuDAO;
import dal.ingredientDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.ingredient2;

/**
 *
 * @author tran tung
 */
@WebServlet(name = "ingredientList", urlPatterns = {"/ingredientlist"})
public class ingredientList extends HttpServlet {

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
            out.println("<title>Servlet ingredientList</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ingredientList at " + request.getContextPath() + "</h1>");
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
        ingredientDAO md = new ingredientDAO();
        MenuDAO menu = new MenuDAO();
        List<ingredient2> ingredientList4 = md.getIngredientist2();
        request.setAttribute("ingredientList", ingredientList4);
        request.getRequestDispatcher("newIngredient.jsp").forward(request, response);
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
        ingredientDAO d = new ingredientDAO();

        switch (action) {
            case "add":
                 String addNamea = request.getParameter("name");
                int updateQuantitya = Integer.parseInt(request.getParameter("quantity"));
                d.newIngredient(addNamea, updateQuantitya);
          response.sendRedirect("ingredientlist");

                break;
                
            
            
            
            
            
            
            
            case "edit":
    try {
                // Lấy tham số từ request
                String updateName = request.getParameter("name");
                int updateQuantity = Integer.parseInt(request.getParameter("quantity"));
                int ingredientID = Integer.parseInt(request.getParameter("ingredientID"));

                // Gọi phương thức update của DAO
                d.ingredientUpdate(ingredientID, updateQuantity, updateName);

                // Phản hồi thông báo cập nhật thành công
                response.sendRedirect("ingredientlist");
            } catch (NumberFormatException e) {
                System.out.println("Lỗi định dạng số: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Đã xảy ra lỗi khi cập nhật nguyên liệu: " + e.getMessage());
            }
            break;
            case "delete":
                                int ingredientIDd = Integer.parseInt(request.getParameter("ingredientID"));
                                d.deleteI(ingredientIDd);
                                                response.sendRedirect("ingredientlist");

                break;
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
