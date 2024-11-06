/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import DAO.LoginDAO;
import DAO.orderDAOt;
import DAO.tableOrderDAO;
import Model.order;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author tran tung
 */
@WebServlet(name="addToOrder", urlPatterns={"/addtoorder"})
public class addToOrder extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<title>Servlet addToOrder</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet addToOrder at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
                        HttpSession session = request.getSession();

        String addType = request.getParameter("addType");
        orderDAOt d = new orderDAOt();
        LoginDAO ld = new LoginDAO();
        tableOrderDAO td = new tableOrderDAO();
        switch (addType) {
            case "food":


                int foodID = Integer.parseInt(request.getParameter("foodID"));
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                String note = request.getParameter("note");
                String goc = (String) session.getAttribute("goc");
                switch (goc) {
                    case "customer":
                        int accountID = (int) session.getAttribute("id");
                        int customerID = ld.getCustomerId(accountID);
                        int tableOrderIDc = td.getTableOrderIDc(customerID);
                        order newOrderc = d.createOrderWithFood(note, null, customerID, 0, tableOrderIDc, foodID, quantity);
                        if (newOrderc != null) {
                            response.sendRedirect("orderlist");
                        } else {
                            response.sendRedirect("orderlist");
                        }
                        break;

                    case "guest":
                        int guestID = (int) session.getAttribute("guestID");
                       
                        int tableOrderIDg = td.getTableOrderIDc(guestID);   
                        System.out.println(guestID);
                         System.out.println(tableOrderIDg);
                        order newOrderg = d.createOrderWithFood(note, guestID, null, 0, tableOrderIDg, foodID, quantity);
                        if (newOrderg != null) {
                            response.sendRedirect("orderlist");
                        } else {
                            response.sendRedirect("orderlist");
                        }
                        break;
                }

                break;

            case "drink":

                int drinkID = Integer.parseInt(request.getParameter("drinkID"));
                int quantityD = Integer.parseInt(request.getParameter("quantity"));
                String noteD = request.getParameter("note");
                String gocD = (String) session.getAttribute("goc");
                switch (gocD) {
                    case "customer":
                        int accountID = (int) session.getAttribute("id");
                        int customerID = ld.getCustomerId(accountID);
                        int tableOrderIDc = td.getTableOrderIDc(customerID);
                        order drinkOrderc = d.createOrderWithDrink(noteD, null, customerID, 0, tableOrderIDc, drinkID, quantityD);
                        if (drinkOrderc != null) {
                            response.sendRedirect("orderlist");
                        } else {
                            response.sendRedirect("orderlist");
                        }
                        break;

                    case "guest":
                        int guestID = (int) session.getAttribute("guestID");
                       
                        int tableOrderIDg = td.getTableOrderIDc(guestID);   
                        System.out.println(guestID);
                         System.out.println(tableOrderIDg);
                        order drinkOrderg = d.createOrderWithDrink(noteD, guestID, null, 0, tableOrderIDg, drinkID, quantityD);
                        if (drinkOrderg != null) {
                            response.sendRedirect("orderlist");
                        } else {
                            response.sendRedirect("orderlist");
                        }
                        break;
                }











                break;
                
                
                
                
                

            case "buffet":
                int buffetID = Integer.parseInt(request.getParameter("buffetID"));
                String noteB = request.getParameter("note");
                String gocB = (String) session.getAttribute("goc");
                switch (gocB) {
                    case "customer":
                        int accountID = (int) session.getAttribute("id");
                        int customerID = ld.getCustomerId(accountID);
                        int tableOrderIDb = td.getTableOrderIDc(customerID);
                        order buffetOrderc = d.createOrderWithBuffet(noteB, null, customerID, 0, tableOrderIDb, buffetID);
                        if (buffetOrderc != null) {
                            response.sendRedirect("orderlist");
                        } else {
                            response.sendRedirect("orderlist");
                        }
                        break;

                    case "guest":
                        int guestID = (int) session.getAttribute("guestID");
                       
                        int tableOrderIDbg = td.getTableOrderIDc(guestID);   
                        System.out.println(guestID);
                        order buffetOrderg = d.createOrderWithBuffet(noteB, guestID, null, 0, tableOrderIDbg, buffetID);
                        if (buffetOrderg != null) {
                            response.sendRedirect("orderlist");
                        } else {
                            response.sendRedirect("orderlist");
                        }











                break;

            default:
                // Xử lý trường hợp không hợp lệ
                System.out.println("Invalid add type: " + addType);
                break;
        }

    }    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
