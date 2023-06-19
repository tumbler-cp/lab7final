package dragon;

import java.io.Serializable;

/**
 * Dragon coordinates
 *
 * @author Abdujalol Khodjaev
 */
public class Coordinates implements Comparable<Coordinates>, Serializable {
    /**
     * Coordinate x
     */
    private final float x; //Поле не может быть null
    /**
     * Coordinate y
     */
    private final float y; //Максимальное значение поля: 346

    /**
     * Default coordinates
     *
     * @param x - x
     * @param y - y
     */
    public Coordinates(double x, double y) {
        this.x = (float) x;
        this.y = (float) y;
    }

    /**
     * Parse String to Coordinates
     *
     * @param string string to parse
     * @return Coordinates
     */
    public static Coordinates toCoordinates(String string) {
        float x, y;
        try {
            x = Float.parseFloat(string.split(" ")[0]);
            y = Float.parseFloat(string.split(" ")[1]);
        } catch (IndexOutOfBoundsException | NumberFormatException i) {
            x = Float.parseFloat(string.split("/")[0]);
            y = Float.parseFloat(string.split("/")[1]);
        }
        return new Coordinates(x, y);
    }

    /**
     * toString Override
     *
     * @return "x y"
     */
    @Override
    public String toString() {
        return this.x + " " + this.y;
    }

    /**
     * compareTo Override
     *
     * @param o the object to be compared.
     * @return 0 or 1
     */

    @Override
    public int compareTo(Coordinates o) {
        return Float.compare(this.y, o.y);
    }
}
