package com.storage.storageBusiness.Services;

import com.storage.storageBusiness.Common.LoggerMessages;
import com.storage.storageBusiness.Common.NotificationMessages;
import com.storage.storageBusiness.Models.AgentViewModel;
import com.storage.storageBusiness.Models.StorageViewModel;
import com.storage.storagedb.DAO.StatusDAO;
import com.storage.storagedb.DAO.StorageDAO;
import com.storage.storagedb.DAO.UserDAO;
import com.storage.storagedb.Entity.Agent;
import com.storage.storagedb.Entity.Storage;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class StorageService {
    private static final Logger LOGGER = Logger.getLogger(LoggerMessages.LoggerName);
    private StorageDAO _storageDao;
    private UserDAO _userDao;
    private StatusDAO _statusDao;

    private NotificationService _notificationService;

    public StorageService(NotificationService notificationService) {
        _storageDao = new StorageDAO();
        _userDao = new UserDAO();
        _notificationService = notificationService;
        _statusDao= new StatusDAO();
    }
    public void changeStorageStatusById(int storageId, int statusId)
    {
        _storageDao.openSession();
        Storage storage = _storageDao.get(storageId);
        storage.setStatusId(statusId);
        _storageDao.update(storage);
        _storageDao.close();
        LOGGER.log(Level.INFO, String.format(LoggerMessages.ChangeStorageStatus, storageId, statusId));
    }

    public StorageViewModel getById(int id){
        _storageDao.openSession();
        var model = _storageDao.get(id);
        _storageDao.close();
        return new StorageViewModel(model.getId(), model.getAddress(),
                model.getStatus().getName(), model.getHeight(), model.getWidth(),
                model.getLength(), model.getAgents().isEmpty()
                ? "None"
                : String.join("\n", model.getAgents().stream()
                .map(a -> String.format("%s %s - %s", a.getFirstName(), a.getLastName(), a.getPhone()))
                .collect(Collectors.toList())));
    }

    public int getOwnerId(int id){
        _storageDao.openSession();
        var result = _storageDao.get(id).getOwner().getId();
        _storageDao.close();
        return result;
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
        LOGGER.log(Level.INFO, String.format(LoggerMessages.CreateStorage, ownerId));
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
        LOGGER.log(Level.INFO, String.format(LoggerMessages.DisableStorage, id));
    }
    public void updateStorages(List<StorageViewModel>storages)
    {
        _storageDao.openSession();
        for (StorageViewModel s:storages) {
            Storage storage =_storageDao.get(s.getId());
            storage.setAddress(s.getAddress());
            storage.setHeight(s.getHeight());
            storage.setLength(s.getLength());
            storage.setWidth(s.getWidth());
            _storageDao.update(storage);
        }
        _storageDao.close();
        LOGGER.log(Level.INFO, LoggerMessages.UpdateStorages);
    }
    public void assignAgentToStorage(StorageViewModel storage, AgentViewModel agent)
    {
        _storageDao.openSession();
        _userDao.openSession();
        Agent a =(Agent)_userDao.get(agent.getId());
        Storage s = _storageDao.get(storage.getId());
        s.getAgents().add(a);
        _statusDao.openSession();
        s.setStatusId(3);
        _statusDao.close();
        _storageDao.update(s);
        _storageDao.close();
        _userDao.close();
        _notificationService.addNotification(agent.getId(), NotificationMessages.agentNewStorage);
        LOGGER.log(Level.INFO, String.format(LoggerMessages.AssignAgentToStorage, agent.getId(), storage.getId()));
    }
    public List<StorageViewModel> getAllByAgentId(int id)
    {
        _userDao.openSession();
       Agent agent = (Agent)_userDao.get(id);
       _userDao.close();
        List<StorageViewModel> storages = agent.getStorages().stream().filter(a->a.getIsActive())
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
        return storages;
    }

    public void setContractAgent(Integer storageId, Integer agentId) {
        _storageDao.openSession();
        _userDao.openSession();
        var agent = (Agent) _userDao.get(agentId);
        _userDao.close();
        var storage = _storageDao.get(storageId);
        storage.setAgents(new HashSet<>(Arrays.asList(agent)));
        _storageDao.update(storage);
        _storageDao.close();
        LOGGER.log(Level.INFO, String.format(LoggerMessages.ContractToAgent, agentId));
    }
}
