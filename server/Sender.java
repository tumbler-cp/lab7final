package server;

class Sender extends Thread implements Runnable{
    private Handable object;
    private ServerNetwork network;
    @Override
    public void run() {
        System.out.println("Начало отправки в " + object.src);
        network.send(object.request.bytes(), object.src);
        System.out.println("Успешно отправлен ответ: " + object.src);
        System.out.println("Конец отправки!");
    }

    public void setNetwork(ServerNetwork network) {
        this.network = network;
    }

    public void setObject(Handable object) {
        this.object = object;
    }
}
