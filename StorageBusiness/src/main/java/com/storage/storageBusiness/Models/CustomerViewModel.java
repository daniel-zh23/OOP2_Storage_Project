package com.storage.storageBusiness.Models;

import com.storage.storageBusiness.Models.Contracts.UserViewModel;
import javafx.beans.property.SimpleStringProperty;

public class CustomerViewModel extends UserViewModel {
    private SimpleStringProperty firstName;
    private SimpleStringProperty lastName;
    private SimpleStringProperty phone;


    public CustomerViewModel(int id, String fName, String lname, String phone)
   {
       super(id);
       this.firstName=new SimpleStringProperty(fName);
       this.lastName=new SimpleStringProperty(lname);
       this.phone=new SimpleStringProperty(phone);
   }

    public String getFirstName() {
        return firstName.get();
    }

    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public SimpleStringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getPhone() {
        return phone.get();
    }

    public SimpleStringProperty phoneProperty() {
        return phone;
    }
}
