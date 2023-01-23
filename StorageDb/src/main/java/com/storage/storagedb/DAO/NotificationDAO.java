package com.storage.storagedb.DAO;

import com.storage.storagedb.Entity.Notification;
import org.hibernate.query.Query;
import com.storage.storagedb.Entity.User;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.stream.Stream;


public class NotificationDAO extends DAO<Notification>
{
    @Override
    public Notification get(Integer id)
    {
        return session.get(Notification.class,id);
    }
    @Override
    public Stream<Notification> getAll()
    {
        //TODO: getAll notificationDAO
//        //Query query = session.createQuery("Select n from Notification n");
//        //return (List<Notification>) query.getResultList();
//        CriteriaBuilder cb = session.getCriteriaBuilder();
//        CriteriaQuery<Notification> cq =cb.createQuery(Notification.class);
//        Root<Notification> root = cq.from(Notification.class);
//        cq.select(root);
//        Query<Notification> query = session.createQuery(cq);
//        return query.getResultList();
        return null;
    }
    public List<Notification> getByUser(User user)
    {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Notification> cq = cb.createQuery(Notification.class);
        Root<Notification> root = cq.from(Notification.class);
        cq.select(root).where(cb.equal(root.get("user"),user));
        Query<Notification>  query = session.createQuery(cq);


        //Query query = session.createQuery("Select n from Notification n where  like :usr");
        //query.setParameter("usr",user);
        return (List<Notification>) query.getResultList();
    }

    public NotificationDAO()
    {
        super();
    }

}
