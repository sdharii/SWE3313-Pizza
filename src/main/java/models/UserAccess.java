package models;

import database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

// Verifies user during login/signup phase
public class UserAccess {
    public User login(String phone, String password) {
        String sqlInput = "SELECT * FROM Customer WHERE PhoneNumber = ? AND Password = ?";
        // Attempting to connect to the DB
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(sqlInput)) {
                // Fills in the ? placeholders for the SQL Query Statement
                 statement.setString(1,phone);
                 statement.setString(2,password);

                 ResultSet result = statement.executeQuery();

                 if (result.next()) {
                     return new User(
                         result.getInt("CustomerID"),
                         result.getString("FirstName"),
                         result.getString("LastName"),
                         result.getString("PhoneNumber"),
                         result.getString("Password"),
                         result.getString("Address")
                     );
                 }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // If login fails, return null
        return null;
    }
    public User signup(String fullName, String phone, String password) {
        return null;
    }
}
