package ca.bcit.comp2522.termproject.planetbandit;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * Represents the login page of the game.
 *
 * @author Benny and Prab
 * @version 2022
 */
public class LoginApp extends Application {
    private static final int APP_WIDTH = 1280;
    private static final int APP_HEIGHT = 720;

    /**
     * Displays the main menu.
     * @param stage a Stage object
     */
    @Override
    public void start(final Stage stage) throws Exception {

        System.out.println(getClass());
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(
                "/login_form.fxml")));
        stage.setTitle("User Login");
        stage.setScene(new Scene(root, APP_WIDTH, APP_HEIGHT));
        stage.show();
    }

    /**
     * Launches the JavaFX application.
     *
     * @param args command line arguments
     */
    public static void main(final String[] args) {
        launch(args);
    }

}
