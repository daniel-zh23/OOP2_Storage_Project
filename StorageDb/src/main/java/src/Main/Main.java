package src.Main;

import src.DAO.NotificationDAO;
import src.DAO.UserDAO;
import src.Entity.*;

import java.util.List;

public class Main {
    public static void main(String[] args)
    {
        UserDAO user = new UserDAO();
        NotificationDAO notisDao = new NotificationDAO();

       // Owner owner = (Owner)user.getByUsername("mkd");
        //Storage storage = new Storage(23.0,40.0,12.0,"adresen adres",0,owner);
        //owner.addStorage(storage);

        //User user1 = user.get(11);
       //Notification n1= new Notification(user1,"Someval");
        //notisDao.save(n1);
        List<Notification> nlist =notisDao.getAll();
        //user.update(owner);
       //System.out.println(user1.toString());
       // Notification n2=nlist.get(0);
       // n2.setRead(true);
        //notisDao.delete(n2);
       // notisDao.update(n2);
      //nlist =notisDao.getByUser(user1);
       for(Notification n :nlist)
       {
          System.out.println(n.toString());
       }



       // User owner= new Owner("Mitko","Mihailov","mkd","mm@mitko.com","0887776");
        // if(user.save(owner))
       //  {
         //    System.out.println("Saved");
        // }
        // else System.out.println("Error in save");
    }
}
