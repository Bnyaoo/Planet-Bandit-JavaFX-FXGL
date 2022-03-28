package ca.bcit.comp2522.termproject.planetbandit.components;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.ExpireCleanComponent;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.util.Duration;

public class TempPlatformComponent extends Component {
    private PhysicsComponent physics;
    private boolean begin;

    AnimationChannel ac = new AnimationChannel(FXGL.image("ice/iceBlock.png"), 1, 70, 70, Duration.millis(200), 0, 1);
    AnimatedTexture animatedTexture = new AnimatedTexture(ac);

    @Override
    public void onAdded() {
        entity.getViewComponent().addChild(animatedTexture);
        double x = entity.getX();
        double y = entity.getY();
        physics.addGroundSensor(entity.getBoundingBoxComponent().hitBoxesProperty().get(0));
        physics.onGroundProperty().addListener((ob, ov, nv) ->{
            if (nv && !begin){
                begin = true;
                entity.addComponent(new ExpireCleanComponent(Duration.seconds(2.5)));
                FXGL.runOnce(animatedTexture::loop, Duration.seconds(0.5));

                FXGL.runOnce(() -> {
                    if (entity!=null) {
                        FXGL.spawn("iceDisappear", x, y);
                    }
                }, Duration.seconds(2.5));
            }
        });
    }


}
