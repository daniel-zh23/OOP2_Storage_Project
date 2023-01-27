package com.storage.storagedb.DAO;

import com.storage.storagedb.DAO.Contracts.DAO;
import com.storage.storagedb.Entity.Sales;
import com.storage.storagedb.Entity.Agent;

import java.util.List;
import java.util.stream.Stream;

public class RentDAO extends DAO<Sales> {
    @Override
    public Sales get(Integer id) {
        return session.get(Sales.class, id);
    }

    @Override
    public Stream<Sales> getAll() {
        return session.createQuery("Select s from Sales s", Sales.class).stream();
    }

    public List<Sales> getByAgent(Agent agent) {
        try {
            return this.getAll().filter(s -> s.getAgent().equals(agent)).toList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Sales> getByAgentId(int id) {
        try {
            return this.getAll().filter(n -> n.getAgent().getId() == id).toList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    public List<Sales>getAllActive()
    {
        try
        {
            return this.getAll().filter(n->n.isActive()).toList();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public RentDAO()
    {
        super();
    }
}
