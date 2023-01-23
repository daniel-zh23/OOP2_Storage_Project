package com.storage.storageui.Controllers;

import com.storage.storageBusiness.Services.AgentService;
import com.storage.storageBusiness.Services.UserService;
import com.storage.storageui.Common.ErrorMessages;
import com.storage.storageui.Controllers.Contracts.CreateController;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CreateAgentController extends CreateController {
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

        isValid = validateInput(fName, fNameError, NamePattern, ErrorMessages.FirstName, isValid);
        isValid = validateInput(lName, lNameError, NamePattern, ErrorMessages.LastName, isValid);
        isValid = validateInput(phone, phoneError, PhonePattern, ErrorMessages.Phone, isValid);
        isValid = validateInput(company, companyError, NamePattern, ErrorMessages.Company, isValid);
        isValid = validateInput(email, emailError, EmailPattern, ErrorMessages.Email, isValid);
        var parsedSalary = tryParseDouble(salary.getText());

        if (_userService.checkUsername(username.getText())){
            isValid = false;
            usernameError.setText(ErrorMessages.Username);
        } else {
            usernameError.setText("");
        }
        if (parsedSalary == null){
            isValid = false;
            salaryError.setText(ErrorMessages.Salary);
        } else {
            salaryError.setText("");
        }

        if (isValid){
            _agentService.createAgent(fName.getText(), lName.getText(), username.getText(), phone.getText(), email.getText(), company.getText(), parsedSalary);
            onBack();
        }
    }

    public void onBack(){
        Stage window = (Stage) backBtn.getScene().getWindow();
        window.setScene(_scene);
    }
}
