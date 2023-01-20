package com.storage.storageBusiness.Models;

import com.storage.storageBusiness.Models.Contracts.UserViewModel;
import javafx.beans.property.*;

public class AgentViewModel extends UserViewModel {
    private StringProperty firstName;

    private StringProperty lastName;

    private StringProperty phone;

    private StringProperty company;

    private DoubleProperty salary;


    public AgentViewModel(int id, String firstName, String lastName, String phone, String company, Double salary){
        super(id);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.phone = new SimpleStringProperty(phone);
        this.company = new SimpleStringProperty(company);
        this.salary = new SimpleDoubleProperty(salary);
    }

    public String getFirstName() {
        return firstName.get();
    }


    public StringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getPhone() {
        return phone.get();
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public String getCompany() {
        return company.get();
    }

    public StringProperty companyProperty() {
        return company;
    }

    public void setCompany(String company) {
        this.company.set(company);
    }

    public double getSalary() {
        return salary.get();
    }

    public DoubleProperty salaryProperty() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary.set(salary);
    }
}
