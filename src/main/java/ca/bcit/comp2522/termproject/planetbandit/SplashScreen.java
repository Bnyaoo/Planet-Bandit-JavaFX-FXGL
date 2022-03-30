package ca.bcit.comp2522.termproject.planetbandit;

/*
 * JavaFX Splash Screen utility
 *
 * The author of this software "Eudy Contreras" grants you ("Licensee")
 * a non-exclusive, royalty free, license to use,modify and redistribute this
 * software in source and binary code form.
 *
 * Please be aware that this software is simply part of a personal test
 * and may in fact be unstable. The software in its current state is not
 * considered a finished product and has plenty of room for improvement and
 * changes due to the range of different approaches which can be used to
 * achieved the desired result of the software.
 *
 */


import javafx.animation.FadeTransition;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;


import java.util.Random;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * This class represents a splash screen which is to be shown while the game is loading!
 * one the game has loaded the splash screen will fade out!
 * @author Eudy Contreras
 *
 */
public class SplashScreen{

    private FadeTransition fadeSplash;

    private Pane splashLayout;

    private ImageView splash;

    private int splashWidth;
    private int splashHeight;

    private Image splash_image;

    public SplashScreen(Stage stage, Image splash_Image, Runnable initializeMethod, InitCompletionHandler showGameMethod) {
        this.splash_image = splash_Image;
        this.splash = new ImageView(splash_image);
//        this.splashWidth = (int) splash.getImage().getWidth();
//        this.splashHeight = (int) splash.getImage().getHeight();
        this.splashWidth = 900;
        this.splashHeight = 600;
        this.splashLayout = new StackPane();
        this.splashLayout.getChildren().addAll(splash);
        this.splashLayout.setBackground(Background.fill(Color.BLACK));
        this.splashLayout.setEffect(new DropShadow());
        this.initSplash(stage,initializeMethod,showGameMethod);
    }
    private void initSplash(Stage primaryStage, Runnable initializeMethod, InitCompletionHandler showGameMethod) {
        final Scene splashScene = new Scene(splashLayout, Color.BLACK);
        final Rectangle2D bounds = Screen.getPrimary().getBounds();
        final FadeTransition fade = new FadeTransition(Duration.millis(1000),splashLayout);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();
        fade.setOnFinished( e -> {
            final Task<Void> task = new Task<Void>() {
                @Override
                protected Void call() throws InterruptedException {
                    if (initializeMethod!=null) {
                        initializeMethod.run();
                    }
                    return null;
                }
            };
            new Thread(task).start();
            showSplashScreen(primaryStage, task,showGameMethod);
        });
        primaryStage.setScene(splashScene);
//        primaryStage.setX(bounds.getMinX() + bounds.getWidth() / 2 - splashWidth / 2);
//        primaryStage.setY(bounds.getMinY() + bounds.getHeight() / 2 - splashHeight / 2);

        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.setAlwaysOnTop(true);
        primaryStage.show();

    }

    private void showSplashScreen(final Stage initStage, Task<?> task, InitCompletionHandler initCompletionHandler) {
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

    public interface InitCompletionHandler {
        void complete();
    }

}





