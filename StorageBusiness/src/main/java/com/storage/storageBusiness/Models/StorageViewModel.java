package com.storage.storageBusiness.Models;

import com.storage.storageBusiness.Models.Contracts.UserViewModel;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class StorageViewModel extends UserViewModel {
    private StringProperty address;

    private StringProperty status;

    private DoubleProperty height;

    private DoubleProperty width;

    private DoubleProperty length;

    private StringProperty agentsInfo;


    public StorageViewModel(int id, String address, String status, Double height, Double width, Double length, String agentInfo){
        super(id);
        this.address = new SimpleStringProperty(address);
        this.status = new SimpleStringProperty(status);
        this.height = new SimpleDoubleProperty(height);
        this.width = new SimpleDoubleProperty(width);
        this.length = new SimpleDoubleProperty(length);
        this.agentsInfo = new SimpleStringProperty(agentInfo);
    }

    public String getAgentsInfo() {
        return agentsInfo.get();
    }

    public StringProperty agentsInfoProperty() {
        return agentsInfo;
    }

    public String getAddress() {
        return address.get();
    }

    public StringProperty addressProperty() {
        return address;
    }

    public String getStatus() {
        return status.get();
    }

    public StringProperty statusProperty() {
        return status;
    }

    public double getHeight() {
        return height.get();
    }

    public DoubleProperty heightProperty() {
        return height;
    }

    public double getWidth() {
        return width.get();
    }

    public DoubleProperty widthProperty() {
        return width;
    }

    public double getLength() {
        return length.get();
    }

    public DoubleProperty lengthProperty() {
        return length;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public void setHeight(double height) {
        this.height.set(height);
    }

    public void setWidth(double width) {
        this.width.set(width);
    }

    public void setLength(double length) {
        this.length.set(length);
    }

    public void setAgentsInfo(String agentsInfo) {
        this.agentsInfo.set(agentsInfo);
    }
}
