package com.storage.storageui;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class Employee {

    private Long id;
    private SimpleStringProperty firstName;

    private SimpleStringProperty lastName;

    private SimpleDoubleProperty salary;

    public Employee(){}

    public Employee(String firstName, String lastName, double salary){
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.salary = new SimpleDoubleProperty(salary);
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

    public SimpleDoubleProperty getSalary() {
        return salary;
    }
}
