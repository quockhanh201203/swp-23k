/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;
import java.sql.Date;

/**
 *
 * @author ADMIN
 */
public class DateUtils {
    public static long getTimeFromDate(Date date) {
        return date != null ? date.getTime() : 0L;
    }
}
