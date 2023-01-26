package com.storage.storageui.Controllers;

import com.storage.storageBusiness.Models.NotificationModel;
import com.storage.storageBusiness.Services.*;
import com.storage.storageui.Controllers.Contracts.UserController;
import com.storage.storageui.Controllers.Extensions.NotificationsTask;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.util.List;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AgentController extends UserController {
    private Integer _agentId;
    private AgentService _agentService;
    private OwnerService _ownerService;
    private UserService _userService;
    private StorageService _storageService;

    private NotificationService _notificationService;

    @FXML
    private Button logoutBtn;

    @FXML
    private MenuButton notificationsCombo2;

    @FXML
    private Button clearNotificationsBtn;

    public void setServices(AgentService agentService, OwnerService ownerService, UserService userService, StorageService storageService, NotificationService notificationService) {
        if (_ownerService == null){
            _ownerService = ownerService;
        }
        if (_userService == null){
            _userService = userService;
        }
        if (_agentService == null){
            _agentService = agentService;
        }
        if (_storageService == null){
            _storageService = storageService;
        }
        if (_notificationService == null){
            _notificationService = notificationService;
        }
    }

    public void setAgentId(int id) {
        if (_agentId == null){
            _agentId = id;
        }
    }

    public void onClearNotifications() {
        _notificationService.setNotificationReadStatus(_notificationService.fetchNotificationsbyUserId(_agentId,false));
        clearNotificationsBtn.setVisible(false);
        notificationsCombo2.setText("No notifications");
        notificationsCombo2.getItems().setAll();
    }

    public void startNotificationService(){
        this.timer = new Timer();
        timer.scheduleAtFixedRate(
                new NotificationsTask(_notificationService, notificationsCombo2, clearNotificationsBtn, _agentId),
                0, 14500);
        Runnable task = () -> {
            Platform.runLater(() -> notificationsCombo2.setText(String.format("%s notifications", notificationsCombo2.getItems().size())));
        };
        this.executor = Executors.newScheduledThreadPool(5, r -> {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t ;
        });
        executor.scheduleAtFixedRate(task, 0, 15, TimeUnit.SECONDS);
    }
}
