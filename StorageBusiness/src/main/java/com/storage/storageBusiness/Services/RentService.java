package com.storage.storageBusiness.Services;

import com.storage.storageBusiness.Common.LoggerMessages;
import com.storage.storageBusiness.Common.NotificationMessages;
import com.storage.storageBusiness.Models.CustomerViewModel;
import com.storage.storageBusiness.Models.SaleViewModel;
import com.storage.storagedb.DAO.*;
import com.storage.storagedb.Entity.*;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RentService {
    private static final Logger LOGGER = Logger.getLogger(LoggerMessages.LoggerName);
    private final CustomerDAO _customerDao;
    private final RentDAO _rentDao;
    private StorageService _storageService;
    private UserService _userService;
    private NotificationService _notificationService;
    public RentService(StorageService storageService, NotificationService notificationService, UserService userService)
    {
        _customerDao = new CustomerDAO();
        _rentDao =new RentDAO();
        _userService = userService;
        _storageService = storageService;
        _notificationService = notificationService;
    }
    public List<CustomerViewModel> getAllRenters()
    {
        _customerDao.openSession();
        List<CustomerViewModel> renters = _customerDao.getAll().map(r->new CustomerViewModel(r.getId(),r.getFirstName(),r.getLastName(), r.getPhone())).toList();
        _customerDao.close();
        return renters;
    }
    public void createRenter(String fName, String lName, String phone)
    {
        _customerDao.openSession();
        _customerDao.save(new Customer(fName, lName, phone));
        _customerDao.close();
        LOGGER.log(Level.INFO, String.format(LoggerMessages.CreateCustomer, phone));
    }
    public void createRent(Double recommendedPrice, Double price, Integer duration, Integer storageId, Integer agentId, Integer customerId)
    {
        try {
            _rentDao.openSession();
            _rentDao.save(new Sales(price, duration, LocalDate.now(), storageId, agentId, customerId));
            var ownerId = _storageService.getOwnerId(storageId);
            var priceDifference = (price - recommendedPrice) / 100;
            _userService.adjustRatingBy(agentId, priceDifference);
            _notificationService.addNotification(ownerId, NotificationMessages.ownerNewContract);
            _rentDao.close();
           _storageService.changeStorageStatusById(storageId,2);
           _storageService.setContractAgent(storageId, agentId);
            LOGGER.log(Level.INFO, String.format(LoggerMessages.CreateRent, storageId, agentId, customerId));
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            LOGGER.log(Level.SEVERE, e.getMessage());
        }
    }
    public List<SaleViewModel> fetchByAgentId(int id)
    {
        _rentDao.openSession();
        List<SaleViewModel> sales= _rentDao.getAll()
                .filter(s->s.getAgentId()==id)
                .map(s->new SaleViewModel(s.getId(),s.getPrice(),s.getDuration(),s.getDateOfSale(),
                        s.getStorageId(),s.getAgentId(),s.getCustomerId(),
                        String.format("Address: %s",s.getStorage().getAddress()),
                        String.format("Name: %s %s",s.getAgent().getFirstName(),s.getAgent().getLastName()),
                        String.format("Name: %s %s",s.getRenter().getFirstName(),s.getRenter().getLastName()))
                )
                .toList();
        _rentDao.close();
        return sales;
    }
    public List<SaleViewModel> fetchByStorageId(int id)
    {
        _rentDao.openSession();
        List<SaleViewModel> sales= _rentDao.getAll()
                .filter(s->s.getStorageId()==id)
                .map(s->new SaleViewModel(s.getId(),s.getPrice(),s.getDuration(),s.getDateOfSale(),
                        s.getStorageId(),s.getAgentId(),s.getCustomerId(),
                        String.format("Address: %s",s.getStorage().getAddress()),
                        String.format("Name: %s %s",s.getAgent().getFirstName(),s.getAgent().getLastName()),
                        String.format("Name: %s %s",s.getRenter().getFirstName(),s.getRenter().getLastName()))
                )
                .toList();
        _rentDao.close();
        return sales;
    }


    public boolean checkPhone(String phone) {
        _customerDao.openSession();
        var result = _customerDao.getAll().map(r -> r.getPhone()).anyMatch(u -> u.equals(phone));
        _customerDao.close();
        return result;
    }
}
