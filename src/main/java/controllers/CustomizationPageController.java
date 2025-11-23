package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import models.MenuItem;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class CustomizationPageController implements Initializable {
    // Navigation Methods
    @FXML
    private void goToMenu(ActionEvent event) throws IOException {
        System.out.print("Menu button was clicked!");
        Navigation.goTo("menupage.fxml");
    }

    // Populating choice boxes for customization
    @FXML private ChoiceBox<String> sizeSelection;
    @FXML private ChoiceBox<String> sauceSelection;
    @FXML private ChoiceBox<String> crustSelection;
    @FXML private TilePane toppingSelection;

    // Vbox Containers
    @FXML private VBox sizeVbox;
    @FXML private VBox crustVbox;
    @FXML private VBox sauceVbox;
    @FXML private VBox toppingVbox;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Size options
        sizeSelection.getItems().addAll("Small","Medium","Large","Extra Large");
        sizeSelection.setValue("Small"); // Default Option
        // Sauce Options
        sauceSelection.getItems().addAll("Marinara","Alfredo");
        sauceSelection.setValue("Marinara");
        // Crust Options
        crustSelection.getItems().addAll("Thin","Regular","Pan");
        crustSelection.setValue("Regular");
    }

    // Disabling/Enabling customization sections based on item type
    private MenuItem currentItem;
    @FXML
    private Text itemName;
    @FXML
    private Text itemDescription;
    @FXML
    private ImageView itemPicture;


    public void setMenuItem(MenuItem item) {
        this.currentItem = item;
        this.basePrice = item.getPrice();
        itemName.setText(item.getName());
        System.out.println("Item type:" + item.getCategory());

        if (item.getCategory().equalsIgnoreCase("Pizza")) {
            itemDescription.setText("Customize your pizza with toppings, sauce, and crust!");
            sizeVbox.setVisible(true);
            sizeVbox.setManaged(true);
            crustVbox.setVisible(true);
            crustVbox.setManaged(true);
            sauceVbox.setVisible(true);
            sauceVbox.setManaged(true);
            toppingVbox.setVisible(true);
            toppingVbox.setManaged(true);
    } else {
            itemDescription.setText("Please adjust the quantity!");
            sizeVbox.setVisible(false);
            sizeVbox.setManaged(false);
            crustVbox.setVisible(false);
            crustVbox.setManaged(false);
            sauceVbox.setVisible(false);
            sauceVbox.setManaged(false);
            toppingVbox.setVisible(false);
            toppingVbox.setManaged(false);
        }

        String imagePath;
        switch (item.getCategory().toLowerCase()) {
            case "pizza":
                imagePath = "/cus_Pizza.png";
                break;
            case "beverage":
                imagePath = "/cus_bev.png";
                break;
            case "dessert":
                imagePath = "/cus_dessert.png";
                break;
            default:
                imagePath = "/cus_Pizza.png";
        }
        itemPicture.setImage(new Image(getClass().getResourceAsStream(imagePath)));
        updatePrice();

    }
    // Allows quantity to be updated
    @FXML private Button decreaseButton;
    @FXML private Button increaseButton;
    @FXML private Button addToCartButton;
    @FXML private Text quantityText;


    private int quantity = 1;
    private double basePrice;
    @FXML
    private void updatePrice() {
        double totalPrice = basePrice;
        // Calculate pizza based on toppings
        if (currentItem.getCategory().equalsIgnoreCase("Pizza")) {
            double toppingsCost = calculateToppingsCost();
            double sizeCost = calculateSizeCost();
            double crustCost = calculateCrustCost();
            totalPrice += toppingsCost + sizeCost + crustCost;
        }

        totalPrice *= quantity;
        // Update Price Button
        addToCartButton.setText(String.format("$%.2f Add to Cart", totalPrice));
    }

    private double calculateToppingsCost() {
        double cost = 0;

        for (Node node: toppingSelection.getChildren()) {
            if (node instanceof CheckBox) { // Checking if it's a checkbox
                CheckBox cb = (CheckBox) node;
                if (cb.isSelected()) {
                    cost += 0.45; //Additional topping is $0.45
                }
            }
        }
        return cost;
    }

    private double calculateSizeCost() {
        String size = sizeSelection.getValue();
        switch (size) {
            case "Small":
                return 0.00;
            case "Medium":
                return 1.00;
            case "Large":
                return 2.00;
            case "Extra Large":
                return 3.00;
            default:
                return 0.00; // Default is small
        }
    }

    private double calculateCrustCost () {
        String crust = crustSelection.getValue();
        switch (crust) {
            case "Regular":
                return 0.00;
            case "Thin":
                return 0.50;
            case "Pan":
                return 1.00;
            default:
                return 0.00; // Default is regular
        }
    }

    private void changeQuantity(int num) {
        quantity += num;

        if (quantity < 1) {
            quantity = 1; // Sets minimum to 1
        }
        quantityText.setText(String.valueOf(quantity));
        updatePrice();
    }
    @FXML
    private void increaseQuantity(ActionEvent event) {
        changeQuantity(1);
    }
    @FXML
    private void decreaseQuantity(ActionEvent event) {
        changeQuantity(-1);
    }

}
