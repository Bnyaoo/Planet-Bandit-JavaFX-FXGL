package ca.bcit.comp2522.termproject.planetbandit;

import ca.bcit.comp2522.termproject.planetbandit.Entities.Actor;
import ca.bcit.comp2522.termproject.planetbandit.Entities.Spaceship;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a spaceship object.
 *
 * @author Prab and Benny
 * @version 2022
 */
public class Space {
    // Distance in pixels that the spaceship moves when a key is pressed
//    public static final int MOVE_SPEED = 15;

    // Contains the image of the spaceship
//    private ImageView playerImageView;

    // Contains the image of the spaceship
    private ImageView backgroundImageView;

    private static String backgroundImgName = "space.jpg";

    public static final int appWidth = 600;
    public static final int appHeight = 600;

    private Group root;


    /**
     * Displays an image that can be moved using the arrow keys.
     *
     * @param primaryStage a Stage
     */
    public void start(Stage primaryStage, Spaceship spaceship) {

//        Image player = new Image(spaceship.getImageName(), true);
//        playerImageView = new ImageView(player);
//        playerImageView.setFitHeight(100);
//        playerImageView.setFitWidth(60);
//
//        playerImageView.setX(spaceship.getxCoordinate());
//        playerImageView.setY(spaceship.getyCoordinate());


        Image background = new Image(backgroundImgName, true);
        backgroundImageView = new ImageView(background);
        backgroundImageView.setFitHeight(appHeight);
        backgroundImageView.setFitWidth(appWidth);

        root = new Group(backgroundImageView, spaceship.getPlayerImageView());
        Scene scene = new Scene(root, appWidth, appHeight, Color.BLACK);



        // Register the key listener here
        scene.setOnKeyPressed(spaceship::processKeyPress);
        primaryStage.setTitle("Planet Bandit");
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    public void removeSpaceship(Spaceship spaceship) {
        root.getChildren().remove(spaceship.getPlayerImageView());
    }

    //    /**
//     * Modifies the position of the image view when an arrow key is pressed.
//     *
//     * @param event invoked this method
//     */
//    public void processKeyPress(KeyEvent event) {
//        switch (event.getCode()) {
//            case UP:
//                playerImageView.setY(playerImageView.getY() - MOVE_SPEED);
//                break;
//            case DOWN:
//                playerImageView.setY(playerImageView.getY() + MOVE_SPEED);
//                break;
//            case LEFT:
//                playerImageView.setX(playerImageView.getX() - MOVE_SPEED);
//                break;
//            case RIGHT:
//                playerImageView.setX(playerImageView.getX() + MOVE_SPEED);
//                break;
//            default:
//                break; // Does nothing if it's not an arrow key
//        }
//    }

}
