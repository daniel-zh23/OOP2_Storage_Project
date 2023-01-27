package com.storage.storageBusiness.Services;

import com.google.common.hash.Hashing;
import com.storage.storageBusiness.Common.LoggerMessages;
import com.storage.storageBusiness.Models.OwnerViewModel;
import com.storage.storageBusiness.Models.StorageViewModel;
import com.storage.storagedb.DAO.StorageDAO;
import com.storage.storagedb.DAO.UserDAO;
import com.storage.storagedb.Entity.Agent;
import com.storage.storagedb.Entity.Owner;
import com.storage.storagedb.Entity.User;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OwnerService {
    private static final Logger LOGGER = Logger.getLogger(LoggerMessages.LoggerName);
    private final UserDAO _userDao;

    public OwnerService(){
        _userDao = new UserDAO();
    }

    public List<OwnerViewModel> getOwners(){
        _userDao.openSession();
        var owners =  _userDao.getAll().filter(o -> o instanceof Owner && o.isActive())
                .map(o -> new OwnerViewModel(o.getId(), o.getFirstName(), o.getLastName(), o.getPhone(), o.getEmail()))
                .collect(Collectors.toList());
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
        LOGGER.log(Level.INFO, String.format(LoggerMessages.CreateOwner, username));
    }
    public void updateOwners(List<OwnerViewModel>owners) {
        _userDao.openSession();
        for (OwnerViewModel o : owners) {
            Owner owner = (Owner) _userDao.get(o.getId());
            owner.setFirstName(o.getFirstName());
            owner.setLastName(o.getLastName());
            owner.setPhone(o.getPhone());
            owner.setEmail(o.getEmail());
            _userDao.update(owner);
        }
        _userDao.close();
        LOGGER.log(Level.INFO, LoggerMessages.UpdateOwners);
    }
}
