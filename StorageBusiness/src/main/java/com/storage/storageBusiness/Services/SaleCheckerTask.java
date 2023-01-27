package com.storage.storageBusiness.Services;
import com.storage.storageBusiness.Common.NotificationMessages;
import com.storage.storagedb.DAO.NotificationDAO;
import com.storage.storagedb.DAO.SaleDAO;
import com.storage.storagedb.DAO.StatusDAO;
import com.storage.storagedb.DAO.StorageDAO;
import com.storage.storagedb.Entity.Sales;
import com.storage.storagedb.Entity.Status;
import com.storage.storagedb.Entity.Storage;

import java.time.LocalDate;
import java.util.List;

public class SaleCheckerTask implements Runnable{
    private NotificationService _notificationService;
    private SaleDAO _saleDao;
    private StorageDAO _storageDao;
    private StatusDAO _statusDao;
    public SaleCheckerTask(NotificationService _notificationService)
    {
        this._notificationService=_notificationService;
        _saleDao= new SaleDAO();
        _storageDao = new StorageDAO();
        _statusDao = new StatusDAO();

    }
    public void run()
    {
        _saleDao.openSession();
        List<Sales> activeSales = _saleDao.getAllActive();
        for (Sales s:activeSales)
        {
            LocalDate endDate = s.getDateOfSale().plusMonths(s.getDuration());
            if(endDate.isBefore(LocalDate.now()))
            {
                s.setActive(false);
                _saleDao.update(s);
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
        _saleDao.close();
    }
}
