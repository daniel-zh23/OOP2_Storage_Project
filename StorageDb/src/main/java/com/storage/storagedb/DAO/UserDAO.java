package com.storage.storagedb.DAO;
import java.util.*;

import com.storage.storagedb.Entity.User;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class UserDAO extends DAO<User>
{
    @Override
    public User get(Integer id) {
        return session.find(User.class, id);
    }
    public User getByUsername(String username)
    {
    //Query query  = session.createQuery("Select u from User u where u.username like :usr" );
    //query.setParameter("usr",username);
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);
        cq.select(root).where(cb.equal(root.get("username"), username));
        Query<User> query = session.createQuery(cq);
    return (User)query.getSingleResult();
    }

    public boolean exists(String username){
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<User> root = cq.from(User.class);
        cq.select(cb.count(root)).where(cb.equal(root.get("username"), username));
        Query<Long> query = session.createQuery(cq);

        return query.getSingleResult() != 0;
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
