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
import model.dao.food;

/**
 *
 * @author tran tung
 */
public class homepageDAO extends DBContext{
    
    public List<model.dao.food> getNewList() {
        List<model.dao.food> foodList = new ArrayList<>();
        String sql = "SELECT TOP 6 *\n" +
"FROM product\n" +
"LEFT JOIN Food ON product.FoodID = Food.FoodID\n" +
"LEFT JOIN Food_Category ON Food.CategoryID = Food_Category.CategoryID\n" +
"WHERE product.FoodID IS NOT NULL\n" +
"ORDER BY product.FoodID DESC; ";

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
    public List<model.dao.food> getTopList() {
        List<model.dao.food> foodList = new ArrayList<>();
        String sql = "SELECT TOP 3 *\n" +
"FROM product\n" +
"LEFT JOIN Food ON product.FoodID = Food.FoodID\n" +
"LEFT JOIN Food_Category ON Food.CategoryID = Food_Category.CategoryID\n" +
"WHERE product.FoodID IS NOT NULL\n" +
"ORDER BY RAND(); ";

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
    
    public List<model.dao.buffet> getTopBuffetList() {
        List<model.dao.buffet> buffetList = new ArrayList<>();
        String sql = "SELECT \n" +
"    product.*, \n" +
"    Buffet.*,       \n" +
"    Buffet_Food.*,   \n" +
"    Buffet_Drink.*, \n" +
"    Drink.DrinkName, \n" +
"    Food.FoodName\n" +
"FROM product\n" +
"LEFT JOIN Buffet ON product.BuffetID = Buffet.BuffetID\n" +
"LEFT JOIN Buffet_Food ON Buffet.BuffetID = Buffet_Food.BuffetID\n" +
"LEFT JOIN Buffet_Drink ON Buffet.BuffetID = Buffet_Drink.BuffetID\n" +
"LEFT JOIN Drink ON Buffet_Drink.DrinkID = Drink.DrinkID\n" +
"LEFT JOIN Food ON Buffet_Food.FoodID = Food.FoodID\n" +
"WHERE Buffet.BuffetID IS NOT NULL ORDER BY RAND();";

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
    
    
    
    
    
    
    
        public static void main(String[] args) {
        homepageDAO dao = new homepageDAO();
        List<model.dao.buffet> pr = dao.getTopBuffetList(); 

        for (int i = 0; i < pr.size(); i++) {
            System.out.println(pr.get(i));
        }
    }

        
        
        
        
        
        
    }
    
    
    
    
    
    
    
    
    
