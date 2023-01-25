package com.storage.storageBusiness.Models;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Date;

public class RentViewModel {
    private SimpleIntegerProperty id;
    private SimpleDoubleProperty price;
    private SimpleIntegerProperty duration;
    private SimpleStringProperty date;
    private SimpleStringProperty ownerInfo;
    private SimpleStringProperty agentInfo;
    private SimpleStringProperty renterInfo;

    public RentViewModel(int id, double price, int duration, Date date,String ownerInfo,String agentInfo,String renterInfo)
    {
        this.id=new SimpleIntegerProperty(id);
        this.price=new SimpleDoubleProperty(price);
        this.duration=new SimpleIntegerProperty(duration);
        this.date=new SimpleStringProperty(date.toString());
        this.ownerInfo=new SimpleStringProperty(ownerInfo);
        this.agentInfo=new SimpleStringProperty(agentInfo);
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

    public String getOwnerInfo() {
        return ownerInfo.get();
    }

    public SimpleStringProperty ownerInfoProperty() {
        return ownerInfo;
    }

    public String getAgentInfo() {
        return agentInfo.get();
    }

    public SimpleStringProperty agentInfoProperty() {
        return agentInfo;
    }

    public String getRenterInfo() {
        return renterInfo.get();
    }

    public SimpleStringProperty renterInfoProperty() {
        return renterInfo;
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

    public void setOwnerInfo(String ownerInfo) {
        this.ownerInfo.set(ownerInfo);
    }

    public void setAgentInfo(String agentInfo) {
        this.agentInfo.set(agentInfo);
    }

    public void setRenterInfo(String renterInfo) {
        this.renterInfo.set(renterInfo);
    }
}
