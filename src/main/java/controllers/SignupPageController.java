package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import models.UserAccess;

import java.io.IOException;

public class SignupPageController {
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

    // Handling sign up
    @FXML
    private TextField phoneNumber;

    @FXML
    private PasswordField passwordInput;

    @FXML
    private TextField fullName;
    @FXML
    private void handleSignup(ActionEvent event) throws IOException {
        System.out.println("Verifying sign up...");

        String username = phoneNumber.getText();
        String password = passwordInput.getText();
        String name = fullName.getText().trim();

        // If the user has invalid fields
        if (name.isEmpty() || username.isEmpty() || password.isEmpty()) {
            Alert showError = new Alert(Alert.AlertType.ERROR);
            showError.setHeaderText("Signup Error!");
            showError.setContentText("All fields must be filled out.");
        }
        // Valid password
        boolean hasUpper = password.chars().anyMatch(Character::isUpperCase);
        boolean hasSpecial = password.matches(".*\\W.*");
        boolean longEnough = password.length() >= 8;

        boolean validPassword = hasUpper && hasSpecial && longEnough;

        if (!validPassword) {
            Alert passwordError = new Alert(Alert.AlertType.ERROR);
            passwordError.setHeaderText("Signup Error!");
            passwordError.setContentText("Password must be 8+ characters, include one uppercase letter and one special character.");
            return;
        }
        // If all criteria is met -> split full name into FName & Lname

        String[] nameParts = name.split(" ",2);
        String firstName = nameParts[0];
        String lastName = nameParts[1];

        boolean created = UserAccess.signup(firstName,lastName,username,password);

        // If user already exists
        if (!created) {
            Alert signupError = new Alert(Alert.AlertType.ERROR);
            signupError.setHeaderText("Signup Error!");
            signupError.setContentText("An account with this phone number already exists.");
            return;
        } else {
            System.out.println("Signup successful!");
            Navigation.goTo("loginpage.fxml");
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Signup Successful");
            successAlert.setHeaderText("Signup Successful");
            successAlert.setContentText("Your account has been created! Please log in now.");
            successAlert.showAndWait();
        }

    }
}
