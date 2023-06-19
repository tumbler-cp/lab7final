package server;

import comm.Request;
import comm.User;
import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;
import java.net.SocketAddress;

public class Handable implements Serializable {
    public SocketAddress src;
    public Request request;
    private User client;
    public Handable(SocketAddress src, Request request){
        this.src = src;
        this.request = request;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }
}
