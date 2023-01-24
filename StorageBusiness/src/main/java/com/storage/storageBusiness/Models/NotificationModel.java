package com.storage.storageBusiness.Models;

import com.storage.storagedb.Entity.User;

public class NotificationModel {
    private int id;
    private int userId;
    private String value;
    private boolean isRead= false;

    public NotificationModel(int id, int userId, String value) {
        this.id = id;
        this.userId = userId;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }
}
