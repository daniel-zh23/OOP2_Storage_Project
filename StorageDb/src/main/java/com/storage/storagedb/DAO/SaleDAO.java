package com.storage.storagedb.DAO;

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
    public Sales get(Integer id)
    {
        return session.get(Sales.class,id);
    }
    @Override
    public Stream<Sales> getAll()
    {
        //TODO: getAll SalesDAO
//        CriteriaBuilder cb = session.getCriteriaBuilder();
//        CriteriaQuery<Sales> cq =cb.createQuery(Sales.class);
//        Root<Sales> root = cq.from(Sales.class);
//        cq.select(root);
//        Query<Sales> query = session.createQuery(cq);
//        return query.getResultList();
        return null;
    }
    public List<Sales>getByAgent(Agent agent)
    {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Sales> cq = cb.createQuery(Sales.class);
        Root<Sales>root = cq.from(Sales.class);
        cq.select(root).where(cb.equal(root.get("agent"),agent));
        Query<Sales> query = session.createQuery(cq);
        return (List<Sales>) query.getResultList();
    }

    @Override
    public boolean save(Sales s)
    {
        executeInsideTransaction(session->session.persist(s));
        return true;

    }
    @Override
    public void delete(Sales s)
    {
        executeInsideTransaction(session->session.remove(s));
    }
    @Override
    public void update(Sales s)
    {
        executeInsideTransaction(session->session.merge(s));
    }



    public SaleDAO()
    {
        super();
    }
}
