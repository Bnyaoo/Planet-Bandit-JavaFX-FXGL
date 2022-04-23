package ca.bcit.comp2522.termproject.planetbandit.components;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.time.LocalTimer;
import javafx.util.Duration;

/**
 * Represents a moving platform object.
 *
 * @author Prab and Benny
 * @version 2022
 */
public class MovePlatformComponent extends Component {
    private PhysicsComponent physic;
    private final LocalTimer moveTimer = FXGL.newLocalTimer();
    private final Duration seconds;
    private double speed;
    private final boolean isHor;

    /**
     * Constructs a moving platform object.
     * @param data a SpawnData object
     */
    public MovePlatformComponent(final SpawnData data) {
        this(data.<Integer>get("millis"), data.<Integer>get("speed"), data.<Boolean>get("isHor"));
    }

    /**
     * Constructs a moving platform object.
     *
     * @param millis an int that represents the milliseconds
     * @param speed an int that represents the speed
     * @param isHor a boolean
     */
    public MovePlatformComponent(final int millis, final int speed, final boolean isHor) {
        seconds = Duration.millis(millis);
        this.speed = speed;
        this.isHor = isHor;
    }

    /**
     * Starts the timer when a platform is added.
     */
    @Override
    public void onAdded() {
        moveTimer.capture();
    }

    /**
     * Updates the timer when a platform is added.
     */
    @Override
    public void onUpdate(final double tpf) {
        if (moveTimer.elapsed(seconds)) {
            speed = speed * -1;
            moveTimer.capture();
        }
        if (isHor) {
            physic.setVelocityX(speed);
        } else {
            physic.setVelocityY(speed);
        }
    }
}
