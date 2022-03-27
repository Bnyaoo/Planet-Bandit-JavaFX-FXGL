package ca.bcit.comp2522.termproject.planetbandit;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Random;

public class SplashTest {

    private int itemsToLoad = 10000;
    private TilePane root;
    private Stage mainWindow;
    private Scene scene;
    private Image splash_image = new Image("welcomeScreen.png");



    public void start(Stage stage) throws Exception {
        new SplashScreen(stage, splash_image, () -> initializeGame(), () -> showGame());
    }

    private void initializeGame() {
        Circle[] circles = new Circle[itemsToLoad];
        root = new TilePane();
        for (int i = 0; i < circles.length; i++) {
            circles[i] = new Circle(5, randomColor());
        }
        root.getChildren().addAll(circles);
    }

    private void showGame() {
        scene = new Scene(root, 900, 600, Color.BLACK);
//        mainWindow = new Stage();
//        mainWindow.setMaxHeight(splash_image.getHeight());
//        mainWindow.setMaxWidth(splash_image.getWidth());
//        mainWindow.setScene(scene);
//        mainWindow.initStyle(StageStyle.DECORATED);
//        mainWindow.show();
        Space space = new Space();
        GamePlay gamePlay = new GamePlay();
        space.start(gamePlay.getStage());
    }

    private Color randomColor() {
        return Color.rgb(0, getRandomColor(0, 125), getRandomColor(180, 255));
    }

    public int getRandomColor(int minValue, int maxValue) {
        Random rand = new Random();
        int color = rand.nextInt(maxValue + 1 - minValue) + minValue;
        return color;
    }
}
