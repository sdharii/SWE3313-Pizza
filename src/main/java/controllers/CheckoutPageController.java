package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import models.*;

import javax.swing.*;
import java.io.IOException;
import java.time.LocalDate;

public class CheckoutPageController {
    @FXML
    private RadioButton cardRB;
    @FXML
    private RadioButton cashRB;
    @FXML
    private ToggleGroup paymentSelection;
    @FXML
    private VBox cardDetailVbox;
    @FXML
    private TextField cardHolderNameField;
    @FXML
    private TextField cardNumberField;
    @FXML
    private DatePicker expDateField;
    @FXML
    private PasswordField cvvField;
    @FXML
    private Label totalLabel;
    @FXML
    private Button placeOrderButton;
    @FXML
    private Button backArrow;

    @FXML
    private void goToCart(ActionEvent event) throws IOException {
        System.out.println("Going back to cart...");
        Navigation.goTo("cartpage.fxml");
    }

    private User currentUser;

    public void initialize() {
        currentUser = User.getInstance();

        // Loading saved card information
        Payment storedCard = PaymentAccess.getCustomerPayment(currentUser.getCustomerID());

        if (storedCard != null) {
            cardHolderNameField.setText(storedCard.getCardholderName());
            cardNumberField.setText(storedCard.getCardNumber());

            // Converting expiration from a string to localdate
            if (storedCard.getExpiration() != null && !storedCard.getExpiration().isEmpty()) {
                expDateField.setValue(LocalDate.parse(storedCard.getExpiration()));
            }
            cvvField.setText(storedCard.getCvv());
        }
        totalLabel.setText(String.format("$%.2f", Cart.getInstance().getTotal()));
    }

    // Disabling & Enabling card detail section based on user's payment type
    @FXML
    private void showCardDetails() {
        if (cardRB.isSelected()) {
            cardDetailVbox.setVisible(true);
            cardDetailVbox.setManaged(true);
        } else {
            cardDetailVbox.setVisible(false);
            cardDetailVbox.setManaged(false);
        }
    }

    // Place Order Button Logic
    @FXML
    void handleOrder() throws IOException {
        // Determining Payment type
        RadioButton selected = (RadioButton) paymentSelection.getSelectedToggle();
        String paymentMethod = selected.getText();
        double total = Cart.getInstance().getTotal();
        int paymentID = -1;

        // Payment validation
        if (paymentMethod.equalsIgnoreCase("Debit/Credit Card")) {
            if (cardHolderNameField.getText().isEmpty() || cardNumberField.getText().isEmpty() || expDateField.getValue() == null || cvvField.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Missing Card Info");
                alert.setContentText("Please fill out all card fields.");
                alert.showAndWait();
                return;
            }

            String expDate = expDateField.getValue().toString();

            // Saving payment to DB
            Payment payment = new Payment(
                    currentUser.getCustomerID(),
                    cardHolderNameField.getText(),
                    cardNumberField.getText(),
                    expDate,
                    cvvField.getText()
            );
            paymentID = PaymentAccess.savePayment(payment);
        } else if (paymentMethod.equalsIgnoreCase("Cash")) {
            Payment cashPayment = new Payment(currentUser.getCustomerID(), "Cash", "", "", "");
            paymentID = PaymentAccess.savePayment(cashPayment);
        }
        // Placing order
        int orderID = Order.placeOrder(currentUser.getCustomerID(), paymentID, total);
        // Clear cart
        Cart.clear();
        System.out.println("Checkout successful. Redirecting to Receipt!");
        // Navigate to receipt page
//        ReceiptPageController.setOrderID(orderID);
//        Navigation.goTo("receiptpage.fxml");
    }
}
