package com.storage.storageui.Common;

import com.storage.storageBusiness.Models.AgentViewModel;
import com.storage.storageBusiness.Models.OwnerViewModel;
import com.storage.storageBusiness.Services.AgentService;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.NumberStringConverter;

import java.util.List;

public class AgentTable {

    private List<AgentViewModel> agents;
    public AgentTable(List<AgentViewModel> agents)
    {
        this.agents=agents;
    }
    public AgentTable() {this.agents=null;}
    public void generateTable(TableView table,boolean isEditable)
    {
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setEditable(isEditable);
        //Fist name
        TableColumn colFirstName = new TableColumn("First Name");
        colFirstName.setCellValueFactory(new PropertyValueFactory<AgentViewModel,String>("firstName"));
        colFirstName.setCellFactory(TextFieldTableCell.forTableColumn());
        colFirstName.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<AgentViewModel,String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent cellEditEvent) {
                AgentViewModel agent= (AgentViewModel)cellEditEvent.getTableView().getItems().get(cellEditEvent.getTablePosition().getRow());
               agents.stream().filter(a->a.getId()==agent.getId()).forEach(a->a.setFirstName(cellEditEvent.getNewValue().toString()));
            }
        });
        //Last Name
        TableColumn<AgentViewModel, String> colLastName = new TableColumn<>("Last Name");
        colLastName.setCellValueFactory(new PropertyValueFactory<AgentViewModel,String>("lastName"));
        colLastName.setCellFactory(TextFieldTableCell.forTableColumn());
        colLastName.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<AgentViewModel,String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent cellEditEvent) {
                AgentViewModel agent= (AgentViewModel)cellEditEvent.getTableView().getItems().get(cellEditEvent.getTablePosition().getRow());
                agents.stream().filter(a->a.getId()==agent.getId()).forEach(a->a.setLastName(cellEditEvent.getNewValue().toString()));
            }
        });

        //Phone
        TableColumn<AgentViewModel, String> colPhone = new TableColumn<>("Phone");
        colPhone.setCellValueFactory(new PropertyValueFactory<AgentViewModel,String>("phone"));
        colPhone.setCellFactory(TextFieldTableCell.forTableColumn());
        colPhone.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<AgentViewModel,String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent cellEditEvent) {
                AgentViewModel agent= (AgentViewModel)cellEditEvent.getTableView().getItems().get(cellEditEvent.getTablePosition().getRow());
                agents.stream().filter(a->a.getId()==agent.getId()).forEach(a->a.setPhone(cellEditEvent.getNewValue().toString()));
            }
        });

//Company
        TableColumn<AgentViewModel, String> colCompany = new TableColumn<>("Company");
        colCompany.setCellValueFactory(new PropertyValueFactory<AgentViewModel,String>("company"));
        colCompany.setCellFactory(TextFieldTableCell.forTableColumn());
        colCompany.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<AgentViewModel,String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent cellEditEvent) {
                AgentViewModel agent= (AgentViewModel)cellEditEvent.getTableView().getItems().get(cellEditEvent.getTablePosition().getRow());
                agents.stream().filter(a->a.getId()==agent.getId()).forEach(a->a.setCompany(cellEditEvent.getNewValue().toString()));
            }
        });
        TableColumn<AgentViewModel, Double> colSalary = new TableColumn<>("Salary");
        colSalary.setCellValueFactory(new PropertyValueFactory<AgentViewModel,Double>("salary"));
        colSalary.setCellFactory(TextFieldTableCell.forTableColumn((new DoubleStringConverter())));
        colSalary.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<AgentViewModel,Double>>() {
            @Override
            public void handle(TableColumn.CellEditEvent cellEditEvent) {
                AgentViewModel agent= (AgentViewModel)cellEditEvent.getTableView().getItems().get(cellEditEvent.getTablePosition().getRow());
                agents.stream().filter(a->a.getId()==agent.getId()).forEach(a->a.setSalary((double)cellEditEvent.getNewValue()));
            }
        });

        //Rating
        TableColumn<AgentViewModel, String> colRating = new TableColumn<>("Rating");
        colRating.setCellValueFactory(cellData -> cellData.getValue().ratingProperty());

        table.setItems(FXCollections.observableArrayList(agents));
        table.getColumns().setAll(colFirstName, colLastName, colPhone, colCompany, colRating, colSalary);
    }
public void feedAgents(List<AgentViewModel> agents)
    {
        this.agents=agents;
    }
    public List<AgentViewModel> retrieveAgents()
    {
        return this.agents;
    }
}