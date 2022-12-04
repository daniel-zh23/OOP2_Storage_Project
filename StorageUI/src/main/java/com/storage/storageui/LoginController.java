package com.storage.storageui;

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
        if (username.getText().equals("admin") && password.getText().equals("123456")){
            FXMLLoader fxmlLoader = new FXMLLoader(StorageApplication.class.getResource("logged-in.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
           Stage window = (Stage) loginBtn.getScene().getWindow();
           window.setScene(scene);
        }else{
            errorMsg.setText("Invalid Login");
        }
    }
}