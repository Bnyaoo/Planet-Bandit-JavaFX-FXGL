package ca.bcit.comp2522.termproject.planetbandit;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public abstract class SpaceObjects extends Shape {
    private final int xCoordinate;
    private final int yCoordinate;
    private final String type;
    private boolean dead;
    private final int moveSpeed = 10;
    private Rectangle rectangle;
    private Circle circle;

    public SpaceObjects(final int xCoordinate, final int yCoordinate, final double radius,
                        final String type, final Color color) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.type = type;
        dead = false;
        circle = new Circle(xCoordinate, yCoordinate, radius);
        setTranslateX(xCoordinate);
        setTranslateY(yCoordinate);
    }

    public SpaceObjects(final int xCoordinate, final int yCoordinate, final int width, final int height,
                        final String type, final Color color) {
        rectangle = new Rectangle(width, height, color);
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.type = type;
        dead = false;
        setTranslateX(xCoordinate);
        setTranslateY(yCoordinate);
    }


    /**
     * Move the spaceship down.
     */
    public void moveDown() {
        setTranslateY(getTranslateY() + moveSpeed);
    }

    /**
     * Determine if the spaceship has died.
     *
     * @return a boolean value
     */
    public boolean isDead() {
        return dead;
    }

    /**
     * Sets the living status of the spaceship.
     *
     * @param dead a boolean value that represents the status of the spaceship
     */
    public void setDead(final boolean dead) {
        this.dead = dead;
    }

    /**
     * Returns the type of this space object.
     *
     * @return a String that represents the type of this space object
     */
    public String getType() {
        return type;
    }
}
