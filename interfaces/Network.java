package interfaces;
import comm.Signal;

import java.io.IOException;
import java.net.SocketAddress;

public interface Network {
    boolean send(byte[] data) throws IOException;
    boolean receive() throws IOException;
    void close() throws IOException;
    void request(Signal signal, Object object) throws IOException;
    byte[] buffer();
    SocketAddress connected();
}
