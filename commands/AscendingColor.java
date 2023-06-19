package commands;

import dragon.Dragon;
import interfaces.Command;
import managers.DragonManager;

import java.util.Comparator;

public class AscendingColor implements Command<String> {
    private DragonManager dragons;
    private String response;
    public AscendingColor(DragonManager dragonManager){
        dragons = dragonManager;
    }
    @Override
    public boolean execute() {
        response = "";
        dragons.stream().sorted(Comparator.comparing(Dragon::getColor))
                .toList()
                .forEach(d -> {response+=d.getId() + " " + d.getColor() + " " + d.getOwner() + "\n";});
        return true;
    }

    @Override
    public String getArgs() {
        return null;
    }

    @Override
    public void setArg(String arg) {

    }

    @Override
    public void setLineArgs(String[] lineArgs) {

    }

    @Override
    public String description() {
        return "print_field_ascending_color - вывести значения поля color всех элементов в порядке возрастания";
    }

    @Override
    public String response() {
        return response;
    }
}
