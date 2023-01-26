package com.storage.storageBusiness.Services;

import com.google.common.hash.Hashing;
import com.storage.storageBusiness.Models.AgentViewModel;
import com.storage.storagedb.DAO.UserDAO;
import com.storage.storagedb.Entity.Agent;
import com.storage.storagedb.Entity.User;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AgentService {
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
                    var model = new AgentViewModel(u.getId(), u.getFirstName(), u.getLastName(), u.getPhone(), ((Agent) u).getCompany(), ((Agent) u).getSalary(), ((Agent) u).getRating().toString(), salary);
                    return model;
                })
                .sorted(comparator)
                .collect(Collectors.toList());
        _userDao.close();
        return users;
    }

    public void createAgent(String fName, String lName, String username, String phone, String email, String company, double salary){
        _userDao.openSession();
        //TODO: Pass hash!
        String hashedPass = Hashing.sha256()
                .hashString(fName + phone, StandardCharsets.UTF_8)
                .toString();
        _userDao.save(new Agent(fName, lName, username, email, phone, salary, company, fName + phone));
        _userDao.close();
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
            _userDao.update((User)agent);
        }
        _userDao.close();
    }
}
