package managers;

import comm.User;
import db.DragonBase;
import dragon.Dragon;
import exceptions.IncorrectValueException;
import exceptions.KeyExistException;
import exceptions.NoSuchOptionException;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

public class DragonManager {
    private ConcurrentHashMap<Integer, Dragon> dragons;
    LocalDate creationDate;
    private final DragonBase dragonBase;
    public DragonManager(DragonBase dragonBase) throws SQLException, NoSuchOptionException, IncorrectValueException
    {
        this.dragonBase = dragonBase;
        dragons = new ConcurrentHashMap<>();
        for(Integer k : dragonBase.map().keySet()){
            dragons.put(k, dragonBase.map().get(k));
        }
        creationDate = LocalDate.now();
    }
    public void add(Integer KEY, Dragon DRAGON, User user) throws KeyExistException {
        Dragon d = DRAGON;
        d.setOwner(user.getLogin());
        dragonBase.insert(KEY, DRAGON);
        try {
            d = dragonBase.get(KEY);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        dragons.put(KEY, d);
    }
    public boolean del(Integer KEY, User user){
        try {
            if (dragonBase.delete(KEY, user)) {
                dragons.remove(KEY);
                return true;
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void clear(User user) throws SQLException {
        dragonBase.clear(user);
        dragons = new ConcurrentHashMap<>();
        try {
            for(Integer k : dragonBase.map().keySet()){
                dragons.put(k, dragonBase.map().get(k));
            }
        } catch (NoSuchOptionException | IncorrectValueException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Integer k, Dragon d, User user) throws SQLException {
        dragonBase.update(k, d, user);
    }

    public Dragon get(Integer KEY){
        return dragons.get(KEY);
    }
    public Stream<Dragon> stream(){
        return dragons.values().stream();
    }
    public Stream<Integer> keyStream(){return dragons.keySet().stream();}
    public boolean keyCheck(int k){
        return dragons.containsKey(k);
    }
    public int size(){return dragons.size();}

    public LocalDate getCreationDate() {
        return creationDate;
    }
}
