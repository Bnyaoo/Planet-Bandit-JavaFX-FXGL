package ca.bcit.comp2522.termproject.planetbandit;

import ca.bcit.comp2522.termproject.planetbandit.Entities.Player;
import ca.bcit.comp2522.termproject.planetbandit.Entities.Spaceship;
import javafx.application.Application;
import javafx.stage.Stage;

public class GamePlay extends Application {
    private final Stage stage = new Stage();
    private final Space space = new Space();
    private final String spaceBackground = "space.jpg";

    private final Player player = new Player(3, 1, 100,
            270, 450, 0, "Bob");

    private final Riddles riddles = new Riddles(player);
    private final Spaceship spaceship = new Spaceship();

    /**
     * Displays an image that can be moved using the arrow keys.
     *
     * @param primaryStage a Stage
     */
    public void start(Stage primaryStage) {
        space.start(stage, spaceship);
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
