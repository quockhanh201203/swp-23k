/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.MenuDAO;
import Model.Food;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Legion
 */
@WebServlet(name = "BuffetDetailController", urlPatterns = {"/buffet-detail"})
public class BuffetDetailController extends HttpServlet {

    private MenuDAO menuDAO;

    @Override
    public void init() throws ServletException {
        menuDAO = new MenuDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int buffetId = Integer.parseInt(request.getParameter("buffetId"));
        List<Food> foods = menuDAO.getFoodsByBuffetId(buffetId);
        List<Food> foodsNotInBuffet = menuDAO.getFoodsNotInBuffet(buffetId);
        request.setAttribute("foods", foods);
        request.setAttribute("buffetId", buffetId);
        request.setAttribute("foodsNotInBuffet", foodsNotInBuffet);
        request.getRequestDispatcher("buffet-detail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        String action = request.getParameter("action"); // "add" or "update"

        if ("add".equals(action)) {
            addFoodBuffet(request, response);
        } else if ("delete".equals(action)) {
            deleteFoodBuffet(request, response);
        }
        
        
        

    }

    private void addFoodBuffet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int buffetId = Integer.parseInt(request.getParameter("buffetId"));
        String[] foodIds = request.getParameterValues("foodIds"); // Array of selected food IDs
        boolean success = false;
        if (foodIds != null) {
            for (String foodIdStr : foodIds) {
                int foodId = Integer.parseInt(foodIdStr);
                success = menuDAO.addFoodToBuffet(buffetId, foodId);

            }
        }

        if (success) {
            response.sendRedirect("buffet-detail?buffetId=" + buffetId + "&success");
        } else {
            response.sendRedirect("buffet-detail?buffetId=" + buffetId + "&fail");
        }
    }

    private void deleteFoodBuffet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int buffetId = Integer.parseInt(request.getParameter("buffetId"));
        int foodId = Integer.parseInt(request.getParameter("foodId"));
        menuDAO.removeFoodFromBuffet(buffetId, foodId);
        response.sendRedirect("buffet-detail?buffetId=" + buffetId + "&success");
    }

}
