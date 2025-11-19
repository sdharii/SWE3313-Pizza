package models;

import database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

// Verifies user during login/signup phase
public class UserAccess {
    public static User login(String phone, String password) {
        String sqlInput = "SELECT * FROM Customer WHERE PhoneNumber = ? AND Password = ?";
        // Attempting to connect to the DB
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(sqlInput)) {
            // Fills in the ? placeholders for the SQL Query Statement
            statement.setString(1, phone);
            statement.setString(2, password);

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

    public static boolean signup(String fName, String lName, String phone, String password) {
        // Checking if user already exists
        String checkSQL = "SELECT CustomerID FROM Customer WHERE phoneNumber = ?";
        // If user doesn't exist, insert new row
        String insertSQL = "INSERT Into Customer (firstName, lastName, phoneNumber, password) VALUES (?,?,?,?)";

        try (Connection conn = DatabaseConnection.getConnection()) {
            // Does phone already exist?
            try (PreparedStatement checkStatement = conn.prepareStatement(checkSQL)) {
                checkStatement.setString(1, phone);
                ResultSet result = checkStatement.executeQuery();

                if (result.next()) {
                    // Phone found in DB -> fail signup
                    return false;
                }
            }
            // If there's no pre-existing user, insert new data
            try (PreparedStatement insertStatement = conn.prepareStatement(insertSQL)) {
                insertStatement.setString(1, fName);
                insertStatement.setString(2, lName);
                insertStatement.setString(3, phone);
                insertStatement.setString(4, password);

                insertStatement.executeUpdate();
            }
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
