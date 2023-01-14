package com.storage.storageui.Controllers.Contracts;

import com.storage.storageBusiness.Services.AgentService;
import com.storage.storageBusiness.Services.OwnerService;
import com.storage.storageBusiness.Services.UserService;

public abstract class UserController {
    public abstract void setServices(AgentService agentService, OwnerService ownerService, UserService userService);
}
