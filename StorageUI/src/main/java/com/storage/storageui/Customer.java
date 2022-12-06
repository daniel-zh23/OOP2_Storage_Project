package com.storage.storageui;

import javafx.beans.property.SimpleStringProperty;

public class Customer {

    private Long id;
    private SimpleStringProperty firstName;

    private SimpleStringProperty lastName;

    public Customer(){}

    public Customer(String firstName, String lastName){
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName.get();
    }

    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    public String getLastName() {
        return lastName.get();
    }

    public SimpleStringProperty lastNameProperty() {
        return lastName;
    }
}
