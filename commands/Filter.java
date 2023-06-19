package commands;

import interfaces.Command;
import managers.DragonManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Filter implements Command<String> {
    private String arg;
    private String response;
    private DragonManager dragons;
    public Filter(DragonManager dragonManager){
        dragons = dragonManager;
    }
    @Override
    public boolean execute() {
        String reg = ".*" + arg + ".*";
        Pattern pattern = Pattern.compile(reg);
        response = "";
        dragons.stream().forEach(dragon ->
        {
            Matcher matcher = pattern.matcher(dragon.getName());
            if (matcher.matches()) response += dragon + "\n";
        });
        return true;
    }

    @Override
    public String getArgs() {
        return arg;
    }

    @Override
    public void setArg(String arg) {
        this.arg = arg;
    }

    @Override
    public void setLineArgs(String[] lineArgs) {

    }

    @Override
    public String description() {
        return "filter_contains_name name - вывести элементы, значение поля name которых содержит заданную подстроку";
    }

    @Override
    public String response() {
        return response;
    }
}
