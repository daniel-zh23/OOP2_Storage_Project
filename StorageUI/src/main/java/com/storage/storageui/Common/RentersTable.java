package com.storage.storageui.Common;

import com.storage.storageBusiness.Models.AgentViewModel;
import com.storage.storageBusiness.Models.RenterViewModel;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class RentersTable {
    private List<RenterViewModel> renters;
    public RentersTable(){
        this.renters=null;
    }
    public void generateTable(TableView table)
    {
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        TableColumn<RenterViewModel, String> colFirstName = new TableColumn("First Name");
        colFirstName.setCellValueFactory(new PropertyValueFactory<RenterViewModel,String>("firstName"));
        TableColumn<RenterViewModel, String> colLastName = new TableColumn<>("Last Name");
        colLastName.setCellValueFactory(new PropertyValueFactory<RenterViewModel,String>("lastName"));
        TableColumn<RenterViewModel, String> colPhone = new TableColumn<>("Phone");
        colPhone.setCellValueFactory(new PropertyValueFactory<RenterViewModel,String>("phone"));

        table.setItems(FXCollections.observableArrayList(renters));
        table.getColumns().setAll(colFirstName, colLastName, colPhone);
    }
    public void feedRenters(List<RenterViewModel>renters)
    {
        this.renters=renters;
    }
    public List<RenterViewModel>retrieveRenters()
    {
        return this.renters;
    }
}
