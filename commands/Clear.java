package commands;

import comm.User;
import interfaces.Command;
import interfaces.Managing;
import managers.DragonManager;

import java.sql.SQLException;

public class Clear implements Command<String>, Managing {
    private User user;
    private String response;
    private final DragonManager dragons;
    public Clear(DragonManager dragonManager){
        dragons = dragonManager;
    }
    @Override
    public boolean execute() {
        try {
            dragons.clear(user);
            response = "Ваши драконы успешно уничтожены!";
            return true;
        } catch (SQLException e) {
            System.out.println("Ошибка SQL");
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
        return "clear - очистить коллекцию";
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
