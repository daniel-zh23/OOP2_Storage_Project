package com.storage.storageui;

import com.storage.storageBusiness.UserManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
        UserManager userManager = new UserManager();
        String userRole = userManager.login(username.getText(), password.getText());

        if (!(userRole == null)){
            String view = switch (userRole) {
                case "Admin" -> "admin.fxml";
                case "Agent" -> "agent.fxml";
                default -> null;
            };

            assert view != null;
            FXMLLoader fxmlLoader = new FXMLLoader(StorageApplication.class.getResource(view));
            Scene scene = new Scene(fxmlLoader.load());
            Stage window = (Stage) loginBtn.getScene().getWindow();
            window.setScene(scene);
        }else{
            errorMsg.setText("Invalid Login");
        }
    }
}