package com.storage.storageui.Controllers;

import com.storage.storageBusiness.Models.NotificationModel;
import com.storage.storageBusiness.Services.AgentService;
import com.storage.storageBusiness.Services.OwnerService;
import com.storage.storageBusiness.Services.StorageService;
import com.storage.storageBusiness.Services.UserService;
import com.storage.storageui.Controllers.Contracts.UserController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import org.controlsfx.control.Notifications;

import java.util.List;

public class AgentController extends UserController {
    private Integer _agentId;
    private UserService _userService;
    private StorageService _storageService;

    @FXML
    private Button logoutBtn;
    @FXML
    private ComboBox<String> notificationsCombo;

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
    public void onNotificationClick()
    {
        List<NotificationModel> notifications =_userService.fetchNotificationsbyUserId(_agentId,false);
        notificationsCombo.setItems( FXCollections.observableArrayList( notifications.stream().map(s->new String(s.getValue())).toList()));
    }


}
