package com.storage.storageui.Controllers;

import com.storage.storageBusiness.Services.AgentService;
import com.storage.storageBusiness.Services.OwnerService;
import com.storage.storageBusiness.Services.UserService;
import com.storage.storageui.Controllers.Contracts.UserController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class AgentController extends UserController {

    @FXML
    private Button logoutBtn;
    @FXML
    private ComboBox<String> notisCombo;

    @Override
    public void setServices(AgentService agentService, OwnerService ownerService, UserService userService) {

    }
}
