package com.storage.storageBusiness.Models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class StorageViewModel {
    private StringProperty address;

    private StringProperty status;

    private DoubleProperty height;

    private DoubleProperty width;

    private DoubleProperty length;


    public StorageViewModel(String address, String status, Double height, Double width, Double length){
        this.address = new SimpleStringProperty(address);
        this.status = new SimpleStringProperty(status);
        this.height = new SimpleDoubleProperty(height);
        this.width = new SimpleDoubleProperty(width);
        this.length = new SimpleDoubleProperty(length);
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
}
