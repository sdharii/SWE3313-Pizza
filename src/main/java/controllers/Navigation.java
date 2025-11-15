package controllers;

// Allows user to be redirected when clicking the home, menu, and login button. There's no duplicated code!

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigation {
    private static Stage stage;
    // Sets main stage from Main
    public static void setStage(Stage primaryStage) {
        stage = primaryStage;
    }

    // Switches to different page(s)
    public static void goTo(String fxmlFile) throws IOException {
        Parent root = FXMLLoader.load(Navigation.class.getResource("/views/"+fxmlFile));
        stage.setScene(new Scene(root));
        stage.show();

    }
}

