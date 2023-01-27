package com.storage.storagedb.DAO;

import com.storage.storagedb.Entity.Status;

import java.util.stream.Stream;

public class StatusDAO extends DAO<Status>{
    @Override
    public Status get(Integer id) {
        //return null;
        return session.get(Status.class,id);
    }

    @Override
    public Stream<Status> getAll() {
        return session.createQuery("Select s from Status s").stream();
    }
}
