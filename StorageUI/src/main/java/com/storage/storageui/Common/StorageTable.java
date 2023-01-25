package com.storage.storageui.Common;

import com.storage.storageBusiness.Models.OwnerViewModel;
import com.storage.storageBusiness.Models.StorageViewModel;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DoubleStringConverter;

import java.util.List;

public class StorageTable {
    private List<StorageViewModel> storages;
    public StorageTable(List<StorageViewModel>storages){this.storages=storages;}
    public StorageTable(){this.storages=null;}
    public void generateTable(TableView table, boolean isEditable)
    {
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setEditable(isEditable);
        //Storage Address
        TableColumn colAddress= new TableColumn("Address");
        colAddress.setCellValueFactory(new PropertyValueFactory<StorageViewModel,String>("Address"));
        colAddress.setCellFactory(TextFieldTableCell.forTableColumn());
        colAddress.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<StorageViewModel,String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent cellEditEvent) {
                StorageViewModel storage =(StorageViewModel) cellEditEvent.getTableView().getItems().get(cellEditEvent.getTablePosition().getRow());
                storages.stream().filter(s->s.getId()==storage.getId()).forEach(s->s.setAddress(cellEditEvent.getNewValue().toString()));
            }
        });
        //Status
        TableColumn<StorageViewModel, String> colStatus = new TableColumn<>("Status");
        colStatus.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
       //height
        TableColumn colHeight= new TableColumn("Height");
        colHeight.setCellValueFactory(new PropertyValueFactory<StorageViewModel,Double>("Height"));
        colHeight.setCellFactory(TextFieldTableCell.forTableColumn((new DoubleStringConverter())));
        colHeight.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<StorageViewModel,Double>>() {
            @Override
            public void handle(TableColumn.CellEditEvent cellEditEvent) {
                StorageViewModel storage =(StorageViewModel) cellEditEvent.getTableView().getItems().get(cellEditEvent.getTablePosition().getRow());
                storages.stream().filter(s->s.getId()==storage.getId()).forEach(s->s.setHeight((double)cellEditEvent.getNewValue()));
            }
        });
        //width
        TableColumn colWidth= new TableColumn("Width");
        colWidth.setCellValueFactory(new PropertyValueFactory<StorageViewModel,Double>("Width"));
        colWidth.setCellFactory(TextFieldTableCell.forTableColumn((new DoubleStringConverter())));
        colWidth.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<StorageViewModel,Double>>() {
            @Override
            public void handle(TableColumn.CellEditEvent cellEditEvent) {
                StorageViewModel storage =(StorageViewModel) cellEditEvent.getTableView().getItems().get(cellEditEvent.getTablePosition().getRow());
                storages.stream().filter(s->s.getId()==storage.getId()).forEach(s->s.setWidth((double)cellEditEvent.getNewValue()));
            }
        });
        //length
        TableColumn colLength= new TableColumn("Length");
        colLength.setCellValueFactory(new PropertyValueFactory<StorageViewModel,Double>("Length"));
        colLength.setCellFactory(TextFieldTableCell.forTableColumn((new DoubleStringConverter())));
        colLength.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<StorageViewModel,Double>>() {
            @Override
            public void handle(TableColumn.CellEditEvent cellEditEvent) {
                StorageViewModel storage =(StorageViewModel) cellEditEvent.getTableView().getItems().get(cellEditEvent.getTablePosition().getRow());
                storages.stream().filter(s->s.getId()==storage.getId()).forEach(s->s.setLength((double)cellEditEvent.getNewValue()));
            }
        });
        //agents
        TableColumn<StorageViewModel,String> colAgents = new TableColumn<>("Agent");
        colAgents.setCellValueFactory(cellData->cellData.getValue().agentsInfoProperty());
        table.setItems(FXCollections.observableArrayList(storages));
        table.getColumns().setAll(colAddress,colStatus,colHeight,colWidth,colLength,colAgents);
    }
    public void feedStorages(List<StorageViewModel>storages)
    {
        this.storages=storages;
    }
    public List<StorageViewModel> retrieveStorages()
    {
        return this.storages;
    }
}
