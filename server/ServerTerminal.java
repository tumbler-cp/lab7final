package server;

import db.UserBase;
import managers.CommandManager;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerTerminal {
    private final Scanner in = new Scanner(System.in);
    private final CommandManager commands;
    private final ServerNetwork network;
    private UserBase userBase;
    ServerTerminal(CommandManager commandManager, ServerNetwork network, UserBase userBase) {
        this.commands = commandManager;
        this.network = network;
        this.userBase = userBase;
    }
    ConcurrentMap<Integer, Handable> par1 = new ConcurrentHashMap<>();
    ConcurrentMap<Integer, Handable> par2 = new ConcurrentHashMap<>();
    void admin(){
        String[] line = in.nextLine().split(" ");
        if (line[0].equals("exit")) {
            System.exit(0);
        }
    }
    void run() throws CloneNotSupportedException {
        Reader reader = new Reader();
        reader.queue = par1;
        reader.network = network;
        ExecutorService pool = Executors.newCachedThreadPool();
        reader.start();
        while (true)
        {
            if (!reader.isAlive()){
                reader = new Reader();
                reader.queue = par1;
                reader.network = network;
                reader.start();
            }
            try {
                if (System.in.available() > 0) admin();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (par1.size() > 0){
                for (Integer key : par1.keySet()){
                    new ServerHandler(commands, par2, userBase){{
                        setObj(par1.get(key));
                        start();
                    }};
                    par1.remove(key);
                }
            }
            reader.setLocked(true);
            if (par2.size() > 0){
                System.out.println(par2);
                for (Integer key : par2.keySet()){
                    pool.submit(
                    new Sender(){{
                        setObject(par2.get(key));
                        setNetwork(network);
                    }});
                    par2.remove(key);
                }
            }
            reader.setLocked(false);
        }
    }
}