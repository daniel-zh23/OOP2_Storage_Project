package com.storage.storagedb.DAO;

import com.storage.storagedb.Entity.Owner;
import com.storage.storagedb.Entity.Storage;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class StorageDAO extends DAO <Storage>{
@Override
public Storage get(Integer id)
{
    return session.get(Storage.class,id);
}
@Override
public List<Storage> getAll()
{
    CriteriaBuilder cb = session.getCriteriaBuilder();
    CriteriaQuery<Storage> cq =cb.createQuery(Storage.class);
    Root<Storage> root = cq.from(Storage.class);
    cq.select(root);
    Query<Storage> query = session.createQuery(cq);
    return query.getResultList();
}
    public List<Storage> getByOwner(Owner owner)
    {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Storage> cq = cb.createQuery(Storage.class);
        Root<Storage> root = cq.from(Storage.class);
        cq.select(root).where(cb.equal(root.get("owner"),owner));
        Query<Storage>  query = session.createQuery(cq);
        return (List<Storage>) query.getResultList();
    }
    @Override
    public boolean save(Storage s)
    {
            executeInsideTransaction(session->session.persist(s));
            return true;
    }
    @Override
    public void delete(Storage s)
    {
        executeInsideTransaction(session->session.remove(s));
    }
    @Override
    public void update(Storage s)
    {
        executeInsideTransaction(session->session.merge(s));
    }



    public StorageDAO()
    {
        super();
    }
}
