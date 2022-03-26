package ca.bcit.comp2522.termproject.planetbandit.Entities;

/**
 * Represents a Fugitive.
 * @author Benny and Prab
 * @version 2022
 */
public class Fugitive extends Actor {
    private static final int THIRTY_ONE = 31;
    // used to identify and track which inmates have been captured so far
    private int inmateNumber;
    private final static String image = "player.png";
    /**
     * Constructs an object of type Actor.
     *
     * @param health actor's health points as an int
     * @param attackPower actor's attack power as an int
     * @param level actor's level as an int
     * @param xCoordinate actor's x-coordinate as a double
     * @param yCoordinate actor's y-coordinate as a double
     * @param inmateNumber unique id number of a Fugitive object as an int
     */
    protected Fugitive(final int health, final int level, final int attackPower,
                       final double xCoordinate, final double yCoordinate, final int inmateNumber) {
        super(health, level, attackPower, xCoordinate, yCoordinate, image);
        this.inmateNumber = inmateNumber;
    }

    /**
     * Returns the inmate number of this Fugitive.
     *
     * @return inmateNumber as an int
     */
    public int getInmateNumber() {
        return inmateNumber;
    }

    /**
     * Sets the inmate number of this Fugitive.
     * @param inmateNumber of this Fugitive as an int
     */
    public void setInmateNumber(final int inmateNumber) {
        this.inmateNumber = inmateNumber;
    }

    /**
     * Fugitives are equal if their names are equal.
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

        Fugitive fugitive = (Fugitive) o;

        return inmateNumber == fugitive.inmateNumber;
    }

    /**
     * Returns a hashCode for this instance of the Fugitive class.
     *
     * @return hashCode as an int.
     */
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = THIRTY_ONE * result + inmateNumber;
        return result;
    }

    /**
     * Returns this Fugitive as a String.
     *
     * @return toString description
     */
    @Override
    public String toString() {
        return "Fugitive{" + "health=" + health + ", level=" + level
                + ", xCoordinate=" + xCoordinate + ", yCoordinate=" + yCoordinate
                + ", inmateNumber=" + inmateNumber + '}';
    }
}
