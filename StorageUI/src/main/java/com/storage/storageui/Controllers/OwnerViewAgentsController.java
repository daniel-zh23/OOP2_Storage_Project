package com.storage.storageui.Controllers;

import com.storage.storageBusiness.Models.AgentViewModel;
import com.storage.storageBusiness.Models.StorageViewModel;
import com.storage.storageBusiness.Services.AgentService;
import com.storage.storageBusiness.Services.StorageService;
import com.storage.storageui.Common.AgentTable;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class OwnerViewAgentsController {
    @FXML
    private Button backButton;
    @FXML
    private Button loadAgentsButton;
    @FXML
    private Button assignButton;
    @FXML
    private TableView tableBox;
    private AgentTable agentTable = new AgentTable();
    private Scene _scene;
    private AgentService _agentService;
    private StorageService _storageService;
    private StorageViewModel storage;
    public void setServices(AgentService agentService, StorageService storageService, Scene scene, boolean assign, StorageViewModel storage) // boolean to indicate if the scene is used to assign agent to storage
    {
        if (_scene == null){
            _scene = scene;
        }
        if (_agentService == null){
            _agentService = agentService;
        }
        if (_storageService == null){
            _storageService = storageService;
        }

        assignButton.setVisible(assign);
        this.storage=storage;
    }
    @FXML
    protected void onBack(){
        Stage window = (Stage) backButton.getScene().getWindow();
        window.setScene(_scene);
    }
    @FXML
    protected void onLoadAgents()
    {
        agentTable.feedAgents(_agentService.getAgents());
        agentTable.generateTable(tableBox,false);
    }
    @FXML
    protected void onAssign()
    {
        AgentViewModel agent=(AgentViewModel)tableBox.getSelectionModel().getSelectedItem();
        _storageService.assignAgentToStorage(storage,agent);
        Stage window = (Stage) backButton.getScene().getWindow();
        window.setScene(_scene);
    }



}
