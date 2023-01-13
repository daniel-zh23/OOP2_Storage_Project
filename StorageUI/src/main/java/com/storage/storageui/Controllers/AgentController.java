package com.storage.storageui.Controllers;

import com.storage.storageBusiness.Services.UserService;
import com.storage.storageui.StorageApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AgentController {

    @FXML
    private Button logoutBtn;

    @FXML
    public void onLogout() throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(StorageApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage window = (Stage) logoutBtn.getScene().getWindow();
        window.setScene(scene);
    }
}
