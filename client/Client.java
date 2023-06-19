package client;

import comm.CommType;
import comm.CommandClient;
import comm.clientReqs.*;
import commands.Info;

import java.io.IOException;
import java.net.SocketException;
import java.util.HashMap;

public class Client {
    public static void main(String[] args) {
        HashMap<CommType, CommandClient<?>> commands = new HashMap<>(){{
            put(CommType.HELP, new HelpReq());
            put(CommType.SHOW, new ShowReq());
            put(CommType.INSERT, new InsertReq());
            put(CommType.REMOVE_KEY, new RemoveKeyReq());
            put(CommType.CLEAR, new ClearReq());
            put(CommType.REMOVE_LOWER_KEY, new RemoveLowerReq());
            put(CommType.UPDATE, new UpdateReq());
            put(CommType.REPLACE_IF_GREATER, new ReplaceGreaterReq());
            put(CommType.INFO, new InfoReq());
            put(CommType.PRINT_ASCENDING, new AscendingReq());
            put(CommType.PRINT_FIELD_ASCENDING_COLOR, new AscendingColorReq());
            put(CommType.FILTER_CONTAINS_NAME, new FilterReq());
        }};
        ClientNetwork network;
        try {
            network = new ClientNetwork("localhost", 7000);
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
        LogReg logReg;
        try {
            logReg = new LogReg(network){{run();}};
        } catch (IOException e) {
            System.out.println("Ошибка сети!");
            return;
        }
        ClientTerminal terminal = new ClientTerminal(commands, network, logReg.getResult());
        terminal.start();
    }
}
