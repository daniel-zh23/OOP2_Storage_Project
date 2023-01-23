package com.storage.storageui.Controllers;

import com.storage.storageBusiness.Services.OwnerService;
import com.storage.storageBusiness.Services.StorageService;
import com.storage.storageBusiness.Services.UserService;
import com.storage.storageui.Common.ErrorMessages;
import com.storage.storageui.Controllers.Contracts.CreateController;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CreateStorageController extends CreateController {
    private Scene _scene;

    private StorageService _storageService;

    private Integer _ownerId;

    public void setServices(Scene scene, StorageService storageService, int ownerId) {
        if (_scene == null) {
            _scene = scene;
        }
        if (_storageService == null) {
            _storageService = storageService;
        }
        if (_ownerId == null) {
            _ownerId = ownerId;
        }
    }

    @FXML
    private Button backBtn;

    @FXML
    private TextField address;

    @FXML
    private TextField width;

    @FXML
    private TextField height;

    @FXML
    private TextField length;


    @FXML
    private Text addressError;

    @FXML
    private Text widthError;

    @FXML
    private Text heightError;

    @FXML
    private Text lengthError;

    public void onCreate(){
        boolean isValid = true;

        var parsedWidth = tryParseDouble(width.getText());
        var parsedHeight = tryParseDouble(height.getText());
        var parsedLength = tryParseDouble(length.getText());

        if (address.getText().length() > 255){
            isValid = false;
            addressError.setText(ErrorMessages.Address);
        }

        if (parsedWidth == null){
            widthError.setText(ErrorMessages.Width);
            isValid = false;
        } else {
            widthError.setText("");
        }
        if (parsedHeight == null){
            heightError.setText(ErrorMessages.Height);
            isValid = false;
        } else {
            heightError.setText("");
        }
        if (parsedLength == null){
            lengthError.setText(ErrorMessages.Length);
            isValid = false;
        } else {
            lengthError.setText("");
        }

        if (isValid){
            _storageService.createStorage(_ownerId, address.getText(), parsedWidth, parsedHeight, parsedLength);
            onBack();
        }
    }

    public void onBack(){
        Stage window = (Stage) backBtn.getScene().getWindow();
        window.setScene(_scene);
    }

}
