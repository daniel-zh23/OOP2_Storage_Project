package com.storage.storageui.Controllers;

import com.storage.storageBusiness.Models.StorageViewModel;
import com.storage.storageBusiness.Services.*;
import com.storage.storageui.Common.RentersTable;
import com.storage.storageui.Common.SalesTable;
import com.storage.storageui.Common.StorageTable;
import com.storage.storageui.Controllers.Contracts.UserController;
import com.storage.storageui.Controllers.Extensions.NotificationsTask;
import com.storage.storageui.StorageApplication;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AgentController extends UserController {
    private Integer _agentId;
    private UserService _userService;
    private StorageService _storageService;

    private NotificationService _notificationService;

    private RentService _rentService;
    @FXML
    private Button logoutBtn;

    @FXML
    private MenuButton notificationsCombo2;

    @FXML
    private Button clearNotificationsBtn;
    @FXML
    private Button loadStorageButton;
    @FXML
    private Button viewContractsButton;
    @FXML
    private Button createContractButton;
    @FXML
    private TableView tableBox;
    @FXML
    private Label errorLabel;
    private StorageTable storageTable = new StorageTable();
    private SalesTable salesTable = new SalesTable();
    private boolean tableToggle = false; //false for storages , true for contracts

    public void setServices(UserService userService, StorageService storageService, NotificationService notificationService, RentService rentService) {
        if (_userService == null){
            _userService = userService;
        }
        if (_storageService == null){
            _storageService = storageService;
        }
        if (_notificationService == null){
            _notificationService = notificationService;
        }
        if(_rentService==null) {
            _rentService = rentService;
        }
    }

    public void setAgentId(int id) {
        if (_agentId == null) {
            _agentId = id;
        }
    }

    public void onClearNotifications() {
        _notificationService.setNotificationReadStatus(_notificationService.fetchNotificationsbyUserId(_agentId,false));
        clearNotificationsBtn.setVisible(false);
        notificationsCombo2.setText("No notifications");
        notificationsCombo2.getItems().setAll();
    }

    public void startNotificationService() {
        this.timer = new Timer();
        timer.scheduleAtFixedRate(
                new NotificationsTask(_notificationService, notificationsCombo2, clearNotificationsBtn, _agentId),
                0, 14500);
        Runnable task = () -> {
            Platform.runLater(() -> {
                notificationsCombo2
                        .setText(notificationsCombo2.getItems().size() != 0
                                ? String.format("%s notifications", notificationsCombo2.getItems().size())
                                : "No notifications");
            });
        };
        this.executor = Executors.newScheduledThreadPool(5, r -> {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        });
        executor.scheduleAtFixedRate(task, 0, 15, TimeUnit.SECONDS);
    }

    @FXML
    private void onLoadStorage() {
       tableToggle=false;
       loadTable();
    }
    @FXML
    private void onViewContracts()
    {
        tableToggle=true;
        loadTable();
    }

    private void loadTable() {
        if (tableToggle) {
            salesTable.feedSales(_rentService.fetchByAgentId(_agentId));
            salesTable.generateTable(tableBox);
        }
        else {
            storageTable.feedStorages(_storageService.getAllByAgentId(_agentId));
            storageTable.generateTable(tableBox, false);
        }
    }
    public void onCreateContract()throws Exception
    {
        if(tableToggle){errorLabel.setText("Wrong table selected!");return;}
        var data=(StorageViewModel)tableBox.getSelectionModel().getSelectedItem();
        if(data==null){   errorLabel.setText("Select Storage!"); return;}
        FXMLLoader fxmlLoader = new FXMLLoader(StorageApplication.class.getResource("create_sale.fxml"));
        Parent object = fxmlLoader.load();
        var controller = fxmlLoader.<CreateSaleController>getController();
        controller.setServices(logoutBtn.getScene(),_userService,_rentService, _storageService, _agentId, (((StorageViewModel) tableBox.getSelectionModel().getSelectedItem()).getId()));
        Scene scene = new Scene(object);
        Stage window = (Stage)logoutBtn.getScene().getWindow();
        window.setScene(scene);
    }
}

