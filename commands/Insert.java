package commands;

import comm.User;
import dragon.Dragon;
import exceptions.KeyExistException;
import interfaces.Command;
import interfaces.Managing;
import managers.DragonManager;

public class Insert implements Command<Dragon>, Managing {
    private final DragonManager dragons;
    private Dragon currDragon;
    private String response;
    private String[] args;
    private User user;

    public Insert(DragonManager dragonManager){
        dragons = dragonManager;
    }
    @Override
    public boolean execute() {
        response = "";
        try {
            int key = Integer.parseInt(args[0]);
            if (dragons.keyCheck(key)){
                response = "Дракон с таким ключом уже существует!";
                return true;
            }
            try {
                dragons.add(key, currDragon, user);
            } catch (KeyExistException k) {
                response = "Дракон с таким ключом уже существует!";
                return true;
            }
            System.out.println(key + " " + currDragon);
            response = "Успешно добавлен дракон. Ключ: " + key;
            return true;
        } catch (Exception e){
            System.out.println("Ошибка в insert");
            return false;
        }
    }

    @Override
    public Dragon getArgs() {
        return currDragon;
    }

    @Override
    public void setArg(Dragon arg) {
        currDragon = arg;
    }

    @Override
    public void setLineArgs(String[] lineArgs) {
        args = lineArgs;
    }

    @Override
    public String description() {
        return "insert null {element} - добавить новый элемент с заданным ключом. Введите insert man для просмотра инструкции";
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
