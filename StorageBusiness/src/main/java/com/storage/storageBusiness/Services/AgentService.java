package com.storage.storageBusiness.Services;

import com.google.common.hash.Hashing;
import com.storage.storageBusiness.Common.LoggerMessages;
import com.storage.storageBusiness.Models.AgentViewModel;
import com.storage.storagedb.DAO.UserDAO;
import com.storage.storagedb.Entity.Agent;

import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AgentService {
    private static final Logger LOGGER = Logger.getLogger(LoggerMessages.LoggerName);
    private final UserDAO _userDao;

    public AgentService(){
        _userDao = new UserDAO();
    }

    public List<AgentViewModel> getAgents(){
        return this.getAgents(Comparator.comparing(AgentViewModel::getId));
    }

    public List<AgentViewModel> getAgents(Comparator<AgentViewModel> comparator){
        _userDao.openSession();
        var users = _userDao.getAll()
                .filter(u -> u instanceof Agent)
                .filter(u -> u.isActive())
                .map(u -> {
                    var salary = ((Agent) u).getSalary();
                    var rating = ((Agent) u).getRating();
                    salary = salary * (1.0 + (rating / 100.0));
                    var model = new AgentViewModel(u.getId(), u.getFirstName(), u.getLastName(), u.getPhone(), ((Agent) u).getCompany(), ((Agent) u).getSalary(), String.format("%.2f", (double)(Math.round(((Agent) u).getRating() * 100)/100)), (double) Math.round(salary * 100)/100);
                    return model;
                })
                .sorted(comparator)
                .collect(Collectors.toList());
        _userDao.close();
        return users;
    }

    public void createAgent(String fName, String lName, String username, String phone, String email, String company, double salary){
        _userDao.openSession();
        String hashedPass = Hashing.sha256()
                .hashString(fName + phone, StandardCharsets.UTF_8)
                .toString();
        _userDao.save(new Agent(fName, lName, username, email, phone, salary, company, hashedPass));
        _userDao.close();
        LOGGER.log(Level.INFO, String.format(LoggerMessages.CreateAgent, username));

    }
    public void updateAgents(List<AgentViewModel>agents)
    {
        _userDao.openSession();
        for (AgentViewModel a:agents) {
            Agent agent =(Agent)_userDao.get(a.getId());
            agent.setFirstName(a.getFirstName());
            agent.setLastName(a.getLastName());
            agent.setPhone(a.getPhone());
            agent.setCompany(a.getCompany());
            agent.setSalary(a.getSalary());
            _userDao.update(agent);
        }
        _userDao.close();
        LOGGER.log(Level.INFO, LoggerMessages.UpdateAgents);
    }
}
