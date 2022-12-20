package com.storage.storageui.Controllers;

import com.storage.storageBusiness.Services.UserService;
import com.storage.storageui.StorageApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AgentController {
    private UserService _userService;

    public void setUserService(UserService service){
        if (_userService == null){
            _userService = service;
        }
    }

    @FXML
    private Button logoutBtn;

    @FXML
    public void onLogout() throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(StorageApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage window = (Stage) logoutBtn.getScene().getWindow();
        window.setScene(scene);
    }
}
