/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.util.Date;
import model.Ingredient_Food;
import model.tableOrder;

/**
 *
 * @author tran tung
 */
public class tableOrderDAO extends DBContext{
    public tableOrder newOrderTable(int totalPrice, Date orderDate, int TableID) {
    String sql = "INSERT INTO [dbo].[Table_Order]\n" +
"           ([TotalPrice]\n" +
"           ,[OrderDate]\n" +
"           ,[TableID])\n" +
"     VALUES\n" +
"           (?\n" +
"           ,?\n" +
"           ,?)";
    try {
        tableOrder a = new tableOrder();
        PreparedStatement st = connection.prepareStatement(sql);
        st.setInt(1, totalPrice);
         if (orderDate != null) {
                st.setDate(2, new java.sql.Date(orderDate.getTime()));
            } else {
                st.setNull(2, java.sql.Types.DATE); // Nếu orderDate là null
            }
        st.setInt(3, TableID);
        
        int rowsAffected = st.executeUpdate();
        if (rowsAffected > 0) {
            a.setTotalPrice(totalPrice); // Sử dụng setter hợp lệ
            a.setOrderDate(orderDate); // Sử dụng setter hợp lệ
            a.setTableID(TableID); // Sử dụng setter hợp lệ
            return a;
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return null;
}
 
        
}
