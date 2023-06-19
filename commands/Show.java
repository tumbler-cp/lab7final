package commands;

import interfaces.Command;
import managers.CommandManager;
import managers.DragonManager;

public class Show implements Command<String> {
    private final DragonManager dragons;
    private String resp;
    public Show(DragonManager dragonManager){
        dragons = dragonManager;
    }
    @Override
    public boolean execute() {
        resp = "";
        try {
            dragons.keyStream().forEach(key ->{resp+="Key: " + key + "\n" + dragons.get(key) + "\n" + "_____\n";});
            return true;
        } catch (Exception e){
            System.out.println("Ошибка в show");
            return false;
        }
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

        return "show - вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }

    @Override
    public String response() {
        return resp;
    }
}
