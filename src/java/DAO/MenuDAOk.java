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
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class MenuDAOk extends DBContext{
    private Connection connection;

    public MenuDAOk() {
        try {
            connection = getConnection();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List<FoodCategory> getAllFoodCategories() {
        List<FoodCategory> categories = new ArrayList<>();
        String sql = "SELECT CategoryID, CategoryName FROM FoodCategory";

        try (PreparedStatement statement = connection.prepareStatement(sql); ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("CategoryID");
                String name = rs.getString("CategoryName");
                categories.add(new FoodCategory(id, name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    public List<DrinkCategory> getAllDrinkCategories() {
        List<DrinkCategory> categories = new ArrayList<>();
        String sql = "SELECT CategoryID, CategoryName FROM Drink_Category";

        try (PreparedStatement statement = connection.prepareStatement(sql); ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("CategoryID");
                String name = rs.getString("CategoryName");
                categories.add(new DrinkCategory(id, name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    public void addDrinkCategory(DrinkCategory category) {
        String sql = "INSERT INTO Drink_Category (CategoryName) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, category.getCategoryName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateDrinkCategory(DrinkCategory category) {
        String sql = "UPDATE Drink_Category SET CategoryName = ? WHERE CategoryID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, category.getCategoryName());
            statement.setInt(2, category.getCategoryID());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDrinkCategory(int id) {
        String sql = "DELETE FROM Drink_Category WHERE CategoryID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void deletePriceHistory(int id) {
        String sql = "DELETE FROM Price_History WHERE [Price_HistoryID] = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addFoodCategory(FoodCategory category) {
        String sql = "INSERT INTO FoodCategory (CategoryName) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, category.getCategoryName());
            System.out.println(statement.executeUpdate());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateFoodCategory(FoodCategory category) {
        String sql = "UPDATE FoodCategory SET CategoryName = ? WHERE CategoryID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, category.getCategoryName());
            statement.setInt(2, category.getCategoryID());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteFoodCategory(int id) {
        String sql = "DELETE FROM FoodCategory WHERE CategoryID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
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
        String query = "SELECT [DrinkID], [DrinkName], [Quantity], [CategoryID], [BrandID], [Status], [Image] FROM [5AnhLucDB].[dbo].[Drink]";

        try (
                PreparedStatement ps = connection.prepareStatement(query); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Drink drink = new Drink();
                drink.setDrinkID(rs.getInt("DrinkID"));
                drink.setDrinkName(rs.getString("DrinkName"));
                drink.setQuantity(rs.getInt("Quantity"));
                drink.setCategoryID(rs.getInt("CategoryID"));
                drink.setBrandID(rs.getInt("BrandID"));
                drink.setStatus(rs.getString("Status"));
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
        String query = "SELECT * FROM [Food] ORDER BY FoodID DESC";

        try (PreparedStatement ps = connection.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
            System.out.println(rs == null);
            while (rs.next()) {
                Food food = new Food();
                food.setFoodID(rs.getInt("FoodID"));
                food.setFoodName(rs.getString("FoodName"));
                food.setCategoryID(rs.getInt("CategoryID"));
                food.setStatus(rs.getString("Status"));
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
        String sql = "INSERT INTO Drink (DrinkName, Quantity, CategoryID, BrandID, Status, Image) VALUES ( ?, ?, ?, ?, ?, ?)";
        int generatedKey = -1;
        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Set parameters for the prepared statement
            pstmt.setString(1, drink.getDrinkName());
            pstmt.setInt(2, drink.getQuantity());
            pstmt.setInt(3, drink.getCategoryID());
            pstmt.setInt(4, drink.getBrandID());
            pstmt.setString(5, drink.getStatus());
            pstmt.setString(6, drink.getImage());

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
        String sql = "UPDATE Drink SET DrinkName = ?, Quantity = ?, CategoryID = ?, BrandID = ?, Status = ?, Image = ? WHERE DrinkID = ?";

        try (
                PreparedStatement pstmt = connection.prepareStatement(sql)) {

            // Set parameters for the prepared statement
            pstmt.setString(1, drink.getDrinkName());
            pstmt.setInt(2, drink.getQuantity());
            pstmt.setInt(3, drink.getCategoryID());
            pstmt.setInt(4, drink.getBrandID());
            pstmt.setString(5, drink.getStatus());
            pstmt.setString(6, drink.getImage());
            pstmt.setInt(7, drink.getDrinkID()); // Set the DrinkID for the WHERE clause

            // Execute the update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("updateDrink: " + e.getMessage());
        }
    }

    public int addFood(Food food) {
        String sql = "INSERT INTO Food (FoodName, CategoryID, Status, Image) VALUES (?, ?, ?, ?)";
        int generatedKey = -1;
        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Set parameters for the prepared statement
            pstmt.setString(1, food.getFoodName());
            pstmt.setInt(2, food.getCategoryID());
            pstmt.setString(3, food.getStatus());
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
            pstmt.setString(3, food.getStatus());
            pstmt.setString(4, food.getImage());
            pstmt.setInt(5, food.getFoodID()); // Set the FoodID for the WHERE clause

            // Execute the update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("updateFood: " + e.getMessage());
        }
    }

    public void deleteFood(Food food) {
        String sql = "UPDATE Food SET Status = ? WHERE FoodID = ?";

        try (
                PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(2, food.getFoodID());
            pstmt.setString(1, food.getStatus()); // Set the FoodID for the WHERE clause

            // Execute the update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("updateFood: " + e.getMessage());
        }
    }

    public void deleteDrink(Drink drink) {
        String sql = "UPDATE Drink SET Status = ? WHERE drinkid = ?";

        try (
                PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(2, drink.getDrinkID());
            pstmt.setString(1, drink.getStatus()); // Set the FoodID for the WHERE clause

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
        updatePriceHistory(priceHistory);
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

    public List<PriceHistory> getPriceHistory(String startDate, String endDate) {
        List<PriceHistory> priceHistoryList = new ArrayList<>();
        String query = "SELECT TOP (1000) Price_HistoryID, ProductID, Price, StartDate, EndDate FROM dbo.Price_History WHERE 1=1 ";

        // Add WHERE clause for date filtering
        if (startDate != null && !startDate.isEmpty()) {
            query += " AND StartDate >= ?";
        }
        if (endDate != null && !endDate.isEmpty()) {
            query += " AND EndDate <= ?";
        }

        try (PreparedStatement statement = connection.prepareStatement(query)) {

            if (startDate != null && !startDate.isEmpty()) {
                statement.setDate(1, Date.valueOf(startDate));
            }

            if (endDate != null && !endDate.isEmpty()) {
                statement.setDate(2, Date.valueOf(endDate));

            }

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                PriceHistory ph = new PriceHistory(
                        resultSet.getInt("ProductID"),
                        resultSet.getDouble("Price"),
                        resultSet.getDate("StartDate"),
                        resultSet.getDate("EndDate")
                );
                ph.setPriceHistoryId(resultSet.getInt(1));
                priceHistoryList.add(ph);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return priceHistoryList;
    }

    public void updatePriceHistory(PriceHistory priceHistory) {
        String sql = "UPDATE Price_History set EndDate = GETDATE() WHERE productID = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            // Set parameters from the PriceHistory object
            ps.setInt(1, priceHistory.getProductId());

            // Execute the insert
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("updatePriceHistory: " + e.getMessage());
        }
    }

    public int insertProduct(Product product) {
        String sql = "INSERT INTO Product (FoodID, BuffetID, DrinkID, Price) VALUES (";

        if (product.getFoodId() == 0) {
            sql += "null, ";
        } else {
            sql += product.getFoodId() + ", ";
        }

        if (product.getBuffetId() == 0) {
            sql += "null, ";
        } else {
            sql += product.getBuffetId() + ", ";
        }

        if (product.getDrinkID() == 0) {
            sql += "null, ";
        } else {
            sql += product.getDrinkID() + ", ";
        }

        sql += "? )";

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

    public void getProductId(Product product) {
        String sql = "Select * FROM Product where 1=1 AND ";
        if (product.getFoodId() != 0) {
            sql += "FoodID = " + product.getFoodId();
        }

        if (product.getBuffetId() != 0) {
            sql += "BuffetID = " + product.getBuffetId();
        }

        if (product.getDrinkID() != 0) {
            sql += "DrinkID = " + product.getDrinkID();
        }

        System.out.println(sql);
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            // Execute the insert
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                product.setProductId(rs.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println("getProductId: " + e.getMessage());

        }
    }

    public int updateProduct(Product product) {
        String sql = "Update Product set price = ? where 1=1 AND ";

        if (product.getFoodId() != 0) {
            sql += "FoodID = " + product.getFoodId();
        }

        if (product.getBuffetId() != 0) {
            sql += "BuffetID = " + product.getBuffetId();
        }

        if (product.getDrinkID() != 0) {
            sql += "DrinkID = " + product.getDrinkID();
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
            System.out.println("updateProduct: " + e.getMessage());

        }
        return -1;
    }

    public double getDrinkPrice(int drinkID) {
        double price = -1.0;
        String query = "SELECT TOP 1 Price FROM Product WHERE DrinkID = ? ORDER BY ProductID DESC";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, drinkID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                price = rs.getDouble("Price");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return price;
    }

    public double getBuffetPrice(int buffetID) {
        double price = -1.0;
        String query = "SELECT TOP 1 Price FROM Product WHERE BuffetID = ? ORDER BY ProductID DESC";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, buffetID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                price = rs.getDouble("Price");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return price;
    }

    public List<Food> getFoodsByBuffetId(int buffetId) {
        List<Food> foods = new ArrayList<>();
        String query = "SELECT f.FoodID, f.FoodName, f.CategoryID, f.Status, f.Image "
                + "FROM Buffet_Food bf JOIN Food f ON bf.FoodID = f.FoodID "
                + "WHERE bf.BuffetID = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, buffetId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Food food = new Food();
                food.setFoodID(rs.getInt("FoodID"));
                food.setFoodName(rs.getString("FoodName"));
                food.setCategoryID(rs.getInt("CategoryID"));
                food.setStatus(rs.getString("Status"));
                food.setImage(rs.getString("Image"));
                foods.add(food);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foods;
    }

    public boolean addFoodToBuffet(int buffetId, int foodId) {
        String query = "INSERT INTO Buffet_Food (BuffetID, FoodID) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, buffetId);
            ps.setInt(2, foodId);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;  // Return true if insertion was successful
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Food> getFoodsNotInBuffet(int buffetId) {
        List<Food> foods = new ArrayList<>();
        String query = "SELECT f.FoodID, f.FoodName, f.CategoryID, f.Status, f.Image "
                + "FROM Food f "
                + "WHERE f.FoodID NOT IN (SELECT bf.FoodID FROM Buffet_Food bf WHERE bf.BuffetID = ?)";

        try (PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, buffetId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Food food = new Food();
                food.setFoodID(rs.getInt("FoodID"));
                food.setFoodName(rs.getString("FoodName"));
                food.setCategoryID(rs.getInt("CategoryID"));
                food.setStatus(rs.getString("Status"));
                food.setImage(rs.getString("Image"));
                foods.add(food);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foods;
    }

    public boolean removeFoodFromBuffet(int buffetId, int foodId) {
        String query = "DELETE FROM Buffet_Food WHERE BuffetID = ? AND FoodID = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, buffetId);
            ps.setInt(2, foodId);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;  // Return true if the deletion was successful
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public int getTotalPages(int size, String search) {
        int totalFoods = 0;
        StringBuilder sql = new StringBuilder("""
        SELECT COUNT(DISTINCT f.FoodID) AS total
        FROM Food f
        LEFT JOIN Ingredient_Food ifd ON f.FoodID = ifd.FoodID
        LEFT JOIN Ingredient i ON ifd.IngredientID = i.IngredientID
    """);

        // Adding search conditions
        List<String> conditions = new ArrayList<>();
        if (search != null && !search.isEmpty()) {
            conditions.add("(f.FoodName LIKE ? OR f.Status LIKE ? OR f.Image LIKE ? OR i.IngredientName LIKE ?)");
        }

        // Append search conditions if any
        if (!conditions.isEmpty()) {
            sql.append(" WHERE ").append(String.join(" AND ", conditions));
        }

        try ( PreparedStatement st = connection.prepareStatement(sql.toString())) {
            int paramIndex = 1;

            // Set search parameters if the search string is provided
            if (search != null && !search.isEmpty()) {
                String searchPattern = "%" + search + "%";
                st.setString(paramIndex++, searchPattern); // For f.FoodName
                st.setString(paramIndex++, searchPattern); // For f.Status
                st.setString(paramIndex++, searchPattern); // For f.Image
                st.setString(paramIndex++, searchPattern); // For i.IngredientName
            }

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                totalFoods = rs.getInt("total"); // Get the total count of filtered foods
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Calculate total pages
        int totalPages = (int) Math.ceil((double) totalFoods / size);
        return totalPages;
    }
    
    
    
    public double getFoodPrice(int foodId) {
        double price = -1.0;
        String query = "SELECT TOP 1 Price FROM Product WHERE FoodID = ? ORDER BY ProductID DESC";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, foodId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                price = rs.getDouble("Price");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return price;
    }
}
