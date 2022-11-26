package src.Entity.UserManager;
import src.Entity.Agent;
import src.Entity.Users;
import src.Entity.Admin;
import src.Entity.Owner;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;


public class UserManager
{
    private static SessionFactory factory;
    public static void main(String[] args)
    {
        try
        {
            factory=new Configuration().configure().buildSessionFactory();
        } catch(Throwable ex)
        {
            System.err.println(ex);
            throw new ExceptionInInitializerError(ex);
        }
        UserManager mu = new UserManager();
       // Integer user1= mu.addOwner("Mitko","Mitkov");
       // Integer user2 = mu.addAgent("Agent", "Agentov");
        //System.out.println(user1);
        //System.out.println(user2);
            //mu.addAdmin();
        Users user1 = mu.getUser(1);
        Users user2 = mu.getUser(2);
        Users user3 = mu.getUser(3);
        System.out.println(user1.toString());
        System.out.println(user2.toString());
        System.out.println(user3.toString());
        //System.out.println(mu.fetchByUserName("useragent").toString());




       // Owner owner1=(Owner) mu.getUser();
      //  System.out.print(owner1.toString());
        //System.out.println(user1);
    }
    public Users fetchByUserName(String username)
    {

        Session session = factory.openSession();
        Users target=new Users();
        Query query = session.createQuery("select u"+" from Users u "+"where u.username like :usr");
        query.setParameter("usr", username);
        target= (Users) query.getSingleResult();


        return target;
    }
    public void addAdmin()
    {

        Session session= factory.openSession();
        Transaction tx= session.beginTransaction();
        Admin admin= new Admin("Admin","Adminov","admin","email@admin","000000");
        session.save(admin);
        tx.commit();
    }
    public Integer addOwner(String fname, String lname)
    {
        Session session = factory.openSession();
        Transaction tx = null;
        Integer id =null;
        try
        {
            tx= session.beginTransaction();
            Owner owner = new Owner(fname, lname,"user1","email@email","089696969");
            id=(Integer) session.save(owner);
            tx.commit();
        }catch (HibernateException e)
        {
            if (tx!=null) tx.rollback();
            e.printStackTrace();

        }
        finally {session.close();}
        return id;
    }
    public Integer addAgent(String fname,String lname)
    {
        Session session = factory.openSession();
        Transaction tx= session.beginTransaction();
        Integer id;
        Agent agent= new Agent(fname, lname,"useragent","agent@email","08432",1233.2,"agent.co");
        id=(Integer)session.save(agent);
        tx.commit();
        return id;
    }
    public Users getUser(Integer id)
    {
        Session session=factory.openSession();


        Users user = session.get(Users.class,id);




        return user;

    }

}

