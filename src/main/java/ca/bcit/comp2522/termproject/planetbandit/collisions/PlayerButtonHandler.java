package ca.bcit.comp2522.termproject.planetbandit.collisions;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import ca.bcit.comp2522.termproject.planetbandit.EntityType;

/**
 * Represents the player's button handler.
 *
 * @author Prab and Benny
 * @version 2022
 */
public class PlayerButtonHandler extends CollisionHandler {
    /**
     * The order of types determines the order of entities in callbacks.
     */
    public PlayerButtonHandler() {
        super(EntityType.PLAYER, EntityType.BUTTON);
    }

    /**
     * Starts the collision with the player.
     * @param player an Entity
     * @param btn an Entity
     */
    @Override
    protected void onCollisionBegin(final Entity player, final Entity btn) {
        Entity keyEntity = btn.getObject("keyEntity");
        if (!keyEntity.isActive()) {
            keyEntity.setProperty("activated", false);
            FXGL.getGameWorld().addEntity(keyEntity);
        }
        keyEntity.setOpacity(1);
    }

    /**
     * Ends the collision with the player.
     * @param player an Entity
     * @param btn an Entity
     */
    @Override
    protected void onCollisionEnd(final Entity player, final Entity btn) {
        Entity keyEntity = btn.getObject("keyEntity");
        if (!keyEntity.getBoolean("activated")) {
            keyEntity.setOpacity(0);
        }
    }
}
