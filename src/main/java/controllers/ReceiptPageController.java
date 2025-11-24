package controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import models.CartItem;
import models.Order;

import javax.swing.*;
import java.io.IOException;

public class ReceiptPageController {
    @FXML private Label orderIDLabel;
    @FXML private Label totalLabel;
    @FXML private VBox itemsVbox;


    private static Order currentOrder;

    public static void setOrder(Order order) {
        currentOrder = order;
    }

    @FXML
    public void initialize() {
        if (currentOrder == null) {
            System.out.println("Not current order available for receipt page.");
            return;
        }

        orderIDLabel.setText(String.valueOf(currentOrder.getOrderID()));
        totalLabel.setText(String.format("$%.2f", currentOrder.getTotal()));

        // Populating the items VBox
        itemsVbox.getChildren().clear();

        for (CartItem item : currentOrder.getItems()) {

            String details = item.getCategory().equalsIgnoreCase("Pizza")
                    ? item.getDetails()
                    : "";

            Text line = new Text(
                    item.getName() + " x" + item.getQuantity() + "\nPrice: $" + String.format("%.2f", item.getPrice()) + (details.isEmpty() ? "" : ("\n" + details))
            );
            line.setStyle("-fx-font-size: 14 px; -fx-padding: 5px;");
            itemsVbox.getChildren().add(line); //Adding items
        }
    }

    @FXML
    private void goToMenu(ActionEvent event) throws IOException {
        System.out.println("Menu button was clicked!");
        Navigation.goTo("menupage.fxml");
    }
}
