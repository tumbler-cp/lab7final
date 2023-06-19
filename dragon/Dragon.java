package dragon;

import comm.User;
import db.UserBase;
import exceptions.IncorrectValueException;
import exceptions.NoSuchOptionException;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;

/**
 * Class Dragon
 *
 * @author Abdujalol Khodjaev
 * @see Color
 * @see Coordinates
 * @see Dragon
 * @see DragonCave
 * @see DragonType
 */
public class Dragon implements Comparable<Dragon>, Serializable {
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private int age; //Значение поля должно быть больше 0
    private Color color; //Поле не может быть null
    private DragonType type; //Поле может быть null
    private DragonCharacter character; //Поле может быть null
    private DragonCave cave; //Поле может быть null
    //id, name, coordinates, creationDate, age, color, type, character, cave
    private String owner;

    /**
     * Default constructor
     *
     * @param name        Name of dragon
     * @param coordinates Dragon coordinates
     * @param age         Dragon age
     * @param color       Dragon color 1-5
     * @param type        Dragon type 1-3
     * @param character   Dragon character 1-3
     * @param cave        Dragon cave
     */
    public Dragon(String name, Coordinates coordinates, Integer age, Color color, DragonType type, DragonCharacter character, DragonCave cave) {
        this.id = null;
        this.name = name;
        this.coordinates = coordinates;
        this.age = age;
        this.color = color;
        this.type = type;
        this.character = character;
        this.cave = cave;
        this.creationDate = LocalDate.now();
    }

    /**
     * Checks correctness of Dragon field.
     *
     * @return Correctness
     */
    public boolean check() {
        if (this.name == null || this.name.isEmpty()) return false;
        if (this.coordinates == null) return false;
        if (this.age <= 0) return false;
        if (this.color == null) return false;
        if (this.type == null) return false;
        if (this.character == null) return false;
        if (this.cave == null) return false;
        return cave.check();
    }

    /**
     * Age setter
     *
     * @param age Age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Age getter
     *
     * @return Dragon age
     */
    public int getAge() {
        return age;
    }

    /**
     * Cave setter
     *
     * @param cave cave to set
     */
    public void setCave(DragonCave cave) {
        this.cave = cave;
    }

    /**
     * Cave getter
     *
     * @return Dragon cave
     */

    public DragonCave getCave() {
        return cave;
    }

    /**
     * Dragon character setter
     *
     * @param character character to set
     */
    public void setCharacter(DragonCharacter character) {
        this.character = character;
    }

    /**
     * Dragon character getter
     *
     * @return Dragon character
     */
    public DragonCharacter getCharacter() {
        return character;
    }

    /**
     * Dragon color setter
     *
     * @param color color to set
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Color getter
     *
     * @return dragon color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Coordinates setter
     *
     * @param coordinates coordinates to set
     */
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * Coordinates getter
     *
     * @return Dragon coordinates
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    /**
     * Creation date setter
     *
     * @param s LocalDate to set
     */
    public void setCreationDate(String s) {
        LocalDate creationDate;
        String[] c = s.split("-");
        creationDate = LocalDate.of(Integer.parseInt(c[0]), Integer.parseInt(c[1]), Integer.parseInt(c[2]));
        this.creationDate = creationDate;
    }

    /**
     * Id setter
     *
     * @param id id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Id getter
     *
     * @return Dragon id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Name setter
     *
     * @param name Name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Name getter
     *
     * @return Dragon name
     */
    public String getName() {
        return name;
    }

    /**
     * Type setter
     *
     * @param type Type to set
     */
    public void setType(DragonType type) {
        this.type = type;
    }

    /**
     * Type getter
     *
     * @return Dragon type
     */
    public DragonType getType() {
        return type;
    }


    /**
     * toString override
     *
     * @return Information about Dragon
     */
    @Override
    public String toString() {
        return "id: " + getId() + "\n" +
                "name: " + getName() + "\n" +
                "age: " + getAge() + "\n" +
                "coordinates: " + getCoordinates() + "\n" +
                "color: " + getColor() + "\n" +
                "type: " + getType() + "\n" +
                "character: " + getCharacter() + "\n" +
                "cave: " + getCave() + "\n" +
                "owner: " + owner;
    }

    /**
     * compareTo override
     *
     * @param o Dragon to compare with
     * @return Result of comparing ids of this Dragon and Dragon o
     */
    @Override
    public int compareTo(Dragon o) {
        return this.getName().compareTo(o.getName());
    }

    /**
     * Makes dragon from String[]
     *
     * @param args   String[] param with necessary data
     * @return Dragon
     * @throws NoSuchOptionException If there are problems with enums of Dragon
     */
    public static Dragon parseDrag(String[] args) throws NoSuchOptionException {
        return new Dragon(null, null, 0, null, null, null, null) {{
            setId(Integer.parseInt(args[1]));
            setName(args[2]);
            setCoordinates(Coordinates.toCoordinates(args[3]));
            setCreationDate(args[4]);
            setColor(Color.toColor(args[5]));
            setCharacter(DragonCharacter.parse(args[6]));
            setType(DragonType.toDragonType(args[7]));
            setAge(Integer.parseInt(args[10]));
            try {
                setCave(new DragonCave(Integer.parseInt(args[8])));
            } catch (IncorrectValueException e) {
                System.out.println("Переданное значение для DragonCave неправильное");
            }
            setOwner(args[9]);
        }};
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOwner() {
        return owner;
    }
}

