package com.storage.storageui.Controllers;

import com.storage.storageBusiness.Models.AgentViewModel;
import com.storage.storageBusiness.Models.Contracts.UserViewModel;
import com.storage.storageBusiness.Models.StorageViewModel;
import com.storage.storageBusiness.Services.*;
import com.storage.storageui.Common.StorageTable;
import com.storage.storageui.Controllers.Contracts.UserController;
import com.storage.storageui.StorageApplication;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class OwnerController extends UserController {

    private Integer ownerId;

    private OwnerService _ownerService;

    private UserService _userService;

    private AgentService _agentService;

    private StorageService _storageService;

    @FXML
    private TableView tableBox;

    @FXML
    private Button logoutBtn;
private StorageTable storageTable = new StorageTable();
    @Override
    public void setServices(AgentService agentService, OwnerService ownerService, UserService userService, RentService rentService) {
        if (_ownerService == null){
            _ownerService = ownerService;
        }
        if (_userService == null){
            _userService = userService;
        }
        if (_agentService == null){
            _agentService = agentService;
        }
    }

    public void setStorageService(StorageService storageService) {
        _storageService = storageService;
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
        controller.setServices(logoutBtn.getScene(),false,null);
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
        controller.setServices(logoutBtn.getScene(),true,data);
        Scene scene = new Scene(object);
        Stage window = (Stage) logoutBtn.getScene().getWindow();
        window.setScene(scene);

    }
}
