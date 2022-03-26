package ca.bcit.comp2522.termproject.planetbandit;

import javafx.animation.Animation;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Player {

    ImageView playerImage;

    public int direction = 1;
    public boolean grounded = false;

    public int velocity_x;
    public int velocity_y;

    public int WIDTH = 42;
    public int HEIGHT = 66;

    public Animation runAnimation;
    public Animation idleAnimation;

    public Player() {
        playerImage = new ImageView();
        runAnimation = new SpriteAnimation(playerImage, "run", "run", Duration.millis(700), 8);
        idleAnimation = new SpriteAnimation(playerImage, "idle", "idle", Duration.millis(1000), 12);
        runAnimation.setCycleCount(Animation.INDEFINITE);
        idleAnimation.setCycleCount(Animation.INDEFINITE);
        idleAnimation.play();

        playerImage.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.4), 10, 0, 0, 0);");
    }

    public ImageView getPlayerImage() {
        return playerImage;
    }
}

