package ca.bcit.comp2522.termproject.planetbandit;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Represents a spaceship object.
 *
 * @author Prab and Benny
 * @version 2022
 */
public class Spaceship extends SpaceObjects {
        private boolean dead = false;
        private final String type;
        private final int moveSpeed = 10;

    /**
     * Constructs a spaceship object.
     *
     * @param xCoordinate an int that represents the spaceship's x-coordinate
     * @param yCoordinate an int that represents the spaceship's y-coordinate
     * @param width an int that represents the spaceships width
     * @param height an int that represents the spaceships height
     * @param type a String that represents the type of space object this is
     * @param color a Color that represents the colour of the spaceship
     */
    Spaceship(final int xCoordinate, final int yCoordinate, final int width, final int height,
              final String type, final Color color) {
            super(xCoordinate,yCoordinate, width, height,
        type, color);
            this.type = type;
            setTranslateX(xCoordinate);
            setTranslateY(yCoordinate);
        }

    /**
     * Move the spaceships left.
     */
    public void moveLeft() {
        setTranslateX(getTranslateX() - moveSpeed);
    }

    /**
     * Move the spaceships right.
     */
    public void moveRight() {
        setTranslateX(getTranslateX() + moveSpeed);
    }

    /**
     * Move the spaceship up.
     */
    public void moveUp() {
        setTranslateY(getTranslateY() - moveSpeed);
    }

    /**
     * Move the spaceship down.
     */
//    public void moveDown() {
//        setTranslateY(getTranslateY() + moveSpeed);
//    }

    /**
     * Determines if spaceship collides with the walls.
     *
     * @return a boolean value
     */
    public boolean collideWithWalls() {
        final double startingPoint = 0.0;
        final double screenWidth = 500.0;
        final double screenHeight = 750.0;
        /* If spaceship reaches top of the screen*/
        if (this.getBoundsInParent().intersects(startingPoint, startingPoint, screenWidth, startingPoint)) {
            return true;
        }

        /* If spaceship reaches bottom of the screen*/
        if (this.getBoundsInParent().intersects(startingPoint, screenHeight, screenWidth, startingPoint)) {
            return true;
        }

        /* If spaceship reaches top of the screen*/
        if (this.getBoundsInParent().intersects(startingPoint, startingPoint, startingPoint, screenHeight)) {
            return true;
        }

        /* If spaceship reaches bottom of the screen*/
        if (this.getBoundsInParent().intersects(screenWidth, startingPoint, startingPoint, screenHeight)) {
            return true;
        }
        return false;
    }

    public void shoot(Pane root) {
        if (this.isDead() != true) {
            Spaceship s = new Spaceship((int) this.getTranslateX() + 20, (int) this.getTranslateY(), 5, 20, this.getType() + "bullet", Color.BLACK);
            root.getChildren().add(s);
        }
    }

    /**
     * Determine if the spaceship has died.
     *
     * @return a boolean value
     */
//    public boolean isDead() {
//        return dead;
//    }

    /**
     * Sets the living status of the spaceship.
     *
     * @param dead a boolean value that represents the status of the spaceship
     */
//    public void setDead(final boolean dead) {
//        this.dead = dead;
//    }

    /**
     * Returns the type of this space object.
     *
     * @return a String that represents the type of this space object
     */
//    public String getType() {
//        return type;
//    }

    /**
     * Determines if a spaceship and another object are equal.
     *
     * @param o an Object
     * @return a boolean value
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Spaceship spaceship = (Spaceship) o;
        return isDead() == spaceship.isDead() && getType().equals(spaceship.getType());
    }

//    /**
//     * Returns a unique value based on the state of the spaceship object.
//     *
//     * @return an int that represents the state of the spaceship object.
//     */
//    @Override
//    public int hashCode() {
//        final int seventeen = 17;
//        final int thirtySeven = 37;
//        final int isDeadValue;
//
//        if (dead) {
//            isDeadValue = 1;
//        } else {
//            isDeadValue = 0;
//        }
//        int result = seventeen;
//        result = thirtySeven * result + type.hashCode();
//        result = thirtySeven * result + isDeadValue;
//        result = thirtySeven * result + moveSpeed;
//        return result;
//    }

    /**
     * Returns a string representation of the spaceship object.
     *
     * @return a string that represents a spaceship object
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Spaceship{");
        sb.append("dead=").append(dead);
        sb.append(", type='").append(type).append('\'');
        sb.append(", moveSpeed=").append(moveSpeed);
        sb.append('}');
        return sb.toString();
    }
}
