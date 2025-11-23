package controllers;

// Allows user to be redirected when clicking the home, menu, and login button. There's no duplicated code!

import javafx.fxml.FXML;
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

    public static void goTo(String fxmlFile) throws IOException {
        goTo(fxmlFile,null);
    }



    // Switches to different page(s)
    public static void goTo(String fxmlFile, String itemType) throws IOException {
        FXMLLoader loader = new FXMLLoader(Navigation.class.getResource("/views/"+fxmlFile));
        Parent root = loader.load();

        // Grabbing controller
        Object controller = loader.getController();

        // If controller has a setItemType method (customizationPage), pass value
        try {
            controller.getClass()
                    .getMethod("setItemType",String.class)
                    .invoke(controller,itemType);
        } catch (NoSuchMethodException e) {
            System.out.println("Controller doesn't have a setItemType method!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        stage.setScene(new Scene(root));
        stage.show();
    }
}

