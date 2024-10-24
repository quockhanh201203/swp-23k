/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import DAO.UserDAO;
import Model.Admin;
import Model.Staff;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Legion
 */
@WebServlet(name="AdminProfileController", urlPatterns={"/admin-profile"})
public class AdminProfileController extends HttpServlet {
   
    

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        UserDAO userDAO = new UserDAO();
        Admin admin = userDAO.getFirstAdmin();
        request.setAttribute("admin", admin);
        request.setAttribute("isSuccess", request.getParameter("status"));
        request.setAttribute("type", request.getParameter("type"));
        request.getRequestDispatcher("admin-profile.jsp").forward(request, response);
    } 

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("updateProfile".equals(action)) {
            handleUpdateProfile(request, response);
        } else if ("changePassword".equals(action)) {
            handleChangePassword(request, response);
        } else {
            response.sendRedirect("error.jsp"); // Handle unknown action
        }
    }
    
    
    private void handleUpdateProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String adminName = request.getParameter("adminName");
        String phoneNumber = request.getParameter("phoneNumber");
        String adminID = request.getParameter("adminID");

        // Perform validation if necessary
        // Update profile logic (e.g., update database)
        // Assume a UserDAO class exists to update the user's profile
        UserDAO userDAO = new UserDAO();
        boolean isUpdated = userDAO.updateAdminProfile(adminID, adminName, phoneNumber);

        if (isUpdated) {
            response.sendRedirect("admin-profile?status=true&type=profile");
        } else {
            response.sendRedirect("admin-profile?status=false&type=profile");
        }

    }

    private void handleChangePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        String username = request.getParameter("username");
        String confirmNewPassword = request.getParameter("confirmNewPassword");

        if (!newPassword.equals(confirmNewPassword)) {
            // Passwords don't match
            response.sendRedirect("admin-profile?status=false&type=pass");
            return;
        }

        UserDAO userDAO = new UserDAO();
        boolean isUpdated = userDAO.changePassword(username, newPassword, currentPassword);

        if (isUpdated) {
            response.sendRedirect("admin-profile?status=true&type=pass");
        } else {
            response.sendRedirect("admin-profile?status=false&type=pass");
        }

    }


    

}
