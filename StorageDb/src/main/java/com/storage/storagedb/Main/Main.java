package com.storage.storagedb.Main;

import com.storage.storagedb.DAO.StatusDAO;
import com.storage.storagedb.DAO.UserDAO;
import com.storage.storagedb.Entity.*;


public class Main {
    public static void main(String[] args)
    {
        UserDAO user = new UserDAO();
       StatusDAO status = new StatusDAO();

        user.openSession();
        status.openSession();

        //Seeding with status rows
        var status1 = new Status("Free");
        var status2 = new Status("Leased");
        var status3 = new Status("For lease");

        status.save(status1);
        status.save(status2);
        status.save(status3);
        status.close();

        //Seed Admin
        //Pass: admin1234
        var passAdmin = "ac9689e2272427085e35b9d3e3e8bed88cb3434828b43b86fc0596cad4c6e270";
        Admin admin = new Admin("Admin", "ADMIN", "admin", "daniel@abv.bg", "+1234", "admin");
        admin.setFirstLogin(false);

        user.save(admin);
        user.close();
    }
}
