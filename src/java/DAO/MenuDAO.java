/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Brand;
import Model.Buffet;
import Model.Drink;
import Model.DrinkCategory;
import Model.Food;
import Model.FoodCategory;
import Model.PriceHistory;
import Model.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Legion
 */
public class MenuDAO extends DBContext {

    private Connection connection;

    public MenuDAO() {
        try {
            connection = getConnection();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List<Buffet> getAllBuffets() {
        List<Buffet> buffets = new ArrayList<>();
        String query = "SELECT [BuffetID], [BuffetName], [Image] FROM [5AnhLucDB].[dbo].[Buffet]";

        try (
                PreparedStatement ps = connection.prepareStatement(query); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Buffet buffet = new Buffet();
                buffet.setBuffetID(rs.getInt("BuffetID"));
                buffet.setBuffetName(rs.getString("BuffetName"));
                buffet.setImage(rs.getString("Image"));
                buffets.add(buffet);
            }
        } catch (Exception e) {
            System.out.println("getAllBuffets: " + e.getMessage());
        }
        return buffets;
    }

    public List<Drink> getAllDrinks() {
        List<Drink> drinks = new ArrayList<>();
        String query = "SELECT [DrinkID], [DrinkName], [DrinkPrice], [Quantity], [CategoryID], [BrandID], [Status], [Image] FROM [5AnhLucDB].[dbo].[Drink]";

        try (
                PreparedStatement ps = connection.prepareStatement(query); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Drink drink = new Drink();
                drink.setDrinkID(rs.getInt("DrinkID"));
                drink.setDrinkName(rs.getString("DrinkName"));
                drink.setDrinkPrice(rs.getDouble("DrinkPrice"));
                drink.setQuantity(rs.getInt("Quantity"));
                drink.setCategoryID(rs.getInt("CategoryID"));
                drink.setBrandID(rs.getInt("BrandID"));
                drink.setStatus(rs.getInt("Status"));
                drink.setImage(rs.getString("Image"));
                drinks.add(drink);
            }
        } catch (Exception e) {
            System.out.println("getAllDrinks: " + e.getMessage());
        }
        return drinks;
    }

    public List<Food> getAllFoods() {
        List<Food> foods = new ArrayList<>();
        String query = "SELECT [FoodID], [FoodName], [CategoryID], [Status], [Image] FROM [5AnhLucDB].[dbo].[Food]";

        try (PreparedStatement ps = connection.prepareStatement(query); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Food food = new Food();
                food.setFoodID(rs.getInt("FoodID"));
                food.setFoodName(rs.getString("FoodName"));
                food.setCategoryID(rs.getInt("CategoryID"));
                food.setStatus(rs.getInt("Status"));
                food.setImage(rs.getString("Image"));
                foods.add(food);
            }
        } catch (Exception e) {
            System.out.println("getAllFoods: " + e.getMessage());
        }
        return foods;
    }

    public DrinkCategory getDrinkCategoryByID(int categoryID) {
        String query = "SELECT CategoryID, CategoryName FROM Drink_Category WHERE CategoryID = ?";
        DrinkCategory drinkCategory = null;

        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            // Set the CategoryID parameter
            stmt.setInt(1, categoryID);

            // Execute the query
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("CategoryID");
                    String categoryName = rs.getString("CategoryName");

                    // Create a new DrinkCategory object
                    drinkCategory = new DrinkCategory(id, categoryName);
                }
            }
        } catch (SQLException e) {
            System.out.println("getDrinkCategoryByID: " + e.getMessage());
        }

        return drinkCategory; // Return the DrinkCategory object or null if not found
    }

    public List<DrinkCategory> getDrinkCategories() {
        String query = "SELECT CategoryID, CategoryName FROM Drink_Category";
        List<DrinkCategory> drinkCategories = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            // Execute the query
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("CategoryID");
                    String categoryName = rs.getString("CategoryName");
                    drinkCategories.add(new DrinkCategory(id, categoryName));
                }
            }
        } catch (SQLException e) {
            System.out.println("getDrinkCategories: " + e.getMessage());
        }

        return drinkCategories; // Return the DrinkCategory object or null if not found
    }

    public List<FoodCategory> getFoodCategories() {
        String query = "SELECT CategoryID, CategoryName FROM FoodCategory";
        List<FoodCategory> drinkCategories = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            // Execute the query
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("CategoryID");
                    String categoryName = rs.getString("CategoryName");
                    drinkCategories.add(new FoodCategory(id, categoryName));
                }
            }
        } catch (SQLException e) {
            System.out.println("getFoodCategories: " + e.getMessage());
        }

        return drinkCategories; // Return the DrinkCategory object or null if not found
    }

    public FoodCategory getFoodCategoryByID(int categoryID) {
        String query = "SELECT CategoryID, CategoryName FROM FoodCategory WHERE CategoryID = ?";
        FoodCategory foodCategory = null;

        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            // Set the CategoryID parameter
            stmt.setInt(1, categoryID);

            // Execute the query
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("CategoryID");
                    String categoryName = rs.getString("CategoryName");

                    // Create a new FoodCategory object
                    foodCategory = new FoodCategory(id, categoryName);
                }
            }
        } catch (SQLException e) {
            System.out.println("getFoodCategoryByID: " + e.getMessage());
        }

        return foodCategory;
    }

    public Brand getBrandByID(int brandID) {
        String query = "SELECT BrandID, BrandName FROM Brand WHERE BrandID = ?";
        Brand brand = null;

        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            // Set the BrandID parameter
            stmt.setInt(1, brandID);

            // Execute the query
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("BrandID");
                    String brandName = rs.getString("BrandName");

                    // Create a new Brand object
                    brand = new Brand(id, brandName);
                }
            }
        } catch (SQLException e) {
            System.out.println("getBrandByID: " + e.getMessage());
        }

        return brand; // Return the Brand object or null if not found
    }

    public List<Brand> getBrands() {
        String query = "SELECT BrandID, BrandName FROM Brand";
        List<Brand> brand = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            // Execute the query
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("BrandID");
                    String brandName = rs.getString("BrandName");

                    // Create a new Brand object
                    brand.add(new Brand(id, brandName));
                }
            }
        } catch (SQLException e) {
            System.out.println("getBrandByID: " + e.getMessage());
        }

        return brand; // Return the Brand object or null if not found
    }

    public int addDrink(Drink drink) {
        String sql = "INSERT INTO Drink (DrinkName, DrinkPrice, Quantity, CategoryID, BrandID, Status, Image) VALUES (?, ?, ?, ?, ?, ?, ?)";
        int generatedKey = -1;
        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Set parameters for the prepared statement
            pstmt.setString(1, drink.getDrinkName());
            pstmt.setDouble(2, drink.getDrinkPrice());
            pstmt.setInt(3, drink.getQuantity());
            pstmt.setInt(4, drink.getCategoryID());
            pstmt.setInt(5, drink.getBrandID());
            pstmt.setInt(6, drink.getStatus());
            pstmt.setString(7, drink.getImage());

            // Execute the insert
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        generatedKey = rs.getInt(1); // Get the generated key (usually the primary key)
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("addDrink: " + e.getMessage());
        }
        return generatedKey;
    }

    public void updateDrink(Drink drink) {
        String sql = "UPDATE Drink SET DrinkName = ?, DrinkPrice = ?, Quantity = ?, CategoryID = ?, BrandID = ?, Status = ?, Image = ? WHERE DrinkID = ?";

        try (
                PreparedStatement pstmt = connection.prepareStatement(sql)) {

            // Set parameters for the prepared statement
            pstmt.setString(1, drink.getDrinkName());
            pstmt.setDouble(2, drink.getDrinkPrice());
            pstmt.setInt(3, drink.getQuantity());
            pstmt.setInt(4, drink.getCategoryID());
            pstmt.setInt(5, drink.getBrandID());
            pstmt.setInt(6, drink.getStatus());
            pstmt.setString(7, drink.getImage());
            pstmt.setInt(8, drink.getDrinkID()); // Set the DrinkID for the WHERE clause

            // Execute the update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("updateDrink: " + e.getMessage());
        }
    }

    public int  addFood(Food food) {
        String sql = "INSERT INTO Food (FoodName, CategoryID, Status, Image) VALUES (?, ?, ?, ?)";
        int generatedKey = -1;
        try (
                PreparedStatement pstmt = connection.prepareStatement(sql)) {

            // Set parameters for the prepared statement
            pstmt.setString(1, food.getFoodName());
            pstmt.setInt(2, food.getCategoryID());
            pstmt.setInt(3, food.getStatus());
            pstmt.setString(4, food.getImage());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        generatedKey = rs.getInt(1); // Get the generated key (usually the primary key)
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("addFood: " + e.getMessage());
        }
        return generatedKey;
    }

    public void updateFood(Food food) {
        String sql = "UPDATE Food SET FoodName = ?, CategoryID = ?, Status = ?, Image = ? WHERE FoodID = ?";

        try (
                PreparedStatement pstmt = connection.prepareStatement(sql)) {

            // Set parameters for the prepared statement
            pstmt.setString(1, food.getFoodName());
            pstmt.setInt(2, food.getCategoryID());
            pstmt.setInt(3, food.getStatus());
            pstmt.setString(4, food.getImage());
            pstmt.setInt(5, food.getFoodID()); // Set the FoodID for the WHERE clause

            // Execute the update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("updateFood: " + e.getMessage());
        }
    }

    public int addBuffet(Buffet buffet) {
        String sql = "INSERT INTO Buffet (BuffetName, Image) VALUES (?, ?)";
        int generatedKey = -1;
        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Set parameters for the prepared statement
            pstmt.setString(1, buffet.getBuffetName());
            pstmt.setString(2, buffet.getImage());

            
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        generatedKey = rs.getInt(1); // Get the generated key (usually the primary key)
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("addBuffet: " + e.getMessage());
        }
        return generatedKey;
    }

    public void updateBuffet(Buffet buffet) {
        String sql = "UPDATE Buffet SET BuffetName = ?, Image = ? WHERE BuffetID = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {

            // Set parameters for the prepared statement
            pstmt.setString(1, buffet.getBuffetName());
            pstmt.setString(2, buffet.getImage());
            pstmt.setInt(3, buffet.getBuffetID()); // Set the BuffetID for the WHERE clause

            // Execute the update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("updateBuffet: " + e.getMessage());
        }
    }

    public void insertPriceHistory(PriceHistory priceHistory) {
        String sql = "INSERT INTO Price_History (ProductID, Price, StartDate, EndDate) VALUES (?, ?, getDate(), null)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            // Set parameters from the PriceHistory object
            ps.setInt(1, priceHistory.getProductId());
            ps.setDouble(2, priceHistory.getPrice());

            // Execute the insert
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("insertPriceHistory: " + e.getMessage());
        }
    }
    

    public int insertProduct(Product product) {
        String sql = "INSERT INTO Product (FoodID, BuffetID, DrinkID, Price) VALUES (";
        
        if(product.getFoodId() == 0) {
            sql += "null, ";
        } else {
            sql += product.getFoodId()+ ", ";
        }
        
        if(product.getBuffetId()== 0) {
            sql += "null, ";
        } else {
            sql += product.getBuffetId()+ ", ";
        }
        
        if(product.getDrinkID()== 0) {
            sql += "null, ";
        } else {
            sql += product.getDrinkID()+ ", ";
        }
        


        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setDouble(1, product.getPrice());

            // Execute the insert
            ps.executeUpdate();

            // Retrieve the generated ProductID
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);  // Return the generated ProductID
                }
            }
        } catch (SQLException e) {
            System.out.println("insertProduct: " + e.getMessage());
            
        }
        return -1;
    }

}
