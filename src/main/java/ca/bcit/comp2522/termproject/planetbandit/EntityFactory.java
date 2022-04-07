package ca.bcit.comp2522.termproject.planetbandit;

import ca.bcit.comp2522.termproject.planetbandit.components.LaserComponent;
import ca.bcit.comp2522.termproject.planetbandit.components.MovePlatformComponent;
import ca.bcit.comp2522.termproject.planetbandit.components.PlayerComponent;
import ca.bcit.comp2522.termproject.planetbandit.components.SpringboardComponent;
import ca.bcit.comp2522.termproject.planetbandit.components.TempPlatformComponent;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.ExpireCleanComponent;
import com.almasb.fxgl.dsl.components.LiftComponent;
import com.almasb.fxgl.dsl.views.ScrollingBackgroundView;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.entity.components.IrremovableComponent;
import com.almasb.fxgl.input.view.KeyView;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.CacheHint;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.entityBuilder;
import static com.almasb.fxgl.dsl.FXGL.getAppHeight;
import static com.almasb.fxgl.dsl.FXGL.getAppWidth;
import static com.almasb.fxgl.dsl.FXGL.getAssetLoader;
import static com.almasb.fxgl.dsl.FXGL.texture;

/**
 * Represents an entity factory object.
 *
 * @author Prab and Benny
 * @version 2022
 */
public class EntityFactory implements com.almasb.fxgl.entity.EntityFactory {
    private static final int SIX = 6;
    /**
     * Generates a new background.
     *
     * @param data a SpawnData object that represents the data for the new background
     * @return an Entity object
     */
    @Spawns("background")
    public Entity newBackground(final SpawnData data) {
        Image bgImageName = new Image(getAssetLoader().getStream("/assets/textures/" + "background/"
                + data.get("bgImageName")));
        ScrollingBackgroundView bgView = new ScrollingBackgroundView(bgImageName, getAppWidth(), getAppHeight());
        return entityBuilder(data)
                .view(bgView)
                .zIndex(-1)
                //.with(new IrremovableComponent())
                .build();
    }

    /**
     * Generates a new platform.
     *
     * @param data a SpawnData object that represents the data for the new platform
     * @return an Entity object
     */
    @Spawns("platform")
    public Entity newPlatform(final SpawnData data) {

        PhysicsComponent physicsComponent = new PhysicsComponent();
        HitBox hitBox = getHitBox(data);
        return entityBuilder(data)
                .type(EntityType.PLATFORM)
                .bbox(hitBox)
                .with(physicsComponent)
                .build();
    }

    private HitBox getHitBox(final SpawnData data) {
        HitBox hitBox;
        if (data.getData().containsKey("polygon")) {
            Polygon polygon1 = data.get("polygon");
            ObservableList<Double> points = polygon1.getPoints();
            double[] ps = new double[points.size()];
            for (int i = 0; i < ps.length; i++) {
                ps[i] = points.get(i);
            }
            hitBox = new HitBox(BoundingShape.polygon(ps));
        } else {
            hitBox = new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height")));
        }
        return hitBox;
    }

    /**
     * Generates an exit trigger.
     * @param data a SpawnData object that represents the data for the new exit trigger
     * @return an Entity object
     */
    @Spawns("exitTrigger")
    public Entity newExitTrigger(final SpawnData data) {
        return entityBuilder(data)
                .type(EntityType.EXIT_TRIGGER)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
                .with(new CollidableComponent(true))
                .build();
    }

    /**
     * Generates a door top.
     * @param data a SpawnData object that represents the data for the new door top
     * @return an Entity object
     */
    @Spawns("doorTop")
    public Entity newDoorTop(final SpawnData data) {
        return entityBuilder(data)
                .type(EntityType.DOOR_TOP)
                .opacity(0)
                .build();
    }

    /**
     * Generates a door bot.
     * @param data a SpawnData object that represents the data for the new door bot
     * @return an Entity object
     */
    @Spawns("doorBot")
    public Entity newDoorBot(final SpawnData data) {
        return entityBuilder(data)
                .type(EntityType.DOOR_BOT)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
                .opacity(0)
                .with(new CollidableComponent(true))
                .build();
    }

    /**
     * Generates a new key prompt.
     * @param data a SpawnData object that represents the data for the new key prompt
     * @return an Entity object
     */
    @Spawns("keyPrompt")
    public Entity newKeyPrompt(final SpawnData data) {
        return entityBuilder(data)
                .type(EntityType.KEY_PROMPT)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
                .with(new CollidableComponent(true))
                .build();
    }

    /**
     * Generates an exit sign.
     * @param data a SpawnData object that represents the data for the new exit sign
     * @return an Entity object
     */
    @Spawns("exitSign")
    public Entity newExitSign(final SpawnData data) {
        return entityBuilder(data)
                .type(EntityType.EXIT_SIGN)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
                .with(new CollidableComponent(true))
                .build();
    }

    /**
     * Generates a key code.
     * @param data a SpawnData object that represents the data for the new key code
     * @return an Entity object
     */
    @Spawns("keyCode")
    public Entity newKeyCode(final SpawnData data) {
        String key = data.get("key");
        KeyCode keyCode = KeyCode.getKeyCode(key);
        LiftComponent lift = new LiftComponent();
        lift.setGoingUp(true);
        lift.yAxisSpeedDuration(6, Duration.millis(0.75));
        KeyView keyView = new KeyView(keyCode, Color.YELLOW, 24);
        keyView.setCache(true);
        keyView.setCacheHint(CacheHint.SCALE);

        return entityBuilder(data)
                .view(keyView)
                .with(lift)
                .zIndex(100)
                .build();
    }

    /**
     * Generates a new player.
     * @param data a SpawnData object that represents the data for the new player
     * @return an Entity object
     */
    @Spawns("player")
    public Entity newPlayer(final SpawnData data) {
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);
        physics.addGroundSensor(new HitBox("GROUND_SENSOR", new Point2D(16, 38), BoundingShape.box(6, 8)));
        physics.setFixtureDef(new FixtureDef().friction(0.5f));

        return entityBuilder(data)
                .type(EntityType.PLAYER)
                .bbox(new HitBox(new Point2D(5, 5), BoundingShape.circle(12)))
                .bbox(new HitBox(new Point2D(10, 25), BoundingShape.box(10, 17)))
                .with(new CollidableComponent(true))
                .with(new IrremovableComponent())
                .with(physics)
                .with(new PlayerComponent())
                .build();
    }

    /**
     * Generates a button.
     * @param data a SpawnData object that represents the data for the new button
     * @return an Entity object
     */
    @Spawns("button")
    public Entity newButton(final SpawnData data) {
        Entity keyEntity = FXGL.getGameWorld().create("keyCode",
                new SpawnData(data.getX(), data.getY() - 50).put("key", "E")
        );
        keyEntity.setOpacity(0);

        return entityBuilder(data)
                .type(EntityType.BUTTON)
                .viewWithBBox(texture("button.png", 20, 18))
                .with(new CollidableComponent(true))
                .with("keyEntity", keyEntity)
                .build();
    }

    /**
     * Generates spike objects.
     * @param data a SpawnData object that represents the data for the new spike
     * @return an Entity object
     */
    @Spawns("spike")
    public Entity newSpikes(final SpawnData data) {
        return entityBuilder(data)
                .type(EntityType.SPIKE)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
                .bbox(getHitBox(data))
                .collidable()
                .build();
    }

    /**
     * Generates a new platform to give the illusion of a side scrolling game.
     * @param data a SpawnData object that represents the data for the new platform
     * @return an Entity object
     */
    @Spawns("movePlatform")
    public Entity newMovePlatform(final SpawnData data) {
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.KINEMATIC);
        physics.setFixtureDef(new FixtureDef().friction(1F));

        return entityBuilder(data)
                .type(EntityType.MOVE_PLATFORM)
                .collidable()
                .viewWithBBox(texture("ice/movingHologramBlock.png"))
                .with(physics)
                .with(new MovePlatformComponent(data))
                .build();
    }

    /**
     * Generates a temporary platform to give the illusion of a side scrolling game.
     * @param data a SpawnData object that represents the data for the new platform
     * @return an Entity object
     */
    @Spawns("tempPlatform")
    public Entity newTempPlatform(final SpawnData data) {
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.KINEMATIC);
        physics.setFixtureDef(new FixtureDef().friction(1F));
        return entityBuilder(data)
                .type(EntityType.TEMP_PLATFORM)
                .viewWithBBox(texture("ice/hologramBlock.png"))
                .bbox(new HitBox(BoundingShape.box(70, 70)))
                .collidable()
                .with(physics)
                .with(new TempPlatformComponent())
                .build();
    }

    /**
     * Creates the animation for when the ice is about to disappear.
     */
    AnimationChannel acIceDisappear = new AnimationChannel(FXGL.image("ice/ice_disappear.png", 1273 / 2.0, 179 / 2.0), Duration.seconds(0.3), 7);

    /**
     * Allows the platform to disappear.
     * @param data a SpawnData object that represents the data for the platform
     * @return an Entity object
     */
    @Spawns("iceDisappear")
    public Entity newIceDisappear(final SpawnData data) {
        AnimatedTexture animatedTexture = new AnimatedTexture(acIceDisappear);
        animatedTexture.loop();
        return entityBuilder(data)
                .at(data.getX() - 10, data.getY() - 30)
                .view(animatedTexture)
                .with(new ExpireCleanComponent(Duration.seconds(0.3)))
                .build();
    }


    /**
     * Generates a new coin.
     * @param data a SpawnData object that represents the data for the new coin
     * @return an Entity object
     */
    @Spawns("coin")
    public Entity newCoin(final SpawnData data) {
        return entityBuilder(data)
                .type(EntityType.COIN)
                .viewWithBBox(texture("heart.png"))
                .collidable()
                .build();
    }

    AnimationChannel acIceWater = new AnimationChannel(FXGL.image("ice/iceWater.png"), Duration.seconds(1), 2);

    /**
     * Generates a water platform on the map.
     * @param data a SpawnData object that represents the data for the new water platform
     * @return an Entity object
     */
    @Spawns("iceWater")
    public Entity newIceWater(final SpawnData data) {
        AnimatedTexture animatedTexture = new AnimatedTexture(acIceWater);
        animatedTexture.loop();
        return entityBuilder(data)
                .type(EntityType.ICE_WATER)
                .view(animatedTexture)
                .build();
    }

    /**
     * Creates an animation for when the player collides with the coin.
     */
    AnimationChannel acCollideCoin = new AnimationChannel(FXGL.image("ice/collideCoin.png"), 7, 128, 128, Duration.seconds(0.45), 0, 13);

    /**
     * Generates an entity if player collides with coin.
     * @param data a SpawnData object that represents the data for the collision
     * @return an Entity object
     */
    @Spawns("collideCoin")
    public Entity newCollideCoin(SpawnData data) {
        AnimatedTexture animatedTexture = new AnimatedTexture(acCollideCoin);
        animatedTexture.loop();
        return entityBuilder(data)
                .view(animatedTexture)
                .with(new ExpireCleanComponent(Duration.seconds(0.45)))
                .build();
    }

    /**
     * Generates a laser object.
     * @param data a SpawnData object that represents the data for the laser
     * @return an Entity object
     */
    @Spawns("laser")
    public Entity newLaser(final SpawnData data) {
        return entityBuilder(data)
                .type(EntityType.LASER)
                .collidable()
                .with(new LaserComponent())
                .zIndex(100)
                .build();
    }

    /**
     * Generates a springboard object.
     * @param data a SpawnData object that represents the data for the springboard
     * @return an Entity object
     */
    @Spawns("springboard")
    public Entity newSpringboard(final SpawnData data) {
        return entityBuilder(data)
                .type(EntityType.SPRINGBOARD)
                .with(new PhysicsComponent())
                .collidable()
                .bbox(new HitBox(new Point2D(1, 20), BoundingShape.box(68, 6)))
                .with(new SpringboardComponent())
                .build();
    }

}
