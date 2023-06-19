package dragon;

import exceptions.NoSuchOptionException;

import java.io.Serializable;

/**
 * Type of Dragon
 */
public enum DragonType implements Serializable {
    UNDERGROUND,
    AIR,
    FIRE;

    /**
     * Parses String to DragonType
     *
     * @param string String to parse
     * @return Dragon type
     */
    public static DragonType toDragonType(String string) throws NoSuchOptionException {
        return switch (string) {
            case "UNDERGROUND", "1" -> UNDERGROUND;
            case "AIR", "2" -> AIR;
            case "FIRE", "3" -> FIRE;
            default -> throw new NoSuchOptionException();
        };
    }
}
