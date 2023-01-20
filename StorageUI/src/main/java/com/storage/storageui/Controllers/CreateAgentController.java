package com.storage.storageui.Controllers;

import com.storage.storageBusiness.Services.AgentService;
import com.storage.storageBusiness.Services.UserService;
import com.storage.storageui.Common.ErrorMessages;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.regex.Pattern;

public class CreateAgentController {
    private static final String NamePattern = "[A-Z][a-zA-Z]+$";
    private static final String PhonePattern = "\\+[0-9 ?]{4,}$";
    private static final String EmailPattern = "[A-Za-z.0-9]+@[A-Za-z]+.[A-Za-z]+";

    private Scene _scene;
    private AgentService _agentService;
    private UserService _userService;

    public void setServices(Scene scene, AgentService agentService, UserService userService){
        if (_scene == null){
            _scene = scene;
        }
        if(_agentService == null){
            _agentService = agentService;
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

    @FXML
    private Text companyError;

    @FXML
    private Text salaryError;

    public void onCreate() {
        boolean isValid = true;
        Double parsedSalary;

        try {
            parsedSalary = Double.parseDouble(salary.getText());
        } catch (Exception e){
            parsedSalary = null;
        }

        isValid = validateInput(fName, fNameError, NamePattern, ErrorMessages.FirstNameErrorMessage, isValid);
        isValid = validateInput(lName, lNameError, NamePattern, ErrorMessages.LastNameErrorMessage, isValid);
        isValid = validateInput(phone, phoneError, PhonePattern, ErrorMessages.PhoneErrorMessage, isValid);
        isValid = validateInput(company, companyError, NamePattern, ErrorMessages.CompanyErrorMessage, isValid);
        isValid = validateInput(email, emailError, EmailPattern, ErrorMessages.EmailErrorMessage, isValid);

        if (_userService.checkUsername(username.getText())){
            isValid = false;
            usernameError.setText(ErrorMessages.UsernameErrorMessage);
        } else {
            usernameError.setText("");
        }
        if (parsedSalary == null){
            isValid = false;
            salaryError.setText(ErrorMessages.SalaryErrorMessage);
        } else {
            salaryError.setText("");
        }

        if (isValid){
            _agentService.createAgent(fName.getText(), lName.getText(), username.getText(), phone.getText(), email.getText(), company.getText(), parsedSalary);
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
