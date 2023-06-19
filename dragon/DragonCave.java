package dragon;

import exceptions.IncorrectValueException;

import java.io.Serializable;

/**
 * Class of Dragon cave
 *
 * @author Abdujalol Khodjaev
 */
public class DragonCave implements Comparable<DragonCave>, Serializable {
    private long numberOfTreasures; //Значение поля должно быть больше 0

    /**
     * Default constructor
     *
     * @param number_Of_Treasures Number of treasures
     */
    public DragonCave(int number_Of_Treasures) throws IncorrectValueException {
        setNumberOfTreasures(number_Of_Treasures);
    }

    public void setNumberOfTreasures(long numberOfTreasures) throws IncorrectValueException {
        if (numberOfTreasures <= 0) throw new IncorrectValueException();
        this.numberOfTreasures = numberOfTreasures;
    }

    /**
     * Check correctness of class fields
     *
     * @return Correctness
     */
    public boolean check() {
        return this.numberOfTreasures > 0;
    }

    /**
     * toString override
     *
     * @return Value of numberOfTreasures
     */
    @Override
    public String toString() {
        return String.valueOf(this.numberOfTreasures);
    }


    /**
     * compareTo override
     *
     * @param o Dragon cave to compare with
     * @return 1 if this cave is bigger and 0 if opposite
     */
    @Override
    public int compareTo(DragonCave o) {
        if (this.numberOfTreasures > o.numberOfTreasures) return 1;
        else if (this.numberOfTreasures < o.numberOfTreasures) return -1;
        return 0;
    }
}
