package src.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import src.Entity.Notification;
import src.Entity.Storage;
import src.Entity.User;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


public class NotificationDAO extends DAO<Notification>
{
    @Override
    public Notification get(Integer id)
    {
        return session.get(Notification.class,id);
    }
    @Override
    public List<Notification>getAll()
    {
        //Query query = session.createQuery("Select n from Notification n");
        //return (List<Notification>) query.getResultList();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Notification> cq =cb.createQuery(Notification.class);
        Root<Notification> root = cq.from(Notification.class);
        cq.select(root);
        Query<Notification> query = session.createQuery(cq);
        return query.getResultList();
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
    @Override
    public boolean save(Notification n)
    {
            executeInsideTransaction(session->session.persist(n));
            return true;

    }
    @Override
    public void delete(Notification n)
    {
        executeInsideTransaction(session->session.remove(n));
    }
    @Override
    public void update(Notification n)
    {
        executeInsideTransaction(session->session.merge(n));
    }
    public NotificationDAO()
    {
        super();
    }

}
