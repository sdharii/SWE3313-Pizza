package models;

import database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PaymentAccess {
    // Loading user's card info if it's in DB

    public static Payment getCustomerPayment(int customerID) {
        String SQL = "SELECT * FROM Payment WHERE CustomerID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(SQL)) {

            statement.setInt(1, customerID);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                return new Payment(
                        result.getInt("CustomerID"),
                        result.getString("CardholderName"),
                        result.getString("CardNumber"),
                        result.getString("Expiration"),
                        result.getString("CVV")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // No card found in DB
    }

    // Updating users payment information
    public static int savePayment(Payment payment) {
        int paymentID = -1; // default if something goes wrong
        String sql = "INSERT INTO Payment (CustomerID, CardholderName, CardNumber, Expiration, CVV) VALUES (?,?,?,?,?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, payment.getCustomerID());
            stmt.setString(2, payment.getCardholderName());
            stmt.setString(3, payment.getCardNumber());
            stmt.setString(4, payment.getExpiration());
            stmt.setString(5, payment.getCvv());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                paymentID = rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return paymentID;
    }
}
