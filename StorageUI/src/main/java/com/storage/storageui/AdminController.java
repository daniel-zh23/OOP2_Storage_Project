package com.storage.storageui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdminController {
    private final ObservableList<Customer> customers = FXCollections.observableArrayList(
        new Customer("Jhonny", "Sins"),
            new Customer("Daniel", "Zhekov")

    );
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

        tableBox.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        TableColumn<Customer, String> fName = new TableColumn<>("FirstName");
        fName.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        TableColumn<Customer, String> lName = new TableColumn<>("LastName");
        lName.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

        tableBox.setItems(customers);
        tableBox.getColumns().setAll(fName, lName);
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
