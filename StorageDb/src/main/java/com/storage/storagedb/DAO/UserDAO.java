package com.storage.storagedb.DAO;
import java.util.stream.Stream;

import com.storage.storagedb.Entity.User;
import org.hibernate.*;
public class UserDAO extends DAO<User>
{
    public UserDAO()
    {
        super();
    }


    public User getByUsername(String username)
    {
    try {
        var user =  session.createQuery("Select u from User u", User.class).stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .get();
        return user;
    } catch (Exception e){
        return null;
    }
    }

    public Stream<User> getUsernames(){
        try {
            return session.createQuery("Select u from User u", User.class).stream();
        } catch(Exception e) {
            return null;
        }
    }

    @Override
    public User get(Integer id) {
        return session.find(User.class, id);
    }

    @Override
    public Stream<User> getAll() {
        return session.createQuery("Select u from User u").stream();
    }

}
