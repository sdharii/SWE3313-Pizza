package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import models.User;
import models.UserAccess;
import javafx.scene.control.TextField;
import java.io.IOException;

public class LoginPageController {
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
    private void goToSignup(ActionEvent event) throws IOException {
        System.out.println("Create an account button was clicked!");
        Navigation.goTo("signuppage.fxml");
    }

    // Handling login
    @FXML
    private TextField phoneNumber;
    @FXML
    private PasswordField passwordInput;

    @FXML
    private void handleLogin(ActionEvent event) throws IOException{
        System.out.println("Verifying login...");
        String username = phoneNumber.getText();
        String password = passwordInput.getText();

        UserAccess userAccess = new UserAccess();
        User user = userAccess.login(username, password);

        if (user != null) {
            System.out.println("Login successful!");
            Navigation.goTo("menupage.fxml");
        } else {
            System.out.println("Invalid phone or password.");
        }

    }
}
