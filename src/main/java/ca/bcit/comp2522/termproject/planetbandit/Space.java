package ca.bcit.comp2522.termproject.planetbandit;

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
 * Represents a space object.
 *
 * @author Prab and Benny
 * @version 2022
 */
public class Space {
    /* background image file name */
    private static final String BACKGROUND_IMG_NAME = "space.jpg";

    // Frame sizes
    private static final int APP_WIDTH = 1280;
    private static final int APP_HEIGHT = 720;

    // Contains the image of the background
    private ImageView backgroundImageView;

    // Objects needed in this class
    private final Spaceship spaceship;
    private final Coin coin;


    private Group root;
    private Stage stage;
    private Text text1;


    /**
     * Constructs a space object.
     */
    public Space() {
        this.spaceship = new Spaceship();
        // Starting coordinates for coin
        int coinXcoordinate = 70;
        int coinYcoordinate = 200;
        this.coin = new Coin(coinXcoordinate, coinYcoordinate);
        int coinsCollected = 0;
        final int textx = 1100;
        final int texty = 50;
        this.text1 = new Text(textx, texty, "Coins:" + coinsCollected);
    }


    /**
     * Initializes the space mini-game.
     *
     * @param primaryStage a Stage
     */
    public void start(final Stage primaryStage) {
        this.stage = primaryStage;

        Image background = new Image(BACKGROUND_IMG_NAME, true);
        backgroundImageView = new ImageView(background);
        backgroundImageView.setFitHeight(APP_HEIGHT);
        backgroundImageView.setFitWidth(APP_WIDTH);

        /* Coins collected Text*/
        text1.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 30));
        text1.setFill(Color.YELLOW);


        // root contains all elements of the game
        root = new Group(backgroundImageView, coin.imageView, spaceship.playerImageView, text1);
        // scene is the frame we see
        Scene scene = new Scene(root, APP_WIDTH, APP_HEIGHT, Color.BLACK);

        // Register the key listener here
        scene.setOnKeyPressed(event -> spaceship.processKeyPress(event, coin));
        stage.setTitle("Planet Bandit");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Represents a spaceship object.
     *
     * @author Prab and Benny
     * @version 2022
     */
    public class Spaceship {
        private int health = 3;
        private int xCoordinate;
        private int yCoordinate;
        private boolean alive;
        private final ImageView playerImageView;
        private int coinsCollected = 0;
        private boolean gameOver = false;
        private boolean gameWon = false;

        /**
         * Constructs a spaceship object.
         */
        public Spaceship() {
            //starting coordinates of spaceship
            this.xCoordinate = 270;
            this.yCoordinate = 450;
            this.alive = true;
            // assigns image characteristics of spaceship
            Image spaceshipImg = new Image(getImageName(), true);
            playerImageView = new ImageView(spaceshipImg);
            playerImageView.setFitHeight(100);
            playerImageView.setFitWidth(60);
            playerImageView.setX(this.getxCoordinate());
            playerImageView.setY(this.getyCoordinate());

        }

        /**
         * Returns the spaceship health.
         *
         * @return an int that represents the spaceships health
         */
        public int getHealth() {
            return this.health;
        }

        /**
         * Sets the spaceship health.
         *
         * @param health represents the spaceships new health
         */
        public void setHealth(final int health) {
            this.health = health;
        }

        /**
         * Returns the image name.
         *
         * @return a string that represents the image name
         */
        public static String getImageName() {
            return "spaceship.png";
        }

        /**
         * Gets the spaceship's imageview.
         *
         * @return an imageview object
         */
        public ImageView getPlayerImageView() {
            return playerImageView;
        }

        /**
         * Returns the spaceships x-coordinate.
         *
         * @return the spaceships x-coordinate.
         */
        public int getxCoordinate() {
            return xCoordinate;
        }

        /**
         * Returns the spaceships y-coordinate.
         *
         * @return the spaceships y-coordinate.
         */
        public int getyCoordinate() {
            return yCoordinate;
        }

        /**
         * Sets the spaceships x-coordinate.
         *
         * @param xCoordinate the spaceships new x-coordinate
         */
        public void setxCoordinate(final int xCoordinate) {
            this.xCoordinate = xCoordinate;
        }

        /**
         * Sets the spaceships y-coordinate.
         *
         * @param yCoordinate the spaceships new y-coordinate
         */
        public void setyCoordinate(final int yCoordinate) {
            this.yCoordinate = yCoordinate;
        }

        /**
         * Determines if the spaceship is alive or not.
         *
         * @return a boolean value
         */
        public boolean isAlive() {
            return alive;
        }

        /**
         * Sets the living state of the spaceship object.
         *
         * @param alive a boolean value
         */
        public void setAlive(final boolean alive) {
            this.alive = alive;
        }

        /**
         * Returns the total number of coins collected.
         *
         * @return an int that represents the number of coins collected
         */
        public int getCoinsCollected() {
            return coinsCollected;
        }

        /**
         * Sets the number of coins collected.
         *
         * @param coinsCollected the new number of coins collected
         */
        public void setCoinsCollected(final int coinsCollected) {
            this.coinsCollected = coinsCollected;
        }

        /**
         * Determines if the game has ended.
         *
         * @return a boolean value
         */
        public boolean isGameOver() {
            return gameOver;
        }

        /**
         * Determines if the game has been beat.
         *
         * @return a boolean value
         */
        public boolean isGameWon() {
            return gameWon;
        }

        /**
         * Sets the state of the game.
         *
         * @param gameOver a boolean value
         */
        public void setGameOver(final boolean gameOver) {
            this.gameOver = gameOver;
        }

        /**
         * Sets the state of the game.
         *
         * @param gameWon a boolean value
         */
        public void setGameWon(final boolean gameWon) {
            this.gameWon = gameWon;
        }

        /**
         * Determines if the spaceship is within the frame.
         *
         * @return a boolean value
         */
        public boolean checkIfInBounds() {
            int spaceshipWidth = 60;
            int spaceshipHeight = 100;
            return this.getxCoordinate() > 0 && this.getxCoordinate() < Space.APP_WIDTH - spaceshipWidth
                    && this.getyCoordinate() > 0 && this.getyCoordinate() < Space.APP_HEIGHT - spaceshipHeight;
        }

        /**
         * Determines if a spaceship has collided with a coin.
         *
         * @param collisionCoin a Coin object
         * @return a boolean value
         */
        public boolean collidesWithCoin(final Coin collisionCoin) {
            return playerImageView.getBoundsInParent().intersects(collisionCoin.imageView.getBoundsInParent());
        }

        /**
         * Modifies the position of the image view when an arrow key is pressed.
         *
         * @param event       invoked this method
         * @param currentCoin a Coin object
         */
        public void processKeyPress(final KeyEvent event, final Coin currentCoin) {
            /* Coins collected Text*/
            if (checkIfInBounds() && isAlive() && coinsCollected < 10) {
                if (collidesWithCoin(currentCoin)) {
                    coinsCollected++;
                    currentCoin.addCoins(this);
                    final int x = 1100;
                    final int y = 50;
                    root.getChildren().remove(text1);
                    text1 = new Text(x, y, "Coins:" + coinsCollected);
                    text1.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 30));
                    text1.setFill(Color.YELLOW);

                    root.getChildren().add(text1);

                }
                int moveSpeed = 25;
                switch (event.getCode()) {
                    case W -> {
                        this.setyCoordinate(this.getyCoordinate() - moveSpeed);
                        playerImageView.setY(playerImageView.getY() - moveSpeed);
                    }
                    case S -> {
                        this.setyCoordinate(this.getyCoordinate() + moveSpeed);
                        playerImageView.setY(playerImageView.getY() + moveSpeed);
                    }
                    case A -> {
                        this.setxCoordinate(this.getxCoordinate() - moveSpeed);
                        playerImageView.setX(playerImageView.getX() - moveSpeed);
                    }
                    case D -> {
                        this.setxCoordinate(this.getxCoordinate() + moveSpeed);
                        playerImageView.setX(playerImageView.getX() + moveSpeed);
                    }
                    default -> {
                    } // Does nothing if it's not an arrow key
                }
            } else {
                if (coinsCollected >= 2) {
                    this.setAlive(false);
                    setGameWon(true);
                    setGameOver(false);
                }

                if (!checkIfInBounds()) {
                    setGameOver(true);
                    setGameWon(false);

                }
                changeScreen();

            }

        }

        /**
         * Switches the screens display something new on the screen.
         */
        public void changeScreen() {
            Text text2 = null;

            if (isGameOver()) {
                final int dreamX = 375;
                final int dreamY = 350;
                text2 = new Text(dreamX, dreamY, "GAME OVER");
                text2.setFill(Color.RED);
                text2.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 100));
            }

            if (gameWon) {
                final int x = 75;
                final int y = 350;
                text2 = new Text(x, y, "You have arrived at the next planet!");
                text2.setFill(Color.GREEN);
                text2.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 50));
            }

            root.getChildren().add(text2);
        }

    }

    /**
     * Represents a coin object.
     *
     * @author Prab and Benny
     * @version 2022
     */
    public class Coin {
        private int xCoordinate;
        private int yCoordinate;
        private final ImageView imageView;
        private final Random random = new Random();
        private final int coinHeight;
        private final int coinWidth;

        /**
         * Constructs a coin object.
         *
         * @param x an int that represents the x-coordinate
         * @param y an int that represents the y-coordinate
         */
        public Coin(final int x, final int y) {
            // sets the starting coordinates of the coin
            this.xCoordinate = x;
            this.yCoordinate = y;
            String image = "coin.gif";
            Image coinImg = new Image(image, true);
            imageView = new ImageView(coinImg);

            // Sets the size of the coin on the screen
            coinHeight = 80;
            coinWidth = 70;
            imageView.setFitHeight(coinHeight);
            imageView.setFitWidth(coinWidth);

            // Places the coin at starting coordinates
            imageView.setX(x);
            imageView.setY(y);

        }

        /**
         * Returns the coins x-coordinate.
         *
         * @return an int that represents the coins x-coordinate
         */
        public int getxCoordinate() {
            return xCoordinate;
        }

        /**
         * Returns the coins y-coordinate.
         *
         * @return an int that represents the coins y-coordinate
         */
        public int getyCoordinate() {
            return yCoordinate;
        }

        /**
         * Sets the coins x-coordinate.
         *
         * @param xCoordinate the new x-coordinate
         */
        public void setxCoordinate(final int xCoordinate) {
            this.xCoordinate = xCoordinate;
        }

        /**
         * Sets the coins y-coordinate.
         *
         * @param yCoordinate the new y-coordinate
         */
        public void setyCoordinate(final int yCoordinate) {
            this.yCoordinate = yCoordinate;
        }

        /**
         * Returns the coin's height.
         *
         * @return an int
         */
        public int getCoinHeight() {
            return coinHeight;
        }

        /**
         * Returns the coin's width.
         *
         * @return an int
         */
        public int getCoinWidth() {
            return coinWidth;
        }

        /**
         * Adds a coin to the screen.
         *
         * @param currentSpaceship a spaceship object
         */
        public void addCoins(final Spaceship currentSpaceship) {
            // generates random coordinates for the coin
            int xPosition = random.nextInt(getCoinHeight(), APP_WIDTH - getCoinWidth());
            int yPosition = random.nextInt(getCoinHeight(), APP_HEIGHT - getCoinHeight());

            // creates new coin
            Coin newCoin = new Coin(xPosition, yPosition);

            // adds coin to new group
            root = new Group(backgroundImageView, newCoin.imageView, currentSpaceship.playerImageView);
            // place new group in the scene
            Scene scene = new Scene(root, APP_WIDTH, APP_HEIGHT, Color.BLACK);

            // Register the key listener here
            scene.setOnKeyPressed(event -> currentSpaceship.processKeyPress(event, newCoin));
            stage.setTitle("Planet Bandit");
            stage.setScene(scene);
            stage.show();
        }

    }

}
