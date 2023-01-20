package com.storage.storageBusiness.Models;

public class ResultLoginModel {

    private int userId;
    private String type;

    private boolean isFirstLogin;

    public ResultLoginModel(int userId, String type, boolean isFirstLogin) {
        this.userId = userId;
        this.type = type;
        this.isFirstLogin = isFirstLogin;
    }

    public String getType() {
        return type;
    }

    public boolean isFirstLogin() {
        return isFirstLogin;
    }

    public int getUserId() {
        return userId;
    }
}
