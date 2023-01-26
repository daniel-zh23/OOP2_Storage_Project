package com.storage.storageui.Controllers.Extensions;

import com.storage.storageBusiness.Models.NotificationModel;
import com.storage.storageBusiness.Services.NotificationService;
import com.storage.storageBusiness.Services.UserService;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

import java.util.List;
import java.util.TimerTask;

public class NotificationsTask extends TimerTask {

    private int _agentId;

    private NotificationService _notificationService;

    private MenuButton notificationsCombo2;

    private Button clearNotificationsBtn;

    public NotificationsTask(NotificationService notificationService, MenuButton notificationsCombo2, Button clearNotificationsBtn, int agentId) {
        this._notificationService = notificationService;
        this.notificationsCombo2 = notificationsCombo2;
        this.clearNotificationsBtn = clearNotificationsBtn;
        _agentId = agentId;
    }

    @Override
    public void run() {
        List<NotificationModel> notifications =_notificationService.fetchNotificationsbyUserId(_agentId,false);
        if (notifications.size() != 0){
            clearNotificationsBtn.setVisible(true);
        }
        notificationsCombo2.getItems().setAll(notifications.stream().map(n -> new MenuItem(n.getValue())).toList());
    }
}
