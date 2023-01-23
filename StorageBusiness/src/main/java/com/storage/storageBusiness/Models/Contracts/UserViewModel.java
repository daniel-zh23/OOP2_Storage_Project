package com.storage.storageBusiness.Models.Contracts;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class UserViewModel {
    private IntegerProperty Id=new SimpleIntegerProperty();

    public UserViewModel(int id) {
        Id.set(id);
    }

    public int getId() {
        return Id.getValue();
    }
}
