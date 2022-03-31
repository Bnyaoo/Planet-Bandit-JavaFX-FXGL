package ca.bcit.comp2522.termproject.planetbandit.components;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class PlayerComponent extends Component {


    private PhysicsComponent physics;

    private AnimatedTexture texture;

    private AnimationChannel animIdle,animWalk;


    private static final int MAX_JUMPS=2;
    private int jumps = MAX_JUMPS;

    public PlayerComponent() {
        Image image = FXGL.image("player.png");

        animIdle = new AnimationChannel(image, 4, 32, 42, Duration.seconds(1), 0, 0);
        animWalk = new AnimationChannel(image, 4, 32, 42, Duration.seconds(1), 0, 3);

        texture = new AnimatedTexture(animIdle);
        texture.loop();
    }

    @Override
    public void onAdded() {
        entity.getTransformComponent().setScaleOrigin(new Point2D(16, 21));
        entity.getViewComponent().addChild(texture);
        physics.onGroundProperty().addListener((ob, ov, nv) ->{
            if (nv) {
                jumps = MAX_JUMPS;
            }
        });
    }

    @Override
    public void onUpdate(double tpf) {
        if (physics.isMovingX()) {
            if (texture.getAnimationChannel() != animWalk) {
                texture.loopAnimationChannel(animWalk);
            }
        }else {
            if (texture.getAnimationChannel() != animIdle) {
                texture.loopAnimationChannel(animIdle);
            }
        }
    }

    public void left() {
        getEntity().setScaleX(-1);
        physics.setVelocityX(-170);
    }

    public void right() {
        getEntity().setScaleX(1);
        physics.setVelocityX(170);
    }

    public void stop() {
        physics.setVelocityX(0);
    }

    public void jump() {
        if (jumps == 0) {
            return;
        }
        physics.setVelocityY(-300);
        jumps-- ;
    }


}
