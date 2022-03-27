package ca.bcit.comp2522.termproject.planetbandit.Entities;

import ca.bcit.comp2522.termproject.planetbandit.Space;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;


//public class Spaceship {
//    private static int health = 3;
//    private int xCoordinate;
//    private int yCoordinate;
//    private final static String image = "spaceship.png";
//    private boolean alive;
//    public static final int MOVE_SPEED = 15;
//    private ImageView playerImageView;
//    Image player = new Image(this.getImageName(), true);
//
//    public Spaceship() {
//        this.xCoordinate = 270;
//        this.yCoordinate = 450;
//        this.alive = true;
//        playerImageView = new ImageView(player);
//        playerImageView.setFitHeight(100);
//        playerImageView.setFitWidth(60);
//
//        playerImageView.setX(this.getxCoordinate());
//        playerImageView.setY(this.getyCoordinate());
//    }
//
//    public static int getHealth() {
//        return health;
//    }
//
//    public static String getImageName() {
//        return image;
//    }
//
//    public int getxCoordinate() {
//        return xCoordinate;
//    }
//
//    public int getyCoordinate() {
//        return yCoordinate;
//    }
//
//    public void setxCoordinate(int xCoordinate) {
//        this.xCoordinate = xCoordinate;
//    }
//
//    public void setyCoordinate(int yCoordinate) {
//        this.yCoordinate = yCoordinate;
//    }
//
//    public boolean isAlive() {
//        return alive;
//    }
//
//    public void setAlive(boolean alive) {
//        this.alive = alive;
//    }
//
//    public static void setHealth(int health) {
//        Spaceship.health = health;
//    }
//
//    public ImageView getPlayerImageView() {
//        return playerImageView;
//    }
//
//    public boolean checkIfInBounds() {
//        if (this.getxCoordinate() <= 0 || this.getxCoordinate() >= Space.appWidth - 60
//                || this.getyCoordinate() <= 0 || this.getyCoordinate() >= Space.appHeight - 100) {
//            return false;
//        }
//        return true;
//    }
//
//    /**
//     * Modifies the position of the image view when an arrow key is pressed.
//     *
//     * @param event invoked this method
//     */
//    public void processKeyPress(KeyEvent event) {
//        if (checkIfInBounds()) {
//            switch (event.getCode()) {
//                case UP:
//                    this.setyCoordinate(this.getyCoordinate() - MOVE_SPEED);
//                    playerImageView.setY(playerImageView.getY() - MOVE_SPEED);
//                    break;
//                case DOWN:
//                    this.setyCoordinate(this.getyCoordinate() + MOVE_SPEED);
//                    playerImageView.setY(playerImageView.getY() + MOVE_SPEED);
//                    break;
//                case LEFT:
//                    this.setxCoordinate(this.getxCoordinate() - MOVE_SPEED);
//                    playerImageView.setX(playerImageView.getX() - MOVE_SPEED);
//                    break;
//                case RIGHT:
//                    this.setxCoordinate(this.getxCoordinate() + MOVE_SPEED);
//                    playerImageView.setX(playerImageView.getX() + MOVE_SPEED);
//                    break;
//                default:
//                    break; // Does nothing if it's not an arrow key
//            }
//        } else {
//            this.setAlive(false);
//            System.out.println("dead");
//        }
//
//    }
//}
