package ca.bcit.comp2522.termproject.planetbandit;

import com.almasb.fxgl.animation.Interpolators;
import com.almasb.fxgl.app.ApplicationMode;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.GameView;
import com.almasb.fxgl.app.scene.SceneFactory;
import com.almasb.fxgl.app.scene.Viewport;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.entity.level.Level;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.input.view.KeyView;
import com.almasb.fxgl.input.virtual.VirtualButton;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.texture.Texture;
import ca.bcit.comp2522.termproject.planetbandit.collisions.PlayerButtonHandler;
import ca.bcit.comp2522.termproject.planetbandit.components.PlayerComponent;
import ca.bcit.comp2522.termproject.planetbandit.ui.LevelEndScene;
import ca.bcit.comp2522.termproject.planetbandit.ui.LoadingScene;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameScene;
import static com.almasb.fxgl.dsl.FXGLForKtKt.showMessage;


/**
 * Represents the gameplay platform.
 *
 * @author Benny and Prab
 * @version 2022
 */
public class Platformer extends GameApplication {
    /**
     * Constant value 760.
     */
    public static final int SEVEN_HUNDRED_SIXTY = 760;
    /**
     * Constant value 60.
     */
    public static final int SIXTY = 60;
    /**
     * Constant value 5.
     */
    public static final int FIVE = 5;
    /**
     * Constant value 9.
     */
    public static final int NINE = 9;
    /**
     * Constant value 13.
     */
    public static final int THIRTEEN = 13;
    /**
     * Constant value 50.
     */
    public static final int FIFTY = 50;
    /**
     * Constant value 70.
     */
    public static final int SEVENTY = 70;
    /**
     * Constant value 250.
     */
    public static final int TWO_HUNDRED_FIFTY = 250;
    /**
     * Constant value 150.
     */
    public static final int ONE_HUNDRED_FIFTY = 150;
    /**
     * Constant value for "floor" of the game map.
     */
    public static final int FLOOR = -1500;
    /**
     * Constant value 1.6.
     */
    public static final double ONE_POINT_SIX = 1.6;
    /**
     * Constant value 0.05.
     */
    public static final double ZERO_POINT_FIVE = 0.05;
    /**
     * Constant value 2.5.
     */
    public static final double TWO_POINT_FIVE = 2.5;
    /**
     * Constant value 2.4.
     */
    public static final double TWO_POINT_FOUR = 2.4;
    /**
     * Constant value 1.3.
     */
    public static final double ONE_POINT_THREE = 1.3;

    private static final int MAX_LEVEL = 3;
    private static final int STARTING_LEVEL = 0;
    private static final int SCREEN_WIDTH = 1280;
    private static final int SCREEN_HEIGHT = 720;

    private Entity player;
    private final Riddles riddles = new Riddles();

    /**
     * Initializes game settings.
     * @param settings a GameSettings object
     */
    @Override
    protected void initSettings(final GameSettings settings) {

        settings.setMainMenuEnabled(true);
        settings.setGameMenuEnabled(true);

        settings.setWidth(SCREEN_WIDTH);
        settings.setHeight(SCREEN_HEIGHT);
        settings.setSceneFactory(new SceneFactory() {

            @NotNull
            @Override
            public com.almasb.fxgl.app.scene.LoadingScene newLoadingScene() {
                return new LoadingScene();
            }

            @Override
            public FXGLMenu newMainMenu() {
                return new Login();
            }
        });

        settings.setApplicationMode(ApplicationMode.DEVELOPER);
    }

    /**
     * Initializes user input and handles collision.
     */
    @Override
    protected void initInput() {

        FXGL.getInput().addAction(new UserAction("left") {
            @Override
            protected void onAction() {
                player.getComponent(PlayerComponent.class).left();
            }

            @Override
            protected void onActionEnd() {
                player.getComponent(PlayerComponent.class).stop();
            }
        }, KeyCode.A, VirtualButton.LEFT);

        FXGL.getInput().addAction(new UserAction("right") {
            @Override
            protected void onAction() {
                player.getComponent(PlayerComponent.class).right();
            }

            @Override
            protected void onActionEnd() {
                player.getComponent(PlayerComponent.class).stop();
            }
        }, KeyCode.D, VirtualButton.RIGHT);

        FXGL.getInput().addAction(new UserAction("jump") {
            @Override
            protected void onActionBegin() {
                player.getComponent(PlayerComponent.class).jump();
            }
        }, KeyCode.W, VirtualButton.A);

        FXGL.getInput().addAction(new UserAction("Use") {
            @Override
            protected void onActionBegin() {
                FXGL.getGameWorld().getEntitiesByType(EntityType.BUTTON)
                        .stream()
                        .filter(btn -> btn.hasComponent(CollidableComponent.class)
                                && player.isColliding(btn))
                        .forEach(btn -> {
                            btn.removeComponent(CollidableComponent.class);
                            Entity keyEntity = btn.getObject("keyEntity");
                            keyEntity.setProperty("activated", true);

                            KeyView view = (KeyView) keyEntity
                                    .getViewComponent().getChildren().get(0);
                            view.setKeyColor(Color.RED);

                            makeExitDoor();
                        });
            }
        }, KeyCode.E, VirtualButton.B);
    }

    private void makeExitDoor() {
        Entity doorTop = FXGL.getGameWorld().getSingleton(EntityType.DOOR_TOP);
        Entity doorBot = FXGL.getGameWorld().getSingleton(EntityType.DOOR_BOT);
        doorBot.getComponent(CollidableComponent.class).setValue(true);
        doorTop.setOpacity(1);
        doorBot.setOpacity(1);
    }

    /**
     * Initializes game variables.
     * @param vars map containing strings associated with objects
     */
    @Override
    protected void initGameVars(final Map<String, Object> vars) {
        vars.put("level", STARTING_LEVEL);
        vars.put("levelTime", 0.0);
        vars.put("score", 0);
        vars.put("lives", FIVE);
    }


    /**
     * Sets the volume of bgm and sfx upon initialization.
     */
    @Override
    protected void onPreInit() {
        FXGL.getSettings().setGlobalMusicVolume(ZERO_POINT_FIVE);
        FXGL.getSettings().setGlobalSoundVolume(ZERO_POINT_FIVE);
    }

    /**
     * Handles collision between Player and other game entities.
     */
    @Override
    protected void initPhysics() {
        FXGL.getPhysicsWorld().setGravity(0, SEVEN_HUNDRED_SIXTY);
        FXGL.getPhysicsWorld().addCollisionHandler(new PlayerButtonHandler());

        FXGL.onCollisionBegin(EntityType.PLAYER, EntityType.COIN, (player, coin) -> {
            FXGL.getGameWorld().spawn("collideCoin", coin.getX() - SIXTY, coin.getY() - SIXTY);
            FXGL.play("coin.wav");
            FXGL.inc("lives", +1);
            coin.removeFromWorld();
        });

        FXGL.onCollisionOneTimeOnly(EntityType.PLAYER, EntityType.EXIT_SIGN, (player, sign) -> {
            Texture texture = FXGL.texture("exit_sign.png").brighter();
            texture.setTranslateX(sign.getX() + NINE);
            texture.setTranslateY(sign.getY() + THIRTEEN);

            GameView gameView = new GameView(texture, ONE_HUNDRED_FIFTY);
            FXGL.getGameScene().addGameView(gameView);
            FXGL.runOnce(() -> FXGL.getGameScene().removeGameView(gameView),
                    Duration.seconds(ONE_POINT_SIX));
        });

        FXGL.onCollisionOneTimeOnly(EntityType.PLAYER,
                EntityType.EXIT_TRIGGER, (player, trigger) -> makeExitDoor());

        FXGL.onCollisionBegin(EntityType.PLAYER, EntityType.DOOR_BOT, (player, doorBot) -> {
            System.out.println("Level Completed");
            getGameScene().getViewport().fade(() ->
                    FXGL.showMessage(riddles.getRiddle(), () ->
                            FXGL.getDialogService().showInputBox("Please enter your answer: ",
                                    answer -> {
                System.out.println("You typed: " + answer);
                if (answer.equalsIgnoreCase(riddles.getAnswer())) {
                    FXGL.showConfirm("Correct!\nMove on to the next planet?", result -> {
                        if (result) {
                            nextLevel();
                        } else {
                            FXGL.getGameController().exit();
                        }
                    });
                } else {
                    FXGL.showMessage("I'm sorry that was incorrect, the fugitive has escaped!"
                            + "\nThe correct answer was, '" + riddles.getAnswer() + "'.");
                    playerDead();
                }
            })));
        });

        FXGL.onCollisionBegin(EntityType.PLAYER, EntityType.KEY_PROMPT, (player, keyPrompt) -> {
            String key = keyPrompt.getString("key");

            Entity entity = FXGL.getGameWorld().create("keyCode",
                    new SpawnData(keyPrompt.getX(), keyPrompt.getY()).put("key", key));
            FXGL.spawnWithScale(entity, Duration.seconds(1), Interpolators.ELASTIC.EASE_OUT());

            FXGL.runOnce(() -> {
                if (entity.isActive()) {
                    FXGL.despawnWithScale(entity, Duration.seconds(1),
                            Interpolators.ELASTIC.EASE_IN());
                }
            }, Duration.seconds(TWO_POINT_FIVE));
        });

        FXGL.onCollisionBegin(EntityType.PLAYER, EntityType.SPIKE, (player, spikes) -> {
            playerDead();
            FXGL.play("dead.wav");
        });


        FXGL.onCollision(EntityType.PLAYER, EntityType.LASER, (player, laser) -> {
            playerDead();
            FXGL.play("dead.wav");
        });
    }

    private void playerDead() {
        if (FXGL.geti("lives") > 1) {
            FXGL.inc("lives", -1);
            setLevel(FXGL.geti("level"));
        } else {
            FXGL.showMessage("You have died", () ->
                    FXGL.showConfirm("Would you like to continue?", result -> {
                if (result) {
                    FXGL.getGameController().startNewGame();
                } else {
                    FXGL.getGameController().exit();
                }
            }));
        }
    }

    /**
     * Initializes Player "health" display.
     */
    @Override
    protected void initUI() {
        Text text = FXGL.addText("", FIFTY, FIFTY);
        text.textProperty().bind(FXGL.getip("lives").asString("Lives :%d"));
    }

    private void nextLevel() {
        if (FXGL.geti("level") == MAX_LEVEL) {
            showMessage("Congratulations, you have captured all the fugitives!");
            return;
        }

        FXGL.inc("level", +1);

        setLevel(FXGL.geti("level"));

    }

    private void setLevel(final int levelNum) {
        if (player != null) {
            player.getComponent(PhysicsComponent.class).
                    overwritePosition(new Point2D(FIFTY, FIFTY));
            player.setZIndex(Integer.MAX_VALUE);
        }
        FXGL.set("levelTime", 0.0);
        Level level = FXGL.setLevelFromMap("tmx/level" + levelNum + ".tmx");
        FXGL.getAudioPlayer().stopAllMusic();
        FXGL.loopBGM("level" + levelNum + ".wav");
        boolean bgExists = level.getProperties().exists("bgImageName");
        String bgImageName;
        if (bgExists) {
             bgImageName = level.getProperties().getString("bgImageName");
        } else {
            bgImageName = "forest.png";
        }
        FXGL.spawn("background", new SpawnData().put("bgImageName", bgImageName));

        Double shortTime = level.getProperties().getDouble("star1time");
        LevelEndScene.LevelTimeData timeData =
                new LevelEndScene.LevelTimeData(shortTime
                        * TWO_POINT_FOUR, shortTime * ONE_POINT_THREE, shortTime);
        FXGL.set("levelTimeData", timeData);
    }

    /**
     * Tracks whether the Player has gone off the map.
     * @param tpf Player's current position as a double
     */
    @Override
    protected void onUpdate(final double tpf) {
        FXGL.inc("levelTime", tpf);
        if (player.getY() > FXGL.getAppHeight()) {
            if (player.getY() > FXGL.getAppHeight()) {
                FXGL.play("dead.wav");
                playerDead();
            }
        }
    }

    /**
     * Initializes the entire game.
     */
    @Override
    protected void initGame() {
        FXGL.getGameWorld().addEntityFactory(new EntityFactory());
        player = null;
        nextLevel();
        player = FXGL.spawn("player", FIFTY, FIFTY);
        FXGL.set("player", player);

        Viewport viewport = FXGL.getGameScene().getViewport();
        viewport.setBounds(FLOOR, 0, TWO_HUNDRED_FIFTY * SEVENTY, FXGL.getAppHeight());
        viewport.bindToEntity(player, FXGL.getAppWidth() / 2.0, FXGL.getAppHeight() / 2.0);
        viewport.setLazy(true);
    }

    /**
     * Drives the program.
     * @param args instance of current game
     */
    public static void main(final String[] args) {
        launch(args);
    }

}
