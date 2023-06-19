package comm.clientReqs;

import client.DragonFactory;
import comm.CommType;
import comm.CommandClient;
import comm.User;
import dragon.*;
import interfaces.Managing;


public class InsertReq implements CommandClient<Dragon>, Managing {
    private Dragon dragon;
    private String[] argLine;
    private User me;
    @Override
    public CommType getType() {
        return CommType.INSERT;
    }

    @Override
    public Dragon getArg() {
        return dragon;
    }

    @Override
    public void setArgLine(String[] argLine) {
        this.argLine = argLine;
    }

    @Override
    public String[] getArgLine() {
        return argLine;
    }

    @Override
    public boolean create() {
        try {
            var ignored = Integer.valueOf(argLine[0]);
        } catch (ClassCastException e){
            System.out.println("Ключ должен быть числом!");
            return false;
        }
        dragon = DragonFactory.makeOne();
        return dragon.check();
    }

    @Override
    public User getUser() {
        return me;
    }

    @Override
    public void setUser(User user) {
        me = user;
    }
}
