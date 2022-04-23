package ca.bcit.comp2522.termproject.planetbandit;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Represents as a driver class for the original Java FX classes we couldn't combine with our game.
 *
 * @author Prab and Benny
 * @version 2022
 */
public class GamePlay extends Application {
    private final Stage stage = new Stage();
    private final DisplaySplashScreen welcomeScreen = new DisplaySplashScreen();
    /**
     * Displays an image that can be moved using the arrow keys.
     *
     * @param primaryStage a Stage
     * @throws Exception if login is invalid.
     */
    public void start(final Stage primaryStage) throws Exception {
        welcomeScreen.start(stage);
    }

    /**
     * Returns the stage object.
     * @return a stage object
     */
    public Stage getStage() {
        return stage;
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
