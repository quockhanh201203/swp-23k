/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.*;
import Model.*;
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
@WebServlet(name = "MenuManageController", urlPatterns = {"/menus"})
public class MenuManageController extends HttpServlet {

    private MenuDAO menuDAO;

    @Override
    public void init() throws ServletException {
        menuDAO = new MenuDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Buffet> buffetList = menuDAO.getAllBuffets();
        List<Drink> drinkList = menuDAO.getAllDrinks();
        List<Food> foodList = menuDAO.getAllFoods();
        List<FoodCategory> foodCategorys = menuDAO.getFoodCategories();
        List<DrinkCategory> drinkCategorys = menuDAO.getDrinkCategories();
        List<Brand> brands = menuDAO.getBrands();

        request.setAttribute("foodList", foodList);
        request.setAttribute("drinkList", drinkList);
        request.setAttribute("buffetList", buffetList);
        request.setAttribute("foodCategorys", foodCategorys);
        request.setAttribute("drinkCategorys", drinkCategorys);
        request.setAttribute("brands", brands);

        request.getRequestDispatcher("menu-manage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String itemType = request.getParameter("type"); // "drink", "food", or "buffet"
        String action = request.getParameter("action"); // "add" or "update"

        switch (itemType) {
            case "drink":
                if ("add".equals(action)) {
                    addDrink(request);
                } else if ("update".equals(action)) {
                    updateDrink(request);
                }
                break;

            case "food":
                if ("add".equals(action)) {
                    addFood(request);
                } else if ("update".equals(action)) {
                    updateFood(request);
                }
                break;

            case "buffet":
                if ("add".equals(action)) {
                    addBuffet(request);
                } else if ("update".equals(action)) {
                    updateBuffet(request);
                }
                break;

            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid item type");
                return;
        }

        response.sendRedirect("menus");
    }

    private void addDrink(HttpServletRequest request) {
        String drinkName = request.getParameter("drinkName");
        double drinkPrice = Double.parseDouble(request.getParameter("drinkPrice"));
        String drinkImage = request.getParameter("drinkImage");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        int brandID = Integer.parseInt(request.getParameter("brandId"));

        // Create a Drink object and save it to the database
        Drink drink = new Drink(0, drinkName, drinkPrice, quantity, categoryId, brandID, 1, drinkImage);
        
        int drinkId = menuDAO.addDrink(drink); // Assume this method exists in your DAO
        Product product = new Product(0, 0, 0, drinkId, drinkPrice);
        int productID = menuDAO.insertProduct(product); 
        PriceHistory priceHistory = new PriceHistory(productID, drinkPrice, null, null);
        menuDAO.insertPriceHistory(priceHistory);

    }

    private void updateDrink(HttpServletRequest request) {
        int drinkId = Integer.parseInt(request.getParameter("drinkID")); // Assuming drinkId is passed for updates
        String drinkName = request.getParameter("drinkName");
        double drinkPrice = Double.parseDouble(request.getParameter("drinkPrice"));
        String drinkImage = request.getParameter("image");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        int brandID = Integer.parseInt(request.getParameter("brandId"));

        // Update the existing drink in the database
        Drink drink = new Drink(drinkId, drinkName, drinkPrice, quantity, categoryId, brandID, 1, drinkImage);
        menuDAO.updateDrink(drink); // Assume this method exists in your DAO
    }

    private void addFood(HttpServletRequest request) {
        String foodName = request.getParameter("foodName");
        String foodImage = request.getParameter("foodImage");
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        double foodPrice = Double.parseDouble(request.getParameter("foodPrice"));
        // Create a Food object and save it to the database
        Food food = new Food(0,foodName, categoryId, 1, foodImage);
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

        // Update the existing food in the database
        Food food = new Food(foodId,foodName, categoryId, 1, foodImage);
        menuDAO.updateFood(food); // Assume this method exists in your DAO
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

        // Update the existing buffet in the database
        Buffet buffet = new Buffet(buffetId, buffetName, buffetImage);
        menuDAO.updateBuffet(buffet); // Assume this method exists in your DAO
    }

}
