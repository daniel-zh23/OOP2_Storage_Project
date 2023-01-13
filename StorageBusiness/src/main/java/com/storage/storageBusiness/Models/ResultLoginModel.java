package com.storage.storageBusiness.Models;

public class ResultLoginModel {
    private String type;

    private boolean isFirstLogin;

    public ResultLoginModel(String type, boolean isFirstLogin) {
        this.type = type;
        this.isFirstLogin = isFirstLogin;
    }

    public String getType() {
        return type;
    }

    public boolean isFirstLogin() {
        return isFirstLogin;
    }
}
