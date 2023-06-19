package comm.clientReqs;

import client.DragonFactory;
import comm.CommType;
import comm.CommandClient;
import comm.User;
import dragon.Dragon;
import interfaces.Managing;

public class UpdateReq implements CommandClient<Dragon>, Managing {
    private User user;
    private String[] line;
    private Dragon arg;
    @Override
    public CommType getType() {
        return CommType.UPDATE;
    }

    @Override
    public Dragon getArg() {
        return arg;
    }

    @Override
    public void setArgLine(String[] argLine) {
        this.line = argLine;
    }

    @Override
    public String[] getArgLine() {
        return line;
    }

    @Override
    public boolean create() {
        try {
            var ignored = Integer.valueOf(line[0]);
        } catch (ClassCastException c){
            System.out.println("Id это число!");
            return false;
        }
        arg = DragonFactory.makeOne();
        return arg.check();
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public void setUser(User user) {
        this.user = user;
    }
}
