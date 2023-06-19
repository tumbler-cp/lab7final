package managers;

import comm.CommType;
import interfaces.Command;

import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

public class CommandManager {
    private final ConcurrentHashMap<CommType, Command<?>> commands;
    public CommandManager(){
        commands = new ConcurrentHashMap<>();
    }
    public void add(CommType TYPE, Command<?> COMMAND) {
        commands.put(TYPE, COMMAND);
    }
    public void del(CommType TYPE){
        commands.remove(TYPE);
    }
    public Command<?> get(CommType TYPE){
        return commands.get(TYPE);
    }
    public Stream<Command<?>> stream () {
        return commands.values().stream();
    }
}
