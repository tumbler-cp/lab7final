package client;

import comm.Request;
import interfaces.Handler;

import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.net.SocketException;

public class ClientHandler implements Handler<Request> {
    private Request request;
    private final DatagramSocket socket;
    ClientHandler(DatagramSocket socket){
        this.socket = socket;
    }

    public void run() {
        switch (request.signal()) {
            case TEXT -> System.out.println(request.object());
            case TEMP_CONN -> {
                SocketAddress address = (SocketAddress) request.object();
                try {
                    socket.connect(address);
                } catch (SocketException e) {
                    System.out.println("Ошибка переподключения");
                }
            }
        }
    }

    @Override
    public void setObj(Request obj) {
        this.request = obj;
    }
}
