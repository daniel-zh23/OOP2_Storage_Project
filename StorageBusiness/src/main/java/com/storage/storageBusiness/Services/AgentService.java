package com.storage.storageBusiness.Services;

import com.google.common.hash.Hashing;
import com.storage.storageBusiness.Models.AgentViewModel;
import com.storage.storagedb.DAO.UserDAO;
import com.storage.storagedb.Entity.Agent;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class AgentService {
    private final UserDAO _userDao;

    public AgentService(){
        _userDao = new UserDAO();
    }

    public List<AgentViewModel> getAgents(){
        var users = _userDao.getAll();
        return users.stream().filter(u -> u instanceof Agent)
                .map(u -> new AgentViewModel(u.getFirstName(), u.getLastName(), u.getPhone(), ((Agent) u).getCompany(), ((Agent) u).getSalary()))
                .toList();
    }

    public void createAgent(String fName, String lName, String username, String phone, String email, String company, double salary){
        String hashedPass = Hashing.sha256()
                .hashString(fName + phone, StandardCharsets.UTF_8)
                .toString();
        _userDao.save(new Agent(fName, lName, username, email, phone, salary, company, hashedPass));
    }
}
