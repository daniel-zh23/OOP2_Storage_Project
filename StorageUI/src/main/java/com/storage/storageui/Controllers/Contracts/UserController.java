package com.storage.storageui.Controllers.Contracts;

import com.storage.storageBusiness.Services.AgentService;
import com.storage.storageBusiness.Services.OwnerService;
import com.storage.storageBusiness.Services.UserService;
import com.storage.storageui.StorageApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class UserController {
    public abstract void setServices(AgentService agentService, OwnerService ownerService, UserService userService);

    @FXML
    public void onLogout(ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(StorageApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Node node = (Node) event.getSource();
        Stage window = (Stage) node.getScene().getWindow();
        window.setScene(scene);
    }
}
