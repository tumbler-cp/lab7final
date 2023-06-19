package server;

import comm.CommType;
import commands.*;
import db.DragonBase;
import db.UserBase;
import managers.CommandManager;
import managers.DragonManager;

import java.io.IOException;
import java.sql.SQLException;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerNetwork network = new ServerNetwork("localhost", 7000);
        String url = "jdbc:postgresql://localhost:11111/studs";
        String user = "s368994";
        String password = "511xXyUCKOAkcbJM";
        DragonBase dragons;
        UserBase users;
        try {
            dragons = new DragonBase(url, user, password);
            users = new UserBase(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            System.out.println("Драйвера не найдены");
            return;
        }

        DragonManager manager;
        try {
            manager = new DragonManager(dragons);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        CommandManager commandManager = new CommandManager()
        {{
            add(CommType.HELP, new Help(this));
            add(CommType.SHOW, new Show(manager));
            add(CommType.INSERT, new Insert(manager));
            add(CommType.REMOVE_KEY, new RemoveKey(manager));
            add(CommType.CLEAR, new Clear(manager));
            add(CommType.REMOVE_LOWER_KEY, new RemoveLower(manager));
            add(CommType.UPDATE, new Update(manager));
            add(CommType.REPLACE_IF_GREATER, new ReplaceGreater(manager));
            add(CommType.INFO, new Info(manager));
            add(CommType.FILTER_CONTAINS_NAME, new Filter(manager));
            add(CommType.PRINT_ASCENDING, new Ascending(manager));
            add(CommType.PRINT_FIELD_ASCENDING_COLOR, new AscendingColor(manager));
        }};
        ServerTerminal terminal = new ServerTerminal(commandManager, network, users);
        try {
            terminal.run();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        network.close();
    }
}
