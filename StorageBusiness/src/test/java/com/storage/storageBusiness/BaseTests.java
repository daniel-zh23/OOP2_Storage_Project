package com.storage.storageBusiness;

import com.storage.storageBusiness.Services.AgentService;
import com.storage.storageBusiness.Services.NotificationService;
import com.storage.storageBusiness.Services.OwnerService;
import com.storage.storagedb.DAO.NotificationDAO;
import com.storage.storagedb.DAO.UserDAO;
import com.storage.storagedb.Entity.Agent;
import com.storage.storagedb.Entity.Notification;
import com.storage.storagedb.Entity.Owner;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;

@ExtendWith(MockitoExtension.class)
public abstract class BaseTests {
    public static UserDAO _userDao;

    public static AgentService _agentService;

    static NotificationDAO _notificationDao;

    static NotificationService _notificationService;

    static OwnerService _ownerService;

    public static SessionFactory sessionFactory = null;

    @BeforeAll
    public static void setup(){
        try {
            StandardServiceRegistry standardRegistry
                    = new StandardServiceRegistryBuilder()
                    .configure("hibernate-test.cfg.xml")
                    .build();

            Metadata metadata = new MetadataSources(standardRegistry)
                    .getMetadataBuilder()
                    .build();

            sessionFactory = metadata
                    .getSessionFactoryBuilder().build();

            //UserDao inject
            Class<?> userDAOClass = UserDAO.class;
            Object userDaoObject = userDAOClass.getDeclaredConstructor().newInstance();
            Field f1= userDaoObject.getClass().getSuperclass().getDeclaredField("factory");
            f1.setAccessible(true);
            f1.set(userDaoObject, sessionFactory);

            //AgentService inject
            Class<?> agentServiceClass = AgentService.class;
            Object agentServiceObject = agentServiceClass.getDeclaredConstructor().newInstance();
            Field f2 = agentServiceObject.getClass().getDeclaredField("_userDao");
            f2.setAccessible(true);
            _userDao = (UserDAO) userDaoObject;
            f2.set(agentServiceObject, _userDao);

            //NotificationDao inject
            Class<?> notificationDAOClass = NotificationDAO.class;
            Object notificationDaoObject = notificationDAOClass.getDeclaredConstructor().newInstance();
            Field f3 = notificationDaoObject.getClass().getSuperclass().getDeclaredField("factory");
            f3.setAccessible(true);
            f3.set(notificationDaoObject, sessionFactory);

            //NotificationService inject
            Class<?> notificationServiceClass = NotificationService.class;
            Object notificationServiceObject = notificationServiceClass.getDeclaredConstructor().newInstance();
            Field f4 = notificationServiceObject.getClass().getDeclaredField("_notificationDao");
            f4.setAccessible(true);
            _notificationDao = (NotificationDAO) notificationDaoObject;
            f4.set(notificationServiceObject, _notificationDao);

            //OwnerService inject
            Class<?> ownerServiceClass = OwnerService.class;
            Object ownerServiceObject = ownerServiceClass.getDeclaredConstructor().newInstance();
            Field f5 = ownerServiceObject.getClass().getDeclaredField("_userDao");
            f5.setAccessible(true);
            f4.set(ownerServiceObject, _userDao);

            //Seeding test data
            _userDao.openSession();
            _notificationDao.openSession();
            _userDao.save(new Agent("test", "TEST", "test1", "agent1@abv.bg", "+1234", 10.0, "Google","pass"));
            _userDao.save(new Agent("test", "TEST", "test2", "agent2@abv.bg", "+1234", 15.0, "Google","pass"));
            Agent agent = new Agent("test", "TEST", "test3", "agent3@abv.bg", "+1234", 5.0, "Google","pass");
            agent.setActive(false);
            _userDao.save(agent);
            _userDao.save(new Owner("test", "TEST", "test4", "owner1@abv.bg", "+1234","pass"));
            _notificationDao.save(new Notification(1, "Notification1"));
            _notificationDao.save(new Notification(1, "Notification2"));
            var notification = new Notification(1, "Notification3");
            notification.setRead(true);
            _notificationDao.save(notification);
            _notificationDao.save(new Notification(2, "Notification4"));

            _userDao.close();
            _notificationDao.close();
            _agentService = (AgentService) agentServiceObject;
            _notificationService = (NotificationService) notificationServiceObject;
            _ownerService = (OwnerService) ownerServiceObject;

        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }
}
