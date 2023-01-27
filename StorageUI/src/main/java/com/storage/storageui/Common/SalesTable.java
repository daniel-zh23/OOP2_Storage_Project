package com.storage.storageui.Common;
import com.storage.storageBusiness.Models.SaleViewModel;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class SalesTable {
    private List<SaleViewModel> sales;
    public SalesTable()
    {
        this.sales=null;
    }
    public void generateTable(TableView table)
    {
        //table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        TableColumn<SaleViewModel, String> colStorageInfo = new TableColumn("Storage");
        colStorageInfo.setCellValueFactory(new PropertyValueFactory<SaleViewModel,String>("storageInfo"));
        TableColumn<SaleViewModel, String> colDate  = new TableColumn("Date of Rent");
        colDate.setCellValueFactory(new PropertyValueFactory<SaleViewModel,String>("date"));
        TableColumn<SaleViewModel, String> colDuration  = new TableColumn("Duration of rent (In months)");
        colDuration.setCellValueFactory(new PropertyValueFactory<SaleViewModel,String>("duration"));
        TableColumn<SaleViewModel, String> colRenterInfo  = new TableColumn("Renter");
        colRenterInfo.setCellValueFactory(new PropertyValueFactory<SaleViewModel,String>("renterInfo"));
        TableColumn<SaleViewModel, String> colPrice  = new TableColumn("Negotiated Price");
        colPrice.setCellValueFactory(new PropertyValueFactory<SaleViewModel,String>("price"));
        TableColumn<SaleViewModel, String> colAgentInfo  = new TableColumn("Agent");
        colAgentInfo.setCellValueFactory(new PropertyValueFactory<SaleViewModel,String>("agentInfo"));
        table.setItems(FXCollections.observableArrayList(sales));
        table.getColumns().setAll(colStorageInfo,colDate,colDuration,colRenterInfo,colPrice,colAgentInfo);
    }
    public void feedSales(List<SaleViewModel>sales){this.sales=sales;}

}