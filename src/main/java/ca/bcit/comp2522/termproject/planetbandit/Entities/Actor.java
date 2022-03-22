package ca.bcit.comp2522.termproject.planetbandit.Entities;

/**
 * Represents an actor.
 *
 * @author Benny and Prab
 * @version 2022
 */
public abstract class Actor {
    private static final int THIRTY_ONE = 31;
    private static final int THIRTY_TWO = 32;
    /**
     * Represents the actor's health as an int.
     */
    protected int health;
    /**
     * Represents the actor's attack power as an int.
     */
    protected int attackPower;
    /**
     * Represents the actor's level as an int.
     */
    protected int level;
    /**
     * Represents the actor's x-coordinate as a double.
     */
    protected double xCoordinate;
    /**
     * Represents the actor's y-coordinate as a double.
     */
    protected double yCoordinate;

    /**
     * Constructs an object of type Actor.
     *
     * @param health actor's health points as an int
     * @param attackPower actor's attack power as an int
     * @param level actor's level as an int
     * @param xCoordinate actor's x-coordinate as a double
     * @param yCoordinate actor's y-coordinate as a double
     */
    protected Actor(final int health, final int level, final int attackPower,
                    final double xCoordinate, final double yCoordinate) {
        this.health = health;
        this.attackPower = attackPower;
        this.level = level;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    /**
     * Returns the health for this Actor.
     *
     * @return health as an int
     */
    public int getHealth() {
        return health;
    }

    /**
     * Sets the health of this Actor.
     *
     * @param health of this Actor as an int
     */
    public void setHealth(final int health) {
        this.health = health;
    }

    /**
     * Returns the attack power for this Actor.
     *
     * @return attackPower as an int
     */
    public int getAttackPower() {
        return attackPower;
    }

    /**
     * Sets the attack power of this Actor.
     *
     * @param attackPower of this Actor as an int
     */
    public void setAttackPower(final int attackPower) {
        this.attackPower = attackPower;
    }

    /**
     * Returns the level for this Actor.
     *
     * @return level as an int
     */
    public int getLevel() {
        return level;
    }

    /**
     * Sets the level of this Actor.
     *
     * @param level of this Actor as an int
     */
    public void setLevel(final int level) {
        this.level = level;
    }

    /**
     * Returns the x-coordinate for this Actor.
     *
     * @return xCoordinate as a double
     */
    public double getxCoordinate() {
        return xCoordinate;
    }

    /**
     * Sets the x-coordinate of this Actor.
     *
     * @param xCoordinate of this Actor as a double
     */
    public void setxCoordinate(final double xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    /**
     * Returns the y-coordinate for this Actor.
     *
     * @return yCoordinate as a double
     */
    public double getyCoordinate() {
        return yCoordinate;
    }

    /**
     * Sets the y-coordinate of this Actor.
     *
     * @param yCoordinate of this Actor as a double
     */
    public void setyCoordinate(final double yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    /**
     * Actors are equal if their names are equal.
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

        Actor actor = (Actor) o;

        if (health != actor.health) {
            return false;
        }
        if (attackPower != actor.attackPower) {
            return false;
        }
        if (level != actor.level) {
            return false;
        }
        if (Double.compare(actor.xCoordinate, xCoordinate) != 0) {
            return false;
        }
        return Double.compare(actor.yCoordinate, yCoordinate) == 0;
    }

    /**
     * Returns a hashCode for this instance of the Actor class.
     *
     * @return hashCode as an int.
     */
    @Override
    public int hashCode() {
        int result;
        long temp;
        result = health;
        result = THIRTY_ONE * result + level;
        temp = Double.doubleToLongBits(xCoordinate);
        result = THIRTY_ONE * result + (int) (temp ^ (temp >>> THIRTY_TWO));
        temp = Double.doubleToLongBits(yCoordinate);
        result = THIRTY_TWO * result + (int) (temp ^ (temp >>> THIRTY_TWO));
        return result;
    }

    /**
     * Returns this Actor as a String.
     *
     * @return toString description
     */
    @Override
    public String toString() {
        return "Actor{" + "health=" + health + ", level="
                + level + ", xCoordinate=" + xCoordinate
                + ", yCoordinate=" + yCoordinate + '}';
    }
}
