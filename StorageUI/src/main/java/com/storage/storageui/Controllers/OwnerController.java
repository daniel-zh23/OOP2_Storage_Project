package com.storage.storageui.Controllers;

import com.storage.storageBusiness.Models.AgentViewModel;
import com.storage.storageBusiness.Models.Contracts.UserViewModel;
import com.storage.storageBusiness.Models.StorageViewModel;
import com.storage.storageBusiness.Services.AgentService;
import com.storage.storageBusiness.Services.OwnerService;
import com.storage.storageBusiness.Services.StorageService;
import com.storage.storageBusiness.Services.UserService;
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

    @Override
    public void setServices(AgentService agentService, OwnerService ownerService, UserService userService) {
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

    public void onMyStorages() {
        var storages = _storageService.getAllByOwnerId(ownerId);

        tableBox.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        TableColumn<StorageViewModel, String> address = new TableColumn<>("Address");
        address.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
        TableColumn<StorageViewModel, String> status = new TableColumn<>("Status");
        status.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
        TableColumn<StorageViewModel, Double> height = new TableColumn<>("Height");
        height.setCellValueFactory(cellData -> cellData.getValue().heightProperty().asObject());
        TableColumn<StorageViewModel, Double> width = new TableColumn<>("Width");
        width.setCellValueFactory(cellData -> cellData.getValue().widthProperty().asObject());
        TableColumn<StorageViewModel, Double> length = new TableColumn<>("Length");
        length.setCellValueFactory(cellData -> cellData.getValue().lengthProperty().asObject());
        TableColumn<StorageViewModel, String> agentInfo = new TableColumn<>("Agent");
        agentInfo.setCellValueFactory(cellData -> cellData.getValue().agentsInfoProperty());

        tableBox.setItems(FXCollections.observableArrayList(storages));
        tableBox.getColumns().setAll(address, status, height, width, length, agentInfo);
    }

    public void onViewAgents(ActionEvent actionEvent) {

    }

    public void onAddStorage() throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(StorageApplication.class.getResource("create_storage.fxml"));
        Parent object = fxmlLoader.load();
        var controller = fxmlLoader.<CreateStorageController>getController();
        controller.setServices(logoutBtn.getScene(), _storageService, ownerId);
        Scene scene = new Scene(object);
        Stage window = (Stage) logoutBtn.getScene().getWindow();
        window.setScene(scene);
    }

    public void onDelete() {
        var data = (UserViewModel) tableBox.getSelectionModel().getSelectedItem();
        if (data instanceof StorageViewModel && _storageService.checkId(data.getId())){
            _storageService.deleteById(data.getId());
        }
    }
}
