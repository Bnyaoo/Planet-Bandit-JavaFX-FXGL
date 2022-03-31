package ca.bcit.comp2522.termproject.planetbandit;

import javafx.animation.FadeTransition;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * Represents a splash screen.
 *
 * @author Prab and Benny
 * @version 2022
 */
public class SplashScreen {
    private Pane splashLayout;
    private ImageView splash;

    /**
     * Constructs a splash screen object.
     *
     * @param stage            a Stage object
     * @param splashImage      an image object that will be used for the splash screen
     * @param showGameMethod   a InitCompletionHandler object
     */
    public SplashScreen(final Stage stage, final Image splashImage,
                        final InitCompletionHandler showGameMethod) {
        this.splash = new ImageView(splashImage);
        this.splash.setFitWidth(1280);
        this.splashLayout = new StackPane();
        this.splashLayout.getChildren().addAll(splash);
        this.splashLayout.setBackground(Background.fill(Color.BLACK));
        this.splashLayout.setEffect(new DropShadow());
        this.initSplash(stage, showGameMethod);
    }

    private void initSplash(final Stage primaryStage,
                            final InitCompletionHandler showGameMethod) {
        final Scene splashScene = new Scene(splashLayout, Color.BLACK);
        final FadeTransition fade = new FadeTransition(Duration.millis(9000), splashLayout);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();
        fade.setOnFinished(e -> {
            final Task<Void> task = new Task<>() {
                @Override
                protected Void call() {
                    return null;
                }
            };
            new Thread(task).start();
            showSplashScreen(primaryStage, task, showGameMethod);
        });
        primaryStage.setScene(splashScene);
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.setAlwaysOnTop(true);
        primaryStage.show();

    }

    private void showSplashScreen(final Stage initStage, final Task<?> task,
                                  final InitCompletionHandler initCompletionHandler) {

        task.stateProperty().addListener((observableValue, oldState, newState) -> {
            if (newState == Worker.State.SUCCEEDED) {
                initStage.toFront();
                FadeTransition fadeSplash = new FadeTransition(Duration.seconds(1.2), splashLayout);
                fadeSplash.setFromValue(1.0);
                fadeSplash.setToValue(0.0);
                fadeSplash.setOnFinished(actionEvent -> initStage.hide());
                fadeSplash.play();
                initCompletionHandler.complete();
                fadeSplash.setOnFinished(e -> {
                    splashLayout = null;
                    splash = null;
                    initStage.close();
                });
            }
        });
    }

    /**
     * Handles the stage once the splash screen is finished.
     */
    public interface InitCompletionHandler {
        /**
         * Completes the splash screen and displays the next stage.
         */
        void complete();
    }

}





