package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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

    private int failedAttempts = 0;
    private long lockoutEndTime = 0; // The amount of time left for a locked-out user

    @FXML
    private void handleLogin(ActionEvent event) throws IOException{
        long now = System.currentTimeMillis();
        // Checking if the user is locked out
        if (now < lockoutEndTime) {
            long secondsLeft = (lockoutEndTime-now) / 1000;

            Alert attemptAlert = new Alert(Alert.AlertType.ERROR);
            attemptAlert.setHeaderText("Too Many Attempts!");
            attemptAlert.setContentText("Please wait "+ secondsLeft + " seconds before trying again.");
            attemptAlert.showAndWait();
            return;
        }
        // If user isn't locket out -> verify login...
        System.out.println("Verifying login...");
        String username = phoneNumber.getText();
        String password = passwordInput.getText();

        UserAccess userAccess = new UserAccess();
        User user = userAccess.login(username, password);

        if (user != null) {
            failedAttempts = 0; // Resets attempt counter

            User.setInstance(user);

            System.out.println("Login successful!");
            Navigation.goTo("menupage.fxml");
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Login Successful");
            successAlert.setHeaderText("Login Successful");
            successAlert.setContentText("Welcome back, " + user.getFirstName()+"!");
            successAlert.showAndWait();
        } else {
            failedAttempts++;

            if (failedAttempts >= 3) {
                lockoutEndTime = System.currentTimeMillis() + (30 * 1000);

                Alert lockoutAlert = new Alert(Alert.AlertType.ERROR);
                lockoutAlert.setHeaderText("Too Many Attempts!");
                lockoutAlert.setContentText("You have been locked out for 30 seconds.");
                lockoutAlert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Login Failed!");
                alert.setContentText("Invalid phone or password! \n Attempt " + failedAttempts + " of 3.");
                alert.showAndWait();
                System.out.println("Invalid phone or password.");
            }
        }
    }
}
