package ca.bcit.comp2522.termproject.planetbandit;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;


public class LoginApp extends Application {
    private static final int APP_WIDTH = 1280;
    private static final int APP_HEIGHT = 720;

    @Override
    public void start(Stage stage) throws Exception {

        System.out.println(getClass());
    	Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/login_form.fxml")));
        stage.setTitle("User Login");
        stage.setScene(new Scene(root, APP_WIDTH, APP_HEIGHT));
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }

}
