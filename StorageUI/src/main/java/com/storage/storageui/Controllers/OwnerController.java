package com.storage.storageui.Controllers;

import com.storage.storageBusiness.Services.AgentService;
import com.storage.storageBusiness.Services.OwnerService;
import com.storage.storageBusiness.Services.UserService;
import com.storage.storageui.Controllers.Contracts.UserController;

public class OwnerController extends UserController {
    @Override
    public void setServices(AgentService agentService, OwnerService ownerService, UserService userService) {

    }
}
