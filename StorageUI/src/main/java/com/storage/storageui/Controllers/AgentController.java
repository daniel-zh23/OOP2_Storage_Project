package com.storage.storageui.Controllers;

import com.storage.storageBusiness.Models.NotificationModel;
import com.storage.storageBusiness.Models.StorageViewModel;
import com.storage.storageBusiness.Services.*;
import com.storage.storageui.Common.RentersTable;
import com.storage.storageui.Common.StorageTable;
import com.storage.storageui.Controllers.Contracts.UserController;
import com.storage.storageui.Controllers.Extensions.NotificationsTask;
import com.storage.storageui.StorageApplication;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
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
    private StorageTable storageTable = new StorageTable();
    private RentersTable renterTable = new RentersTable();
    private boolean tableToggle = false; //false for storages , true for contracts

    @Override
    public void setServices(AgentService agentService, OwnerService ownerService, UserService userService, RentService rentService) {

        if (_userService == null) {
            _userService = userService;
        }
//        if(_storageService == null){
//            _storageService = _storageService;
//        }
        _storageService = new StorageService();
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
        _userService.setNotificationReadStatus(_userService.fetchNotificationsbyUserId(_agentId, false));
        clearNotificationsBtn.setVisible(false);
        notificationsCombo2.setText("No notifications");
        notificationsCombo2.getItems().setAll();
    }

    public void startNotificationService() {
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
            return t;
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
            renterTable.feedRenters(_rentService.getAllRenters());
            renterTable.generateTable(tableBox);
        }
        else {
            storageTable.feedStorages(_storageService.getAllByAgentId(_agentId));
            storageTable.generateTable(tableBox, false);
        }
    }
    public void onCreateContract()throws Exception
    {
        if(tableToggle){System.out.println("Wrong table selected!");return;}
        var data=(StorageViewModel)tableBox.getSelectionModel().getSelectedItem();
        FXMLLoader fxmlLoader = new FXMLLoader(StorageApplication.class.getResource("create_sale.fxml"));
        Parent object = fxmlLoader.load();
        var controller = fxmlLoader.<CreateSaleController>getController();
        controller.setServices(logoutBtn.getScene(),_userService,_rentService);
        Scene scene = new Scene(object);
        Stage window = (Stage)logoutBtn.getScene().getWindow();
        window.setScene(scene);

    }
}

