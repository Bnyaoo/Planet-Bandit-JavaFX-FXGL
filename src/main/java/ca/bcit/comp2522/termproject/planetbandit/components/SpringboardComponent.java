package ca.bcit.comp2522.termproject.planetbandit.components;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import ca.bcit.comp2522.termproject.planetbandit.EntityType;
import javafx.scene.image.Image;
import javafx.util.Duration;
import java.util.List;

/**
 * Represents a springboard object.
 *
 * @author Prab and Benny
 * @version 2022
 */
public class SpringboardComponent extends Component {
    private static final int THREE = 3;
    private static final int NEGATIVE_EIGHT_HUNDRED = -800;
    /**
     * Represents the image for the springboard going up.
     */
    Image up = FXGL.image("propellerUP.png");
    /**
     * Represents the image for the springboard going down.
     */
    Image down = FXGL.image("propellerDOWN.png");

    /**
     * Represents the animation duration for the springboard going down.
     */
    AnimationChannel ac = new AnimationChannel(List.of(down), Duration.seconds(THREE));
    /**
     * Represents the animation texture for the springboard going down.
     */
    AnimatedTexture animatedTexture = new AnimatedTexture(ac);

    /**
     * Sets the springboard settings when it is added to the game.
     */
    @Override
    public void onAdded() {
        entity.getViewComponent().addChild(animatedTexture);

        FXGL.onCollisionBegin(EntityType.PLAYER, EntityType.SPRINGBOARD, (player, springboard) -> {
            player.getComponent(PhysicsComponent.class).setVelocityY(NEGATIVE_EIGHT_HUNDRED);
            animatedTexture.play();
            FXGL.play("jump.wav");
        });

    }
}
