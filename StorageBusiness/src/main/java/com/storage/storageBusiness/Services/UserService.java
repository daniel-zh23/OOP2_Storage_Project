package com.storage.storageBusiness.Services;


import com.google.common.hash.Hashing;
import com.storage.storageBusiness.Models.AgentViewModel;
import com.storage.storagedb.DAO.UserDAO;
import com.storage.storagedb.Entity.Agent;
import com.storage.storagedb.Entity.User;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class UserService {
    private final UserDAO _userDao;

    public UserService(){
        _userDao = new UserDAO();
    }

    public String login(String username, String password){
        User user = _userDao.getByUsername(username);
        if (user == null){
            return null;
        }
        String hashedPass = Hashing.sha256()
                .hashString(password, StandardCharsets.UTF_8)
                .toString();
        if (!user.getPassword().equals(hashedPass)){
            return null;
        }

        return user.getClass().getSimpleName();
    }

    public List<AgentViewModel> getAgents(){
        var users = _userDao.getAll();
        return users.stream().filter(u -> u instanceof Agent)
                .map(u -> new AgentViewModel(u.getFirstName(), u.getLastName(), u.getPhone(), ((Agent) u).getCompany(), ((Agent) u).getSalary()))
                .toList();
    }
}
