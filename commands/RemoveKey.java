package commands;

import comm.User;
import interfaces.Command;
import interfaces.Managing;
import managers.DragonManager;

public class RemoveKey implements Command<Integer>, Managing {
    private Integer arg;
    private final DragonManager dragons;
    private User user;
    private String response;
    public RemoveKey(DragonManager dragonManager){
        dragons = dragonManager;
    }
    @Override
    public boolean execute() {
        try {
            if (dragons.del(arg, user)){
                response = "Дракон успешно удален!";
                return true;
            }
            response = "Это не ваш дракон";
            return true;
        } catch (Exception e) {
            System.out.println("Ошибка в remove_key");
            return false;
        }
    }

    @Override
    public Integer getArgs() {
        return arg;
    }

    @Override
    public void setArg(Integer arg) {
        this.arg = arg;
    }

    @Override
    public void setLineArgs(String[] lineArgs) {

    }

    @Override
    public String description() {
        return "remove_key null - удалить элемент из коллекции по его ключу";
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
