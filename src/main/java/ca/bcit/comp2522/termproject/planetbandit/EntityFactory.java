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
 * @author Benny and Prab
 * @version 2022
 */
public class EntityFactory implements com.almasb.fxgl.entity.EntityFactory {
    private static final int FIVE = 5;
    private static final int SIX = 6;
    private static final int SEVEN = 7;
    private static final int EIGHT = 8;
    private static final int TEN = 10;
    private static final int TWELVE = 12;
    private static final int THIRTEEN = 13;
    private static final int SIXTEEN = 16;
    private static final int SEVENTEEN = 17;
    private static final int EIGHTEEN = 18;
    private static final int TWENTY = 20;
    private static final int TWENTY_FOUR = 24;
    private static final int TWENTY_FIVE = 25;
    private static final int THIRTY_EIGHT = 38;
    private static final int FIFTY = 50;
    private static final int SIXTY_EIGHT = 68;
    private static final int SEVENTY = 70;
    private static final int HUNDRED = 100;
    private static final int HUNDRED_TWENTY_EIGHT = 128;
    private static final int THOUSAND_TWO_HUNDRED_SEVENTY_THREE = 1273;
    private static final double HUNDRED_SEVENTY_NINE = 179;
    private static final double THIRTY = 30;
    private static final double POINT_SEVEN_FIVE = 0.75;
    private static final double POINT_THREE = 0.3;
    private static final double POINT_FORTY_FIVE = 0.45;
    private static final float POINT_FIVE = 0.5f;

    private final AnimationChannel acIceDisappear = new AnimationChannel(FXGL.image("ice/ice_disappear.png",
            THOUSAND_TWO_HUNDRED_SEVENTY_THREE / 2.0, HUNDRED_SEVENTY_NINE / 2.0),
            Duration.seconds(POINT_THREE), SEVEN);

    private final AnimationChannel acIceWater = new AnimationChannel(FXGL.image("ice/iceWater.png"),
            Duration.seconds(1), 2);

    private final AnimationChannel acCollideCoin = new AnimationChannel(FXGL.image("ice/collideCoin.png"),
            SEVEN, HUNDRED_TWENTY_EIGHT, HUNDRED_TWENTY_EIGHT, Duration.seconds(POINT_FORTY_FIVE),
            0, THIRTEEN);


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
     *
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
     *
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
     *
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
     *
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
     *
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
     *
     * @param data a SpawnData object that represents the data for the new key code
     * @return an Entity object
     */
    @Spawns("keyCode")
    public Entity newKeyCode(final SpawnData data) {
        String key = data.get("key");
        KeyCode keyCode = KeyCode.getKeyCode(key);
        LiftComponent lift = new LiftComponent();
        lift.setGoingUp(true);
        lift.yAxisSpeedDuration(SIX, Duration.millis(POINT_SEVEN_FIVE));
        KeyView keyView = new KeyView(keyCode, Color.YELLOW, TWENTY_FOUR);
        keyView.setCache(true);
        keyView.setCacheHint(CacheHint.SCALE);

        return entityBuilder(data)
                .view(keyView)
                .with(lift)
                .zIndex(HUNDRED)
                .build();
    }

    /**
     * Generates a new player.
     *
     * @param data a SpawnData object that represents the data for the new player
     * @return an Entity object
     */
    @Spawns("player")
    public Entity newPlayer(final SpawnData data) {
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);
        physics.addGroundSensor(new HitBox("GROUND_SENSOR", new Point2D(SIXTEEN, THIRTY_EIGHT),
                BoundingShape.box(SIX, EIGHT)));
        physics.setFixtureDef(new FixtureDef().friction(POINT_FIVE));

        return entityBuilder(data)
                .type(EntityType.PLAYER)
                .bbox(new HitBox(new Point2D(FIVE, FIVE), BoundingShape.circle(TWELVE)))
                .bbox(new HitBox(new Point2D(TEN, TWENTY_FIVE), BoundingShape.box(TEN, SEVENTEEN)))
                .with(new CollidableComponent(true))
                .with(new IrremovableComponent())
                .with(physics)
                .with(new PlayerComponent())
                .build();
    }

    /**
     * Generates a button.
     *
     * @param data a SpawnData object that represents the data for the new button
     * @return an Entity object
     */
    @Spawns("button")
    public Entity newButton(final SpawnData data) {
        Entity keyEntity = FXGL.getGameWorld().create("keyCode",
                new SpawnData(data.getX(), data.getY() - FIFTY).put("key", "E")
        );
        keyEntity.setOpacity(0);

        return entityBuilder(data)
                .type(EntityType.BUTTON)
                .viewWithBBox(texture("button.png", TWENTY, EIGHTEEN))
                .with(new CollidableComponent(true))
                .with("keyEntity", keyEntity)
                .build();
    }

    /**
     * Generates spike objects.
     *
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
     *
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
     *
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
                .bbox(new HitBox(BoundingShape.box(SEVENTY, SEVENTY)))
                .collidable()
                .with(physics)
                .with(new TempPlatformComponent())
                .build();
    }

    /**
     * Allows the platform to disappear.
     *
     * @param data a SpawnData object that represents the data for the platform
     * @return an Entity object
     */
    @Spawns("iceDisappear")
    public Entity newIceDisappear(final SpawnData data) {
        AnimatedTexture animatedTexture = new AnimatedTexture(acIceDisappear);
        animatedTexture.loop();
        return entityBuilder(data)
                .at(data.getX() - TEN, data.getY() - THIRTY)
                .view(animatedTexture)
                .with(new ExpireCleanComponent(Duration.seconds(POINT_THREE)))
                .build();
    }


    /**
     * Generates a new coin.
     *
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

    /**
     * Generates a water platform on the map.
     *
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
     * Generates an entity if player collides with coin.
     *
     * @param data a SpawnData object that represents the data for the collision
     * @return an Entity object
     */
    @Spawns("collideCoin")
    public Entity newCollideCoin(final SpawnData data) {
        AnimatedTexture animatedTexture = new AnimatedTexture(acCollideCoin);
        animatedTexture.loop();
        return entityBuilder(data)
                .view(animatedTexture)
                .with(new ExpireCleanComponent(Duration.seconds(POINT_FORTY_FIVE)))
                .build();
    }

    /**
     * Generates a laser object.
     *
     * @param data a SpawnData object that represents the data for the laser
     * @return an Entity object
     */
    @Spawns("laser")
    public Entity newLaser(final SpawnData data) {
        return entityBuilder(data)
                .type(EntityType.LASER)
                .collidable()
                .with(new LaserComponent())
                .zIndex(HUNDRED)
                .build();
    }

    /**
     * Generates a springboard object.
     *
     * @param data a SpawnData object that represents the data for the springboard
     * @return an Entity object
     */
    @Spawns("springboard")
    public Entity newSpringboard(final SpawnData data) {
        return entityBuilder(data)
                .type(EntityType.SPRINGBOARD)
                .with(new PhysicsComponent())
                .collidable()
                .bbox(new HitBox(new Point2D(1, TWENTY), BoundingShape.box(SIXTY_EIGHT, SIX)))
                .with(new SpringboardComponent())
                .build();
    }

}
