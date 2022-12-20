package com.storage.storageui.Controllers;

import com.storage.storageBusiness.Models.AgentViewModel;
import com.storage.storageBusiness.Services.UserService;
import com.storage.storageui.Employee;
import com.storage.storageui.StorageApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class AdminController {
    private UserService _userService;

    public void setUserService(UserService service){
        if (_userService == null){
            _userService = service;
        }
    }

    private final ObservableList<Employee> employees = FXCollections.observableArrayList(
            new Employee("Jhonny", "Sins2", 250.0),
            new Employee("Daniel", "Zhekov2", 350.0)

    );

    @FXML
    private HBox horizontalBox;
    @FXML
    private TableView tableBox;
    @FXML
    private Button logoutBtn;

    @FXML
    protected void onloadAgents(){

        var agents = _userService.getAgents();

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
    protected void onloadOwners(){

        tableBox.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        TableColumn<Employee, String> fName = new TableColumn<>("FirstName");
        fName.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        TableColumn<Employee, String> lName = new TableColumn<>("LastName");
        lName.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        TableColumn<Employee, Double> salary = new TableColumn<>("Salary");
        lName.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

        tableBox.setItems(employees);
        tableBox.getColumns().setAll(fName, lName, salary);
    }

    @FXML
    public void onLogout() throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(StorageApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage window = (Stage) logoutBtn.getScene().getWindow();
        window.setScene(scene);
    }
}
