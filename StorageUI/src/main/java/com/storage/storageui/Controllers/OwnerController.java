package com.storage.storageui.Controllers;

import com.storage.storageBusiness.Models.AgentViewModel;
import com.storage.storageBusiness.Models.Contracts.UserViewModel;
import com.storage.storageBusiness.Models.StorageViewModel;
import com.storage.storageBusiness.Services.*;
import com.storage.storageui.Common.StorageTable;
import com.storage.storageui.Controllers.Contracts.UserController;
import com.storage.storageui.Controllers.Extensions.NotificationsTask;
import com.storage.storageui.StorageApplication;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class OwnerController extends UserController {

    private StorageTable storageTable = new StorageTable();

    private Integer ownerId;

    private OwnerService _ownerService;

    private UserService _userService;

    private AgentService _agentService;

    private StorageService _storageService;

    private NotificationService _notificationService;

    private RentService _rentService;

    @FXML
    private TableView tableBox;

    @FXML
    private Button logoutBtn;

    @FXML
    private Button clearNotificationsBtn;

    @FXML
    private MenuButton notificationsCombo2;


    public void setServices(
            AgentService agentService,
            OwnerService ownerService,
            UserService userService,
            StorageService storageService,
            NotificationService notificationService,
            RentService rentService) {

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
        if (_rentService == null){
            _rentService = rentService;
        }
    }

    public void setOwnerId(int id){
        if (ownerId == null){
            ownerId = id;
        }
    }
    @FXML
    protected void onMyStorages() {
        storageTable.feedStorages(_storageService.getAllByOwnerId(ownerId));
        storageTable.generateTable(tableBox,true);
    }
    @FXML
    protected void onViewAgents() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(StorageApplication.class.getResource("agents_table.fxml"));
        Parent object =fxmlLoader.load();
        var controller =fxmlLoader.<OwnerViewAgentsController>getController();
        controller.setServices(_agentService, _storageService, logoutBtn.getScene(),false,null);
        Scene scene = new Scene(object);
        Stage window = (Stage) logoutBtn.getScene().getWindow();
        window.setScene(scene);

    }
    @FXML
    protected void onAddStorage() throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(StorageApplication.class.getResource("create_storage.fxml"));
        Parent object = fxmlLoader.load();
        var controller = fxmlLoader.<CreateStorageController>getController();
        controller.setServices(logoutBtn.getScene(), _storageService, ownerId);
        Scene scene = new Scene(object);
        Stage window = (Stage) logoutBtn.getScene().getWindow();
        window.setScene(scene);
    }

    @FXML
    protected void onDelete() {
        var data = (UserViewModel) tableBox.getSelectionModel().getSelectedItem();
        if (data instanceof StorageViewModel && _storageService.checkId(data.getId())){
            _storageService.deleteById(data.getId());
        }
    }
    @FXML
    protected void onClearNotifications()
    {
        _notificationService.setNotificationReadStatus(_notificationService.fetchNotificationsbyUserId(ownerId,false));
        clearNotificationsBtn.setVisible(false);
        notificationsCombo2.setText("No notifications");
        notificationsCombo2.getItems().setAll();
    }
    @FXML
    protected void onApply()
    {
        _storageService.updateStorages(storageTable.retrieveStorages());
    }
    @FXML
    protected void onDiscard()
    {
        storageTable.feedStorages(_storageService.getAllByOwnerId(ownerId));
        storageTable.generateTable(tableBox,true);
    }
    @FXML
    protected void onAssignAgent() throws Exception
    {
        var data=(StorageViewModel)tableBox.getSelectionModel().getSelectedItem();
        FXMLLoader fxmlLoader = new FXMLLoader(StorageApplication.class.getResource("agents_table.fxml"));
        Parent object =fxmlLoader.load();
        var controller =fxmlLoader.<OwnerViewAgentsController>getController();
        controller.setServices(_agentService, _storageService, logoutBtn.getScene(),true,data);
        Scene scene = new Scene(object);
        Stage window = (Stage) logoutBtn.getScene().getWindow();
        window.setScene(scene);

    }

    public void startNotificationService(){
        this.timer = new Timer();
        timer.scheduleAtFixedRate(
                new NotificationsTask(_notificationService, notificationsCombo2, clearNotificationsBtn, ownerId),
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
            return t ;
        });
        executor.scheduleAtFixedRate(task, 0, 15, TimeUnit.SECONDS);
    }
}
