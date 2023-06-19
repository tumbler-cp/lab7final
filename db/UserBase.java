package db;

import comm.User;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class UserBase {
    private Statement statement;
    private ResultSet set;
    private Connection connection;
    public UserBase(String url, String username, String password) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection(url, username, password);
        statement = connection.createStatement();
    }
    public boolean insert(User obj) {
        String query = "INSERT INTO users(name, password) VALUES ('"+ obj.getLogin() + "','" + hasher(obj.getPassword()) + "');";
        try {
            statement.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
    public boolean find(User user) {
        String query = "SELECT count(*) from (" + "SELECT * FROM users WHERE name='"+ user.getLogin() + "') AS GG WHERE password='" + hasher(user.getPassword())+"';";
        System.out.println(query);
        System.out.println(hasher(user.getPassword()));
        try {
            set = statement.executeQuery(query);
            set.next();
            return set.getInt("count") > 0;
        } catch (SQLException e){
            return false;
        }
    }
    private String hasher(String data){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] messageDigest = md.digest(data.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashText = no.toString(16);
            while (hashText.length() < 32) {
                hashText = "0" + hashText;
            }
            return hashText;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
