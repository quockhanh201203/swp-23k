/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.DiscountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import Model.Discount;

/**
 *
 * @author Admin
 */
@WebServlet(name = "DiscountServlet", urlPatterns = {"/DiscountServlet"})
public class DiscountServlet extends HttpServlet {

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
            out.println("<title>Servlet DiscountServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DiscountServlet at " + request.getContextPath() + "</h1>");
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
    private DiscountDAO discountDAO;

    @Override
    public void init() throws ServletException {
        discountDAO = new DiscountDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          String action = request.getParameter("action");
        String search = request.getParameter("search");

        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "list":
                if (search != null && !search.trim().isEmpty()) {
                    searchDiscounts(request, response, search);
                } else {
                    listDiscounts(request, response);
                }
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteDiscount(request, response);
                break;
            default:
                listDiscounts(request, response);
                break;
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

        if ("add".equals(action)) {
            addDiscount(request, response);
        } else if ("update".equals(action)) {
            updateDiscount(request, response);
        } else {
            listDiscounts(request, response);
        }
    }

   private void listDiscounts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Discount> discounts = discountDAO.getAllDiscounts();
        request.setAttribute("discounts", discounts);
        request.getRequestDispatcher("discounts.jsp").forward(request, response);
    }

    private void searchDiscounts(HttpServletRequest request, HttpServletResponse response, String search) throws ServletException, IOException {
        List<Discount> discounts = discountDAO.searchDiscountsByName(search);
        request.setAttribute("discounts", discounts);
        request.setAttribute("search", search);
        request.getRequestDispatcher("discounts.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int discountID = Integer.parseInt(request.getParameter("id"));
        Discount existingDiscount = discountDAO.getDiscountByID(discountID);
        request.setAttribute("discount", existingDiscount);
        request.getRequestDispatcher("discountForm.jsp").forward(request, response);
    }

    private void addDiscount(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter("discountName");
        double value = Double.parseDouble(request.getParameter("value"));
        String dateStr = request.getParameter("date");

        java.util.Date date = null;
        try {
            date = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }

        Discount newDiscount = new Discount(0, name, value, date);
        discountDAO.addDiscount(newDiscount);
        response.sendRedirect("DiscountServlet?action=list");
    }

    private void updateDiscount(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("discountID"));
        String name = request.getParameter("discountName");
        double value = Double.parseDouble(request.getParameter("value"));
        String dateStr = request.getParameter("date");

        java.util.Date date = null;
        try {
            date = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }

        Discount discount = new Discount(id, name, value, date);
        discountDAO.updateDiscount(discount);
        response.sendRedirect("DiscountServlet?action=list");
    }

    private void deleteDiscount(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int discountID = Integer.parseInt(request.getParameter("id"));
        discountDAO.deleteDiscount(discountID);
        response.sendRedirect("DiscountServlet?action=list");
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
