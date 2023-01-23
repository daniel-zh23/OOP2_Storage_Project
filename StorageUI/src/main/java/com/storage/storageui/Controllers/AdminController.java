package com.storage.storageui.Controllers;

import com.storage.storageBusiness.Models.AgentViewModel;
import com.storage.storageBusiness.Models.Contracts.UserViewModel;
import com.storage.storageBusiness.Models.OwnerViewModel;
import com.storage.storageBusiness.Services.AgentService;
import com.storage.storageBusiness.Services.OwnerService;
import com.storage.storageBusiness.Services.UserService;
import com.storage.storageui.Common.AgentTable;
import com.storage.storageui.Common.OwnerTable;
import com.storage.storageui.Controllers.Contracts.UserController;
import com.storage.storageui.StorageApplication;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class AdminController extends UserController {
    private AgentService _agentService;
    private OwnerService _ownerService;
    private UserService _userService;
    private boolean table_toggle =false; // false for agents, true for owners

    @Override
    public void setServices(AgentService agentService, OwnerService ownerService, UserService userService){
        if (_agentService == null){
            _agentService = agentService;
        }
        if(_ownerService == null){
          _ownerService = ownerService;
        }
        if(_userService == null){
            _userService = userService;
        }
    }

    @FXML
    private HBox horizontalBox;
    @FXML
    private TableView tableBox;
    @FXML
    private Button logoutBtn;
    AgentTable agentTable= new AgentTable();
    OwnerTable ownerTable = new OwnerTable();
    @FXML
    public void onLogout() throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(StorageApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage window = (Stage) logoutBtn.getScene().getWindow();
        window.setScene(scene);
    }

    public void onCreateOwner() throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(StorageApplication.class.getResource("create_owner.fxml"));
        Parent object = fxmlLoader.load();
        var controller = fxmlLoader.<CreateOwnerController>getController();
        controller.setServices(logoutBtn.getScene(), _ownerService, _userService);
        Scene scene = new Scene(object);
        Stage window = (Stage) logoutBtn.getScene().getWindow();
        window.setScene(scene);
    }

    public void onCreateAgent() throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(StorageApplication.class.getResource("create_agent.fxml"));
        Parent object = fxmlLoader.load();
        var controller = fxmlLoader.<CreateAgentController>getController();
        controller.setServices(logoutBtn.getScene(), _agentService, _userService);
        Scene scene = new Scene(object);
        Stage window = (Stage) logoutBtn.getScene().getWindow();
        window.setScene(scene);
    }

    @FXML
    public void onDelete(){
        var data = (UserViewModel) tableBox.getSelectionModel().getSelectedItem();
        if (_userService.checkId(data.getId())){
            _userService.deleteById(data.getId());
        }
        tableBox.refresh();
    }

    @FXML
    public void onApply(){
        if(table_toggle) {
            _ownerService.updateOwners(ownerTable.retrieveOwners());
            ownerTable.generateTable(tableBox);
        }
        else {
            _agentService.updateAgents(agentTable.retrieveAgents());
            agentTable.generateTable(tableBox);
        }
    }
    @FXML
    public void onDiscard()
    {
        if(table_toggle) {
            ownerTable.feedOwners(_ownerService.getOwners());
            ownerTable.generateTable(tableBox);
        }
        else {
            agentTable.feedAgents(_agentService.getAgents());
            agentTable.generateTable(tableBox);
        }
    }
    @FXML
    protected void onLoadOwners(){
        table_toggle =true;
        loadTable();
    }
    @FXML
    protected void onLoadAgents()
    {
        table_toggle =false;
        loadTable();
    }
    private void  loadTable() {
        if (table_toggle) {
            ownerTable.feedOwners(_ownerService.getOwners());
            ownerTable.generateTable(tableBox);
        } else {
            agentTable.feedAgents(_agentService.getAgents());
            agentTable.generateTable(tableBox);
        }
    }
}
