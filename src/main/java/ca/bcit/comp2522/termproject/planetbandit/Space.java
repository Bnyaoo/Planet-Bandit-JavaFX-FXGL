package ca.bcit.comp2522.termproject.planetbandit;

//import ca.bcit.comp2522.termproject.planetbandit.Entities.Spaceship;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
        import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.util.Random;

/**
 * Represents a spaceship object.
 *
 * @author Prab and Benny
 * @version 2022
 */
public class Space {

    // Contains the image of the spaceship
    private ImageView backgroundImageView;

    private static String backgroundImgName = "space.jpg";

    public static final int appWidth = 1280;
    public static final int appHeight = 720;

    private Group root;

    private Stage stage;


    /**
     * Displays an image that can be moved using the arrow keys.
     *
     * @param primaryStage a Stage
     */
    public void start(Stage primaryStage) {
        this.stage = primaryStage;
        Spaceship spaceship1 = new Spaceship();
        Coin coin = new Coin(70, 200);
//        Image player = new Image(spaceship.getImageName(), true);
//        playerImageView = new ImageView(player);
//        playerImageView.setFitHeight(100);
//        playerImageView.setFitWidth(60);
//
//        playerImageView.setX(spaceship.getxCoordinate());
//        playerImageView.setY(spaceship.getyCoordinate());


        Image background = new Image(backgroundImgName, true);
        backgroundImageView = new ImageView(background);
        backgroundImageView.setFitHeight(appHeight);
        backgroundImageView.setFitWidth(appWidth);

//        Font font1 = new Font("Courier", 50);
//        final int dreamX = 30;
//        final int dreamY = 300;
//        Text text1 = new Text(dreamX, dreamY, "Dream Big");
//        text1.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 100));
//        text1.setFill(Color.RED);

        root = new Group(backgroundImageView, coin.imageView, spaceship1.playerImageView);
//        meteorite.addCoins(root);


        Scene scene = new Scene(root, appWidth, appHeight, Color.BLACK);


        // Register the key listener here
        scene.setOnKeyPressed(event -> {
            try {
                spaceship1.processKeyPress(event, coin);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        stage.setTitle("Planet Bandit");
        stage.setScene(scene);
        stage.show();


    }


    //    /**
//     * Modifies the position of the image view when an arrow key is pressed.
//     *
//     * @param event invoked this method
//     */
//    public void processKeyPress(KeyEvent event) {
//        switch (event.getCode()) {
//            case UP:
//                playerImageView.setY(playerImageView.getY() - MOVE_SPEED);
//                break;
//            case DOWN:
//                playerImageView.setY(playerImageView.getY() + MOVE_SPEED);
//                break;
//            case LEFT:
//                playerImageView.setX(playerImageView.getX() - MOVE_SPEED);
//                break;
//            case RIGHT:
//                playerImageView.setX(playerImageView.getX() + MOVE_SPEED);
//                break;
//            default:
//                break; // Does nothing if it's not an arrow key
//        }
//    }

    public class Spaceship {
        private static int health = 3;
        private int xCoordinate;
        private int yCoordinate;
        private final static String image = "spaceship.png";
        private boolean alive;
        public static final int MOVE_SPEED = 25;
        private ImageView playerImageView;
        Image player = new Image(this.getImageName(), true);
        private int coinsCollected = 0;
        boolean gameOver = false;

        public Spaceship() {
            this.xCoordinate = 270;
            this.yCoordinate = 450;
            this.alive = true;
            playerImageView = new ImageView(player);
            playerImageView.setFitHeight(100);
            playerImageView.setFitWidth(60);

            playerImageView.setX(this.getxCoordinate());
            playerImageView.setY(this.getyCoordinate());

        }

        public static int getHealth() {
            return health;
        }

        public static String getImageName() {
            return image;
        }

        public int getxCoordinate() {
            return xCoordinate;
        }

        public int getyCoordinate() {
            return yCoordinate;
        }

        public void setxCoordinate(int xCoordinate) {
            this.xCoordinate = xCoordinate;
        }

        public void setyCoordinate(int yCoordinate) {
            this.yCoordinate = yCoordinate;
        }

        public boolean isAlive() {
            return alive;
        }

        public void setAlive(boolean alive) {
            this.alive = alive;
        }

//        public static void setHealth(int health) {
//            ca.bcit.comp2522.termproject.planetbandit.Entities.Spaceship.health = health;
//        }

        public ImageView getPlayerImageView() {
            return playerImageView;
        }

        public boolean checkIfInBounds() {
            if (this.getxCoordinate() <= 0 || this.getxCoordinate() >= Space.appWidth - 60
                    || this.getyCoordinate() <= 0 || this.getyCoordinate() >= Space.appHeight - 100) {
                return false;
            }
            return true;
        }

        public boolean collides(Coin coin) {
            if (playerImageView.getBoundsInParent().intersects(coin.imageView.getBoundsInParent())) {
                return true;
            }
            return false;
        }

        /**
         * Modifies the position of the image view when an arrow key is pressed.
         *
         * @param event invoked this method
         */
        public void processKeyPress(KeyEvent event, Coin coin) throws InterruptedException {

            if (checkIfInBounds() && isAlive()) {
                if (collides(coin)) {
                    coinsCollected++;
                    coin.addCoins(this);
                    System.out.println("collided!");
                }
                switch (event.getCode()) {
                    case W:
                        this.setyCoordinate(this.getyCoordinate() - MOVE_SPEED);
                        playerImageView.setY(playerImageView.getY() - MOVE_SPEED);
//                        if (collides(coin)) {
//                            System.out.println("collided!");
//                            coinsCollected++;
//                            coin.addCoins(this);
//
//                        }
                        break;
                    case S:
                        this.setyCoordinate(this.getyCoordinate() + MOVE_SPEED);
                        playerImageView.setY(playerImageView.getY() + MOVE_SPEED);
//                        if (collides(coin)) {
//                            System.out.println("collided!");
//                            coinsCollected++;
//                            coin.addCoins(this);
//                        }
                        break;
                    case A:
                        this.setxCoordinate(this.getxCoordinate() - MOVE_SPEED);
                        playerImageView.setX(playerImageView.getX() - MOVE_SPEED);
                        if (collides(coin)) {
                            System.out.println("collided!");
                            coin.addCoins(this);
                        }
                        break;
                    case D:
                        this.setxCoordinate(this.getxCoordinate() + MOVE_SPEED);
                        playerImageView.setX(playerImageView.getX() + MOVE_SPEED);
//                        if (collides(coin)) {
//                            System.out.println("collided!");
//                            coinsCollected++;
//                            coin.addCoins(this);
//                        }
                        break;
                    default:
                        break; // Does nothing if it's not an arrow key
                }
            } else {
                if (gameOver == false) {
                    this.setAlive(false);
                    changeScreen();
                }
                gameOver = true;
            }
        }

        public void changeScreen() {
            System.out.println("Total coins collected: " + coinsCollected);
            final int dreamX = 375;
            final int dreamY = 350;
            Text text1 = new Text(dreamX, dreamY, "GAME OVER");
            text1.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 100));
            text1.setFill(Color.RED);
            root.getChildren().add(text1);
        }

    }


    public class Coin {
        private int xCoordinate = 60;
        private int yCoordinate;
        int velocity = 10;
        boolean visible;
        private final static String image = "coin.gif";
        private boolean alive;
        private ImageView imageView;
        Image meteorite = new Image(image, true);
        Random random = new Random();

        public Coin(int x, int y) {
            this.visible = true;
            this.xCoordinate = x;
            imageView = new ImageView(meteorite);
            imageView.setFitHeight(80);
            imageView.setFitWidth(70);

            imageView.setX(x);
            imageView.setY(y);

        }

        public int getxCoordinate() {
            return xCoordinate;
        }

        public int getyCoordinate() {
            return yCoordinate;
        }

        public void setxCoordinate(int xCoordinate) {
            this.xCoordinate = xCoordinate;
        }

        public void setyCoordinate(int yCoordinate) {
            this.yCoordinate = yCoordinate;
        }


        public void addCoins(Spaceship spaceship) {
            int x = random.nextInt(70, 1210);
            int y = random.nextInt(80, 640);
            Coin coin = new Coin(x, y);
            root = new Group(backgroundImageView, coin.imageView, spaceship.playerImageView);
            Scene scene = new Scene(root, appWidth, appHeight, Color.BLACK);

            // Register the key listener here
            scene.setOnKeyPressed(event -> {
                try {
                    spaceship.processKeyPress(event, coin);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            stage.setTitle("Planet Bandit");
            stage.setScene(scene);
            stage.show();
        }

    }

}
