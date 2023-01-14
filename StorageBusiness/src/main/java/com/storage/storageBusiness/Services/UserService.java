package com.storage.storageBusiness.Services;


import com.google.common.hash.Hashing;
import com.storage.storageBusiness.Models.ResultLoginModel;
import com.storage.storagedb.DAO.UserDAO;
import com.storage.storagedb.Entity.User;

import java.nio.charset.StandardCharsets;

public class UserService {
    private final UserDAO _userDao;

    public UserService(){
        _userDao = new UserDAO();
    }


    public ResultLoginModel login(String username, String password){
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

        return new ResultLoginModel(user.getClass().getSimpleName(), user.isFirstLogin());
    }

    public boolean checkUsername(String username){
        return _userDao.getUsernames().anyMatch(u -> u.getUsername().equals(username));
    }

    public boolean changePassword(String username, String password){
        var user = _userDao.getByUsername(username);
        String hashedPass = Hashing.sha256()
                .hashString(password, StandardCharsets.UTF_8)
                .toString();
        user.setPassword(hashedPass);
        user.setFirstLogin(false);

        _userDao.update(user);
        return true;
    }
}
