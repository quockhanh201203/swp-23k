/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.LoginDAO;
import DAO.orderDAOt;
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
@WebServlet(name = "odlist", urlPatterns = {"/orderlist"})
public class odlist extends HttpServlet {

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
            out.println("<title>Servlet odlist</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet odlist at " + request.getContextPath() + "</h1>");
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
        orderDAOt od = new orderDAOt();
        LoginDAO ld = new LoginDAO();

        HttpSession session = request.getSession();
        String goc = (String) session.getAttribute("goc");
        switch (goc) {
            case "customer":
                int accountID = (int) session.getAttribute("id");
                int customerID = ld.getCustomerId(accountID);
                List<Model.dao.orderN> foodOrderList = od.foodOrderList(customerID);
                List<Model.dao.orderDrink> drinkOrderList = od.drinkOrderList(customerID);
                List<Model.dao.buffetOrder> buffetOrderList = od.buffetOrderList(customerID);

                request.setAttribute("foodOrderList", foodOrderList);
                request.setAttribute("drinkOrderList", drinkOrderList);
                request.setAttribute("buffetOrderList", buffetOrderList);
                request.getRequestDispatcher("orderList.jsp").forward(request, response);
                break;
            case "guest":
                
                
                int guestID = (int) session.getAttribute("guestID");
                 List<Model.dao.orderN> foodOrderListG = od.foodOrderListG(guestID);
                List<Model.dao.orderDrink> drinkOrderListG = od.drinkOrderListG(guestID);
                List<Model.dao.buffetOrder> buffetOrderListG = od.buffetOrderListG(guestID);

                request.setAttribute("buffetOrderListG", foodOrderListG);
                request.setAttribute("buffetOrderListG", drinkOrderListG);
                request.setAttribute("buffetOrderListG", buffetOrderListG);
                request.getRequestDispatcher("orderList.jsp").forward(request, response);
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
        orderDAOt od = new orderDAOt();

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
                        od.quantityDUpdate(quantity, orderDrinkIDd);
                    } catch (Exception e) {
                        e.printStackTrace();
                        response.getWriter().write("Error: " + e.getMessage());
                    }

                    response.sendRedirect("orderlist");
                }

                break;
            case "buffet":
                String actionB = request.getParameter("actionB");
                int orderIDb = Integer.parseInt(request.getParameter("orderID"));
                int orderBuffetIDb = Integer.parseInt(request.getParameter("orderBuffetID"));
                if ("delete".equals(actionB)) {

                    od.deleteOrderBStep1(orderBuffetIDb);
                    od.deleteOrderStep2(orderIDb);

                    response.sendRedirect("orderlist");
                } else {
                    String note = request.getParameter("note");
                    try {
                        od.noteUpdate(note, orderIDb);
                    } catch (Exception e) {
                        e.printStackTrace();
                        response.getWriter().write("Error: " + e.getMessage());
                    }

                    response.sendRedirect("orderlist");
                }

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
