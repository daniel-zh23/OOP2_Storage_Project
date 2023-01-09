package com.storage.storageBusiness.Services;

import com.storage.storageBusiness.Models.AgentViewModel;
import com.storage.storagedb.DAO.UserDAO;
import com.storage.storagedb.Entity.Agent;

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
}
