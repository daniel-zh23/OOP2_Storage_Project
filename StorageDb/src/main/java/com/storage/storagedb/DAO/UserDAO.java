package com.storage.storagedb.DAO;
import java.util.*;

import com.storage.storagedb.Entity.User;
import org.hibernate.*;

public class UserDAO extends DAO<User>
{
    @Override
    public User get(Integer id) {
        return session.find(User.class, id);
    }
    public User getByUsername(String username)
    {
    Query query  = session.createQuery("Select u from User u where u.username like :usr" );
    query.setParameter("usr",username);
    try {
        return (User)query.getSingleResult();
    } catch (Exception e){
        return null;
    }
    }
    @Override
    public List<User> getAll() {

        Query query = session.createQuery("Select u from User u");
        return query.getResultList();
    }

    @Override
    public boolean save(User user) // returns true if user is successfully saved in DB and false if not
    {
        try {
            executeInsideTransaction(session -> session.persist(user));
        }
        catch (Exception ex)
        {
            return false;
        }
        return true;
    }

    @Override
    public void delete(User user)
    {
        executeInsideTransaction(session -> session.remove(user));
    }
    @Override
    public void update(User user)
    {
        executeInsideTransaction(session -> session.merge(user));
    }

    public UserDAO()
    {
      super();
    }
}
