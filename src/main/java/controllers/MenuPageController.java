package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.TilePane;
import java.util.List;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuPageController implements Initializable {
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
    @FXML
    private void goToCustomization(ActionEvent event) throws IOException {
        System.out.println("Heading to customization page...");
        Navigation.goTo("customizationpage.fxml");
    }

    // Pizza Buttons -> Allows menu to switch depending on pizza, bev., desserts
    @FXML private TilePane menuTilePane;
    @FXML private Button createYourOwnButton;
    @FXML private Button pepperoniDeluxeButton;
    @FXML private Button ultimateSupremeButton;
    @FXML private Button cheesePizzaButton;

    private List<Node> pizzaButtons;

    // Beverage Buttons
    @FXML private Button cokeButton;
    @FXML private Button spriteButton;
    @FXML private Button fantaButton;

    private List<Node> beverageButtons;
    // Dessert Buttons
    @FXML private Button choChunkCookieButton;
    @FXML private Button cinnaRollButton;
    @FXML private Button molLavaCakeButton;

    private List<Node> dessertButtons;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Creating a list for pizza FXML Buttons
        pizzaButtons = List.of(createYourOwnButton,pepperoniDeluxeButton,ultimateSupremeButton,cheesePizzaButton);
        beverageButtons = List.of(cokeButton,spriteButton,fantaButton);
        dessertButtons = List.of(choChunkCookieButton, cinnaRollButton,molLavaCakeButton);

        // Hide other buttons initially
        beverageButtons.forEach(b -> {
            b.setVisible(false);
            b.setManaged(false);
        });
        dessertButtons.forEach(d ->{
            d.setVisible(false);
            d.setManaged(false);
        });
    }
    // Menu switching
    @FXML
    private void showPizza() {
        menuTilePane.getChildren().setAll(pizzaButtons);
    }
    @FXML
    private void showBeverages() {
        beverageButtons.forEach(b -> {
            b.setVisible(true);
            b.setManaged(true);
        });
        menuTilePane.getChildren().setAll(beverageButtons);
    }
    @FXML
    private void showDesserts() {
        dessertButtons.forEach(d -> {
            d.setVisible(true);
            d.setManaged(true);
        });
        menuTilePane.getChildren().setAll(dessertButtons);
    }
}
