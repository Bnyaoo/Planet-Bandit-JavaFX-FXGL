package ca.bcit.comp2522.termproject.planetbandit;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Meteorite extends SpaceObjects {
    private boolean dead = false;
    private final String type;
    private final int moveSpeed = 10;

    Meteorite(final int xCoordinate, final int yCoordinate, final double radius,
              final String type, final Color color) {
        super(xCoordinate, yCoordinate, radius, type, color);
        this.type = type;
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