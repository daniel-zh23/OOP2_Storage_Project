package com.storage.storageui.Common;

import com.storage.storageBusiness.Models.AgentViewModel;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DoubleStringConverter;

import java.util.ArrayList;
import java.util.List;

public class AgentTableBuilder {
    private TableView _table;

    private boolean _editable = false;

    private List<AgentViewModel> _agents = new ArrayList<>();

    public AgentTableBuilder feedData(TableView table, List<AgentViewModel> agents, boolean editable) {
        _table = table;
        _editable = editable;
        _agents = agents;
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setItems(FXCollections.observableArrayList(agents));
        table.getColumns().setAll();
        table.setEditable(true);
        return this;
    }

    public AgentTableBuilder firstNameCol(){
        return this.firstNameCol(_editable);
    }

    public AgentTableBuilder firstNameCol(boolean editable){
        TableColumn column = new TableColumn("First Name");
        column.setCellValueFactory(new PropertyValueFactory<AgentViewModel,String>("firstName"));
        if (editable) {
            column.setCellFactory(TextFieldTableCell.forTableColumn());
            column.setOnEditCommit((EventHandler<TableColumn.CellEditEvent<AgentViewModel, String>>) cellEditEvent -> {
                AgentViewModel agent = (AgentViewModel) cellEditEvent.getTableView().getItems().get(cellEditEvent.getTablePosition().getRow());
                _agents.stream().filter(a -> a.getId() == agent.getId()).forEach(a -> a.setFirstName(cellEditEvent.getNewValue().toString()));
            });
        }
        _table.getColumns().add(column);
        return this;
    }

    public AgentTableBuilder lastNameCol(){
        return this.lastNameCol(_editable);
    }

    public AgentTableBuilder lastNameCol(boolean editable){
        TableColumn<AgentViewModel, String> column = new TableColumn<>("Last Name");
        column.setCellValueFactory(new PropertyValueFactory<AgentViewModel,String>("lastName"));
        if (editable) {
            column.setCellFactory(TextFieldTableCell.forTableColumn());
            column.setOnEditCommit(cellEditEvent -> {
                AgentViewModel agent = (AgentViewModel) cellEditEvent.getTableView().getItems().get(cellEditEvent.getTablePosition().getRow());
                _agents.stream().filter(a -> a.getId() == agent.getId()).forEach(a -> a.setLastName(cellEditEvent.getNewValue().toString()));
            });
        }
        _table.getColumns().add(column);
        return this;
    }

    public AgentTableBuilder phoneCol(){
        return this.phoneCol(_editable);
    }

    public AgentTableBuilder phoneCol(boolean editable){
        TableColumn<AgentViewModel, String> column = new TableColumn<>("Phone");
        column.setCellValueFactory(new PropertyValueFactory<AgentViewModel,String>("phone"));
        if (editable) {
            column.setCellFactory(TextFieldTableCell.forTableColumn());
            column.setOnEditCommit(cellEditEvent -> {
                AgentViewModel agent = (AgentViewModel) cellEditEvent.getTableView().getItems().get(cellEditEvent.getTablePosition().getRow());
                _agents.stream().filter(a -> a.getId() == agent.getId()).forEach(a -> a.setPhone(cellEditEvent.getNewValue().toString()));
            });
        }
        _table.getColumns().add(column);
        return this;
    }

    public AgentTableBuilder companyCol(){
        return this.companyCol(_editable);
    }

    public AgentTableBuilder companyCol(boolean editable){
        TableColumn<AgentViewModel, String> column = new TableColumn<>("Company");
        column.setCellValueFactory(new PropertyValueFactory<AgentViewModel,String>("company"));
        if (editable) {
            column.setCellFactory(TextFieldTableCell.forTableColumn());
            column.setOnEditCommit(cellEditEvent -> {
                AgentViewModel agent = (AgentViewModel) cellEditEvent.getTableView().getItems().get(cellEditEvent.getTablePosition().getRow());
                _agents.stream().filter(a -> a.getId() == agent.getId()).forEach(a -> a.setCompany(cellEditEvent.getNewValue().toString()));
            });
        }
        _table.getColumns().add(column);
        return this;
    }

    public AgentTableBuilder salaryCol(){
        return this.salaryCol(_editable);
    }

    public AgentTableBuilder salaryCol(boolean editable){
        TableColumn<AgentViewModel, Double> column = new TableColumn<>("Salary");
        column.setCellValueFactory(new PropertyValueFactory<AgentViewModel,Double>("salary"));
        if (editable){
            column.setCellFactory(TextFieldTableCell.forTableColumn((new DoubleStringConverter())));
            column.setOnEditCommit(cellEditEvent -> {
                AgentViewModel agent= (AgentViewModel)cellEditEvent.getTableView().getItems().get(cellEditEvent.getTablePosition().getRow());
                _agents.stream().filter(a->a.getId()==agent.getId()).forEach(a->a.setSalary((double)cellEditEvent.getNewValue()));
            });
        }
        _table.getColumns().add(column);
        return this;
    }

    public AgentTableBuilder ratingCol(){
        TableColumn<AgentViewModel, Double> column = new TableColumn<>("Rating");
        column.setCellValueFactory(new PropertyValueFactory<AgentViewModel,Double>("rating"));
        _table.getColumns().add(column);
        return this;
    }

    public TableView build(){
        return _table;
    }

    public List<AgentViewModel> retrieveAgents()
    {
        return _agents;
    }
}
