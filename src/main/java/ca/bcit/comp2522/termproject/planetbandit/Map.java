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
public class Map {
    // Distance in pixels that the spaceship moves when a key is pressed
    public static final int MOVE_SPEED = 15;

    // Contains the image of the spaceship
    private ImageView playerImageView;

    // Contains the image of the spaceship
    private ImageView backgroundImageView;

    /**
     * Displays an image that can be moved using the arrow keys.
     *
     * @param primaryStage a Stage
     */
    public void start(Stage primaryStage, String playerImgName, String backgroundImgName) {
        final int appWidth = 600;
        final int appHeight = 600;
        Image player = new Image(playerImgName, true);
        playerImageView = new ImageView(player);
        playerImageView.setFitHeight(100);
        playerImageView.setFitWidth(60);

        final int playerStartXCoordinate = 270;
        final int playerStartYCoordinate = 450;
        playerImageView.setX(playerStartXCoordinate);
        playerImageView.setY(playerStartYCoordinate);

        Image background = new Image(backgroundImgName, true);
        backgroundImageView = new ImageView(background);
        backgroundImageView.setFitHeight(appHeight);
        backgroundImageView.setFitWidth(appWidth);

        Group root = new Group(backgroundImageView, playerImageView);

        Scene scene = new Scene(root, appWidth, appHeight, Color.BLACK);

        // Register the key listener here
        scene.setOnKeyPressed(this::processKeyPress);

        primaryStage.setTitle("Planet Bandit");
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
                playerImageView.setY(playerImageView.getY() - MOVE_SPEED);
                break;
            case DOWN:
                playerImageView.setY(playerImageView.getY() + MOVE_SPEED);
                break;
            case LEFT:
                playerImageView.setX(playerImageView.getX() - MOVE_SPEED);
                break;
            case RIGHT:
                playerImageView.setX(playerImageView.getX() + MOVE_SPEED);
                break;
            default:
                break; // Does nothing if it's not an arrow key
        }
    }
}
