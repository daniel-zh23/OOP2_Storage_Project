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
        try {
            var notifications = session.createQuery("Select n from Notification n",Notification.class).stream();
            return notifications;
        }
        catch(Exception e)
        {
            return null;
        }
//        //Query query = session.createQuery("Select n from Notification n");
//        //return (List<Notification>) query.getResultList();
//        CriteriaBuilder cb = session.getCriteriaBuilder();
//        CriteriaQuery<Notification> cq =cb.createQuery(Notification.class);
//        Root<Notification> root = cq.from(Notification.class);
//        cq.select(root);
//        Query<Notification> query = session.createQuery(cq);
//        return query.getResultList();
    }
    public List<Notification>getByUserId(int id,boolean unreadOnly)
    {
        try {
           var notifications= session.createQuery("Select n from Notification n",Notification.class).stream()
                    .filter(n->n.getUserId()==id).filter(n->!n.getRead());
           return notifications.toList();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }
    }
    public List<Notification> getByUser(User user,boolean unreadOnly)
    {
        try
        {
            var notifications = session.createQuery("Select n from Notification n",Notification.class).stream()
                    .filter(n->n.getUser().equals(user));
            if(unreadOnly)
            {
                notifications.filter(n->n.getRead()==false);
            }
            return notifications.toList();
        }
        catch(Exception e)
        {
            return null;
        }
//        CriteriaBuilder cb = session.getCriteriaBuilder();
//        CriteriaQuery<Notification> cq = cb.createQuery(Notification.class);
//        Root<Notification> root = cq.from(Notification.class);
//        cq.select(root).where(cb.equal(root.get("user"),user));
//        Query<Notification>  query = session.createQuery(cq);


        //Query query = session.createQuery("Select n from Notification n where  like :usr");
        //query.setParameter("usr",user);
      //  return (List<Notification>) query.getResultList();
    }

    public NotificationDAO()
    {
        super();
    }

}
