package com.storage.storageBusiness.Models;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Date;

public class SaleViewModel {
    private SimpleIntegerProperty id;
    private SimpleDoubleProperty price;
    private SimpleIntegerProperty duration;
    private SimpleStringProperty date;
    private SimpleIntegerProperty storageId;
    private SimpleIntegerProperty agentId;
    private SimpleIntegerProperty renterId;
    private SimpleStringProperty storageInfo;
    private SimpleStringProperty agentInfo;
    private SimpleStringProperty renterInfo;

    private SimpleBooleanProperty active;

    public SaleViewModel(int id, double price, int duration, Date date, int storageId, int  agentId, int  renterId,String storageInfo,String agentInfo,String renterInfo )
    {
        this.id=new SimpleIntegerProperty(id);
        this.price=new SimpleDoubleProperty(price);
        this.duration=new SimpleIntegerProperty(duration);
        this.date=new SimpleStringProperty(date.toString());
        this.storageId=new SimpleIntegerProperty(storageId);
        this.agentId=new SimpleIntegerProperty(agentId);
        this.renterId=new SimpleIntegerProperty(renterId);
        this.agentInfo= new SimpleStringProperty(agentInfo);
        this.storageInfo= new SimpleStringProperty(storageInfo);
        this.renterInfo=new SimpleStringProperty(renterInfo);
    }
    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public double getPrice() {
        return price.get();
    }

    public SimpleDoubleProperty priceProperty() {
        return price;
    }

    public int getDuration() {
        return duration.get();
    }

    public SimpleIntegerProperty durationProperty() {
        return duration;
    }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public void setDuration(int duration) {
        this.duration.set(duration);
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public int getStorageId() {
        return storageId.get();
    }

    public SimpleIntegerProperty storageIdProperty() {
        return storageId;
    }

    public void setStorageId(int storageId) {
        this.storageId.set(storageId);
    }

    public String getStorageInfo() {
        return storageInfo.get();
    }

    public SimpleStringProperty storageInfoProperty() {
        return storageInfo;
    }

    public void setStorageInfo(String storageInfo) {
        this.storageInfo.set(storageInfo);
    }

    public String getAgentInfo() {
        return agentInfo.get();
    }

    public SimpleStringProperty agentInfoProperty() {
        return agentInfo;
    }

    public void setAgentInfo(String agentInfo) {
        this.agentInfo.set(agentInfo);
    }

    public String getRenterInfo() {
        return renterInfo.get();
    }

    public SimpleStringProperty renterInfoProperty() {
        return renterInfo;
    }

    public void setRenterInfo(String renterInfo) {
        this.renterInfo.set(renterInfo);
    }

    public int getAgentId() {
        return agentId.get();
    }

    public SimpleIntegerProperty agentIdProperty() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId.set(agentId);
    }

    public int getRenterId() {
        return renterId.get();
    }

    public SimpleIntegerProperty renterIdProperty() {
        return renterId;
    }

    public void setRenterId(int renterId) {
        this.renterId.set(renterId);
    }

    public boolean isActive() {
        return active.get();
    }

    public SimpleBooleanProperty activeProperty() {
        return active;
    }

    public void setActive(boolean active) {
        this.active.set(active);
    }
}
