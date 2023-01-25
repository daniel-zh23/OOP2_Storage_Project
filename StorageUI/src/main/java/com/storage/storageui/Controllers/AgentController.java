package com.storage.storageui.Controllers;

import com.storage.storageBusiness.Models.NotificationModel;
import com.storage.storageBusiness.Services.AgentService;
import com.storage.storageBusiness.Services.OwnerService;
import com.storage.storageBusiness.Services.StorageService;
import com.storage.storageBusiness.Services.UserService;
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
    private Timer timer;
    private ScheduledExecutorService executor;

    private Integer _agentId;
    private UserService _userService;
    private StorageService _storageService;

    @FXML
    private Button logoutBtn;

    @FXML
    private MenuButton notificationsCombo2;

    @FXML
    private Button clearNotificationsBtn;

    @Override
    public void setServices(AgentService agentService, OwnerService ownerService, UserService userService) {

        if(_userService == null){
            _userService = userService;
        }
//        if(_storageService == null){
//            _storageService = userService;
//        }
    }

    public void setAgentId(int id) {
        if (_agentId == null){
            _agentId = id;
        }
    }

    public void onClearNotifications() {
        _userService.setNotificationReadStatus(_userService.fetchNotificationsbyUserId(_agentId,false));
        clearNotificationsBtn.setVisible(false);
        notificationsCombo2.setText("No notifications");
        notificationsCombo2.getItems().setAll();
    }

    public void startNotificationService(){
        this.timer = new Timer();
        timer.scheduleAtFixedRate(
                new NotificationsTask(_userService, notificationsCombo2, clearNotificationsBtn, _agentId),
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

    @Override
    public void onLogout(ActionEvent event) throws Exception {
        super.onLogout(event);

        timer.cancel();
        timer.purge();
        executor.shutdown();
    }
}
