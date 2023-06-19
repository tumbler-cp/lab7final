package db;


import comm.User;
import dragon.*;
import exceptions.IncorrectValueException;
import exceptions.KeyExistException;
import exceptions.NoSuchOptionException;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;


public class DragonBase {
    private final Statement statement;

    public DragonBase(String url, String username, String password) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(url, username, password);
        statement = connection.createStatement();
    }

    public synchronized boolean insert(Integer integer, Dragon obj) throws KeyExistException {
        String query = "INSERT INTO dragons(key, name, coordinates, creation_date, color, character, type, cave, owner, age) " +
                "VALUES (" + integer + ",'" +
                obj.getName() + "','" +
                obj.getCoordinates() + "','" +
                obj.getCreationDate() + "','" +
                obj.getColor() + "','" +
                obj.getCharacter() + "','" +
                obj.getType() + "'," +
                obj.getCave() + ",'" +
                obj.getOwner() + "'," +
                obj.getAge() +
                ");";
        System.out.println(query);
        try {
            ResultSet set = statement.executeQuery(query);
            if (set.getMetaData().getColumnCount() > 0) throw new KeyExistException();
            set.close();
        } catch (SQLException e){
            if (e.getErrorCode() == 23505){
                throw new KeyExistException();
            }
        }
        return true;
    }

    public synchronized boolean delete(Integer integer, User owner) throws SQLException {
        Dragon d = get(integer);
        if (!d.getOwner().equals(owner.getLogin())){
            return false;
        }
        String query = "DELETE FROM dragons WHERE key=" + integer + ";";
        statement.execute(query);
        return true;
    }

    public Map<Integer, Dragon> map() throws SQLException, NoSuchOptionException, IncorrectValueException {
        Map<Integer, Dragon> ret = new HashMap<>();
        String query = "SELECT * FROM dragons;";
        ResultSet set = statement.executeQuery(query);
        Dragon d = new Dragon(null, null,0,null,null,null,null);
        while (set.next()){
            int k = set.getInt(1);
            d.setId(set.getInt(2));
            d.setName(set.getString(3));
            d.setCoordinates(Coordinates.toCoordinates(set.getString(4)));
            d.setCreationDate(set.getString(5));
            d.setColor(Color.toColor(set.getString(6)));
            d.setCharacter(DragonCharacter.parse(set.getString(7)));
            d.setType(DragonType.toDragonType(set.getString(8)));
            d.setCave(new DragonCave(set.getInt(9)));
            d.setOwner(set.getString(10));
            d.setAge(set.getInt(11));
            ret.put(k, d);
        }
        set.close();
        return ret;
    }

    public synchronized void clear(User user) throws SQLException{
        String query = "DELETE FROM dragons WHERE owner='"+user.getLogin()+"';";
        statement.execute(query);
    }

    public Dragon get(Integer integer) throws SQLException {
        String query = "SELECT  * FROM dragons WHERE key="+integer + ";";
        ResultSet set = statement.executeQuery(query);
        set.next();
        String[] data = new String[11];
        for (int i = 1; i< set.getMetaData().getColumnCount() + 1; i++){
            String current = set.getString(i);
            System.out.println(current);
            data[i-1] = current;
        }
        set.close();
        try {
            return Dragon.parseDrag(data);
        } catch (NoSuchOptionException e) {
            throw new RuntimeException(e);
        }
    }
    public void update(Integer k, Dragon d, User u) throws SQLException {
        String query = "UPDATE dragons SET " +
                "name = '" + d.getName() + "'," +
                "coordinates = '" + d.getCoordinates() + "'," +
                "creation_date = '" + d.getCreationDate() + "'," +
                "color = '" + d.getColor() + "'," +
                "character = '" + d.getCharacter() + "'," +
                "type = '" + d.getType() + "'," +
                "cave = " + d.getCave().toString() + "," +
                "age = " + d.getAge() +
                " WHERE key=" + k + " AND owner='"+u.getLogin() +"';";
        statement.execute(query);
    }
}
