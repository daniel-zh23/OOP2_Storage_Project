package com.storage.storagedb.DAO;

import com.storage.storagedb.Entity.Owner;
import com.storage.storagedb.Entity.Sales;
import org.hibernate.query.Query;
import com.storage.storagedb.Entity.Agent;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.stream.Stream;

public class SaleDAO extends DAO<Sales> {
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

    public List<Sales> getByOwner(Owner owner)
    {
        try {
            return this.getAll().filter(s -> s.getOwner().equals(owner)).toList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    public List<Sales> getByOwnerId(int id) {
        try {
            return this.getAll().filter(n -> n.getOwner().getId() == id).toList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public SaleDAO()
    {
        super();
    }
}
