package com.storage.storagedb.DAO;

import com.storage.storagedb.Entity.Renter;

import java.util.List;
import java.util.stream.Stream;

public class RenterDAO extends DAO<Renter>
{
    @Override
    public Renter get(Integer id)
    {
        return session.get(Renter.class,id);
    }
    @Override
    public Stream<Renter> getAll()
    {
        try {
            return session.createQuery("Select r from Renter r", Renter.class).stream();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }
    }
    public List<Renter> getAllList()
    {
        return this.getAll().toList();
    }
}
