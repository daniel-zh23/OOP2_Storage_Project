package com.storage.storagedb.DAO;
import java.util.*;
import java.util.stream.Stream;

import com.storage.storagedb.Entity.Sales;
import com.storage.storagedb.Entity.User;
import org.hibernate.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class UserDAO extends DAO<User>
{
    public UserDAO()
    {
        super();
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

    public Stream<User> getUsernames(){
        return session.createQuery("Select u from User u", User.class).stream();
    }

    @Override
    public User get(Integer id) {
        return session.find(User.class, id);
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
}
