//package controllers;
//
//import database.DatabaseConnection;
//import javafx.fxml.FXML;
//import javafx.scene.control.Label;
//import javafx.scene.layout.VBox;
//import models.Cart;
//import models.Pizza;
//
//import java.io.File;
//import java.io.PrintWriter;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.Statement;
//
//
//public class CheckoutPageController
//{
//
//    @FXML
//    private VBox cartItemsBox;
//
//    @FXML
//    private Label totalLabel;
//
//    public void initialize() {
//        loadCart();
//    }
//
//    private void loadCart() {
//        cartItemsBox.getChildren().clear();
//
//        for (Pizza pizza : Cart.getInstance().getItems()) {
//            Label item = new Label(pizza.getName() + " - $" + pizza.getPrice());
//            cartItemsBox.getChildren().add(item);
//        }
//
//        totalLabel.setText("Total: $" + Cart.getInstance().getTotal());
//    }
//    @FXML
//    private void handleCheckout() {
//        Cart cart = Cart.getInstance();
//
//        if (cart.getItems().isEmpty()) {
//            System.out.println("Cart is empty — cannot checkout.");
//            return;
//        }
//
//        // Save order to database
//        int orderId = saveOrderToDatabase(cart);
//
//        // Print receipt
//        generateReceipt(orderId, cart);
//
//        // Clear cart
//        cart.clear();
//
//        System.out.println("Checkout complete!");
//    }
//    private int saveOrderToDatabase(Cart cart) {
//        String insertOrder = "INSERT INTO Orders(total_price) VALUES(?)";
//        String insertItem = "INSERT INTO OrderItems(order_id, pizza_name, price) VALUES(?, ?, ?)";
//
//        int orderId = -1;
//
//        try (Connection conn = DatabaseConnection.getConnection()) {
//
//            // Insert the order
//            PreparedStatement stmt = conn.prepareStatement(insertOrder, Statement.RETURN_GENERATED_KEYS);
//            stmt.setDouble(1, cart.getTotal());
//            stmt.executeUpdate();
//
//            ResultSet rs = stmt.getGeneratedKeys();
//            if (rs.next()) {
//                orderId = rs.getInt(1);
//            }
//
//            // Insert each pizza in the order
//            PreparedStatement itemStmt = conn.prepareStatement(insertItem);
//
//            for (Pizza p : cart.getItems()) {
//                itemStmt.setInt(1, orderId);
//                itemStmt.setString(2, p.getName());
//                itemStmt.setDouble(3, p.getPrice());
//                itemStmt.addBatch();
//            }
//
//            itemStmt.executeBatch();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return orderId;
//    }
//    private void generateReceipt(int orderId, Cart cart) {
//        try {
//            File receipt = new File("receipt_" + orderId + ".txt");
//            PrintWriter writer = new PrintWriter(receipt);
//
//            writer.println("===== SWE3313 Pizza Receipt =====");
//            writer.println("Order ID: " + orderId);
//            writer.println("---------------------------------");
//
//            for (Pizza p : cart.getItems()) {
//                writer.println(p.getName() + " - $" + p.getPrice());
//            }
//
//            writer.println("---------------------------------");
//            writer.println("TOTAL: $" + cart.getTotal());
//            writer.println("Thank you for ordering!");
//
//            writer.close();
//            System.out.println("Receipt generated: " + receipt.getAbsolutePath());
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//
//}
