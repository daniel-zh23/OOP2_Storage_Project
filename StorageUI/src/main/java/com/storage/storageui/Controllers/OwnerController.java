package com.storage.storageui.Controllers;

import com.storage.storageBusiness.Models.AgentViewModel;
import com.storage.storageBusiness.Models.StorageViewModel;
import com.storage.storageBusiness.Services.AgentService;
import com.storage.storageBusiness.Services.OwnerService;
import com.storage.storageBusiness.Services.StorageService;
import com.storage.storageBusiness.Services.UserService;
import com.storage.storageui.Controllers.Contracts.UserController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class OwnerController extends UserController {

    private Integer ownerId;

    private OwnerService _ownerService;

    private StorageService _storageService;

    @FXML
    private TableView tableBox;

    @Override
    public void setServices(AgentService agentService, OwnerService ownerService, UserService userService) {
        _ownerService = ownerService;
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

        tableBox.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        TableColumn<StorageViewModel, String> address = new TableColumn<>("FirstName");
        address.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
        TableColumn<StorageViewModel, String> status = new TableColumn<>("LastName");
        status.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
        TableColumn<StorageViewModel, Double> height = new TableColumn<>("Phone");
        height.setCellValueFactory(cellData -> cellData.getValue().heightProperty().asObject());
        TableColumn<StorageViewModel, Double> width = new TableColumn<>("Company");
        width.setCellValueFactory(cellData -> cellData.getValue().widthProperty().asObject());
        TableColumn<StorageViewModel, Double> length = new TableColumn<>("Salary");
        length.setCellValueFactory(cellData -> cellData.getValue().lengthProperty().asObject());

        tableBox.setItems(FXCollections.observableArrayList(storages));
        tableBox.getColumns().setAll(address, status, height, width, length);
    }

    public void onViewAgents(ActionEvent actionEvent) {
    }

    public void onAddStorage(ActionEvent actionEvent) {
    }

}
