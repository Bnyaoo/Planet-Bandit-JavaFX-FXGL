package ca.bcit.comp2522.termproject.planetbandit;

import javafx.application.Application;
import javafx.stage.Stage;

public class GamePlay extends Application {
    private final Stage stage = new Stage();
    private final Space space = new Space();

    /**
     * Displays an image that can be moved using the arrow keys.
     *
     * @param primaryStage a Stage
     */
    public void start(Stage primaryStage) {
        space.start(stage);
    }

    /**
     * Launches the JavaFX application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
