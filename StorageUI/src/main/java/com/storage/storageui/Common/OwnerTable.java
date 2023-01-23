package com.storage.storageui.Common;

import com.storage.storageBusiness.Models.AgentViewModel;
import com.storage.storageBusiness.Models.OwnerViewModel;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.util.List;

public class OwnerTable {
    private List<OwnerViewModel> owners;
    public OwnerTable(List<OwnerViewModel> owners){this.owners=owners;}
    public OwnerTable(){this.owners=null;}
    public void  generateTable(TableView table)
    {
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setEditable(true);
        //Fist name
        TableColumn colFirstName = new TableColumn("First Name");
        colFirstName.setCellValueFactory(new PropertyValueFactory<OwnerViewModel,String>("firstName"));
        colFirstName.setCellFactory(TextFieldTableCell.forTableColumn());
        colFirstName.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<OwnerViewModel,String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent cellEditEvent) {
                OwnerViewModel owner= (OwnerViewModel)cellEditEvent.getTableView().getItems().get(cellEditEvent.getTablePosition().getRow());
                owners.stream().filter(a->a.getId()==owner.getId()).forEach(a->a.setFirstName(cellEditEvent.getNewValue().toString()));
            }
        });
        //Last Name
        TableColumn<OwnerViewModel, String> colLastName = new TableColumn<>("Last Name");
        colLastName.setCellValueFactory(new PropertyValueFactory<OwnerViewModel,String>("lastName"));
        colLastName.setCellFactory(TextFieldTableCell.forTableColumn());
        colLastName.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<OwnerViewModel,String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent cellEditEvent) {
                OwnerViewModel owner= (OwnerViewModel)cellEditEvent.getTableView().getItems().get(cellEditEvent.getTablePosition().getRow());
                owners.stream().filter(a->a.getId()==owner.getId()).forEach(a->a.setLastName(cellEditEvent.getNewValue().toString()));
            }
        });

        //Phone
        TableColumn<OwnerViewModel, String> colPhone = new TableColumn<>("Phone");
        colPhone.setCellValueFactory(new PropertyValueFactory<OwnerViewModel,String>("phone"));
        colPhone.setCellFactory(TextFieldTableCell.forTableColumn());
        colPhone.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<OwnerViewModel,String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent cellEditEvent) {
                OwnerViewModel owner= (OwnerViewModel)cellEditEvent.getTableView().getItems().get(cellEditEvent.getTablePosition().getRow());
                owners.stream().filter(a->a.getId()==owner.getId()).forEach(a->a.setPhone(cellEditEvent.getNewValue().toString()));
            }
        });
//email
        TableColumn colEmail = new TableColumn("Email");
        colEmail.setCellValueFactory(new PropertyValueFactory<OwnerViewModel,String>("email"));
        colEmail.setCellFactory(TextFieldTableCell.forTableColumn());
        colEmail.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<OwnerViewModel,String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent cellEditEvent) {
                OwnerViewModel owner= (OwnerViewModel)cellEditEvent.getTableView().getItems().get(cellEditEvent.getTablePosition().getRow());
                owners.stream().filter(a->a.getId()==owner.getId()).forEach(a->a.setFirstName(cellEditEvent.getNewValue().toString()));
            }
        });
        table.setItems(FXCollections.observableArrayList(owners));
        table.getColumns().setAll(colFirstName, colLastName, colPhone, colEmail);


    }
    public void feedOwners(List<OwnerViewModel>owners)
    {
        this.owners=owners;
    }

    public List<OwnerViewModel>retrieveOwners()
    {
        return this.owners;
    }
}
