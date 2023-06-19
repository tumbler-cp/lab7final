package interfaces;

import comm.User;

import java.io.IOException;

public interface Command<T> {
    boolean execute();
    //T - тип аргумента команды
    T getArgs();
    void setArg(T arg);
    void setLineArgs(String[] lineArgs);
    String description();
    String response();
}
