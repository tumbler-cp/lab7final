package server;

import java.io.IOException;
import java.util.Map;

class Reader extends Thread {
    private static int id = 0;
    public Handable object;
    public Map<Integer, Handable> queue;
    public ServerNetwork network;
    private boolean locked = false;
    @Override
    public void run() {
        if (!locked) {
            try {
                object = network.receive();
            } catch (IOException e) {
                System.out.println("Ошибка чтения запроса");
            } finally {
                try {
                    queue.put(id, object);
                    id++;
                } catch (NullPointerException n){
                    System.out.println("Map пропал!");
                }
            }
        }
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }
}
