package comm;

import java.io.Serializable;

/**
 * Интерфейс описывающий методы для запросов команд от клиентского приложения.
 * @param <T> Тип аргумента с которым работает данная команда
 */
public interface CommandClient<T> extends Serializable {
    CommType getType();
    T getArg();
    void setArgLine(String[] argLine); //Аргументы вводимые при вводе команды
    String[] getArgLine();
    boolean create();
}
