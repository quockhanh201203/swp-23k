/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.MenuDAO;
import Model.Brand;
import Model.Buffet;
import Model.Drink;
import Model.DrinkCategory;
import Model.Food;
import Model.FoodCategory;
import Model.PriceHistory;
import Model.Product;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author Legion
 */
@WebServlet(name = "FoodManageController", urlPatterns = {"/foods"})
public class FoodManageController extends HttpServlet {

    private MenuDAO menuDAO;

    @Override
    public void init() throws ServletException {
        menuDAO = new MenuDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Food> foodList = menuDAO.getAllFoods();
        List<FoodCategory> foodCategorys = menuDAO.getFoodCategories();

        request.setAttribute("foodList", foodList);

        request.setAttribute("foodCategorys", foodCategorys);

        request.getRequestDispatcher("food-manage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action"); // "add" or "update"

        if ("add".equals(action)) {
            addFood(request);
        } else if ("update".equals(action)) {
            updateFood(request);
        } else if ("delete".equals(action)) {
            deleteFood(request);
        }

        response.sendRedirect("foods");
    }

    private void addFood(HttpServletRequest request) {
        String foodName = request.getParameter("foodName");
        String foodImage = request.getParameter("foodImage");
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        double foodPrice = Double.parseDouble(request.getParameter("foodPrice"));
        // Create a Food object and save it to the database
        Food food = new Food(0, foodName, categoryId, 1, foodImage);
        int foodId = menuDAO.addFood(food); // Assume this method exists in your DAO
        Product product = new Product(0, foodId, 0, 0, foodPrice);
        int productID = menuDAO.insertProduct(product);
        PriceHistory priceHistory = new PriceHistory(productID, foodPrice, null, null);
        menuDAO.insertPriceHistory(priceHistory);
    }

    private void updateFood(HttpServletRequest request) {
        int foodId = Integer.parseInt(request.getParameter("foodID")); // Assuming foodId is passed for updates
        String foodName = request.getParameter("foodName");
        String foodImage = request.getParameter("image");
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        double price = Double.parseDouble(request.getParameter("price"));

        // Update the existing food in the database
        Food food = new Food(foodId, foodName, categoryId, 1, foodImage);
        menuDAO.updateFood(food); // Assume this method exists in your DAO
        Product product = new Product(0, foodId, 0, 0, price);
        int productID = menuDAO.insertProduct(product);
        PriceHistory priceHistory = new PriceHistory(productID, price, null, null);
        menuDAO.insertPriceHistory(priceHistory);
    }

    private void deleteFood(HttpServletRequest request) {
        int foodId = Integer.parseInt(request.getParameter("id")); // Assuming foodId is passed for updates
        int status = Integer.parseInt(request.getParameter("status"));
        Food food = new Food();
        food.setFoodID(foodId);
        food.setStatus(status == 1? "Active" : "Inactive");
        menuDAO.deleteFood(food);
    }

}
