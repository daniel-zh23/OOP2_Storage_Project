package com.storage.storageBusiness.Services;

import com.storage.storageBusiness.Models.NotificationModel;
import com.storage.storagedb.DAO.NotificationDAO;
import com.storage.storagedb.Entity.Notification;

import java.util.List;
import java.util.stream.Collectors;

public class NotificationService {
    private final NotificationDAO _notificationDao;

    public NotificationService() {
        _notificationDao = new NotificationDAO();
    }

    public List<NotificationModel> fetchNotificationsbyUserId(int id, boolean unreadOnly)
    {
        _notificationDao.openSession();
        List<NotificationModel> notifications =_notificationDao.getByUserId(id,unreadOnly).stream()
                .map(n->new NotificationModel(n.getId(),n.getUserId(),n.getValue())).collect(Collectors.toList());
        _notificationDao.close();
        return notifications;
    }
    public void setNotificationReadStatus(List<NotificationModel> notifications) {
        var ids = notifications.stream().map(n -> n.getId()).toList();
        _notificationDao.openSession();
        var result = _notificationDao.getAll()
                .filter(n -> ids.stream().anyMatch(i -> i == n.getId()))
                .collect(Collectors.toList());
        result.forEach(n -> {
            n.setRead(true);
            _notificationDao.update(n);

        });
        _notificationDao.close();
    }
    public void addNotification(int userId,String notificationBody)
    {
        _notificationDao.openSession();
        _notificationDao.save(new Notification(userId,notificationBody));
        _notificationDao.close();
    }
}
