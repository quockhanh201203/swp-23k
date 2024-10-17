/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.MenuDAO;
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
 * @author tran tung
 */
@WebServlet(name="Menu", urlPatterns={"/menu"})
public class Menu extends HttpServlet {
    
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
            out.println("<title>Servlet Menu</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Menu at " + request.getContextPath () + "</h1>");
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
           MenuDAO md = new MenuDAO();
         List<model.dao.food> foodList = md.getFoodList();
         List<model.dao.buffet> buffetList = md.getBuffetList();
          int page, numperpage = 6;
        int size = foodList.size();
        int num = (size % 6 == 0 ? (size / numperpage) : ((size / numperpage)) + 1);//so trang
        String xpage = request.getParameter("page");
        if (xpage == null) {
            page = 1;
        } else {
            page = Integer.parseInt(xpage);
        }
        int start, end;
        start = (page - 1) * numperpage;
        end = Math.min(page * numperpage, size);
        List<model.dao.food> data = getListByPage(foodList, start, end);
         List<model.dao.food> chickenList = md.getChickenList();
         List<model.dao.food> burgerList = md.getBurgerList();
         List<model.dao.food> sanList = md.getSanList();
         request.setAttribute("buffetList", buffetList);
          request.setAttribute("chickenList", chickenList);
         request.setAttribute("burgerList", burgerList);
         request.setAttribute("sanList", sanList);
         request.setAttribute("foodList", data);
        request.setAttribute("page", page);
        request.setAttribute("num", num);
         request.getRequestDispatcher("menu.jsp").forward(request, response);
         



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
            String Foodkeyword = request.getParameter("Foodkeyword");
            


    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
     public List<model.dao.food> getListByPage(List<model.dao.food> list,
            int start, int end) {
        ArrayList<model.dao.food> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(list.get(i));
        }
        return arr;
    }
   
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
