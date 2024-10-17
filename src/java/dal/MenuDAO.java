/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.dao.buffet;
import model.dao.drink;
import model.dao.food;
import model.dao.product;

/**
 *
 * @author tran tung
 */
public class MenuDAO extends DBContext {

//    public List<model.dao.product> getProductList() {
//        List<model.dao.product> productList = new ArrayList<>();
//        String sql = " SELECT *\n" +
//"FROM product\n" +
//"LEFT JOIN Buffet ON product.BuffetID = Buffet.BuffetID\n" +
//"LEFT JOIN buffet_Food ON Buffet.BuffetID = buffet_Food.BuffetID\n" +
//"LEFT JOIN Food ON buffet_Food.FoodID = Food.FoodID\n" +
//"LEFT JOIN Food_Category ON Food.CategoryID = Food_Category.CategoryID\n" +
//"LEFT JOIN buffet_Drink ON Buffet.BuffetID = buffet_Drink.BuffetID\n" +
//"LEFT JOIN Drink ON buffet_Drink.DrinkID = Drink.DrinkID\n" +
//"LEFT JOIN Drink_Category ON Drink.CategoryID = Drink_Category.CategoryID\n" +
//"LEFT JOIN Brand ON Drink.BrandID = Brand.BrandID;";
//
//        try {
//            PreparedStatement st = connection.prepareStatement(sql);
//            ResultSet rs = st.executeQuery();
//            while (rs.next()) {
//                product p = new product();
//                
//
//                productList.add(new product(
//                        
//                        rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5),
//                        rs.getInt(6), rs.getString(7), rs.getInt(8), rs.getString(9), rs.getString(10),
//                        rs.getInt(11), rs.getString(12), rs.getInt(13), rs.getInt(14), rs.getInt(15),
//                        rs.getString(16), rs.getString(17), rs.getInt(18), rs.getString(19), rs.getInt(20),
//                        rs.getString(21), rs.getInt(22), rs.getString(23), rs.getInt(24), rs.getInt(25),
//                        rs.getInt(26), rs.getInt(27), rs.getInt(28), rs.getInt(29),rs.getInt(30)
//                )
//                );
//            }
//
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        return productList;
//    }
    public List<model.dao.food> getFoodList() {
        List<model.dao.food> foodList = new ArrayList<>();
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

    public List<model.dao.drink> getDrinkList() {
        List<model.dao.drink> drinkList = new ArrayList<>();
        String sql = " SELECT *\n"
                + "FROM product\n"
                + "LEFT JOIN Drink ON product.DrinkID = Drink.DrinkID\n"
                + "LEFT JOIN Drink_Category ON Drink.CategoryID = Drink.CategoryID\n"
                + "WHERE product.DrinkID IS NOT NULL;";

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
                        rs.getString(11), rs.getString(12), rs.getInt(13), rs.getString(14)
                )
                );
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return drinkList;
    }

    public List<model.dao.buffet> getBuffetList() {
        List<model.dao.buffet> buffetList = new ArrayList<>();
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
         public List<model.dao.food> getChickenList() {
        List<model.dao.food> foodList = new ArrayList<>();
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
public List<model.dao.food> getBurgerList() {
        List<model.dao.food> foodList = new ArrayList<>();
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
public List<model.dao.food> getSanList() {
        List<model.dao.food> foodList = new ArrayList<>();
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
    

    public static void main(String[] args) {
        MenuDAO dao = new MenuDAO();
        List<food> pr = dao.getFoodList(); 

        for (int i = 0; i < pr.size(); i++) {
            System.out.println(pr.get(i));
        }
    }

}
