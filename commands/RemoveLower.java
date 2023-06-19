package commands;

import comm.User;
import interfaces.Command;
import interfaces.Managing;
import managers.DragonManager;

public class RemoveLower implements Command<Integer>, Managing {
    private Integer key;
    private User user;
    private String response;
    private final DragonManager dragons;
    public RemoveLower(DragonManager dragonManager){
        dragons = dragonManager;
    }
    @Override
    public boolean execute() {
        try {
            dragons.keyStream().forEach(k -> {
                if (k < key) dragons.del(k, user);
            });
            response = "Ваши драконы с ключом меньше " + key + " успешно удалены!";
            return true;
        } catch (Exception e){
            System.out.println("Ошибка в remove_lower_key");
            return false;
        }
    }

    @Override
    public Integer getArgs() {
        return key;
    }

    @Override
    public void setArg(Integer arg) {
        this.key = arg;
    }

    @Override
    public void setLineArgs(String[] lineArgs) {

    }

    @Override
    public String description() {
        return "remove_lower_key null - удалить из коллекции все элементы, ключ которых меньше, чем заданный";
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
