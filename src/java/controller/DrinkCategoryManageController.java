/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import DAO.MenuDAO;
import Model.DrinkCategory;
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
 * @author Legion
 */
@WebServlet(name="DrinkCategoryManageController", urlPatterns={"/drink-category"})
public class DrinkCategoryManageController extends HttpServlet {
   
    private MenuDAO menuDAO;

    @Override
    public void init() throws ServletException {
        menuDAO = new MenuDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        List<DrinkCategory> categories = menuDAO.getAllDrinkCategories();
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("drink-category-manage.jsp").forward(request, response);
    } 


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action.equals("add")) {
            String categoryName = request.getParameter("categoryName");
            menuDAO.addDrinkCategory(new DrinkCategory(1, categoryName));
        } else if (action.equals("update")) {
            int categoryId = Integer.parseInt(request.getParameter("id"));
            String categoryName = request.getParameter("categoryName");
            menuDAO.updateDrinkCategory(new DrinkCategory(categoryId, categoryName));
        } else if (action.equals("delete")) {
            int categoryId = Integer.parseInt(request.getParameter("id"));
            menuDAO.deleteDrinkCategory(categoryId);
        }
        response.sendRedirect("drink-category");
    }


}