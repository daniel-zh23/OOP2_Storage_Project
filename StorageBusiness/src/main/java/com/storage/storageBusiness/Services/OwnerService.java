package com.storage.storageBusiness.Services;

import com.google.common.hash.Hashing;
import com.storage.storageBusiness.Models.OwnerViewModel;
import com.storage.storagedb.DAO.UserDAO;
import com.storage.storagedb.Entity.Owner;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class OwnerService {
    private final UserDAO _userDao;

    public OwnerService(){
        _userDao = new UserDAO();
    }

    public List<OwnerViewModel> getOwners(){
        _userDao.openSession();
        var owners =  _userDao.getAll().filter(o -> o instanceof Owner && o.isActive())
                .map(o -> new OwnerViewModel(o.getId(), o.getFirstName(), o.getLastName(), o.getPhone(), o.getEmail()))
                .toList();
        _userDao.close();
        return owners;
    }

    public void createOwner(String fName, String lName, String username, String phone, String email) {
        _userDao.openSession();
        String hashedPass = Hashing.sha256()
                .hashString(fName + phone, StandardCharsets.UTF_8)
                .toString();
        _userDao.save(new Owner(fName, lName, username, email, phone, hashedPass));
        _userDao.close();
    }
}
