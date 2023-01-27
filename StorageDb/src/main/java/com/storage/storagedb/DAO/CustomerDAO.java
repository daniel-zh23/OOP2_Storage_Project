package com.storage.storagedb.DAO;

import com.storage.storagedb.DAO.Contracts.DAO;
import com.storage.storagedb.Entity.Customer;

import java.util.List;
import java.util.stream.Stream;

public class CustomerDAO extends DAO<Customer>
{
    @Override
    public Customer get(Integer id)
    {
        return session.get(Customer.class,id);
    }
    @Override
    public Stream<Customer> getAll()
    {
        try {
            return session.createQuery("Select r from Customer r", Customer.class).stream();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }
    }
    public List<Customer> getAllList()
    {
        return this.getAll().toList();
    }
}
