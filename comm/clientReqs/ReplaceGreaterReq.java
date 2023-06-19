package comm.clientReqs;

import client.DragonFactory;
import comm.CommType;
import comm.CommandClient;
import comm.User;
import dragon.Dragon;
import interfaces.Managing;

public class ReplaceGreaterReq implements CommandClient<Dragon>, Managing {
    private Dragon arg;
    private String[] line;
    private User user;
    @Override
    public CommType getType() {
        return CommType.REPLACE_IF_GREATER;
    }

    @Override
    public Dragon getArg() {
        return arg;
    }

    @Override
    public void setArgLine(String[] argLine) {
        line = argLine;
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
            System.out.println("Ключ должен быть числом!");
            return false;
        }
        arg = DragonFactory.makeOne();
        return true;
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
