package ca.bcit.comp2522.termproject.planetbandit.Entities;

import java.util.Objects;

/**
 * Represents a Player.
 *
 * @author Benny and Prab
 * @version 2022
 */
public class Player extends Actor {
    private static final int THIRTY_ONE = 31;
    private int xp;
    private String name;
    /**
     * Constructs an object of type Player.
     *
     * @param health player's health points as an int
     * @param attackPower actor's attack power as an int
     * @param level player's level as an int
     * @param xCoordinate player's x-coordinate as a double
     * @param yCoordinate player's y-coordinate as a double
     * @param xp player's experience points as an int
     * @param name player's name as a String
     */
    protected Player(final int health, final int level, final int attackPower,
                     final double xCoordinate, final double yCoordinate, final int xp,
                     final String name) {
        super(health, level, attackPower, xCoordinate, yCoordinate);
        this.xp = xp;
        this.name = name;
    }

    /**
     * Returns the experience points for this Player.
     *
     * @return xp as an int
     */
    public int getXp() {
        return xp;
    }

    /**
     * Sets the experience points of this Player.
     *
     * @param xp experience points as an int
     */
    public void setXp(final int xp) {
        this.xp = xp;
    }

    /**
     * Returns the avatar name for this Player.
     *
     * @return name as a String
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of this Player.
     *
     * @param name of the player avatar as a String
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Players are equal if their names are equal.
     *
     * @param o Object
     * @return true if the same else false
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        Player player = (Player) o;

        if (xp != player.xp) {
            return false;
        }
        return Objects.equals(name, player.name);
    }

    /**
     * Returns a hashCode for this instance of the Player class.
     *
     * @return hashCode as an int.
     */
    @Override
    public int hashCode() {
        int result = super.hashCode();
            result = THIRTY_ONE * result + xp;
        if (name != null) {
            result = THIRTY_ONE * result + name.hashCode();
        } else {
            result = THIRTY_ONE * result;
        }
        return result;
    }

    /**
     * Returns this Player as a String.
     *
     * @return toString description
     */
    @Override
    public String toString() {
        return "Player{" + "health=" + health + ", level="
                + level + ", xCoordinate=" + xCoordinate
                + ", yCoordinate=" + yCoordinate + ", xp="
                + xp + ", name='" + name + '\'' + '}';
    }
}
