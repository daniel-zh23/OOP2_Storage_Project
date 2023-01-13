package com.storage.storageui.Controllers;

import com.storage.storageBusiness.Services.OwnerService;
import com.storage.storageBusiness.Services.UserService;
import com.storage.storageui.Common.ErrorMessages;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.regex.Pattern;

public class CreateOwnerController {
    private static final String NamePattern = "[A-Z][a-z]+$";
    private static final String PhonePattern = "\\+[0-9 ?]{4,}$";
    private static final String EmailPattern = "[A-Za-z.0-9]+@[A-Za-z]+.[A-Za-z]+";

    private Scene _scene;
    private OwnerService _ownerService;
    private UserService _userService;

    public void setServices(Scene scene, OwnerService ownerService, UserService userService){
        if (_scene == null){
            _scene = scene;
        }
        if(_ownerService == null){
            _ownerService = ownerService;
        }
        if(_userService == null){
            _userService = userService;
        }
    }

    @FXML
    private Button backBtn;

    @FXML
    private TextField fName;

    @FXML
    private TextField lName;

    @FXML
    private TextField username;

    @FXML
    private TextField phone;

    @FXML
    private TextField email;

    @FXML
    private TextField company;

    @FXML
    private TextField salary;

    @FXML
    private Text fNameError;

    @FXML
    private Text lNameError;

    @FXML
    private Text usernameError;

    @FXML
    private Text phoneError;

    @FXML
    private Text emailError;

    public void onCreate() {
        boolean isValid = true;

        isValid = validateInput(fName, fNameError, NamePattern, ErrorMessages.FirstNameErrorMessage, isValid);
        isValid = validateInput(lName, lNameError, NamePattern, ErrorMessages.LastNameErrorMessage, isValid);
        isValid = validateInput(phone, phoneError, PhonePattern, ErrorMessages.PhoneErrorMessage, isValid);
        isValid = validateInput(email, emailError, EmailPattern, ErrorMessages.EmailErrorMessage, isValid);

        if (_userService.checkUsername(username.getText())){
            isValid = false;
            usernameError.setText(ErrorMessages.UsernameErrorMessage);
        } else {
            usernameError.setText("");
        }

        if (isValid){
            //TODO: Owner service createOwner method
            //_ownerService.createOwner(fName.getText(), lName.getText(), username.getText(), phone.getText(), email.getText());
            onBack();
        }
    }

    private boolean validateInput(TextField field, Text errorLabel, String matchPattern, String errorMessage, boolean result){
        if (!Pattern.matches(matchPattern, field.getText())){
            result = false;
            errorLabel.setText(errorMessage);
        } else {
            errorLabel.setText("");
        }
        return result;
    }

    public void onBack(){
        Stage window = (Stage) backBtn.getScene().getWindow();
        window.setScene(_scene);
    }
}
