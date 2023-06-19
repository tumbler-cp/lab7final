package client;

import comm.Request;
import comm.Signal;
import comm.User;
import org.apache.commons.lang3.SerializationUtils;

import java.io.IOException;
import java.util.Scanner;

public class LogReg {
    private final Scanner in = new Scanner(System.in);
    private final ClientNetwork network;
    private User result;

    public LogReg(ClientNetwork network) {
        this.network = network;
    }

    public void run() throws IOException {
        System.out.print("Выберите опцию(l-login, r-registration): ");
        String line = in.nextLine();
        if (line.split(" ").length > 1) {
            System.out.println("введите одно значение");
            run();
            return;
        }
        if (line.equals("l")){
            auth();
        } else if (line.equals("r")) reg();
        else {
            System.out.println("Введите либо r либо l!");
            run();
        }
    }
    private void auth() throws IOException {
        User user = new User(login(), password());
        network.request(Signal.LOGIN, user);
        network.receive();
        Request received = SerializationUtils.deserialize(network.buffer());
        if (received.signal() == Signal.ACCEPT){
            System.out.println(received.object());
            result = user;
        } else {
            System.out.println(received.object());
            run();
        }
    }
    private void reg() throws IOException {
        User user = new User(login(), password());
        network.request(Signal.REG, user);
        network.receive();
        Request received = SerializationUtils.deserialize(network.buffer());
        if (received.signal() == Signal.ACCEPT){
            System.out.println(received.object());
            result = user;
        } else {
            System.out.println(received.object());
            run();
        }
    }
    private String login(){
        System.out.println("Введите имя: ");
        String login  = in.nextLine();
        if (login.split(" ").length > 1){
            System.out.println("Одно слово!");
            return login();
        }
        return login;
    }
    private String password(){
        System.out.print("Введите пароль: ");
        String password = in.nextLine();
        if (password.split(" ").length > 1){
            System.out.println("Пароль не содержит пробелов!");
            return password();
        }
        return password;
    }

    public User getResult() {
        return result;
    }
}
