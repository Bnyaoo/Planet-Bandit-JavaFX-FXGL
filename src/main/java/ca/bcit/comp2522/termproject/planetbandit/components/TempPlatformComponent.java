package ca.bcit.comp2522.termproject.planetbandit.components;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.ExpireCleanComponent;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.util.Duration;

/**
 * Represents a temporary platform object.
 *
 * @author Prab and Benny
 * @version 2022
 */
public class TempPlatformComponent extends Component {
    private static final int SEVENTY = 70;
    private static final int TWO_HUNDRED = 200;
    private static final double TWO_POINT_FIVE = 2.5;
    private static final double ZERO_POINT_FIVE = 0.5;
    /**
     * Represents the animation duration for the platform disappearing.
     */
    AnimationChannel ac = new AnimationChannel(FXGL.image("ice/iceBlock.png"),
            1, SEVENTY, SEVENTY, Duration.millis(TWO_HUNDRED), 0, 1);
    /**
     * Represents the animation texture for the platform disappearing.
     */
    AnimatedTexture animatedTexture = new AnimatedTexture(ac);
    private PhysicsComponent physics;
    private boolean begin;

    /**
     * Sets the platform settings when it is added to the game.
     */
    @Override
    public void onAdded() {
        entity.getViewComponent().addChild(animatedTexture);
        double x = entity.getX();
        double y = entity.getY();
        physics.addGroundSensor(entity.getBoundingBoxComponent().hitBoxesProperty().get(0));
        physics.onGroundProperty().addListener((ob, ov, nv) -> {
            if (nv && !begin) {
                begin = true;
                entity.addComponent(new ExpireCleanComponent(Duration.seconds(TWO_POINT_FIVE)));
                FXGL.runOnce(animatedTexture::loop, Duration.seconds(ZERO_POINT_FIVE));

                FXGL.runOnce(() -> {
                    if (entity != null) {
                        FXGL.spawn("iceDisappear", x, y);
                    }
                }, Duration.seconds(TWO_POINT_FIVE));
            }
        });
    }


}
