package com.storage.storagedb.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.function.Consumer;
import java.util.stream.Stream;

public abstract class DAO<T> implements AutoCloseable{
    protected Session session;
    private SessionFactory factory;
    public abstract T get (Integer id);
    public abstract Stream<T> getAll();
    public abstract boolean save(T t);
    public abstract void update(T t);
    public abstract void delete(T t);
    DAO()
    {
        this.factory = new Configuration().configure().buildSessionFactory();
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

    public void openSession(){
        session = factory.openSession();
    }

    @Override
    public void close()
    {
        session.close();
    }
}
