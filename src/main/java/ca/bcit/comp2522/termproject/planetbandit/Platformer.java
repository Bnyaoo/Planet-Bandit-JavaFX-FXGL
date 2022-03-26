package ca.bcit.comp2522.termproject.planetbandit;

import javafx.animation.Animation;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Platformer extends Application {

    @Override
    public void start(Stage thirdStage) throws Exception{
        AnchorPane anchorPane = new AnchorPane();

        GameEntities gameEntities = new GameEntities(anchorPane);

        Scene scene = new Scene(anchorPane, 600, 400);

        Controller controller = new Controller(scene);
        GameLoop gameLoop = new GameLoop(anchorPane, controller, gameEntities);
        gameLoop.start();

        thirdStage.setScene(scene);
        thirdStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
