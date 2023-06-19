package comm.clientReqs;

import comm.CommType;
import comm.CommandClient;
import comm.User;
import interfaces.Managing;

public class RemoveKeyReq implements CommandClient<Integer>, Managing {
    private Integer arg;
    private String[] line;
    private User user;
    @Override
    public CommType getType() {
        return CommType.REMOVE_KEY;
    }

    @Override
    public Integer getArg() {
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
        if (this.line.length > 1) return false;
        try {
            arg = Integer.parseInt(line[0]);
        } catch (ClassCastException c){
            System.out.println("Аргумент должен быть числом!");
            return false;
        }
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
