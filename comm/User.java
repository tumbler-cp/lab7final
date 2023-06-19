package comm;

import java.io.Serializable;

public class User implements Serializable {
    private final String login;
    private final String password;

    public User(String LOGIN, String PASSWORD){
        this.login = LOGIN;
        this.password = PASSWORD;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
