package ca.bcit.comp2522.termproject.planetbandit.components;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.util.Duration;

/**
 * Constructs a player object.
 *
 * @author Prab and Benny
 * @version 2022
 */
public class PlayerComponent extends Component {
    private static  final int THREE = 3;
    private static  final int FOUR = 4;
    private static  final int SIXTEEN = 16;
    private static  final int TWENTY_ONE = 21;
    private static  final int THIRTY_TWO = 32;
    private static  final int FORTY_TWO = 42;
    private static  final int ONE_HUNDRED_SEVENTY = 170;
    private static  final int NEGATIVE_ONE_HUNDRED_SEVENTY = -170;
    private static  final int NEGATIVE_THREE_HUNDRED = -300;



    private static final int MAX_JUMPS = 2;
    private PhysicsComponent physics;
    private final AnimatedTexture texture;
    private final AnimationChannel animIdle;
    private final AnimationChannel animWalk;
    private int jumps = MAX_JUMPS;

    /**
     * Constructs a player.
     */
    public PlayerComponent() {
        Image image = FXGL.image("player.png");

        animIdle = new AnimationChannel(image, FOUR, THIRTY_TWO, FORTY_TWO, Duration.seconds(1), 0, 0);
        animWalk = new AnimationChannel(image, FOUR, THIRTY_TWO, FORTY_TWO, Duration.seconds(1), 0, THREE);

        texture = new AnimatedTexture(animIdle);
        texture.loop();
    }

    /**
     * Sets the player's settings when added to the game.
     */
    @Override
    public void onAdded() {
        entity.getTransformComponent().setScaleOrigin(new Point2D(SIXTEEN, TWENTY_ONE));
        entity.getViewComponent().addChild(texture);
        physics.onGroundProperty().addListener((ob, ov, nv) -> {
            if (nv) {
                jumps = MAX_JUMPS;
            }
        });
    }

    /**
     * Updates the player's position and animations when moving.
     * @param tpf a double
     */
    @Override
    public void onUpdate(final double tpf) {
        if (physics.isMovingX()) {
            if (texture.getAnimationChannel() != animWalk) {
                texture.loopAnimationChannel(animWalk);
            }
        } else {
            if (texture.getAnimationChannel() != animIdle) {
                texture.loopAnimationChannel(animIdle);
            }
        }
    }

    /**
     * Moves the player left.
     */
    public void left() {
        getEntity().setScaleX(-1);
        physics.setVelocityX(NEGATIVE_ONE_HUNDRED_SEVENTY);
    }

    /**
     * Moves the player right.
     */
    public void right() {
        getEntity().setScaleX(1);
        physics.setVelocityX(ONE_HUNDRED_SEVENTY);
    }

    /**
     * Stops the player's movement.
     */
    public void stop() {
        physics.setVelocityX(0);
    }

    /**
     * Allows player to jump.
     */
    public void jump() {
        if (jumps == 0) {
            return;
        }
        physics.setVelocityY(NEGATIVE_THREE_HUNDRED);
        jumps--;
    }


}
