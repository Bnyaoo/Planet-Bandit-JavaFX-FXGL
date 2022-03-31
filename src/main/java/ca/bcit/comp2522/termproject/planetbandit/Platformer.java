package ca.bcit.comp2522.termproject.planetbandit;

import com.almasb.fxgl.animation.Interpolators;
import com.almasb.fxgl.app.ApplicationMode;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.GameView;
import com.almasb.fxgl.app.scene.SceneFactory;
import com.almasb.fxgl.app.scene.Viewport;
import com.almasb.fxgl.core.util.LazyValue;
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

import java.util.Map;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameScene;
import static com.almasb.fxgl.dsl.FXGLForKtKt.showMessage;

public class Platformer extends GameApplication {

    private static final int MAX_LEVEL = 2;
    private static final int STARTING_LEVEL = 0;

    private LazyValue<LevelEndScene> levelEndSceneValue =
            new LazyValue<>(LevelEndScene::new);

    public Entity player;
    private Riddles riddles = new Riddles();

    @Override
    protected void initSettings(GameSettings settings) {

        settings.setWidth(1280);
        settings.setHeight(720);
        settings.setSceneFactory(new SceneFactory() {
            @Override
            public com.almasb.fxgl.app.scene.LoadingScene newLoadingScene() {
                return new LoadingScene();
            }
        });

        settings.setApplicationMode(ApplicationMode.DEVELOPER);
    }

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
                        .filter(btn -> btn.hasComponent(CollidableComponent.class) && player.isColliding(btn))
                        .forEach(btn -> {
                            btn.removeComponent(CollidableComponent.class);
                            Entity keyEntity = btn.getObject("keyEntity");
                            keyEntity.setProperty("activated", true);

                            KeyView view = (KeyView) keyEntity.getViewComponent().getChildren().get(0);
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

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("level", STARTING_LEVEL);
        vars.put("levelTime", 0.0);
        vars.put("score", 0);
        vars.put("lives", 5);
    }

    @Override
    protected void onPreInit() {
        FXGL.getSettings().setGlobalMusicVolume(0.25);
        FXGL.loopBGM("BGM_dash_runner.wav");
    }

    @Override
    protected void initPhysics() {
        FXGL.getPhysicsWorld().setGravity(0, 760);
        FXGL.getPhysicsWorld().addCollisionHandler(new PlayerButtonHandler());

        FXGL.onCollisionBegin(EntityType.PLAYER, EntityType.COIN, (player, coin) -> {
            FXGL.getGameWorld().spawn("collideCoin", coin.getX() - 60, coin.getY() - 60);
            FXGL.play("coin.wav");
            coin.removeFromWorld();
        });

        FXGL.onCollisionOneTimeOnly(EntityType.PLAYER, EntityType.EXIT_SIGN, (player, sign) -> {
            Texture texture = FXGL.texture("exit_sign.png").brighter();
            texture.setTranslateX(sign.getX() + 9);
            texture.setTranslateY(sign.getY() + 13);

            GameView gameView = new GameView(texture, 150);
            FXGL.getGameScene().addGameView(gameView);
            FXGL.runOnce(() -> {
                FXGL.getGameScene().removeGameView(gameView);
            }, Duration.seconds(1.6));
        });

        FXGL.onCollisionOneTimeOnly(EntityType.PLAYER, EntityType.EXIT_TRIGGER, (player, trigger) -> {
            makeExitDoor();
        });

        FXGL.onCollisionBegin(EntityType.PLAYER, EntityType.DOOR_BOT, (player, doorBot) -> {
            System.out.println("Level Completed");
            getGameScene().getViewport().fade(() -> {
                FXGL.showMessage(riddles.getRiddle(), () -> {
                    FXGL.showConfirm("Move on to the next planet?", result -> {
                        if (result) {
                            nextLevel();
                        } else {
                            FXGL.getGameController().exit();
                        }
                    });
                });
            });
        });

        FXGL.onCollisionBegin(EntityType.PLAYER, EntityType.KEY_PROMPT, (player, keyPrompt) -> {
            String key = keyPrompt.getString("key");

            Entity entity = FXGL.getGameWorld().create("keyCode", new SpawnData(keyPrompt.getX(), keyPrompt.getY()).put("key", key));
            FXGL.spawnWithScale(entity, Duration.seconds(1), Interpolators.ELASTIC.EASE_OUT());

            FXGL.runOnce(() -> {
                if (entity.isActive()) {
                    FXGL.despawnWithScale(entity, Duration.seconds(1), Interpolators.ELASTIC.EASE_IN());
                }
            }, Duration.seconds(2.5));
        });

        FXGL.onCollisionBegin(EntityType.PLAYER, EntityType.SPIKE, (player, spikes) -> {
            playerDead();
        });


        FXGL.onCollision(EntityType.PLAYER, EntityType.LASER, (player, laser)-> {
            playerDead();
        });




    }

    private void playerDead() {
        if (FXGL.geti("lives") > 1) {
            FXGL.inc("lives", -1);
            setLevel(FXGL.geti("level"));
        } else {
            FXGL.showMessage("You have died", () -> {
                FXGL.showConfirm("Would you like to continue?", result -> {
                    if (result) {
                        FXGL.getGameController().startNewGame();
                    } else {
                        FXGL.getGameController().exit();
                    }
                });
            });
        }
    }

    @Override
    protected void initUI() {
        Text text = FXGL.addText("", 50, 50);
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

    private void setLevel(int levelNum) {
        if (player != null) {
            player.getComponent(PhysicsComponent.class).overwritePosition(new Point2D(50, 50));
            player.setZIndex(Integer.MAX_VALUE);
        }
        FXGL.set("levelTime", 0.0);
        Level level = FXGL.setLevelFromMap("tmx/level" + levelNum + ".tmx");
        boolean bgExists = level.getProperties().exists("bgImageName");
        String bgImageName;
        if (bgExists) {
             bgImageName = level.getProperties().getString("bgImageName");
        }else {
            bgImageName = "forest.png";
        }
        FXGL.spawn("background", new SpawnData().put("bgImageName", bgImageName));

        Double shortTime = level.getProperties().getDouble("star1time");
        LevelEndScene.LevelTimeData timeData = new LevelEndScene.LevelTimeData(shortTime * 2.4, shortTime * 1.3, shortTime);
        FXGL.set("levelTimeData", timeData);
    }

    @Override
    protected void onUpdate(double tpf) {
        FXGL.inc("levelTime", tpf);
        if (player.getY() > FXGL.getAppHeight()) {
            if (player.getY() > FXGL.getAppHeight()) {
                playerDead();
            }
        }
    }

    @Override
    protected void initGame() {
        FXGL.getGameWorld().addEntityFactory(new EntityFactory());
        player = null;
        nextLevel();
        player = FXGL.spawn("player", 50, 50);
        FXGL.set("player", player);

        Viewport viewport = FXGL.getGameScene().getViewport();
        viewport.setBounds(-1500, 0, 250 * 70, FXGL.getAppHeight());
        viewport.bindToEntity(player, FXGL.getAppWidth() / 2.0, FXGL.getAppHeight() / 2.0);
        viewport.setLazy(true);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
