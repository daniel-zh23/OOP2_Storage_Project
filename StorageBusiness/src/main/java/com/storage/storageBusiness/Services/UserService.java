package com.storage.storageBusiness.Services;


import com.google.common.hash.Hashing;
import com.storage.storageBusiness.Models.NotificationModel;
import com.storage.storageBusiness.Models.ResultLoginModel;
import com.storage.storagedb.DAO.NotificationDAO;
import com.storage.storagedb.DAO.UserDAO;
import com.storage.storagedb.Entity.Notification;
import com.storage.storagedb.Entity.User;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

public class UserService {
    private final UserDAO _userDao;
    private final NotificationDAO _notificationDao;

    public UserService(){
        _userDao = new UserDAO();
        _notificationDao = new NotificationDAO();
    }


    public ResultLoginModel login(String username, String password){
        _userDao.openSession();
        User user = _userDao.getByUsername(username);
        if (user == null){
            return null;
        }
        //TODO: Pass hash!
        String hashedPass = Hashing.sha256()
                .hashString(password, StandardCharsets.UTF_8)
                .toString();
        if (!user.getPassword().equals(password)){
            return null;
        }
        _userDao.close();
        return new ResultLoginModel(user.getId(), user.getClass().getSimpleName(), user.isFirstLogin());
    }

    public boolean checkUsername(String username){
        _userDao.openSession();
        var result = _userDao.getUsernames().anyMatch(u -> u.getUsername().equals(username));
        _userDao.close();
        return result;
    }

    public boolean changePassword(String username, String password){
        _userDao.openSession();
        var user = _userDao.getByUsername(username);
        //TODO: Pass hash!

        String hashedPass = Hashing.sha256()
                .hashString(password, StandardCharsets.UTF_8)
                .toString();
        user.setPassword(password);
        if(user.isFirstLogin()) {
            user.setFirstLogin(false);
        }
        _userDao.update(user);
        _userDao.close();
        return true;
    }

    public boolean checkId(int id){
        _userDao.openSession();
        var result = _userDao.getAll().anyMatch(u -> u.getId() == id);
        _userDao.close();
        return result;
    }

    public void deleteById(int id){
        _userDao.openSession();
        var user = _userDao.getAll().filter(u -> u.getId() == id).findFirst().get();
        user.setActive(false);
        _userDao.save(user);
        _userDao.close();
    }
    public List<NotificationModel> fetchNotificationsbyUserId(int id,boolean unreadOnly)
    {
        _notificationDao.openSession();
         List<NotificationModel> notifications =_notificationDao.getByUserId(id,unreadOnly).stream()
                 .map(n->new NotificationModel(n.getId(),n.getUserId(),n.getValue())).collect(Collectors.toList());
        _notificationDao.close();
        return notifications;
    }
    public void setNotificationReadStatus(List<NotificationModel> notifications) {
        var ids = notifications.stream().map(n -> n.getId()).toList();
        _notificationDao.openSession();
        var result = _notificationDao.getAll()
                .filter(n -> ids.stream().anyMatch(i -> i == n.getId()))
                .collect(Collectors.toList());
        result.forEach(n -> {
            n.setRead(true);
            _notificationDao.update(n);

        });
        _notificationDao.close();
    }
    public void addNotification(int userId,String notificationBody)
    {
        _notificationDao.openSession();
        _notificationDao.save(new Notification(_userDao.get(userId),notificationBody));
        _notificationDao.close();
    }

}
