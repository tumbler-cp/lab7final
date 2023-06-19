package commands;

import interfaces.Command;
import managers.CommandManager;

public class Help implements Command<String> {
    private final CommandManager commands;
    private String resp;
    public Help(CommandManager commandManager){
        commands = commandManager;
    }
    @Override
    public boolean execute() {
        resp = "";
        try {
            commands.stream().forEach(command -> {resp += command.description() + "\n";});
            return true;
        } catch (Exception e){
            System.out.println("Ошибка в help");
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
        return "help - вывести справку по доступным командам";
    }

    @Override
    public String response() {
        return resp;
    }
}
