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
import model.Ingredient_Food;
import model.dao.food;
import model.ingredient2;


/**
 *
 * @author tran tung
 */
@WebServlet(name = "Ingredient", urlPatterns = {"/ingredient"})
public class Ingredient extends HttpServlet {

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
            out.println("<title>Servlet Ingredient</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Ingredient at " + request.getContextPath() + "</h1>");
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
        List<model.dao.ingredient> ingredientList = md.getIngredientList();
        request.setAttribute("ingredientList", ingredientList);
        List<model.dao.food> foodList = menu.getFoodList();
        request.setAttribute("foodList", foodList);
        List<ingredient2> ingredientList2 = md.getIngredientist2();
        request.setAttribute("ingredientList2", ingredientList2);

        List<Ingredient_Food> ingredientList3 = md.getIngredient_FoodList();
        request.setAttribute("ingredientList3", ingredientList3);
            
        
        
        
        
        

        request.getRequestDispatcher("ingredient.jsp").forward(request, response);

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
                String foodname = request.getParameter("foodname");
                
                int quantity = Integer.parseInt(request.getParameter("quantity"));

                food f = d.getFoodId(foodname);
                int FoodID = f.getFoodID();
                String ingredient = request.getParameter("ingredient");    
                ingredient2 i = d.getIngredientId(ingredient);
                int IngredientID = i.getIngredientID();
                
                Ingredient_Food newIngredientFood = d.newRecipe(FoodID, IngredientID, quantity);
                request.setAttribute("newIngredientFood", newIngredientFood);
                response.sendRedirect("ingredient");

                break;
            case "edit":
                int newfoodID = Integer.parseInt(request.getParameter("foodID"));  
                String newingredientName = request.getParameter("ingredient"); 
                ingredient2 n = d.getIngredientId(newingredientName);
                int newIngredientID = n.getIngredientID();
                int newQuantity = Integer.parseInt(request.getParameter("quantity"));
               

                System.out.println(newfoodID);
                System.out.println(newIngredientID);
                System.out.println(newQuantity);
                
                 try {
        d.update(newfoodID, newIngredientID, newQuantity);
        response.sendRedirect("ingredient"); // Chuyển hướng thành công
    } catch (Exception e) {
        request.setAttribute("errorMessage", e.getMessage());
        response.sendRedirect("ingredient");
    }
                



                break;
            case "delete":
                int foodId = Integer.parseInt(request.getParameter("foodID"));

                d.delete(foodId);
            response.sendRedirect("ingredient");

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
