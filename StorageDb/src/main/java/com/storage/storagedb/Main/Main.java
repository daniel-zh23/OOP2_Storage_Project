package com.storage.storagedb.Main;

import com.storage.storagedb.DAO.UserDAO;
import com.storage.storagedb.Entity.Admin;
import com.storage.storagedb.Entity.Agent;

import java.util.List;

public class Main {
    public static void main(String[] args)
    {
        UserDAO user = new UserDAO();

        //admin1234
        var passAdmin = "ac9689e2272427085e35b9d3e3e8bed88cb3434828b43b86fc0596cad4c6e270";
        Admin admin = new Admin("Daniel", "Zhekov", "daniel23", "daniel@abv.bg", "1234", passAdmin);

        //admin1234
        var passAgent = "6fd42aa949e5c54374638dd066e2017bb594b6d4899bbaeecf9dfbd0ceaa514f";
        Agent agent = new Agent("Kris", "Mihalev", "mihalev64", "kris@abv.bg", "1234", 255d, "Google", passAgent);

        user.save(admin);
        user.save(agent);
        user.close();
    }
}
