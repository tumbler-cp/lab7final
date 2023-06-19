package commands;

import comm.User;
import dragon.Dragon;
import interfaces.Command;
import interfaces.Managing;
import managers.DragonManager;

import java.sql.SQLException;

public class ReplaceGreater implements Command<Dragon>, Managing {
    private User user;
    private String response;
    private String[] line;
    private Dragon arg;
    private final DragonManager manager;
    public ReplaceGreater(DragonManager dragonManager){
        manager = dragonManager;
    }
    @Override
    public boolean execute() {
        int key = Integer.parseInt(line[0]);
        if (manager.get(key).compareTo(arg) >= 0){
            response = "Этот дракон меньше существующего!";
            return true;
        }
        try {
            manager.update(key, arg, user);
            response = "Дракон успешно обновлен!";
            return true;
        } catch (SQLException e) {
            System.out.println("Ошибка при replace_greater");
            return false;
        }
    }

    @Override
    public Dragon getArgs() {
        return arg;
    }

    @Override
    public void setArg(Dragon arg) {
        this.arg = arg;
    }

    @Override
    public void setLineArgs(String[] lineArgs) {
        line = lineArgs;
    }

    @Override
    public String description() {
        return "replace_if_greater null {element} - заменить значение по ключу, если новое значение больше старого";
    }

    @Override
    public String response() {
        return response;
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public void setUser(User user) {
        this.user = user;
    }
}
