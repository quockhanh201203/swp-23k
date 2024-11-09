/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;


import Model.Brand;
import Model.Buffet;
import Model.Drink;
import Model.FoodCategory;
import Model.Food;
import Model.PriceHistory;
import Model.Product;
import Model.dao.DrinkCategory;
import Model.dao.buffet;
import Model.dao.drink;
import Model.dao.food;
import Model.dao.foodCategory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tran tung
 */
public class MenuDAO extends DBContext{
     public List<Model.dao.food> getFoodList() {
        List<Model.dao.food> foodList = new ArrayList<>();
        String sql = " select * from product\n"
                + "LEFT JOIN Food ON product.FoodID = Food.FoodID\n"
                + "LEFT JOIN Food_Category ON Food.CategoryID = Food_Category.CategoryID\n"
                + "                WHERE product.FoodID IS NOT NULL";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                food p = new food();

                foodList.add(new food(
                        rs.getInt(1), rs.getInt(2), rs.getInt(3),
                        rs.getInt(4), rs.getInt(5), rs.getInt(6),
                        rs.getString(7), rs.getInt(8), rs.getString(9),
                        rs.getString(10), rs.getInt(11), rs.getString(12)
                )
                );
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return foodList;
    }

    public List<Model.dao.drink> getDrinkList() {
        List<Model.dao.drink> drinkList = new ArrayList<>();
        String sql = "SELECT product.*, Drink.*, Drink_Category.CategoryName\n" +
"FROM product\n" +
"LEFT JOIN Drink ON product.DrinkID = Drink.DrinkID\n" +
"LEFT JOIN Drink_Category ON Drink.CategoryID = Drink_Category.CategoryID\n" +
"WHERE product.DrinkID IS NOT NULL;";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                drink p = new drink();

                drinkList.add(new drink(
                        rs.getInt(1), rs.getInt(2), rs.getInt(3),
                        rs.getInt(4), rs.getInt(5), rs.getInt(6),
                        rs.getString(7), rs.getInt(8), rs.getInt(9),
                        rs.getInt(10),
                        rs.getString(11), rs.getString(12), rs.getString(13)
                )
                );
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return drinkList;
    }

    public List<Model.dao.buffet> getBuffetList() {
        List<Model.dao.buffet> buffetList = new ArrayList<>();
        String sql = " SELECT product.*, \n"
                + "       Buffet.*, \n"
                + "       Buffet_Food.*, \n"
                + "       Buffet_Drink.*, \n"
                + "       Drink.DrinkName, \n"
                + "       Food.FoodName\n"
                + "FROM product\n"
                + "LEFT JOIN Buffet ON product.BuffetID = Buffet.BuffetID\n"
                + "LEFT JOIN Buffet_Food ON Buffet.BuffetID = Buffet_Food.BuffetID\n"
                + "LEFT JOIN Buffet_Drink ON Buffet.BuffetID = Buffet_Drink.BuffetID\n"
                + "LEFT JOIN Drink ON Buffet_Drink.DrinkID = Drink.DrinkID\n"
                + "LEFT JOIN Food ON Buffet_Food.FoodID = Food.FoodID\n"
                + "WHERE Buffet.BuffetID IS NOT NULL;";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                buffet p = new buffet();

                buffetList.add(new buffet(
                        rs.getInt(1), rs.getInt(2), rs.getInt(3),
                        rs.getInt(4), rs.getInt(5), rs.getInt(6),
                        rs.getString(7), rs.getString(8), rs.getInt(9),
                        rs.getInt(10),
                        rs.getInt(11), rs.getInt(12), rs.getInt(13), rs.getInt(14),
                        rs.getString(15), rs.getString(16)
                )
                );
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return buffetList;
    }
         public List<Model.dao.food> getChickenList() {
        List<Model.dao.food> foodList = new ArrayList<>();
        String sql = "SELECT *\n" +
"FROM product\n" +
"LEFT JOIN Food ON product.FoodID = Food.FoodID\n" +
"LEFT JOIN Food_Category ON Food.CategoryID = Food_Category.CategoryID\n" +
"WHERE product.FoodID IS NOT NULL\n" +
"AND Food.CategoryID = 1;";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                food p = new food();

                foodList.add(new food(
                        rs.getInt(1), rs.getInt(2), rs.getInt(3),
                        rs.getInt(4), rs.getInt(5), rs.getInt(6),
                        rs.getString(7), rs.getInt(8), rs.getString(9),
                        rs.getString(10), rs.getInt(11), rs.getString(12)
                )
                );
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return foodList;
    }
public List<Model.dao.food> getBurgerList() {
        List<Model.dao.food> foodList = new ArrayList<>();
        String sql = "SELECT *\n" +
"FROM product\n" +
"LEFT JOIN Food ON product.FoodID = Food.FoodID\n" +
"LEFT JOIN Food_Category ON Food.CategoryID = Food_Category.CategoryID\n" +
"WHERE product.FoodID IS NOT NULL\n" +
"AND Food.CategoryID = 2;";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                food p = new food();

                foodList.add(new food(
                        rs.getInt(1), rs.getInt(2), rs.getInt(3),
                        rs.getInt(4), rs.getInt(5), rs.getInt(6),
                        rs.getString(7), rs.getInt(8), rs.getString(9),
                        rs.getString(10), rs.getInt(11), rs.getString(12)
                )
                );
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return foodList;
    }
public List<Model.dao.food> getSanList() {
        List<Model.dao.food> foodList = new ArrayList<>();
        String sql = "SELECT *\n" +
"FROM product\n" +
"LEFT JOIN Food ON product.FoodID = Food.FoodID\n" +
"LEFT JOIN Food_Category ON Food.CategoryID = Food_Category.CategoryID\n" +
"WHERE product.FoodID IS NOT NULL\n" +
"AND Food.CategoryID = 3;";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                food p = new food();

                foodList.add(new food(
                        rs.getInt(1), rs.getInt(2), rs.getInt(3),
                        rs.getInt(4), rs.getInt(5), rs.getInt(6),
                        rs.getString(7), rs.getInt(8), rs.getString(9),
                        rs.getString(10), rs.getInt(11), rs.getString(12)
                )
                );
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return foodList;
    }
     public List<Model.dao.food> getFoodSearch(String keyword) {
    List<Model.dao.food> foodList = new ArrayList<>();
    String sql = "SELECT * FROM product "
               + "LEFT JOIN Food ON product.FoodID = Food.FoodID "
               + "LEFT JOIN Food_Category ON Food.CategoryID = Food_Category.CategoryID "
               + "WHERE product.FoodID IS NOT NULL AND Food.FoodName LIKE ?";

    try {
        PreparedStatement st = connection.prepareStatement(sql);

        // Ghép dấu % trước và sau từ khóa tìm kiếm
        st.setString(1, "%" + keyword + "%");

        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            food p = new food();

            // Thêm food vào danh sách
            foodList.add(new food(
                rs.getInt(1), rs.getInt(2), rs.getInt(3),
                rs.getInt(4), rs.getInt(5), rs.getInt(6),
                rs.getString(7), rs.getInt(8), rs.getString(9),
                rs.getString(10), rs.getInt(11), rs.getString(12)
            ));
        }

    } catch (Exception e) {
        System.out.println(e);
    }
    return foodList;
}
      public List<Model.dao.drink> getDrinkSearch(String keyword) {
    List<Model.dao.drink> drinkList = new ArrayList<>();
    String sql = "SELECT * FROM product\n" +
"LEFT JOIN Drink ON product.DrinkID = Drink.DrinkID\n" +
"LEFT JOIN Drink_Category ON Drink.CategoryID = Drink_Category.CategoryID\n" +
"WHERE product.DrinkID IS NOT NULL\n" +
"  AND Drink.DrinkName LIKE ?";

    try {
        PreparedStatement st = connection.prepareStatement(sql);

        // Ghép dấu % trước và sau từ khóa tìm kiếm
        st.setString(1, "%" + keyword + "%");

        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            drink p = new drink();

            // Thêm food vào danh sách
            drinkList.add(new drink(
              rs.getInt(1), rs.getInt(2), rs.getInt(3),
                        rs.getInt(4), rs.getInt(5), rs.getInt(6),
                        rs.getString(7), rs.getInt(8), rs.getInt(9),
                        rs.getInt(10),
                        rs.getString(11), rs.getString(12), rs.getString(13)
            ));
        }

    } catch (Exception e) {
        System.out.println(e);
    }
    return drinkList;
}
       public List<Model.dao.buffet> getBuffetSearch(String keyword) {
    List<Model.dao.buffet> buffetList = new ArrayList<>();
    String sql = " SELECT product.*, \n"
                + "       Buffet.*, \n"
                + "       Buffet_Food.*, \n"
                + "       Buffet_Drink.*, \n"
                + "       Drink.DrinkName, \n"
                + "       Food.FoodName\n"
                + "FROM product\n"
                + "LEFT JOIN Buffet ON product.BuffetID = Buffet.BuffetID\n"
                + "LEFT JOIN Buffet_Food ON Buffet.BuffetID = Buffet_Food.BuffetID\n"
                + "LEFT JOIN Buffet_Drink ON Buffet.BuffetID = Buffet_Drink.BuffetID\n"
                + "LEFT JOIN Drink ON Buffet_Drink.DrinkID = Drink.DrinkID\n"
                + "LEFT JOIN Food ON Buffet_Food.FoodID = Food.FoodID\n"
                + "WHERE Buffet.BuffetID IS NOT NULL  AND Buffet.BuffetName LIKE ?;";

    try {
        PreparedStatement st = connection.prepareStatement(sql);

        // Ghép dấu % trước và sau từ khóa tìm kiếm
        st.setString(1, "%" + keyword + "%");

        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            buffet p = new buffet();

            // Thêm food vào danh sách
            buffetList.add(new buffet(
rs.getInt(1), rs.getInt(2), rs.getInt(3),
                        rs.getInt(4), rs.getInt(5), rs.getInt(6),
                        rs.getString(7), rs.getString(8), rs.getInt(9),
                        rs.getInt(10),
                        rs.getInt(11), rs.getInt(12), rs.getInt(13), rs.getInt(14),
                        rs.getString(15), rs.getString(16)
            ));
        }

    } catch (Exception e) {
        System.out.println(e);
    }
    return buffetList;
}
       public List<Model.dao.foodCategory> getFoodCategory() {
    List<Model.dao.foodCategory> foodCategoryList = new ArrayList<>();
    String sql = "select * from Food_Category";

    try {
        PreparedStatement st = connection.prepareStatement(sql);


        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            foodCategory p = new foodCategory();

            // Thêm food vào danh sách
            foodCategoryList.add(new foodCategory(
rs.getInt(1), rs.getString(2)
            ));
        }

    } catch (Exception e) {
        System.out.println(e);
    }
    return foodCategoryList;
}
       public List<Model.dao.DrinkCategory> getDrinkCategory() {
    List<Model.dao.DrinkCategory> drinkListCategory = new ArrayList<>();
    String sql = " select * from Drink_Category";

    try {
        PreparedStatement st = connection.prepareStatement(sql);

        

        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            DrinkCategory p = new DrinkCategory();

            // Thêm food vào danh sách
            drinkListCategory.add(new DrinkCategory(
rs.getInt(1), rs.getString(2)

            ));
        }

    } catch (Exception e) {
        System.out.println(e);
    }
    return drinkListCategory;
}
 public List<Model.dao.food> getFoodfilter(String CategoryName) {
    List<Model.dao.food> foodList = new ArrayList<>();
    String sql = "SELECT * FROM product " +
                 "LEFT JOIN Food ON product.FoodID = Food.FoodID " +
                 "LEFT JOIN Food_Category ON Food.CategoryID = Food_Category.CategoryID " +
                 "WHERE product.FoodID IS NOT NULL " +
                 "  AND Food_Category.CategoryName = ?";  // Không cần dấu % vì tìm kiếm chính xác

    try {
        PreparedStatement st = connection.prepareStatement(sql);

  
        st.setString(1, CategoryName); 

        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            food p = new food();

        
            foodList.add(new food(
                 rs.getInt(1), rs.getInt(2), rs.getInt(3),
                        rs.getInt(4), rs.getInt(5), rs.getInt(6),
                        rs.getString(7), rs.getInt(8), rs.getString(9),
                        rs.getString(10), rs.getInt(11), rs.getString(12)
            ));
        }

    } catch (Exception e) {
        System.out.println(e);
    }
    return foodList;
}
 public List<Model.dao.drink> getDrinkfilter(String CategoryName) {
    List<Model.dao.drink> drinkList = new ArrayList<>();
    String sql = "SELECT product.*, Drink.*, Drink_Category.CategoryName\n" +
"FROM product\n" +
"LEFT JOIN Drink ON product.DrinkID = Drink.DrinkID\n" +
"LEFT JOIN Drink_Category ON Drink.CategoryID = Drink_Category.CategoryID\n" +
"WHERE product.DrinkID IS NOT NULL\n" +
"AND Drink_Category.CategoryName = ?";  

    try {
        PreparedStatement st = connection.prepareStatement(sql);

  
        st.setString(1, CategoryName); 

        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            drink p = new drink();

        
            drinkList.add(new drink(
                   rs.getInt(1), rs.getInt(2), rs.getInt(3),
                        rs.getInt(4), rs.getInt(5), rs.getInt(6),
                        rs.getString(7), rs.getInt(8), rs.getInt(9),
                        rs.getInt(10),
                        rs.getString(11), rs.getString(12), rs.getString(13)
            ));
        }

    } catch (Exception e) {
        System.out.println(e);
    }
    return drinkList;
}
}
