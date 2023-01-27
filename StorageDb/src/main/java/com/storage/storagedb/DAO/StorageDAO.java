package com.storage.storagedb.DAO;

import com.storage.storagedb.DAO.Contracts.DAO;
import com.storage.storagedb.Entity.Storage;

import java.util.stream.Stream;

public class StorageDAO extends DAO<Storage> {
@Override
public Storage get(Integer id)
{
    return session.get(Storage.class,id);
}
@Override
public Stream<Storage> getAll()
{
    var storages = session.createQuery("select s from Storage s", Storage.class).stream();
    return storages;
}
    public Stream<Storage> getByOwnerId(int id)
    {
        var storages = session.createQuery("select s from Storage s join s.owner", Storage.class).stream()
                .filter(s -> s.getOwner().getId() == id);
        return storages;
    }

    public StorageDAO()
    {
        super();
    }
}
