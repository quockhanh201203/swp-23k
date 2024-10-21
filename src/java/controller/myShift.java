/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import DAO.ShiftDAO;
import DAO.StaffDAO;
import Model.Shift;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
@WebServlet(name="myShift", urlPatterns={"/myShift"})
public class myShift extends HttpServlet {
   
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
            out.println("<title>Servlet myShift</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet myShift at " + request.getContextPath () + "</h1>");
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
        ShiftDAO sd = new ShiftDAO();
        StaffDAO std = new StaffDAO();
        
        
        HttpSession session = request.getSession();
        Integer accid = (Integer) session.getAttribute("id");

            // Check if the user is logged in
        if (accid == null) {
            // Not logged in, redirect to Login
            response.sendRedirect("Login.jsp");
        }
        

        // Retrieve form parameters
        String weekParam = request.getParameter("week");
        String searchStaff = request.getParameter("searchStaff");

        // Calculate the start and end dates based on the selected week
        LocalDate startDate;
        LocalDate endDate;

        //Get current week
        LocalDate today = LocalDate.now();
        int thisyear = today.getYear();
        int thisweekOfYear = today.get(WeekFields.of(DayOfWeek.MONDAY, 7).weekOfYear());
        String currentWeek = String.format("%d-W%02d", thisyear, thisweekOfYear);

        if (weekParam == null || weekParam.isEmpty()) {
            // If no week is provided, use the current week
            startDate = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
            endDate = today.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
        } else {
            // Parse the week parameter (in format 'YYYY-WXX')
            String[] parts = weekParam.split("-W");
            int year = Integer.parseInt(parts[0]);
            int weekNumber = Integer.parseInt(parts[1]);

            // Get the first day of the week (Monday) for the specified week number
            startDate = LocalDate.ofYearDay(year, 1)
                    .with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY))
                    .plusWeeks(weekNumber - 1);
            // The last day of the week (Sunday)
            endDate = startDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
        }

        List<Date> dates = new ArrayList<>();
        for (int i = 0; i <= 6; i++) {
            // Add days to the start date
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(Date.valueOf(startDate));
            calendar.add(Calendar.DAY_OF_MONTH, i);
            dates.add(new Date(calendar.getTimeInMillis()));
        }
        
        sd.updateShiftStaffStatusBeforeToday("future", "absent");

        try {
            List<Shift> shifts = sd.getMyShiftsByDateRange(Date.valueOf(startDate), Date.valueOf(endDate), std.findStaffByAccountID(accid).getStaffID());
            // If no shifts found, clone from the previous week with shifts
            if (shifts.isEmpty()) {
                List<Shift> previousWeekShifts = sd.getMostRecentShiftsBefore(Date.valueOf(startDate));
                if (!previousWeekShifts.isEmpty()) {
                    shifts = sd.cloneShiftsForNewWeek(previousWeekShifts, startDate);
                    sd.insertShifts(shifts);
                } else {
                    System.out.println("No previous shifts found. Generating default shifts.");
                    // If no previous shifts, generate default shifts for the week
                    shifts = sd.generateDefaultShifts(startDate);
                    sd.insertShifts(shifts);
                }
            }

            shifts = sd.getShiftsByDateRange(Date.valueOf(startDate), Date.valueOf(endDate), searchStaff);
            request.setAttribute("shifts", shifts);
            request.setAttribute("week", weekParam);
            request.setAttribute("dates", dates);
            request.setAttribute("startDate", startDate);
            request.setAttribute("currentWeek", currentWeek);
            request.getRequestDispatcher("myShift.jsp").forward(request, response);
        } catch (Exception ex) {
            System.out.println(accid);
            System.out.println(ex);
        }
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
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
