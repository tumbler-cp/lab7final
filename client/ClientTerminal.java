package client;

import comm.*;
import interfaces.Managing;
import interfaces.Network;
import org.apache.commons.lang3.SerializationUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class ClientTerminal {
    private final Scanner in = new Scanner(System.in);
    private final HashMap<CommType, CommandClient<?>> commands;
    private final Network network;
    private final ClientHandler handler;
    private final User user;
    ClientTerminal(HashMap<CommType, CommandClient<?>> clientCommands, Network network, User user){
        commands = clientCommands;
        this.network = network;
        handler = new ClientHandler(((ClientNetwork) network).getSocket());
        this.user = user;
    }
    public void start()
    {
        while (true) {
            String[] line = in.nextLine().split(" ");
            String command = line[0];
            if (command.equals("exit")) break;
            String[] args = new String[line.length - 1];
            System.arraycopy(line, 1, args, 0, args.length);
            command = command.toUpperCase();
            CommType type;
            try {
                type = CommType.valueOf(command);
            } catch (IllegalArgumentException e){
                System.out.println("Такой команды нет: " + command);
                continue;
            }
            CommandClient<?> reqObj = commands.get(type);
            if (reqObj instanceof Managing){
                ((Managing) reqObj).setUser(user);
                System.out.println(((Managing) reqObj).getUser().getLogin());
            }
            reqObj.setArgLine(args);
            if (!reqObj.create()) {
                System.out.println("Команда не прошла валидацию. Проверьте корректность ввода!");
                continue;
            }
            try {
                network.request(Signal.COMMAND, reqObj);
            } catch (IOException e) {
                System.out.println("Ошибка отправки данных!");
                continue;
            }
            try {
                network.receive();
            } catch (IOException e) {
                System.out.println("Ошибка получения данных!");
                continue;
            }
            Request response = SerializationUtils.deserialize(network.buffer());
            handler.setObj(response);
            handler.run();
        }
    }
}
