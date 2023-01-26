package com.storage.storageui.Controllers.Extensions;

import com.storage.storageBusiness.Services.*;

public class DependencyInjector {
    private AgentService _agentService;

    private NotificationService _notificationService;

    private OwnerService _ownerService;

    private StorageService _storageService;

    private UserService _userService;

    private RentService _rentService;

    public DependencyInjector() {
        _notificationService = new NotificationService();
        _userService = new UserService();
        _storageService = new StorageService(_notificationService);
        _ownerService = new OwnerService();
        _agentService = new AgentService();
        _rentService = new RentService(_storageService, _notificationService);
    }

    public AgentService agentServiceInstance() {
        return _agentService;
    }

    public NotificationService notificationServiceInstance() {
        return _notificationService;
    }

    public OwnerService ownerServiceInstance() {
        return _ownerService;
    }

    public StorageService storageServiceInstance() {
        return _storageService;
    }

    public RentService rentServiceInstance() { return _rentService; }

    public UserService userServiceInstance() {
        return _userService;
    }
}
