package com.storage.storagedb.DAO;

import com.storage.storagedb.DAO.Contracts.DAO;
import com.storage.storagedb.Entity.Notification;
import com.storage.storagedb.Entity.User;

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
    }

    public NotificationDAO()
    {
        super();
    }

}
