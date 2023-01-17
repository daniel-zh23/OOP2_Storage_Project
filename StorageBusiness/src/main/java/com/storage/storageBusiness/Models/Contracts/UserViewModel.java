package com.storage.storageBusiness.Models.Contracts;

public abstract class UserViewModel {
    private int Id;

    public UserViewModel(int id) {
        Id = id;
    }

    public int getId() {
        return Id;
    }
}
