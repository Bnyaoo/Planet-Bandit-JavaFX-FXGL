package ca.bcit.comp2522.termproject.planetbandit.collisions;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import ca.bcit.comp2522.termproject.planetbandit.EntityType;

/**
 *
 */
public class PlayerButtonHandler extends CollisionHandler {
    /**
     * The order of types determines the order of entities in callbacks.
     */
    public PlayerButtonHandler() {
        super(EntityType.PLAYER, EntityType.BUTTON);
    }

    @Override
    protected void onCollisionBegin(Entity player, Entity btn) {
        Entity keyEntity = btn.getObject("keyEntity");
        if (!keyEntity.isActive()) {
            keyEntity.setProperty("activated", false);
            FXGL.getGameWorld().addEntity(keyEntity);
        }
        keyEntity.setOpacity(1);
    }

    @Override
    protected void onCollisionEnd(Entity player, Entity btn) {
        Entity keyEntity = btn.getObject("keyEntity");
        if (!keyEntity.getBoolean("activated")) {
            keyEntity.setOpacity(0);
        }
    }
}
