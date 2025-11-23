package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import models.MenuItem;

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
        crustSelection.getItems().addAll("Thin, Regular, Pan");
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

    }
    // Allows quantity to be updated
    @FXML private Button decreaseButton;
    @FXML private Button increaseButton;
    @FXML private Button addToCartButton;
    @FXML private Text quantityText;

    private int quantity = 1;

}
