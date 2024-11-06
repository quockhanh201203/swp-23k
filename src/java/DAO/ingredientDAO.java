/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Ingredient_Food;
import Model.dao.food;
import Model.dao.ingredient;
import Model.ingredient2;
import Model.ingredientUpdated;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author tran tung
 */

    public class ingredientDAO extends DBContext {

    public List<Model.dao.ingredient> getIngredientList() {
        List<Model.dao.ingredient> ingredientList = new ArrayList<>();
        String sql = "SELECT \n"
                + "    f.FoodID,\n"
                + "    f.FoodName,\n"
                + "    STRING_AGG(CONCAT(i.IngredientName, ': ', COALESCE(ingredient_food.Quantity, 0)), ', ') AS IngredientQuantities,\n"
                + "    SUM(COALESCE(ingredient_food.Quantity, 0)) AS TotalQuantity\n"
                + "FROM \n"
                + "    Food f\n"
                + "LEFT JOIN \n"
                + "    Ingredient_Food ingredient_food ON f.FoodID = ingredient_food.FoodID\n"
                + "LEFT JOIN \n"
                + "    Ingredient i ON ingredient_food.IngredientID = i.IngredientID\n"
                + "GROUP BY \n"
                + "    f.FoodID, f.FoodName\n"
                + "ORDER BY \n"
                + "    f.FoodName;";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ingredient p = new ingredient();

                ingredientList.add(new ingredient(
                        rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getInt(4)
                )
                );
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return ingredientList;
    }

    public List<ingredient2> getIngredientist2() {
        List<ingredient2> ingredientList2 = new ArrayList<>();
        String sql = "select*from Ingredient";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ingredient2 p = new ingredient2();

                ingredientList2.add(new ingredient2(
                        rs.getInt(1), rs.getString(2), rs.getInt(3)
                )
                );
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return ingredientList2;
    }

    public List<Ingredient_Food> getIngredient_FoodList() {
        List<Ingredient_Food> Ingredient_FoodList = new ArrayList<>();
        String sql = "select*from Ingredient_Food";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Ingredient_Food p = new Ingredient_Food();

                Ingredient_FoodList.add(new Ingredient_Food(
                        rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4)
                )
                );
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return Ingredient_FoodList;
    }

    public Model.dao.food getFoodId(String FoodName) {
        String sql = "SELECT FoodID FROM Food WHERE FoodName = ?";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, FoodName);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    food a = new food();
                    a.setFoodID(rs.getInt("FoodID"));
                    return a;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ingredient2 getIngredientId(String IngredientName) {
        String sql = "SELECT IngredientID FROM Ingredient WHERE IngredientName = ?";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, IngredientName);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    ingredient2 a = new ingredient2();
                    a.setIngredientID(rs.getInt("IngredientID"));
                    return a;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Ingredient_Food newRecipe(int FoodID, int IngredientID, int Quantity) {
        String sql = "INSERT INTO [dbo].[Ingredient_Food] "
                + "([FoodID], [IngredientID], [Quantity]) "
                + "VALUES (?, ?, ?)";
        try {
            Ingredient_Food a = new Ingredient_Food();
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, FoodID);
            st.setInt(2, IngredientID);
            st.setInt(3, Quantity);

            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                a.setFoodID(FoodID); // Sử dụng setter hợp lệ
                a.setIngredientID(IngredientID); // Sử dụng setter hợp lệ
                a.setQuantity(Quantity); // Sử dụng setter hợp lệ
                return a;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void delete(int FoodID) {
        boolean success = false;

        String sql = "DELETE FROM [dbo].[Ingredient_Food]\n"
                + "      WHERE FoodID  = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, FoodID);
            ResultSet rs = st.executeQuery();
            int rowsDeleted = st.executeUpdate();
            if (rowsDeleted > 0) {

                success = true;
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void deleteI(int ingredientID) {
        boolean success = false;

        String sql = "DELETE FROM [dbo].[Ingredient]\n" +
"      WHERE IngredientID = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, ingredientID);
            ResultSet rs = st.executeQuery();
            int rowsDeleted = st.executeUpdate();
            if (rowsDeleted > 0) {

                success = true;
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void update(int FoodID, int IngredientID, int quantity) throws Exception {
        String sql = "UPDATE ingredient_food SET Quantity = ? WHERE FoodID = ? AND IngredientID = ?;";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, quantity);
            st.setInt(2, FoodID);
            st.setInt(3, IngredientID);
            int rowsUpdated = st.executeUpdate();
            if (rowsUpdated == 0) {
                throw new Exception("No rows updated, check FoodID and IngredientID.");
            }
        } catch (Exception e) {
            throw new Exception("Error updating quantity: " + e.getMessage());
        }

    }

    public List<ingredientUpdated> getIngredientUpdated(int FoodID) {
        List<ingredientUpdated> ingredientUpdatedList = new ArrayList<>();
        String sql = "SELECT \n"
                + "    ingredient_food.FoodID,  -- Thêm cột FoodID\n"
                + "    i.IngredientName FROM \n"
                + "    ingredient_food\n"
                + "JOIN \n"
                + "    ingredient i ON ingredient_food.IngredientID = i.IngredientID\n"
                + "WHERE \n"
                + "    ingredient_food.FoodID = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, FoodID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ingredientUpdated p = new ingredientUpdated();

                ingredientUpdatedList.add(new ingredientUpdated(
                        rs.getInt(1), rs.getString(2)
                )
                );
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return ingredientUpdatedList;
    }

    public void ingredientUpdate(int ingredientID, int quantity, String ingredientName) throws Exception {
        String sql = "UPDATE [dbo].[Ingredient]\n"
                + "   SET [IngredientName] = ?\n"
                + "      ,[Quantity] = ?\n"
                + " WHERE IngredientID = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, ingredientName);
            st.setInt(2, quantity);
            st.setInt(3, ingredientID);
            int rowsUpdated = st.executeUpdate();
            if (rowsUpdated == 0) {
                throw new Exception("No rows updated, check FoodID and IngredientID.");
            }
        } catch (Exception e) {
            throw new Exception("Error updating quantity: " + e.getMessage());
        }

    }
    public ingredient2 newIngredient(String ingredientName, int quantity) {
    String sql = "INSERT INTO [dbo].[Ingredient] ([IngredientName], [Quantity]) VALUES (?, ?)";
    
    try {
        // Chuẩn bị câu lệnh SQL và thiết lập các tham số
        PreparedStatement st = connection.prepareStatement(sql);
        st.setString(1, ingredientName);
        st.setInt(2, quantity);

        // Thực hiện cập nhật và kiểm tra số dòng bị ảnh hưởng
        int rowsAffected = st.executeUpdate();
        
        if (rowsAffected > 0) {
            // Tạo đối tượng ingredient2 và thiết lập các thuộc tính
            ingredient2 newIngredient = new ingredient2();
            newIngredient.setIngredientName(ingredientName);
            newIngredient.setQuantity(quantity);
            
            return newIngredient; // Trả về đối tượng mới tạo
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    
    return null; // Trả về null nếu xảy ra lỗi hoặc không chèn được
}
        public static void main(String[] args) {
            ingredientDAO d = new ingredientDAO();
            d.deleteI(2);
        }
}