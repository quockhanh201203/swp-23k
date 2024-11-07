/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.dao.buffet;
import Model.dao.food;
import Model.homePage;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tran tung
 */
public class homepageDAO  extends DBContext{
    public List<Model.dao.food> getNewList() {
        List<Model.dao.food> foodList = new ArrayList<>();
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
    public List<Model.dao.food> getTopList() {
        List<Model.dao.food> foodList = new ArrayList<>();
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
    
    public List<Model.dao.buffet> getTopBuffetList() {
        List<Model.dao.buffet> buffetList = new ArrayList<>();
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
    
     public homePage getHomePageBanner() {
    homePage homePage = new homePage();
    String sql = "SELECT * FROM Homepage WHERE id = 1";

    try {
        PreparedStatement st = connection.prepareStatement(sql);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            // Gán giá trị từ ResultSet vào đối tượng homePage
            homePage.setBanner(rs.getString("banner"));
            homePage.setImg1(rs.getString("img1"));
            homePage.setImg2(rs.getString("img2"));
            homePage.setImg3(rs.getString("img3"));
            homePage.setImg4(rs.getString("img4"));
            homePage.setScript1(rs.getString("script1"));
            homePage.setScript2(rs.getString("script2"));
        }
    } catch (Exception e) {
        System.out.println(e);
    }
    return homePage;
}

    
    public static void main(String[] args) {
       
         homepageDAO dao = new homepageDAO();
    homePage homePage = dao.getHomePageBanner();

    System.out.println("Banner: " + homePage.getBanner());
    System.out.println("Img1: " + homePage.getImg1());
    System.out.println("Img2: " + homePage.getImg2());
    System.out.println("Img3: " + homePage.getImg3());
    System.out.println("Img4: " + homePage.getImg4());
    System.out.println("Script1: " + homePage.getScript1());
    System.out.println("Script2: " + homePage.getScript2());
    }
    
    
}