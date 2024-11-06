/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Staff;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.EmailUtility;
import utils.PasswordUtil;
import utils.RandomGenerate;

/**
 *
 * @author ADMIN
 */
public class StaffDAO extends DBContext {

    public List<Staff> findStaffByPage(int pageNumber, int pageSize, String search, String orderByColumn, boolean isAsc) {
        List<Staff> staffList = new ArrayList<>();
        StringBuilder sql = new StringBuilder("""
        SELECT StaffID, StaffName, PhoneNumber, Email, Salary, NewAccount, AccountID
        FROM Staff
        """);

        // Add search conditions if search is not null or empty
        if (search != null && !search.isEmpty()) {
            sql.append("""
            WHERE StaffName LIKE ? OR PhoneNumber LIKE ? OR Email LIKE ?
            """);
        }

        sql.append(" ORDER BY ");
        if (orderByColumn != null && !orderByColumn.isEmpty()) {
            sql.append(orderByColumn); // Add specified column for sorting
        } else {
            sql.append("Staff.StaffID"); // Default sorting column
        }

        // Add ordering if column is specified
        if (orderByColumn != null && !orderByColumn.isEmpty()) {
            sql.append(isAsc ? " ASC" : " DESC");
        }

        // Add pagination
        sql.append(" OFFSET ? ROWS FETCH NEXT ? ROWS ONLY");

        try (PreparedStatement statement = connection.prepareStatement(sql.toString())) {
            int paramIndex = 1;

            // Set search parameters
            if (search != null && !search.isEmpty()) {
                String searchPattern = "%" + search + "%";
                statement.setString(paramIndex++, searchPattern); // StaffName
                statement.setString(paramIndex++, searchPattern); // PhoneNumber
                statement.setString(paramIndex++, searchPattern); // Email
            }

            // Set pagination parameters
            int offset = (pageNumber - 1) * pageSize;
            statement.setInt(paramIndex++, offset);
            statement.setInt(paramIndex, pageSize);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Staff staff = new Staff();
                staff.setStaffID(rs.getInt("StaffID"));
                staff.setStaffName(rs.getString("StaffName"));
                staff.setPhoneNumber(rs.getString("PhoneNumber"));
                staff.setEmail(rs.getString("Email"));
                staff.setSalary(rs.getInt("Salary"));
                staff.setNewAccount(rs.getBoolean("NewAccount"));
                staff.setAccountID(rs.getInt("AccountID"));
                staffList.add(staff);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return staffList;
    }

    public Staff findStaffByAccountID(int accountID) {
        Staff staff = null; // To hold the result

        String sql = """
        SELECT StaffID, StaffName, PhoneNumber, Email, Salary, NewAccount, AccountID
        FROM Staff
        WHERE AccountID = ?
    """;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, accountID); // Set AccountID parameter

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    staff = new Staff();
                    staff.setStaffID(rs.getInt("StaffID"));
                    staff.setStaffName(rs.getString("StaffName"));
                    staff.setPhoneNumber(rs.getString("PhoneNumber"));
                    staff.setEmail(rs.getString("Email"));
                    staff.setSalary(rs.getInt("Salary"));
                    staff.setNewAccount(rs.getBoolean("NewAccount"));
                    staff.setAccountID(rs.getInt("AccountID"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return staff; // Return the Staff object (null if not found)
    }

    public Staff findStaffByStaffID(int staffID) {
        Staff staff = null; // To hold the result

        String sql = """
        SELECT StaffID, StaffName, PhoneNumber, Email, Salary, NewAccount, AccountID
        FROM Staff
        WHERE StaffID = ?
    """;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, staffID); // Set AccountID parameter

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    staff = new Staff();
                    staff.setStaffID(rs.getInt("StaffID"));
                    staff.setStaffName(rs.getString("StaffName"));
                    staff.setPhoneNumber(rs.getString("PhoneNumber"));
                    staff.setEmail(rs.getString("Email"));
                    staff.setSalary(rs.getInt("Salary"));
                    staff.setNewAccount(rs.getBoolean("NewAccount"));
                    staff.setAccountID(rs.getInt("AccountID"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return staff; // Return the Staff object (null if not found)
    }

    public static void main(String[] args) {
        StaffDAO sd = new StaffDAO();
        System.out.println(sd.findStaffByPage(1, 10, null, null, true));
    }

    public int getTotalPages(int pageSize, String search) {
        int totalPages = 0;

        StringBuilder sql = new StringBuilder("""
            SELECT COUNT(*) AS TotalStaff
            FROM Staff
            """);

        // Add search conditions if search is not null or empty
        if (search != null && !search.isEmpty()) {
            sql.append("""
                WHERE StaffName LIKE ? OR PhoneNumber LIKE ? OR Email LIKE ? OR Salary LIKE ?
                """);
        }

        try (PreparedStatement statement = connection.prepareStatement(sql.toString())) {
            int paramIndex = 1;

            if (search != null && !search.isEmpty()) {
                String searchPattern = "%" + search + "%";
                statement.setString(paramIndex++, searchPattern);
                statement.setString(paramIndex++, searchPattern);
                statement.setString(paramIndex++, searchPattern);
                statement.setString(paramIndex, searchPattern);
            }

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                int totalStaff = rs.getInt("TotalStaff");
                totalPages = (int) Math.ceil((double) totalStaff / pageSize);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return totalPages;
    }

    public String addStaff(String StaffName, String PhoneNumber, String Email, int Salary) {
        // Initialize the utility classes
        RandomGenerate rd = new RandomGenerate();
        PasswordUtil pw = new PasswordUtil();
        EmailUtility eu = new EmailUtility();

        // Validation for email format
        if (!Email.matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) {
            return "Định dạng email không hợp lệ.";
        }

        // Validation for phone number format (e.g., 10-11 digits)
        if (!PhoneNumber.matches("^\\d{10,11}$")) {
            return "Số điện thoại không hợp lệ. Số điện thoại chỉ nên chứa chữ số và có độ dài từ 10 đến 11 ký tự.";
        }

        // Validation for salary (e.g., ensure salary is greater than 0)
        if (Salary <= 0) {
            return "Lương phải lớn hơn 0.";
        }

        String emailCheckQuery = "SELECT COUNT(*) FROM Staff WHERE Email = ? AND AccountID IS NOT NULL";
        try (PreparedStatement emailCheckStatement = connection.prepareStatement(emailCheckQuery)) {
            emailCheckStatement.setString(1, Email);
            try (ResultSet resultSet = emailCheckStatement.executeQuery()) {
                if (resultSet.next() && resultSet.getInt(1) > 0) {
                    return "Email đã tồn tại. Vui lòng sử dụng email khác.";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Đã xảy ra lỗi khi kiểm tra email: " + e.getMessage();
        }

        String query = "INSERT INTO Staff (StaffName, PhoneNumber, Email, Salary, NewAccount, AccountID) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            String[] EmailCut = Email.split("@");
            String username = EmailCut[0];
            String password = rd.generateRandomString(6);

            String accountQuery = "INSERT INTO Account (Username, Password, RoleID) VALUES (?, ?, ?)";
            PreparedStatement accountStatement = connection.prepareStatement(accountQuery, PreparedStatement.RETURN_GENERATED_KEYS);
            accountStatement.setString(1, username);
            accountStatement.setString(2, pw.hashPassword(password));
            accountStatement.setInt(3, 2);
            int accountID;
            int affectedRowsA = accountStatement.executeUpdate();

            try (ResultSet generatedKeys = accountStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    accountID = generatedKeys.getInt(1); // Assuming the first column is the primary key

                    // Set parameters for the SQL query
                    preparedStatement.setString(1, StaffName);
                    preparedStatement.setString(2, PhoneNumber);
                    preparedStatement.setString(3, Email);
                    preparedStatement.setInt(4, Salary);
                    preparedStatement.setBoolean(5, true);
                    preparedStatement.setInt(6, accountID);

                    // Email content
                    String note = """
                    <div>Chào mừng bạn đến với đội ngũ của nhà hàng 5 Anh Lực!</div>
                    <div>Chúng tôi rất vui mừng khi bạn gia nhập và mong muốn được làm việc cùng bạn.</div>
                    <div>Dưới đây là thông tin tài khoản nhân viên của bạn, mà bạn sẽ sử dụng để truy cập vào cổng thông tin nhân viên và các hệ thống nội bộ khác.</div>""";
                    note += "<div> Tên tài khoản :" + username + "</div>";
                    note += "<div> Mật khẩu :" + password + "</div>";
                    note += "<div> Vui lòng sử dụng mật khẩu tạm thời này để đăng nhập lần đầu và thay đổi thành mật khẩu an toàn của riêng bạn.</div>";

                    String subject = "Chào mừng đến với nhà hàng 5 Anh Lực – Thông tin tài khoản nhân viên của bạn";
                    eu.sendEmail(Email, subject, note);

                    // Execute the query
                    int rowsAffectedS = preparedStatement.executeUpdate();

                    if (affectedRowsA == 0 || rowsAffectedS == 0) {
                        return "Thêm nhân viên thất bại.";
                    } else {
                        return "Success";
                    }
                } else {
                    return "Tạo tài khoản cho nhân viên thất bại.";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Đã xảy ra lỗi: " + e.getMessage();
        }
    }

    public String updateStaffSalary(int StaffID, int newSalary) {
        // Validation for salary (ensure salary is greater than 0)
        if (newSalary <= 0) {
            return "Lương phải lớn hơn 0.";
        }

        String query = "UPDATE Staff SET Salary = ? WHERE StaffID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            // Set parameters for the SQL query
            preparedStatement.setInt(1, newSalary);
            preparedStatement.setInt(2, StaffID);

            // Execute the update query
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected == 0) {
                return "Cập nhật lương thất bại. StaffID có thể không tồn tại.";
            } else {
                return "Success";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Đã xảy ra lỗi: " + e.getMessage();
        }
    }

    public boolean FireStaff(int accountID) {
        String updateStaffSQL = "UPDATE Staff SET AccountID = NULL WHERE AccountID = ?";
        String deleteAccountSQL = "DELETE FROM Account WHERE AccountID = ?";

        try {
            // Start a transaction
            connection.setAutoCommit(false);

            // Step 1: Set AccountID to NULL in the Staff table
            try (PreparedStatement updateStaffStatement = connection.prepareStatement(updateStaffSQL)) {
                updateStaffStatement.setInt(1, accountID);
                updateStaffStatement.executeUpdate();
            }

            // Step 2: Delete the Account
            try (PreparedStatement deleteAccountStatement = connection.prepareStatement(deleteAccountSQL)) {
                deleteAccountStatement.setInt(1, accountID);
                int rowsAffected = deleteAccountStatement.executeUpdate();

                // Commit the transaction
                connection.commit();

                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            // Rollback the transaction in case of an error
            try {
                connection.rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true); // Reset to default
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }
}
