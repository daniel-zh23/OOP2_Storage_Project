package com.storage.storageBusiness.Services;


import com.google.common.hash.Hashing;
import com.storage.storageBusiness.Common.LoggerMessages;
import com.storage.storageBusiness.Models.NotificationModel;
import com.storage.storageBusiness.Models.ResultLoginModel;
import com.storage.storagedb.DAO.NotificationDAO;
import com.storage.storagedb.DAO.UserDAO;
import com.storage.storagedb.Entity.Agent;
import com.storage.storagedb.Entity.Notification;
import com.storage.storagedb.Entity.User;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class UserService {
    private static final Logger LOGGER = Logger.getLogger(LoggerMessages.LoggerName);

    private final UserDAO _userDao;

    public UserService(){
        _userDao = new UserDAO();
    }


    public ResultLoginModel login(String username, String password){
        _userDao.openSession();
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
        _userDao.close();
        LOGGER.log(Level.INFO, String.format(LoggerMessages.Login, username));
        return new ResultLoginModel(user.getId(), user.getClass().getSimpleName(), user.isFirstLogin());
    }

    public boolean checkUsername(String username){
        _userDao.openSession();
        var result = _userDao.getUsernames().anyMatch(u -> u.equals(username));
        _userDao.close();
        return result;
    }

    public boolean changePassword(String username, String password){
        _userDao.openSession();
        var user = _userDao.getByUsername(username);
        String hashedPass = Hashing.sha256()
                .hashString(password, StandardCharsets.UTF_8)
                .toString();
        user.setPassword(hashedPass);
        if(user.isFirstLogin()) {
            user.setFirstLogin(false);
        }
        _userDao.update(user);
        _userDao.close();
        LOGGER.log(Level.INFO, String.format(LoggerMessages.ChangePassword, username));
        return true;
    }

    public boolean checkId(int id){
        _userDao.openSession();
        var result = _userDao.getAll().anyMatch(u -> u.getId() == id);
        _userDao.close();
        return result;
    }

    public boolean deleteById(int id){
        _userDao.openSession();
        var user = _userDao.getAll().filter(u -> u.getId() == id).findFirst().get();
        user.setActive(false);
        _userDao.update(user);
        _userDao.close();
        LOGGER.log(Level.INFO, String.format(LoggerMessages.DisableUser, id));
        return true;
    }

    public void adjustRatingBy(Integer agentId, double value) {
        _userDao.openSession();
        var agent = (Agent) _userDao.get(agentId);
        agent.setRating(agent.getRating() + value);
        if (agent.getRating() > 10) agent.setRating(10.0);
        if (agent.getRating() < 0) agent.setRating(0.0);
        _userDao.update(agent);
        _userDao.close();
        LOGGER.log(Level.INFO, String.format(LoggerMessages.ChangeAgentRating, agentId, agent.getRating()));
    }
}
