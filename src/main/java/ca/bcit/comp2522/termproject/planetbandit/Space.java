package ca.bcit.comp2522.termproject.planetbandit;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Represents a spaceship object.
 *
 * @author Prab and Benny
 * @version 2022
 */
public class Space {
    // Distance in pixels that the alien moves when a key is pressed
    public static final int MOVE_SPEED = 15;

    // Contains the image of the spaceship
    private ImageView spaceshipImageView;

    // Contains the image of the spaceship
    private ImageView spaceImageView;

    /**
     * Displays an image that can be moved using the arrow keys.
     *
     * @param primaryStage a Stage
     */
    public void start(Stage primaryStage) {
        final int appWidth = 600;
        final int appHeight = 600;
        Image spaceship = new Image("spaceship.png", true);
        spaceshipImageView = new ImageView(spaceship);
        spaceshipImageView.setFitHeight(100);
        spaceshipImageView.setFitWidth(60);

        final int spaceshipStartXCoordinate = 270;
        final int spaceshipStartYCoordinate = 450;
        spaceshipImageView.setX(spaceshipStartXCoordinate);
        spaceshipImageView.setY(spaceshipStartYCoordinate);

        Image space = new Image("space.jpg", true);
        spaceImageView = new ImageView(space);
        spaceImageView.setFitHeight(appHeight);
        spaceImageView.setFitWidth(appWidth);

        Group root = new Group(spaceImageView, spaceshipImageView);

        Scene scene = new Scene(root, appWidth, appHeight, Color.BLACK);

        // Register the key listener here
        scene.setOnKeyPressed(this::processKeyPress);

        primaryStage.setTitle("AlienDirection");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Modifies the position of the image view when an arrow key is pressed.
     *
     * @param event invoked this method
     */
    public void processKeyPress(KeyEvent event) {
        switch (event.getCode()) {
            case UP:
                spaceshipImageView.setY(spaceshipImageView.getY() - MOVE_SPEED);
                break;
            case DOWN:
                spaceshipImageView.setY(spaceshipImageView.getY() + MOVE_SPEED);
                break;
            case LEFT:
                spaceshipImageView.setX(spaceshipImageView.getX() - MOVE_SPEED);
                break;
            case RIGHT:
                spaceshipImageView.setX(spaceshipImageView.getX() + MOVE_SPEED);
                break;
            default:
                break; // Does nothing if it's not an arrow key
        }
    }

    /**
     * Launches the JavaFX application.
     *
     * @param args command line arguments
     */
//    public static void main(String[] args) {
//        launch(args);
//    }
}
