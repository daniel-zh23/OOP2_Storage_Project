package com.storage.storageBusiness.Services;

import com.google.common.hash.Hashing;
import com.storage.storageBusiness.Models.AgentViewModel;
import com.storage.storageBusiness.Models.OwnerViewModel;
import com.storage.storagedb.DAO.UserDAO;
import com.storage.storagedb.Entity.Agent;
import com.storage.storagedb.Entity.Owner;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class OwnerService {
    private final UserDAO _userDao;

    public OwnerService(){
        _userDao = new UserDAO();
    }

    public List<OwnerViewModel> getOwners(){
        var users = _userDao.getAll();
        return users.stream().filter(u -> u instanceof Owner)
                .map(u -> new OwnerViewModel(u.getFirstName(), u.getLastName(), u.getPhone(), u.getEmail()))
                .toList();
    }

    public void createOwner(String fName, String lName, String username, String phone, String email) {
        String hashedPass = Hashing.sha256()
                .hashString(fName + phone, StandardCharsets.UTF_8)
                .toString();
        _userDao.save(new Owner(fName, lName, username, email, phone, hashedPass));
    }
}
