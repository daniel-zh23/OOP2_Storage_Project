package com.storage.storageBusiness;

import com.storage.storageBusiness.Models.NotificationModel;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)

public class NotificationServiceTests extends BaseTests{

    @Test
    public void fetchNotificationsByUserId_should_return_only_for_user(){
        var result = _notificationService.fetchNotificationsByUserId(1, false);

        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals(1, result.get(0).getUserId());
        Assertions.assertEquals(1, result.get(1).getUserId());
    }

    @Test
    public void setNotificationReadStatus_should_set_read_to_true(){
        var list = new ArrayList<NotificationModel>(){
            {
                add(new NotificationModel(1, 1, "Notification1"));
                add(new NotificationModel(2, 1, "Notification2"));
            }
        };

        _notificationService.setNotificationReadStatus(list);

        _notificationDao.openSession();
        Assertions.assertEquals(0, _notificationService.fetchNotificationsByUserId(1, true).size());
    }

    @Test
    public void addNotification_should_set_read_to_true(){
        var list = new ArrayList<NotificationModel>(){
            {
                add(new NotificationModel(1, 1, "Notification1"));
                add(new NotificationModel(2, 1, "Notification2"));
            }
        };

        _notificationService.setNotificationReadStatus(list);

        _notificationDao.openSession();
        Assertions.assertEquals(0, _notificationService.fetchNotificationsByUserId(1, true).size());
    }

    @AfterEach
    public void tear(){
        try{
            _notificationDao.close();
        } catch (Exception e){
            System.out.println("Already closed.");
        }
    }

}
