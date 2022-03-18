package ca.bcit.comp2522.termproject.planetbandit;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Space extends Application {
    private Pane root = new Pane();
    private double time = 0;
    private Spaceship spaceship = new Spaceship(250, 600, 40, 40, "spaceship", Color.BLUE);

    private Parent createContent() {

        /* Sets the frame size*/
        root.setPrefSize(500, 750);
        /*Adds the spaceship to the screen*/
        root.getChildren().add(spaceship);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }
        };

        timer.start();
        addMeteorite();
        return root;
    }

    private void addMeteorite() {
        Random number = new Random();
        for (int i = 0; i < 3; i++) {
            int random_x = number.nextInt(50, 450);
            Spaceship s = new Spaceship(random_x, 100, 30, 30, "enemy", Color.RED);
            root.getChildren().add(s);
        }
    }

    private List<Spaceship> spaceObjects() {
        return root.getChildren().stream().map(n -> (Spaceship)n).collect(Collectors.toList());
    }

    private void update() {
        time += 0.016;

        spaceObjects().forEach(object -> {
            switch (object.getType()) {

                case "enemybullet":
                    object.moveDown();

                    if (object.getBoundsInParent().intersects(spaceship.getBoundsInParent())) {
                        spaceship.setDead(true);
                        object.setDead(true);
                    }
                    break;

                case "playerbullet":
                    object.moveUp();

                    spaceObjects().stream().filter(e -> e.getType().equals("enemy")).forEach(enemy -> {
                        if (object.getBoundsInParent().intersects(enemy.getBoundsInParent())) {
                            enemy.setDead(true);
                            object.setDead(true);
                        }
                    });

                    break;

                case "enemy":
                    object.moveDown();
                    if (time > 2) {
                        if (Math.random() < 0.3) {
                            shoot(object);
                        }
                    }

                    if (object.getBoundsInParent().intersects(spaceship.getBoundsInParent())) {
                        spaceship.setDead(true);
                        object.setDead(true);
                    }

                    if (object.getBoundsInParent().intersects(0.0, 750.0, 500.0, 30.0)) {
                        object.setDead(true);
                    }
                    break;

                case "spaceship":
                    if (spaceship.collideWithWalls()) {
                        spaceship.setDead(true);
                        System.out.println("Dead");
                    }
            }
        });

        root.getChildren().removeIf(n -> {
            Spaceship s = (Spaceship) n;
            return s.isDead();
        });

        if (time > 2) {
            time = 0;
        }
    }

    private void shoot(Spaceship who) {
        if (spaceship.isDead() != true) {
            Spaceship s = new Spaceship((int) who.getTranslateX() + 20, (int) who.getTranslateY(), 5, 20, who.getType() + "bullet", Color.BLACK);
            root.getChildren().add(s);
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(createContent());

        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case LEFT:
                    spaceship.moveLeft();
                    break;
                case RIGHT:
                    spaceship.moveRight();
                    break;
                case UP:
                    spaceship.moveUp();
                    break;
                case DOWN:
                    spaceship.moveDown();
                    break;
                case SPACE:
                    shoot(spaceship);
                    break;
            }
        });

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
