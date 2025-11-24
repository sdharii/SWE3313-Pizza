package models;

import database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;



public class Order {
    // Creating an order
    public  static int placeOrder( int customerID, int paymentID, double total) {
        int orderID = -1;
        String SQL = "INSERT INTO \"Order\" (CustomerID, PaymentID, Total) VALUES (?,?,?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(SQL, PreparedStatement.RETURN_GENERATED_KEYS)) {

            statement.setInt(1, customerID);
            statement.setInt(2, paymentID);
            statement.setDouble(3, total);

            statement.executeUpdate();

            // Getting orderID
            ResultSet result = statement.getGeneratedKeys();
            if (result.next()) {
                orderID = result.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderID;
    }

}
