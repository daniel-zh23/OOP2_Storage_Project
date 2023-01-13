package com.storage.storageui.Controllers;

import com.storage.storageBusiness.Models.ResultLoginModel;
import com.storage.storageBusiness.Services.AgentService;
import com.storage.storageBusiness.Services.OwnerService;
import com.storage.storageBusiness.Services.UserService;
import com.storage.storageui.StorageApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class LoginController {

    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Button loginBtn;
    @FXML
    private Label errorMsg;

    @FXML
    protected void onLoginButtonClick() throws Exception{
        loginBtn.setDisable(true);
        loginBtn.setOpacity(0.5);
        UserService userManager = new UserService();
        ResultLoginModel userResult = userManager.login(username.getText(), password.getText());

        if (userResult != null){

            if (userResult.isFirstLogin()){
                FXMLLoader fxmlLoader = new FXMLLoader(StorageApplication.class.getResource("change_password.fxml"));
                Parent object = fxmlLoader.load();
                var controller = fxmlLoader.<ChangePasswordController>getController();
                //controller.setServices(loginBtn.getScene(), userManager);
                Scene scene = new Scene(object);
                Stage window = (Stage) loginBtn.getScene().getWindow();
                window.setScene(scene);
            }

            String view = switch (userResult.getType()) {
                case "Admin" -> "admin.fxml";
                case "Agent" -> "agent.fxml";
                default -> null;
            };

            assert view != null;
            FXMLLoader fxmlLoader = new FXMLLoader(StorageApplication.class.getResource(view));
            Parent object = fxmlLoader.load();
            var controller = fxmlLoader.<AdminController>getController();
            AgentService as = new AgentService();
            OwnerService os = new OwnerService();
            controller.setServices(as, os, userManager);
            Scene scene = new Scene(object);
            Stage window = (Stage) loginBtn.getScene().getWindow();
            window.setScene(scene);
        }else{
            errorMsg.setText("Invalid Login");
            loginBtn.setOpacity(1);
            loginBtn.setDisable(false);
        }
    }
}