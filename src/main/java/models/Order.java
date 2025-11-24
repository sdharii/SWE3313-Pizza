package models;

import database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class Order {
    private int orderID;
    private int customerID;
    private int paymentID;
    private double total;
    private List<CartItem> items;

    public Order(int orderID, int customerID, int paymentID, double total, List<CartItem> items) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.paymentID = paymentID;
        this.total = total;
        this.items = items;
    }

    // Getters
    public int getOrderID() {return orderID;}
    public int getCustomerID() {return customerID;}
    public int getPaymentID() {return paymentID;}
    public double getTotal() {return total;}
    public List<CartItem> getItems() {return items;}




    // Creating an order
    public  static Order placeOrder( int customerID, int paymentID, double total) {
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
        return new Order(orderID, customerID, paymentID, total, new ArrayList<>(Cart.getItems()));
    }

}
