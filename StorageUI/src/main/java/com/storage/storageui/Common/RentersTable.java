package com.storage.storageui.Common;

import com.storage.storageBusiness.Models.CustomerViewModel;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class RentersTable {
    private List<CustomerViewModel> renters;
    public RentersTable(){
        this.renters=null;
    }
    public void generateTable(TableView table)
    {
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        TableColumn<CustomerViewModel, String> colFirstName = new TableColumn("First Name");
        colFirstName.setCellValueFactory(new PropertyValueFactory<CustomerViewModel,String>("firstName"));
        TableColumn<CustomerViewModel, String> colLastName = new TableColumn<>("Last Name");
        colLastName.setCellValueFactory(new PropertyValueFactory<CustomerViewModel,String>("lastName"));
        TableColumn<CustomerViewModel, String> colPhone = new TableColumn<>("Phone");
        colPhone.setCellValueFactory(new PropertyValueFactory<CustomerViewModel,String>("phone"));

        table.setItems(FXCollections.observableArrayList(renters));
        table.getColumns().setAll(colFirstName, colLastName, colPhone);
    }
    public void feedRenters(List<CustomerViewModel>renters)
    {
        this.renters=renters;
    }
    public List<CustomerViewModel>retrieveRenters()
    {
        return this.renters;
    }
}
