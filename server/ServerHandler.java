package server;

import comm.*;
import db.UserBase;
import interfaces.Command;
import interfaces.Handler;
import interfaces.Managing;
import managers.CommandManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ServerHandler extends Thread implements Handler<Handable> {
    private static int id = 0;
    private Handable current;
    private final CommandManager commands;
    private final UserBase users;
    private final Map<Integer, Handable> box;
    private static List<User> authedUsers = new ArrayList<>();
    ServerHandler(CommandManager commandManager, Map<Integer, Handable> box, UserBase userBase){
        commands = commandManager;
        this.box = box;
        users = userBase;
    }

    @Override
    public void run() {
        switch (current.request.signal()){
            case COMMAND -> {
                CommandClient<?> request = (CommandClient<?>) current.request.object();
                CommType key = request.getType();
                String[] argLine = request.getArgLine();
                System.out.println(Arrays.toString(argLine));
                Command command = commands.get(key);
                command.setLineArgs(argLine);
                command.setArg(request.getArg());
                if (command instanceof Managing){
                    ((Managing) command).setUser(((Managing) request).getUser());
                }
                command.execute();
                String response = command.response();
                current.request = new Request(Signal.TEXT, response);
            }
            case LOGIN -> {
                User user = (User) current.request.object();
                if (authedUsers.contains(user)) {
                    current.request = new Request(Signal.DECLINE, "Такой пользователь уже авторизовался");
                }
                if (users.find(user)) current.request = new Request(Signal.ACCEPT, "Добро пожаловать!");
                else {
                    current.request = new Request(Signal.DECLINE, "Не удалось авторизоваться. Проверьте логин/пароль");
                }
                authedUsers.add(user);
            } case REG -> {
                User user = (User) current.request.object();
                if (users.insert(user)) {
                    current.request = new Request(Signal.ACCEPT, "Регистрация успешна!");
                } else current.request = new Request(Signal.DECLINE, "Регистрация безуспешна!");
                authedUsers.add(user);
            }
        }
        box.put(id, current);
        id++;
    }

    @Override
    public void setObj(Handable obj) {
        current = obj;
    }

}
