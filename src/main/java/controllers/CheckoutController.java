package controllers;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.CartItem;
import models.Order;

import java.net.URL;
import java.util.ResourceBundle;

public class CheckoutController implements Initializable {

    @FXML
    private TableView<CartItem> cartTable;

    @FXML
    private TableColumn<CartItem, String> colDescription;

    @FXML
    private TableColumn<CartItem, Integer> colQuantity;

    @FXML
    private TableColumn<CartItem, Double> colLineTotal;

    @FXML
    private Label lblSubtotal;

    @FXML
    private Label lblTax;

    @FXML
    private Label lblTotal;

    private static final double TAX_RATE = 0.07; // 7%

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Bind columns to CartItem fields
        colDescription.setCellValueFactory(
                new PropertyValueFactory<>("description"));
        colQuantity.setCellValueFactory(
                new PropertyValueFactory<>("quantity"));
        colLineTotal.setCellValueFactory(
                new PropertyValueFactory<>("lineTotal"));

        // Hook the table to the shared cart
        cartTable.setItems(Order.getInstance().getItems());

        // Calculate totals now and whenever cart changes
        Order.getInstance().getItems()
                .addListener((ListChangeListener<CartItem>) c -> updateTotals());

        updateTotals();
    }

    private void updateTotals() {
        double subtotal = Order.getInstance().getItems()
                .stream()
                .mapToDouble(CartItem::getLineTotal)
                .sum();

        double tax = subtotal * TAX_RATE;
        double total = subtotal + tax;

        lblSubtotal.setText(String.format("$%.2f", subtotal));
        lblTax.setText(String.format("$%.2f", tax));
        lblTotal.setText(String.format("$%.2f", total));
    }
}
