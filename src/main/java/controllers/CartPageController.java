package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.io.IOException;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import models.Cart;
import models.CartItem;
import models.User;
import models.UserAccess;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class CartPageController implements Initializable {

    @FXML private ListView<String> cartListView;
    @FXML private Label totalLabel;
    @FXML private RadioButton pickupRadio;
    @FXML private RadioButton deliveryRadio;
    @FXML private ToggleGroup orderTypeGroup;
    @FXML private VBox addressBox;
    @FXML private TextField addressField;
    @FXML private Button checkoutButton;

    @Override
    public void initialize(URL location, ResourceBundle rb) {
        // Hiding address box initially
        addressBox.setVisible(false);
        addressBox.setManaged(false);

        loadCartItems();
        setupOrderTypeToggle();
    }

    private void loadCartItems() {
        cartListView.getItems().clear();

        double total = 0;
        for (CartItem item : Cart.getInstance().getItems()) {
            StringBuilder display = new StringBuilder();

            display.append(item.getName())
                    .append(" (x")
                    .append(item.getQuantity())
                    .append(")");
            if (item.getCategory().equalsIgnoreCase("Pizza")) {
                display.append("\n").append(item.getDetails());
            }
            display.append("\n$").append(String.format("%.2f",item.getPrice()));

            cartListView.getItems().add(display.toString());
            total += item.getPrice();

        }
        totalLabel.setText(String.format("$%.2f", total));
    }
    // Determines if addressbox should be shown or not (only for delivery option)
    private void setupOrderTypeToggle() {
        orderTypeGroup.selectedToggleProperty().addListener((obs, oldToggle, newToggle)-> {
            if (newToggle == deliveryRadio) {
                showAddressBox();
            } else {
                hideAddressBox();
            }
        });
    }

    private void showAddressBox() {
        addressBox.setVisible(true);
        addressBox.setManaged(true);
        // If user already have address in DB, load it
        User currentUser = User.getInstance();
        if (currentUser.getAddress() != null && !currentUser.getAddress().isEmpty()) {
            addressField.setText(currentUser.getAddress());
        }
    }

    private void hideAddressBox() {
        addressBox.setVisible(false);
        addressBox.setManaged(false);
    }

    @FXML
    private void goToMenu(ActionEvent event) throws IOException {
        System.out.print("Menu button was clicked!");
        Navigation.goTo("menupage.fxml");
    }
    // Proceeding to checkout + saving address
    @FXML
    private void handleCheckout(ActionEvent event) throws IOException {
        User currentUser = User.getInstance();

        // Ensuring its only saving if delivery button is chosen
        if (deliveryRadio.isSelected()) {
            String newAddress = addressField.getText().trim();

            if (newAddress.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Missing Address");
                alert.setContentText("Please enter your delivery address.");
                return;
            }
            // Updating user object
            currentUser.setAddress(newAddress);
            // Updating user in DB
            UserAccess.updateAddress(currentUser.getCustomerID(), newAddress);

            System.out.println("Address saved before checkout: "+newAddress);
        }
        // Sending user to check out
        Navigation.goTo("checkoutpage.fxml");
    }
}
