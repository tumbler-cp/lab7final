package comm.clientReqs;

import comm.CommType;
import comm.CommandClient;
import comm.User;
import interfaces.Managing;

public class ClearReq implements CommandClient<String>, Managing {
    private User user;
    @Override
    public CommType getType() {
        return CommType.CLEAR;
    }

    @Override
    public String getArg() {
        return null;
    }

    @Override
    public void setArgLine(String[] argLine) {

    }

    @Override
    public String[] getArgLine() {
        return new String[0];
    }

    @Override
    public boolean create() {
        return user!=null;
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
