package commands;

import interfaces.Command;
import managers.DragonManager;

public class Info implements Command<String> {
    private final DragonManager dragons;
    private String response;
    public Info(DragonManager dragonManager){
        dragons = dragonManager;
    }

    @Override
    public boolean execute() {
        response = "";
        response += "Тип коллекции: Hashmap + \n";
        response += "Тип объектов: Dragon" + "\n";
        response += "Кол-во объектов: " + dragons.size() + "\n";
        response += "Дата создания: " + dragons.getCreationDate().toString();
        return  true;
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
        return "info - вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)";
    }

    @Override
    public String response() {
        return response;
    }
}
