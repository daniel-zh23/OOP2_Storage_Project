package com.storage.storageui.Controllers;

import com.storage.storageBusiness.Services.UserService;
import com.storage.storageui.Common.ErrorMessages;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ChangePasswordController {

    private Scene _scene;
    private UserService _userService;

    private String _username;

    @FXML
    private TextField password;

    @FXML
    private TextField confirmPassword;

    @FXML
    private Text errorMessage;

    public void changePassword(){
        if (password.getText().equals(confirmPassword.getText())){
            var isCompleted = _userService.changePassword(_username, password.getText());
            if (isCompleted){
                Stage window = (Stage) password.getScene().getWindow();
                window.setScene(_scene);
            }
            return;
        }
        errorMessage.setText(ErrorMessages.PasswordChange);
    }

    public void setServices(Scene scene, UserService userManager, String username) {
        if (_scene == null){
            _scene = scene;
        }
        if (_userService == null){
            _userService = userManager;
        }
        if (_username == null){
            _username = username;
        }
    }
}
