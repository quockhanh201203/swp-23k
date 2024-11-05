/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.LoginDAO;
import dal.MenuDAO;
import dal.orderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author tran tung
 */
@WebServlet(name = "orderList", urlPatterns = {"/orderlist"})
public class orderList extends HttpServlet {

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
            out.println("<title>Servlet orderList</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet orderList at " + request.getContextPath() + "</h1>");
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
        orderDAO od = new orderDAO();
        LoginDAO ld = new LoginDAO();

        HttpSession session = request.getSession();
        String goc = (String) session.getAttribute("goc");
        switch (goc) {
            case "customer":
                int accountID = (int) session.getAttribute("id");
                int customerID = ld.getCustomerId(accountID);
                List<model.dao.orderN> foodOrderList = od.foodOrderList(customerID);
                List<model.dao.orderDrink> drinkOrderList = od.drinkOrderList(customerID);
                request.setAttribute("foodOrderList", foodOrderList);
                request.setAttribute("drinkOrderList", drinkOrderList);
                request.getRequestDispatcher("orderList.jsp").forward(request, response);
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
        orderDAO od = new orderDAO();

        String product = request.getParameter("product");
        switch (product) {
            case "food":
                String actionF = request.getParameter("actionB");

                int orderID = Integer.parseInt(request.getParameter("orderID"));
                int orderFoodID = Integer.parseInt(request.getParameter("orderFoodID"));
                if ("delete".equals(actionF)) {
                     od.deleteOrderStep1(orderFoodID);
                    od.deleteOrderStep2(orderID);
                   
                    response.sendRedirect("orderlist");
                } else {
                    String note = request.getParameter("note");
                    int quantity = Integer.parseInt(request.getParameter("quantity"));
                    try {
                        od.noteUpdate(note, orderID);
                        od.quantityUpdate(quantity, orderFoodID);
                    } catch (Exception e) {
                        e.printStackTrace();
                        response.getWriter().write("Error: " + e.getMessage());
                    }

                    response.sendRedirect("orderlist");
                }

                break;
            case "drink":
                
                 String actionD = request.getParameter("actionB");

                int orderIDd = Integer.parseInt(request.getParameter("orderID"));
                int orderDrinkIDd = Integer.parseInt(request.getParameter("orderDrinkID"));
                System.out.println(orderIDd);
                System.out.println(orderDrinkIDd);
                if ("delete".equals(actionD)) {
                    
                    
                    
                     od.deleteOrderDStep1(orderDrinkIDd);
                      od.deleteOrderStep2(orderIDd);
                   
                  
                    response.sendRedirect("orderlist");
                } else {
                    String note = request.getParameter("note");
                    int quantity = Integer.parseInt(request.getParameter("quantity"));
                    try {
                        od.noteUpdate(note, orderIDd);
                        od.quantityUpdate(quantity, orderDrinkIDd);
                    } catch (Exception e) {
                        e.printStackTrace();
                        response.getWriter().write("Error: " + e.getMessage());
                    }

                    response.sendRedirect("orderlist");
                }
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                break;

//                switch (action) {
//            case "delete":
//
//                
//                break;
//            case "update":
//               
//
//                break;
//
//        }
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
