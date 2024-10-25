/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Model.Table;

/**
 *
 * @author Admin
 */
public class TableDAO extends DBContext {

    // Lấy tất cả các bảng
    public ArrayList<Table> getAllTables() {
        ArrayList<Table> tables = new ArrayList<>();
        try {
            String sql = "SELECT * FROM dbo.[Table] ORDER BY TableID ASC";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("TableID");
                String name = resultSet.getString("TableName");
                String status = resultSet.getString("Status");
                tables.add(new Table(id, name, status));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tables;
    }

    // Lấy bảng theo ID
    public Table getTableById(int id) {
        Table table = null;
        try {
            String sql = "SELECT * FROM dbo.[Table] WHERE TableID = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("TableName");
                String status = resultSet.getString("Status");
                table = new Table(id, name, status);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return table;
    }

    // Thêm bảng mới
    public void insertTable(Table table) {
        try {
            String sql = "INSERT INTO dbo.[Table] (TableName, Status) VALUES (?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, table.getTableName());
            statement.setString(2, table.getStatus());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Cập nhật thông tin bảng
    public void updateTable(Table table) {
        try {
            String sql = "UPDATE dbo.[Table] SET TableName = ?, Status = ? WHERE TableID = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, table.getTableName());
            statement.setString(2, table.getStatus());
            statement.setInt(3, table.getTableID());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Xóa bảng và sắp xếp lại ID
    public void deleteTable(int id) {
        try {
            // Xóa bảng với ID được truyền vào
            String sql = "DELETE FROM dbo.[Table] WHERE TableID = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();

            // Sau khi xóa, sắp xếp lại ID cho tất cả các bảng còn lại
            rearrangeTableIDs();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Phương thức sắp xếp lại các ID sau khi xóa
    private void rearrangeTableIDs() {
        try {
            // Lấy tất cả các bảng, sắp xếp theo ID tăng dần
            String sql = "SELECT * FROM dbo.[Table] ORDER BY TableID ASC";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            // Đặt lại ID bắt đầu từ 1
            int newId = 1;
            while (resultSet.next()) {
                int currentId = resultSet.getInt("TableID");

                // Cập nhật ID của bảng hiện tại nếu nó không khớp với newId
                if (currentId != newId) {
                    String updateSql = "UPDATE dbo.[Table] SET TableID = ? WHERE TableID = ?";
                    PreparedStatement updateStatement = connection.prepareStatement(updateSql);
                    updateStatement.setInt(1, newId); // gán ID mới
                    updateStatement.setInt(2, currentId); // ID hiện tại
                    updateStatement.executeUpdate();
                }
                newId++;
            }

            // Sau khi cập nhật tất cả ID, đặt lại chuỗi tự động tăng ID nếu cần
            resetIdentity(newId);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Đặt lại chuỗi tự động tăng (Identity) cho bảng sau khi sắp xếp lại
    private void resetIdentity(int nextId) {
        try {
            String resetIdentitySql = "DBCC CHECKIDENT ('[Table]', RESEED, ?)";
            PreparedStatement resetStatement = connection.prepareStatement(resetIdentitySql);
            resetStatement.setInt(1, nextId - 1); // Đặt giá trị cho ID tiếp theo
            resetStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<Table> getTableList() {
        List<Table> tableList = new ArrayList<>();
        String sql = " select * from [table]";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Table p = new Table();

                tableList.add(new Table(
                        rs.getInt(1),  rs.getString(2), rs.getString(3)                )
                );
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return tableList;
    }
        public static void main(String[] args) {
        TableDAO dao = new TableDAO();
        List<Table> pr = dao.getTableList(); 

        for (int i = 0; i < pr.size(); i++) {
            System.out.println(pr.get(i));
        }
    }
}
