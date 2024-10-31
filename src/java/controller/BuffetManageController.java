/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.MenuDAO;
import Model.Buffet;
import Model.PriceHistory;
import Model.Product;
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
 * @author Nhat Anh
 */
@WebServlet(name = "BuffetManageController", urlPatterns = {"/buffets"})
public class BuffetManageController extends HttpServlet {

    private MenuDAO menuDAO;

    @Override
    public void init() throws ServletException {
        menuDAO = new MenuDAO();
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
        List<Buffet> buffetList = menuDAO.getAllBuffets();
        request.setAttribute("buffetList", buffetList);
        request.getRequestDispatcher("buffet-manage.jsp").forward(request, response);
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
        String action = request.getParameter("action"); // "add" or "update"
        if ("add".equals(action)) {
            addBuffet(request);
        } else if ("update".equals(action)) {
            updateBuffet(request);
        }
        response.sendRedirect("buffets");
    }

    private void addBuffet(HttpServletRequest request) {
        String buffetName = request.getParameter("buffetName");
        String buffetImage = request.getParameter("buffetImage");
        double buffetPrice = Double.parseDouble(request.getParameter("buffetPrice"));

        // Create a Buffet object and save it to the database
        Buffet buffet = new Buffet(0, buffetName, buffetImage);
        int bufferID = menuDAO.addBuffet(buffet); // Assume this method exists in your DAO
        Product product = new Product(0, 0, bufferID, 0, buffetPrice);
        int productID = menuDAO.insertProduct(product);
        PriceHistory priceHistory = new PriceHistory(productID, buffetPrice, null, null);
        menuDAO.insertPriceHistory(priceHistory);
    }

    private void updateBuffet(HttpServletRequest request) {
        int buffetId = Integer.parseInt(request.getParameter("buffetId")); // Assuming buffetId is passed for updates
        String buffetName = request.getParameter("buffetName");
        String buffetImage = request.getParameter("image");
        double price = Double.parseDouble(request.getParameter("price"));

        // Update the existing buffet in the database
        Buffet buffet = new Buffet(buffetId, buffetName, buffetImage);
        menuDAO.updateBuffet(buffet); // Assume this method exists in your DAO
        
        Product product = new Product(0, 0, buffetId, 0, price);
        menuDAO.updateProduct(product);
        menuDAO.getProductId(product);
        int productID = product.getProductId();
        PriceHistory priceHistory = new PriceHistory(productID, price, null, null);
        menuDAO.insertPriceHistory(priceHistory);
    }

}
