package com.storage.storageBusiness.Services;

import com.storage.storageBusiness.Models.StorageViewModel;
import com.storage.storagedb.DAO.StorageDAO;

import java.util.List;
import java.util.stream.Collectors;

public class StorageService {
    private StorageDAO _storageDao;

    public StorageService() {
        _storageDao = new StorageDAO();
    }

    public List<StorageViewModel> getAllByOwnerId(int id){
        _storageDao.openSession();
        var storages = _storageDao.getByOwnerId(id)
                .map(s -> new StorageViewModel(s.getAddress(), s.getStatus() == 2 ? "Leased" : "Ddz", s.getHeight(), s.getWidth(), s.getLength()))
                .collect(Collectors.toList());
        _storageDao.close();
        return storages;
    }


}
