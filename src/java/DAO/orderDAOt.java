/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.dao.buffetOrder;
import Model.dao.orderDrink;
import Model.dao.orderN;
import Model.foodOrder;
import Model.order;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 *
 * @author tran tung
 */
public class orderDAOt extends DBContext {
    
//  public order newOrder(String GuestNote, Integer GuestID, Integer CustomerID, int total, int Table_OrderID) {
//    String sql = "INSERT INTO [dbo].[Order] " +
//                 "([GuestNote], [GuestID], [CustomerID], [total], [Table_OrderID]) " +
//                 "VALUES (?, ?, ?, ?, ?); " +
//                 "SELECT SCOPE_IDENTITY() AS orderID;";  // Lấy orderID vừa tạo
//
//    try {
//        order a = new order();
//        PreparedStatement st = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); // Thêm option để trả về khóa
//
//        st.setString(1, GuestNote);
//        st.setObject(2, GuestID, java.sql.Types.INTEGER);
//        st.setObject(3, CustomerID, java.sql.Types.INTEGER);
//        st.setInt(4, total);
//        st.setInt(5, Table_OrderID);
//
//        // Thực hiện câu lệnh INSERT
//        int rowsAffected = st.executeUpdate(); // Sử dụng executeUpdate cho INSERT
//
//        if (rowsAffected > 0) {
//            // Lấy orderID từ SCOPE_IDENTITY()
//            try (ResultSet rs = st.getGeneratedKeys()) { // Lấy các khóa đã sinh ra
//                if (rs.next()) {
//                    int orderID = rs.getInt(1); // Chỉ số đầu tiên
//                    a.setOrderID(orderID); // Gán orderID vào đối tượng
//                }
//            }
//            // Thiết lập các thuộc tính khác cho đối tượng order
//            a.setGuestNote(GuestNote);
//            a.setGuestID(GuestID);
//            a.setCustomerID(CustomerID);
//            a.setTotal(total);
//            a.setTable_OrderID(Table_OrderID);
//
//            return a;  // Trả về đối tượng order đã được thiết lập đầy đủ
//        }
//    } catch (Exception e) {
//        e.printStackTrace();
//    }
//
//    return null; // Trả về null nếu có lỗi xảy ra
//}

 public foodOrder newFoodOrder(int orderID, int FoodID, int Quantity) {
    String sql = "INSERT INTO [dbo].[Order_Food]\n" +
"           ([OrderID]\n" +
"           ,[FoodID]\n" +
"           ,[Quantity])\n" +
"     VALUES\n" +
"           (?\n" +
"           ,?\n" +
"           ,?)";
    try {
        foodOrder a = new foodOrder();
        PreparedStatement st = connection.prepareStatement(sql);
        st.setInt(1, orderID);
        st.setInt(2, FoodID);
        st.setInt(3, Quantity);
        
        int rowsAffected = st.executeUpdate();
        if (rowsAffected > 0) {
            a.setFoodID(orderID); // Sử dụng setter hợp lệ
            a.setFoodID(FoodID); // Sử dụng setter hợp lệ
            a.setQuantity(Quantity); // Sử dụng setter hợp lệ
            return a;
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return null;
}
     public order createOrderWithFood(String guestNote, Integer guestID, Integer customerID, int total, int tableOrderID, int foodID, int quantity) {
    String sql = "DECLARE @NewOrderID INT; " +
                 "INSERT INTO [dbo].[Order] " +
                 "([GuestNote], [GuestID], [CustomerID], [total], [Table_OrderID]) " +
                 "VALUES (?, ?, ?, ?, ?); " +
                 "SET @NewOrderID = SCOPE_IDENTITY(); " +
                 "INSERT INTO [dbo].[Order_Food] ([OrderID], [FoodID], [Quantity]) " +
                 "VALUES (@NewOrderID, ?, ?); " +
                 "SELECT @NewOrderID AS OrderID;"; 
    
    try {
        // Prepare the statement for the SQL command
        PreparedStatement st = connection.prepareStatement(sql);
        st.setString(1, guestNote);
        st.setObject(2, guestID, java.sql.Types.INTEGER);
        st.setObject(3, customerID, java.sql.Types.INTEGER);
        st.setInt(4, total);
        st.setInt(5, tableOrderID);
        st.setInt(6, foodID);   // Set FoodID
        st.setInt(7, quantity);  // Set Quantity

        // Execute the statement
        boolean hasResultSet = st.execute(); // Use execute() to run the SQL

        if (hasResultSet) {
            // Retrieve the OrderID from the result set
            try (ResultSet rs = st.getResultSet()) {
                if (rs.next()) {
                    int newOrderID = rs.getInt("OrderID");

                    // Create and return the Order object
                    order order = new order();
                    order.setOrderID(newOrderID);
                    order.setGuestNote(guestNote);
                    order.setGuestID(guestID);
                    order.setCustomerID(customerID);
                    order.setTotal(total);
                    order.setTable_OrderID(tableOrderID);
                    return order;
                }
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null; // Return null if there was an error
}
     
     public order createOrderWithDrink(String guestNote, Integer guestID, Integer customerID, int total, int tableOrderID, int drinkID, int quantity) {
    String sql = "DECLARE @NewOrderID INT;\n" +
"                 INSERT INTO [dbo].[Order]\n" +
"                 ([GuestNote], [GuestID], [CustomerID], [total], [Table_OrderID])\n" +
"                 VALUES (?, ?, ?, ?, ?);\n" +
"                 SET @NewOrderID = SCOPE_IDENTITY();\n" +
"                 INSERT INTO [dbo].Order_Drink ([OrderID], [DrinkID], [Quantity]) \n" +
"VALUES (@NewOrderID,? , ?);\n" +
"                 SELECT @NewOrderID AS OrderID"; 

    try {
        // Prepare the statement for the SQL command
        PreparedStatement st = connection.prepareStatement(sql);
        st.setString(1, guestNote);
        st.setObject(2, guestID, java.sql.Types.INTEGER);
        st.setObject(3, customerID, java.sql.Types.INTEGER);
        st.setInt(4, total);
        st.setInt(5, tableOrderID);
        st.setInt(6, drinkID);   // Set FoodID
        st.setInt(7, quantity);  // Set Quantity

        // Execute the statement
        boolean hasResultSet = st.execute(); // Use execute() to run the SQL

        if (hasResultSet) {
            // Retrieve the OrderID from the result set
            try (ResultSet rs = st.getResultSet()) {
                if (rs.next()) {
                    int newOrderID = rs.getInt("OrderID");

                    // Create and return the Order object
                    order order = new order();
                    order.setOrderID(newOrderID);
                    order.setGuestNote(guestNote);
                    order.setGuestID(guestID);
                    order.setCustomerID(customerID);
                    order.setTotal(total);
                    order.setTable_OrderID(tableOrderID);
                    return order;
                }
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null; // Return null if there was an error
}
      public order createOrderWithBuffet(String guestNote, Integer guestID, Integer customerID, int total, int tableOrderID, int bufffetID) {
    String sql = "DECLARE @NewOrderID INT;"
            + "INSERT INTO [dbo].[Order]\n" +
            
"    ([GuestNote], [GuestID], [CustomerID], [total], [Table_OrderID])\n" +
"VALUES (?, ?, ?, ?, ?);\n" +
"\n" +
"SET @NewOrderID = SCOPE_IDENTITY();\n" +
"\n" +
"INSERT INTO [dbo].[Order_Buffet] ([OrderID], [buffetID]) \n" +
"VALUES (@NewOrderID, ?);\n" +
"\n" +
"SELECT @NewOrderID AS OrderID;"; 

    try {
        // Prepare the statement for the SQL command
        PreparedStatement st = connection.prepareStatement(sql);
        st.setString(1, guestNote);
        st.setObject(2, guestID, java.sql.Types.INTEGER);
        st.setObject(3, customerID, java.sql.Types.INTEGER);
        st.setInt(4, total);
        st.setInt(5, tableOrderID);
        st.setInt(6, bufffetID);   // Set FoodID
      
        // Execute the statement
        boolean hasResultSet = st.execute(); // Use execute() to run the SQL

        if (hasResultSet) {
            // Retrieve the OrderID from the result set
            try (ResultSet rs = st.getResultSet()) {
                if (rs.next()) {
                    int newOrderID = rs.getInt("OrderID");

                    // Create and return the Order object
                    order order = new order();
                    order.setOrderID(newOrderID);
                    order.setGuestNote(guestNote);
                    order.setGuestID(guestID);
                    order.setCustomerID(customerID);
                    order.setTotal(total);
                    order.setTable_OrderID(tableOrderID);
                    return order;
                }
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null; // Return null if there was an error
}
     
     
     
     
      public List<Model.dao.orderN> foodOrderList(int customerID) {
        List<Model.dao.orderN> orderList = new ArrayList<>();
        String sql = " SELECT [order].*, Order_Food.*, Product.price, Food.foodName, Food.image\n" +
"FROM [order]\n" +
"LEFT JOIN Order_Food ON [order].orderID = Order_Food.orderID\n" +
"LEFT JOIN Product ON Order_Food.foodID = Product.foodID\n" +
"LEFT JOIN Food ON Order_Food.foodID = Food.foodID\n" +
"WHERE customerID = ? AND Order_Food.Order_FoodID IS NOT NULL";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
                    st.setInt(1, customerID);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                orderN p = new orderN();

                orderList.add(new orderN(
                        rs.getInt(1), rs.getString(2), rs.getInt(3),
                        rs.getInt(4), rs.getInt(5), rs.getInt(6),
                        rs.getInt(7), rs.getInt(8), rs.getInt(9),
                        rs.getInt(10), rs.getInt(11), rs.getString(12), rs.getString(13)
                )
                );
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return orderList;
    }
      
        public List<Model.dao.orderDrink> drinkOrderList(int customerID) {
        List<Model.dao.orderDrink> orderList = new ArrayList<>();
        String sql = " SELECT [order].*, Order_Drink.*, Product.price, Drink.DrinkName, Drink.Image\n" +
"FROM [order]\n" +
"LEFT JOIN Order_Drink ON [order].orderID = Order_Drink.orderID\n" +
"LEFT JOIN Product ON Order_Drink.DrinkID = Product.DrinkID\n" +
"LEFT JOIN Drink ON Order_Drink.DrinkID = Drink.DrinkID\n" +
"WHERE customerID = ? AND Order_Drink.Order_DrinkID IS NOT NULL";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
                    st.setInt(1, customerID);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                orderDrink p = new orderDrink();

                orderList.add(new orderDrink(
                        rs.getInt(1), rs.getString(2), rs.getInt(3),
                        rs.getInt(4), rs.getInt(5), rs.getInt(6),
                        rs.getInt(7), rs.getInt(8), rs.getInt(9),
                        rs.getInt(10), rs.getInt(11), rs.getString(12), rs.getString(13)
                )
                );
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return orderList;
    }

        public List<Model.dao.buffetOrder> buffetOrderList(int customerID) {
        List<Model.dao.buffetOrder> orderList = new ArrayList<>();
        String sql = " SELECT [order].*, \n" +
"       Order_Buffet.*, \n" +
"       Product.price, \n" +
"       Buffet.BuffetName, \n" +
"       Buffet.Image\n" +
"FROM [order]\n" +
"LEFT JOIN Order_Buffet ON [order].orderID = Order_Buffet.orderID\n" +
"LEFT JOIN Product ON Order_Buffet.BuffetID = Product.BuffetID\n" +
"LEFT JOIN Buffet ON Order_Buffet.BuffetID = Buffet.BuffetID\n" +
"WHERE [order].customerID = ?\n" +
"  AND Order_Buffet.Order_BuffetID IS NOT NULL;";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
                    st.setInt(1, customerID);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                buffetOrder p = new buffetOrder();

                orderList.add(new buffetOrder(
                        rs.getInt(1), rs.getString(2), rs.getInt(3),
                        rs.getInt(4), rs.getInt(5), rs.getInt(6),
                        rs.getInt(7), rs.getInt(8), rs.getInt(9),
                        rs.getInt(10), rs.getString(11), rs.getString(12)
                )
                );
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return orderList;
    }
      
       public List<Model.dao.orderN> foodOrderListG(int guestID) {
        List<Model.dao.orderN> orderList = new ArrayList<>();
        String sql = " SELECT [order].*, Order_Food.*, Product.price, Food.foodName, Food.image\n" +
"FROM [order]\n" +
"LEFT JOIN Order_Food ON [order].orderID = Order_Food.orderID\n" +
"LEFT JOIN Product ON Order_Food.foodID = Product.foodID\n" +
"LEFT JOIN Food ON Order_Food.foodID = Food.foodID\n" +
"WHERE guestID = ? AND Order_Food.Order_FoodID IS NOT NULL";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
                    st.setInt(1, guestID);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                orderN p = new orderN();

                orderList.add(new orderN(
                        rs.getInt(1), rs.getString(2), rs.getInt(3),
                        rs.getInt(4), rs.getInt(5), rs.getInt(6),
                        rs.getInt(7), rs.getInt(8), rs.getInt(9),
                        rs.getInt(10), rs.getInt(11), rs.getString(12), rs.getString(13)
                )
                );
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return orderList;
    }
      
        public List<Model.dao.orderDrink> drinkOrderListG(int guestID) {
        List<Model.dao.orderDrink> orderList = new ArrayList<>();
        String sql = " SELECT [order].*, Order_Drink.*, Product.price, Drink.DrinkName, Drink.Image\n" +
"FROM [order]\n" +
"LEFT JOIN Order_Drink ON [order].orderID = Order_Drink.orderID\n" +
"LEFT JOIN Product ON Order_Drink.DrinkID = Product.DrinkID\n" +
"LEFT JOIN Drink ON Order_Drink.DrinkID = Drink.DrinkID\n" +
"WHERE guestID = ? AND Order_Drink.Order_DrinkID IS NOT NULL";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
                    st.setInt(1, guestID);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                orderDrink p = new orderDrink();

                orderList.add(new orderDrink(
                        rs.getInt(1), rs.getString(2), rs.getInt(3),
                        rs.getInt(4), rs.getInt(5), rs.getInt(6),
                        rs.getInt(7), rs.getInt(8), rs.getInt(9),
                        rs.getInt(10), rs.getInt(11), rs.getString(12), rs.getString(13)
                )
                );
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return orderList;
    }

        public List<Model.dao.buffetOrder> buffetOrderListG(int guestID) {
        List<Model.dao.buffetOrder> orderList = new ArrayList<>();
        String sql = " SELECT [order].*, \n" +
"       Order_Buffet.*, \n" +
"       Product.price, \n" +
"       Buffet.BuffetName, \n" +
"       Buffet.Image\n" +
"FROM [order]\n" +
"LEFT JOIN Order_Buffet ON [order].orderID = Order_Buffet.orderID\n" +
"LEFT JOIN Product ON Order_Buffet.BuffetID = Product.BuffetID\n" +
"LEFT JOIN Buffet ON Order_Buffet.BuffetID = Buffet.BuffetID\n" +
"WHERE [order].guestID = ?\n" +
"  AND Order_Buffet.Order_BuffetID IS NOT NULL;";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
                    st.setInt(1, guestID);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                buffetOrder p = new buffetOrder();

                orderList.add(new buffetOrder(
                        rs.getInt(1), rs.getString(2), rs.getInt(3),
                        rs.getInt(4), rs.getInt(5), rs.getInt(6),
                        rs.getInt(7), rs.getInt(8), rs.getInt(9),
                        rs.getInt(10), rs.getString(11), rs.getString(12)
                )
                );
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return orderList;
    }
      
       
      
      
      public void deleteOrderStep1(int Order_FoodID) {
                boolean success = false;

        
                String sql = "DELETE FROM [dbo].[Order_Food]\n" +
"      WHERE Order_FoodID   = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, Order_FoodID);
            ResultSet rs = st.executeQuery();
                        int rowsDeleted = st.executeUpdate();
                if (rowsDeleted > 0) {
                
                success = true;
            }
        
        
        
    }catch (Exception e) {
            System.out.println(e);
        }
        
}
      public void deleteOrderStep2(int OrderID) {
    boolean success = false;
    String sql = "DELETE FROM [dbo].[Order] WHERE OrderID = ?";

    try {
        PreparedStatement st = connection.prepareStatement(sql);
        st.setInt(1, OrderID);
        
        int rowsDeleted = st.executeUpdate();  // Chỉ cần sử dụng executeUpdate
        if (rowsDeleted > 0) {
            success = true;
        }
        
    } catch (Exception e) {
        System.out.println(e);
    }
}

       
      public void deleteOrderDStep1(int Order_DrinkID) {
    boolean success = false;
    String sql = "DELETE FROM [dbo].[Order_Drink] WHERE Order_DrinkID = ?";

    try {
        PreparedStatement st = connection.prepareStatement(sql);
        st.setInt(1, Order_DrinkID);
        
        int rowsDeleted = st.executeUpdate();  // Chỉ cần sử dụng executeUpdate
        if (rowsDeleted > 0) {
            success = true;
        }
        
    } catch (Exception e) {
        System.out.println(e);
    }
}

         public void deleteOrderBStep1(int Order_BuffetID) {
    boolean success = false;
    String sql = "DELETE FROM [dbo].[Order_Buffet] WHERE Order_BuffetID = ?";

    try {
        PreparedStatement st = connection.prepareStatement(sql);
        st.setInt(1, Order_BuffetID);
        
        int rowsDeleted = st.executeUpdate();  // Chỉ cần sử dụng executeUpdate
        if (rowsDeleted > 0) {
            success = true;
        }
        
    } catch (Exception e) {
        System.out.println(e);
    }
}
       
       
       
        public void noteUpdate(String  note, int orderID) throws Exception {
    String sql = "UPDATE [dbo].[Order]\n" +
"   SET [GuestNote] = ?\n" +
"      \n" +
" WHERE orderID= ?";

    try {
        PreparedStatement st = connection.prepareStatement(sql);
        st.setString(1, note);
        st.setInt(2, orderID);
       
        int rowsUpdated = st.executeUpdate();
        if (rowsUpdated == 0) {
            throw new Exception("No rows updated, check FoodID and IngredientID.");
        }
    } catch (Exception e) {
        throw new Exception("Error updating quantity: " + e.getMessage());
    }
    
} 
        public void quantityUpdate(int quantity, int orderFoodID) throws Exception {
    String sql = "UPDATE [dbo].[Order_Food]\n" +
"   SET \n" +
"      [Quantity] = ?\n" +
" WHERE Order_FoodID = ?";

    try {
        PreparedStatement st = connection.prepareStatement(sql);
        st.setInt(1, quantity);
      st.setInt(2, orderFoodID);
        int rowsUpdated = st.executeUpdate();
        if (rowsUpdated == 0) {
            throw new Exception("No rows updated, check FoodID and IngredientID.");
        }
    } catch (Exception e) {
        throw new Exception("Error updating quantity: " + e.getMessage());
    }
    
}
          public void quantityDUpdate(int quantity, int orderDrinkID) throws Exception {
    String sql = "UPDATE [dbo].[Order_Drink]\n" +
"   SET \n" +
"      [Quantity] = ?\n" +
" WHERE Order_drinkID = ?";

    try {
        PreparedStatement st = connection.prepareStatement(sql);
        st.setInt(1, quantity);
      st.setInt(2, orderDrinkID);
        int rowsUpdated = st.executeUpdate();
        if (rowsUpdated == 0) {
            throw new Exception("No rows updated, check FoodID and IngredientID.");
        }
    } catch (Exception e) {
        throw new Exception("Error updating quantity: " + e.getMessage());
    }
    
}
          public List<Model.dao.orderN> foodOrderCheckout(int Table_OrderID) {
        List<Model.dao.orderN> orderList = new ArrayList<>();
        String sql = "	SELECT [order].*, Order_Food.*, Product.price, Food.foodName, Food.image\n" +
"FROM [order]\n" +
"LEFT JOIN Order_Food ON [order].orderID = Order_Food.orderID\n" +
"LEFT JOIN Product ON Order_Food.foodID = Product.foodID\n" +
"LEFT JOIN Food ON Order_Food.foodID = Food.foodID\n" +
"WHERE Table_OrderID = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
                    st.setInt(1, Table_OrderID);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                orderN p = new orderN();

                orderList.add(new orderN(
                        rs.getInt(1), rs.getString(2), rs.getInt(3),
                        rs.getInt(4), rs.getInt(5), rs.getInt(6),
                        rs.getInt(7), rs.getInt(8), rs.getInt(9),
                        rs.getInt(10), rs.getInt(11), rs.getString(12), rs.getString(13)
                )
                );
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return orderList;
    }

  public static void main(String[] args) {
    // Khởi tạo đối tượng DAO
    orderDAOt dao = new orderDAOt();
    
    int customerID = 1;

    List<Model.dao.buffetOrder> orderList = dao.buffetOrderList(customerID);

    if (orderList != null && !orderList.isEmpty()) {
        System.out.println("Danh sách Buffet Orders cho customerID = " + customerID + ":");
        for (Model.dao.buffetOrder order : orderList) {
            System.out.println("OrderID: " + order.getOrderID());
            System.out.println("Guest Note: " + order.getGuestNote());
            System.out.println("GuestID: " + order.getGuestID());
            System.out.println("CustomerID: " + order.getCustomerID());
            System.out.println("Total: " + order.getTotal());
            System.out.println("Table_OrderID: " + order.getTable_OrderID());
            System.out.println("Price: " + order.getPrice());
            System.out.println("Buffet Name: " + order.getBuffetName());
            System.out.println("Image: " + order.getImage());
            System.out.println("---------------");
        }
    } else {
        System.out.println("Không có Buffet Orders cho customerID = " + customerID);
    }
}


    }
    

       

