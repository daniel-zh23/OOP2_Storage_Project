package com.storage.storageBusiness.Services;
import com.storage.storageBusiness.Common.LoggerMessages;
import com.storage.storageBusiness.Common.NotificationMessages;
import com.storage.storagedb.DAO.RentDAO;
import com.storage.storagedb.DAO.StatusDAO;
import com.storage.storagedb.DAO.StorageDAO;
import com.storage.storagedb.Entity.Sales;
import com.storage.storagedb.Entity.Storage;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SaleCheckerTask implements Runnable{
    private static final Logger LOGGER = Logger.getLogger(LoggerMessages.LoggerName);
    private NotificationService _notificationService;
    private RentDAO _rentDao;
    private StorageDAO _storageDao;
    private StatusDAO _statusDao;
    public SaleCheckerTask(NotificationService _notificationService)
    {
        this._notificationService=_notificationService;
        _rentDao = new RentDAO();
        _storageDao = new StorageDAO();
        _statusDao = new StatusDAO();

    }
    public void run()
    {
        _rentDao.openSession();
        List<Sales> activeSales = _rentDao.getAllActive();
        for (Sales s:activeSales)
        {
            LocalDate endDate = s.getDateOfSale().plusMonths(s.getDuration());
            if(endDate.isBefore(LocalDate.now()))
            {
                s.setActive(false);
                _rentDao.update(s);
                Storage storage = s.getStorage();
                _statusDao.openSession();
                storage.setStatusId(1);
                _statusDao.close();
                _storageDao.openSession();
                _storageDao.update(storage);
                _storageDao.close();
                _notificationService.addNotification(s.getAgentId(), NotificationMessages.contractExpired);
                _notificationService.addNotification(s.getStorage().getOwner().getId(),NotificationMessages.contractExpired);
            }
        }
        _rentDao.close();
        LOGGER.log(Level.INFO, LoggerMessages.SaleCheckerTask);
    }
}
