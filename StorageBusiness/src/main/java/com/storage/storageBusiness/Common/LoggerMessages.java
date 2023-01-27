package com.storage.storageBusiness.Common;

public class LoggerMessages {

    //General
    public static final String LoggerName = "StorageApp";
    public static final String SaleCheckerTask = "SaleChecker task completed successfully.";

    //Agent
    public static final String UpdateAgents = "Updated agents from TableView.";
    public static final String CreateAgent = "Created agent with username: %s.";

    //Notification
    public static final String AddNotification = "Added notification to user with id: ";

    //Owner
    public static final String CreateOwner =  "Created agent with username: %s.";
    public static final String UpdateOwners = "Updated owners from TableView.";

    //Rent
    public static final String CreateCustomer =  "Created customer with phone: %s.";
    public static final String CreateRent =  "Created rent on storage with id: %s, made by agent id: %s, on customer with id: %s.";

    //Storage
    public static final String ChangeStorageStatus =  "Status of storage with id: %s, changed to %s.";
    public static final String CreateStorage =  "Created storage assigned to owner with id: %s.";
    public static final String DisableStorage =  "Disabled storage with with id: %s.";
    public static final String UpdateStorages =  "Updated storages.";
    public static final String AssignAgentToStorage =  "Assigned agent with id: %s to storage with id: %s.";
    public static final String ContractToAgent =  "Successfully added contract to agent with id: %s.";

    //User
    public static final String Login =  "User: %s logged in.";
    public static final String ChangePassword =  "User: %s changed password.";
    public static final String DisableUser =  "User with id: %s disabled.";
    public static final String ChangeAgentRating =  "Agent with id: %s changed rating to %s.";

}
