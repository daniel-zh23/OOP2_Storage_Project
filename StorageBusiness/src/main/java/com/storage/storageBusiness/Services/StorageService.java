package com.storage.storageBusiness.Services;

import com.storage.storageBusiness.Models.StorageViewModel;
import com.storage.storagedb.DAO.StorageDAO;
import com.storage.storagedb.DAO.UserDAO;
import com.storage.storagedb.Entity.Storage;

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
        var storages = _storageDao.getByOwnerId(id)
                .map(s -> new StorageViewModel(s.getAddress(), s.getStatus().getName(), s.getHeight(), s.getWidth(), s.getLength()))
                .collect(Collectors.toList());
        _storageDao.close();
        return storages;
    }

    public void createStorage(int ownerId, String address, Double width, Double height, Double length) {
        _storageDao.openSession();
        _storageDao.save(new Storage(width, length, height, address, 1, ownerId));
        _storageDao.close();
    }
}
