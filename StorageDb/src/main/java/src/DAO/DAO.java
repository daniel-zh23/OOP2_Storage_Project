package src.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.*;
import java.util.function.Consumer;

public abstract class DAO<T> implements AutoCloseable{
    protected Session session;
    public abstract T get (Integer id);
    public abstract List<T> getAll();
    public abstract boolean save(T t);
    public abstract void update(T t);
    public abstract void delete(T t);
    DAO()
    {
        SessionFactory factory=new Configuration().configure().buildSessionFactory();
        session = factory.openSession();
    }
    protected void executeInsideTransaction(Consumer<Session> action) {
        Transaction tx = session.getTransaction();
        try {
            tx.begin();
            action.accept(session);
            tx.commit();
        }
        catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }
    @Override
    public void close()
    {
        session.close();
    }
}
