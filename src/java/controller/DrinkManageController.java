/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.MenuDAO;
import Model.Brand;
import Model.Drink;
import Model.DrinkCategory;
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
@WebServlet(name = "DrinkManageController", urlPatterns = {"/drinks"})
public class DrinkManageController extends HttpServlet {

    private MenuDAO menuDAO;

    @Override
    public void init() throws ServletException {
        menuDAO = new MenuDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Drink> drinkList = menuDAO.getAllDrinks();
        List<DrinkCategory> drinkCategorys = menuDAO.getDrinkCategories();
        List<Brand> brands = menuDAO.getBrands();
        request.setAttribute("drinkList", drinkList);
        request.setAttribute("drinkCategorys", drinkCategorys);
        request.setAttribute("brands", brands);
        request.getRequestDispatcher("drink-manage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         String action = request.getParameter("action"); // "add" or "update"
        if ("add".equals(action)) {
            addDrink(request);
        } else if ("update".equals(action)) {
            updateDrink(request);
        }
        response.sendRedirect("drinks");
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

}
