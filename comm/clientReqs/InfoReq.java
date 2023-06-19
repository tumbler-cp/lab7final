package comm.clientReqs;

import comm.CommType;
import comm.CommandClient;

public class InfoReq implements CommandClient<String> {
    @Override
    public CommType getType() {
        return CommType.INFO;
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
        return true;
    }
}
