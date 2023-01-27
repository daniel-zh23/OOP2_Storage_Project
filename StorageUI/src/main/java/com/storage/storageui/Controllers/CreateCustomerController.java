package com.storage.storageui.Controllers;

import com.storage.storageBusiness.Services.RentService;
import com.storage.storageui.Common.ErrorMessages;
import com.storage.storageui.Controllers.Contracts.CreateController;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CreateCustomerController extends CreateController {

    private Scene _scene;
    private RentService _rentService;

    @FXML
    private Button backBtn;

    @FXML
    private TextField fName;

    @FXML
    private TextField lName;

    @FXML
    private TextField phone;

    @FXML
    private Text fNameError;

    @FXML
    private Text lNameError;

    @FXML
    private Text phoneError;

    public void setServices(Scene scene, RentService rentService){
        _scene = scene;
        _rentService = rentService;
    }

    public void onCreate() {
        boolean isValid = true;

        isValid = validateInput(fName, fNameError, NamePattern, ErrorMessages.FirstName, isValid);
        isValid = validateInput(lName, lNameError, NamePattern, ErrorMessages.LastName, isValid);
        isValid = validateInput(phone, phoneError, PhonePattern, ErrorMessages.Phone, isValid);


        if (_rentService.checkPhone(phone.getText())){
            isValid = false;
            phoneError.setText(ErrorMessages.PhoneExists);
        } else if (phoneError.getText().equals("")) {
            phoneError.setText("");
        }

        if (isValid){
            _rentService.createRenter(fName.getText(), lName.getText(), phone.getText());
            onBack();
        }
    }

    public void onBack() {
        Stage window = (Stage) backBtn.getScene().getWindow();
        window.setScene(_scene);
    }
}
