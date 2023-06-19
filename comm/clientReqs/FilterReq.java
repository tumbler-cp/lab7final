package comm.clientReqs;

import comm.CommType;
import comm.CommandClient;

public class FilterReq implements CommandClient<String> {
    private String arg;
    private String[] line;
    @Override
    public CommType getType() {
        return CommType.FILTER_CONTAINS_NAME;
    }

    @Override
    public String getArg() {
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
        if (line.length == 0) {
            System.out.println("Введите паттерн");
            return false;
        }
        if (line.length > 1){
            System.out.println("Только один аргумент");
            return false;
        }
        arg = line[0];
        return true;
    }
}
