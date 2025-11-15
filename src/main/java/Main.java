import controllers.Navigation;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    // Loading the home page
    @Override
    public void start(Stage primaryStage) throws Exception {

        Navigation.setStage(primaryStage); // Hands Navigation class the homepage

        Parent root = FXMLLoader.load(getClass().getResource("/views/homepage.fxml"));

        Scene scene = new Scene(root);
        primaryStage.setTitle("Mom's and Pop's Pizzeria App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
