package com.storage.storageui.Controllers;

import com.storage.storageBusiness.Models.AgentViewModel;
import com.storage.storageBusiness.Models.OwnerViewModel;
import com.storage.storageBusiness.Services.AgentService;
import com.storage.storageBusiness.Services.OwnerService;
import com.storage.storageBusiness.Services.UserService;
import com.storage.storageui.Controllers.Contracts.UserController;
import com.storage.storageui.StorageApplication;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class AdminController extends UserController {
    private AgentService _agentService;
    private OwnerService _ownerService;
    private UserService _userService;

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

    @FXML
    protected void onLoadAgents(){

        var agents = _agentService.getAgents();

        tableBox.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        TableColumn<AgentViewModel, String> fName = new TableColumn<>("FirstName");
        fName.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        TableColumn<AgentViewModel, String> lName = new TableColumn<>("LastName");
        lName.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        TableColumn<AgentViewModel, String> phone = new TableColumn<>("Phone");
        phone.setCellValueFactory(cellData -> cellData.getValue().phoneProperty());
        TableColumn<AgentViewModel, String> company = new TableColumn<>("Company");
        company.setCellValueFactory(cellData -> cellData.getValue().companyProperty());
        TableColumn<AgentViewModel, Double> salary = new TableColumn<>("Salary");
        salary.setCellValueFactory(cellData -> cellData.getValue().salaryProperty().asObject());

        tableBox.setItems(FXCollections.observableArrayList(agents));
        tableBox.getColumns().setAll(fName, lName, phone, company, salary);
    }

    @FXML
    protected void onLoadOwners(){

        var owners = _ownerService.getOwners();

        tableBox.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        TableColumn<OwnerViewModel, String> fName = new TableColumn<>("FirstName");
        fName.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        TableColumn<OwnerViewModel, String> lName = new TableColumn<>("LastName");
        lName.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        TableColumn<OwnerViewModel, String> phone = new TableColumn<>("Phone");
        phone.setCellValueFactory(cellData -> cellData.getValue().phoneProperty());
        TableColumn<OwnerViewModel, String> email = new TableColumn<>("Email");
        email.setCellValueFactory(cellData -> cellData.getValue().emailProperty());

        tableBox.setItems(FXCollections.observableArrayList(owners));
        tableBox.getColumns().setAll(fName, lName, phone, email);
    }

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
}
