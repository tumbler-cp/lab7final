package comm.clientReqs;

import comm.CommType;
import comm.CommandClient;
import comm.User;
import interfaces.Managing;

public class RemoveLowerReq implements CommandClient<Integer>, Managing {
    private Integer arg;
    private User user;
    private String[] argLine;
    @Override
    public CommType getType() {
        return CommType.REMOVE_LOWER_KEY;
    }

    @Override
    public Integer getArg() {
        return arg;
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
        if (user == null) return false;
        if (argLine.length > 1) {
            System.out.println("Много аргументов!");
            return false;
        }
        try {
            arg = Integer.valueOf(argLine[0]);
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
