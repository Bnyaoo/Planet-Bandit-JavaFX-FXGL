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

public class SpringboardComponent extends Component {
    Image up = FXGL.image("propellerUP.png");
    Image down = FXGL.image("propellerDOWN.png");

    AnimationChannel ac = new AnimationChannel(List.of(down), Duration.seconds(3));
    AnimatedTexture animatedTexture = new AnimatedTexture(ac);
    @Override
    public void onAdded() {
        entity.getViewComponent().addChild(animatedTexture);

        FXGL.onCollisionBegin(EntityType.PLAYER, EntityType.SPRINGBOARD, (player, springboard)->{
            player.getComponent(PhysicsComponent.class).setVelocityY(-800);
            animatedTexture.play();
            FXGL.play("jump.wav");
        });

    }
}
