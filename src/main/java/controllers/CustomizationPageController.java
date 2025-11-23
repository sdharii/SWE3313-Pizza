package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

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
    private String itemType;

    public void setItemType(String itemType) {
        this.itemType = itemType;
        System.out.println("Item type:" + itemType);

        updateUI();
    }

    private void updateUI() {
        if (itemType == null)
            return;

        if (itemType.equals("pizza")) {
            sizeVbox.setVisible(true);
            sizeVbox.setManaged(true);
            crustVbox.setVisible(true);
            crustVbox.setManaged(true);
            sauceVbox.setVisible(true);
            sauceVbox.setManaged(true);
            toppingVbox.setVisible(true);
            toppingVbox.setManaged(true);
        }
        if (itemType.equals("beverage") || itemType.equals("dessert")) {
            sizeVbox.setVisible(false);
            sizeVbox.setManaged(false);
            crustVbox.setVisible(false);
            crustVbox.setManaged(false);
            sauceVbox.setVisible(false);
            sauceVbox.setManaged(false);
            toppingVbox.setVisible(false);
            toppingVbox.setManaged(false);
        }
    }
}
