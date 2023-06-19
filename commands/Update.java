package commands;

import comm.User;
import dragon.Dragon;
import interfaces.Command;
import interfaces.Managing;
import managers.DragonManager;

import java.sql.SQLException;

public class Update implements Command<Dragon>, Managing {
    private User user;
    private String[] line;
    private Dragon arg;
    private String response;
    private final DragonManager dragons;
    public Update(DragonManager dragonManager){
        dragons = dragonManager;
    }
    @Override
    public boolean execute() {
        int id = Integer.parseInt(line[0]);
        try {
            dragons.keyStream().forEach(k -> {
                if (dragons.get(k).getId() == id){
                    try {
                        dragons.update(k, arg, user);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            response = "Дракон с id " + id + " обновлен!";
            return true;
        } catch (Exception e){
            System.out.println("Ошибка в update");
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
        this.line = lineArgs;
    }

    @Override
    public String description() {
        return "update id {element} - обновить значение элемента коллекции, id которого равен заданному";
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
