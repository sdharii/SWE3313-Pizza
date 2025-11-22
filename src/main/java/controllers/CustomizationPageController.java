package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomizationPageController implements Initializable {
    // Navigation Methods
    @FXML
    private void goToHome(ActionEvent event) throws IOException {
        System.out.print("Home button was clicked!");
        Navigation.goTo("homepage.fxml");
    }

    @FXML
    private void goToMenu(ActionEvent event) throws IOException {
        System.out.print("Menu button was clicked!");
        Navigation.goTo("menupage.fxml");
    }

    @FXML
    private void goToLogin(ActionEvent event) throws IOException {
        System.out.print("Login button was clicked!");
        Navigation.goTo("loginpage.fxml");
    }
    // Populating choice boxes for customization
    @FXML private ChoiceBox<String> sizeSelection;
    @FXML private ChoiceBox<String> sauceSelection;
    @FXML private ChoiceBox<String> crustSelection;

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
}
