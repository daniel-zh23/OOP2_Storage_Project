package com.storage.storagedb.Main;

import com.storage.storagedb.DAO.NotificationDAO;
import com.storage.storagedb.DAO.StatusDAO;
import com.storage.storagedb.DAO.StorageDAO;
import com.storage.storagedb.DAO.UserDAO;
import com.storage.storagedb.Entity.*;


public class Main {
    public static void main(String[] args)
    {
        UserDAO user = new UserDAO();
        StorageDAO storage = new StorageDAO();
        StatusDAO status = new StatusDAO();
        NotificationDAO notifications= new NotificationDAO();

        user.openSession();
        storage.openSession();
        status.openSession();
        notifications.openSession();


        //Seeding with status rows
        var status1 = new Status("Free");
        var status2 = new Status("Leased");
        var status3 = new Status("For lease");

        status.save(status1);
        status.save(status2);
        status.save(status3);

        //Seed Admin
        //Pass: admin1234
        var passAdmin = "ac9689e2272427085e35b9d3e3e8bed88cb3434828b43b86fc0596cad4c6e270";
        Admin admin = new Admin("Daniel", "Zhekov", "daniel23", "daniel@abv.bg", "1234", "admin1234");
        admin.setFirstLogin(false);

        //Seed Agent
        //Pass: agent1234
        var passAgent = "6fd42aa949e5c54374638dd066e2017bb594b6d4899bbaeecf9dfbd0ceaa514f";
        Agent agent = new Agent("Kris", "Mihalev", "mihalev64", "kris@abv.bg", "1234", 255d, "Google", "agent1234");
        agent.setFirstLogin(false);

        var passAgent2 = "6fd42aa949e5c54374638dd066e2017bb594b6d4899bbaeecf9dfbd0ceaa514f";
        Agent agent2 = new Agent("Strahil", "Strahilov", "strahcho24", "strahil@abv.bg", "1234", 255d, "Oracle", "agent1234");
        agent2.setFirstLogin(false);

        //Seed Owner
        //Pass: Ivan+1234
        Owner owner = new Owner("Ivan", "Ivanov", "ivanov25", "kris@abv.bg", "1234", "owner1234");
        owner.setFirstLogin(false);

        Notification n = new Notification(6,"NotificationTest1");
        Notification n2 = new Notification(6,"NotificationTest2");
        Notification n3 = new Notification(4,"NotificationTest1");

        Storage storage1 = new Storage(25.5, 25.5, 25.5, "Kur", 1, 4);
        Storage storage2 = new Storage(25.5, 25.5, 25.5, "Kur", 1, 4);



        user.save(owner);
        user.save(admin);
        user.save(agent);
        storage.save(storage1);
        storage.save(storage2);
        storage1.getAgents().add(agent);
        storage1.getAgents().add(agent2);

        storage.update(storage1);

        notifications.save(n);
        notifications.save(n2);
        notifications.save(n3);
        notifications.close();
        user.close();
        storage.close();
        status.close();
    }
}
