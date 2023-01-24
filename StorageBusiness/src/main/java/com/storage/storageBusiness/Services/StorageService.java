package com.storage.storageBusiness.Services;

import com.storage.storageBusiness.Models.StorageViewModel;
import com.storage.storagedb.DAO.StorageDAO;
import com.storage.storagedb.DAO.UserDAO;
import com.storage.storagedb.Entity.Storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StorageService {
    private StorageDAO _storageDao;
    private UserDAO _userDao;

    public StorageService() {
        _storageDao = new StorageDAO();
    }

    public List<StorageViewModel> getAllByOwnerId(int id){
        _storageDao.openSession();
        var storagesRaw = _storageDao.getByOwnerId(id)
                .filter(s -> s.getIsActive())
                .collect(Collectors.toList());

        var storages = storagesRaw.stream()
                .map(ss -> new StorageViewModel(
                        ss.getId(),
                        ss.getAddress(),
                        ss.getStatus().getName(),
                        ss.getHeight(),
                        ss.getWidth(),
                        ss.getLength(),
                        ss.getAgents().isEmpty()
                                ? "None"
                                : String.join("\n", ss.getAgents().stream()
                                .map(a -> String.format("%s %s - %s", a.getFirstName(), a.getLastName(), a.getPhone()))
                                .collect(Collectors.toList()))))
                        .collect(Collectors.toList());
        _storageDao.close();
        return storages;
    }

    public void createStorage(int ownerId, String address, Double width, Double height, Double length) {
        _storageDao.openSession();
        _storageDao.save(new Storage(width, length, height, address, 1, ownerId));
        _storageDao.close();
    }

    public boolean checkId(int id){
        _storageDao.openSession();
        var result = _storageDao.getAll().anyMatch(s -> s.getId() == id);
        _storageDao.close();
        return result;
    }

    public void deleteById(int id) {
        _storageDao.openSession();
        var storage = _storageDao.getAll().filter(u -> u.getId() == id).findFirst().get();
        storage.setIsActive(false);
        _storageDao.save(storage);
        _storageDao.close();
    }
}
