package server;

import comm.Request;
import org.apache.commons.lang3.SerializationUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class ServerNetwork {
    private final DatagramChannel channel;
    ServerNetwork(String HOST, int PORT) throws IOException {
        channel = DatagramChannel.open();
        channel.bind(new InetSocketAddress(HOST, PORT));
    }
    public boolean send(byte[] data, SocketAddress dest) {
        System.out.println("Отправка 1");
        try {
            channel.send(ByteBuffer.wrap(data), dest);
            System.out.println("Отправка 2");
        } catch (IOException e) {
            return false;
        }
        System.out.println("Отправка 3");
        return true;
    }

    public Handable receive() throws IOException {
        System.out.println("Вызван receive()");
        SocketAddress remoteAdd;
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        do {
            remoteAdd = channel.receive(buffer);
        }
        while (remoteAdd == null);
        buffer.flip();
        Request request = SerializationUtils.deserialize(buffer.array());
        System.out.println("Получен запрос от " + remoteAdd);
        return new Handable(remoteAdd, request);
    }

    public void close() throws IOException {
        channel.close();
    }
}
