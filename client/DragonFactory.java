package client;

import dragon.*;
import exceptions.IncorrectValueException;
import exceptions.NoSuchOptionException;

import java.util.Scanner;

public class DragonFactory {
    private DragonFactory(){}
    private static final Scanner in = new Scanner(System.in);
    public static Dragon makeOne(){
        Dragon dragon = new Dragon(null, null, 0, null, null, null, null);

        String[] mans = new String[]
                {
                        "Введите имя дракона: ",
                        "Введите координаты(x y): ",
                        "Введите возраст: ",
                        "1.GREEN\n2.RED\n3.BLUE\n4.YELLOW\n5.BROWN\nВыберите цвет: ",
                        "1.UNDERGROUND\n2.AIR\n3.FIRE\nВыберите тип:",
                        "1.WISE\n2.GOOD\n3.CHAOTIC-EVIL\nВыберите характер: ",
                        "Введите число сокровищ в пещере: "
                };
        String buffer;
        for (int i = 0; i<mans.length; i++){
            System.out.println(mans[i]);
            buffer = in.nextLine();
            try {
                switch (i)
                {
                    case 0 -> dragon.setName(buffer);
                    case 1 -> dragon.setCoordinates(Coordinates.toCoordinates(buffer));
                    case 2 -> dragon.setAge(Integer.parseInt(buffer));
                    case 3 -> dragon.setColor(Color.toColor(buffer));
                    case 4 -> dragon.setType(DragonType.toDragonType(buffer));
                    case 5 -> dragon.setCharacter(DragonCharacter.parse(buffer));
                    case 6 -> dragon.setCave(new DragonCave(Integer.parseInt(buffer)));
                }
            } catch (NoSuchOptionException a){
                System.out.println("Такой опции нет!");
                i--;
            } catch (NumberFormatException | IncorrectValueException b) {
                System.out.println("Неправильное число!");
                i--;
            }
        }
        return dragon;
    }
}
