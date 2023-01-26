package com.storage.storageui.Controllers;

import com.storage.storageBusiness.Models.ResultLoginModel;
import com.storage.storageBusiness.Services.*;
import com.storage.storageui.Controllers.Contracts.UserController;
import com.storage.storageui.Controllers.Extensions.DependencyInjector;
import com.storage.storageui.StorageApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


public class LoginController {
    private static DependencyInjector _injector;

    private static UserService _userService;

    private Map<String, Method> configures = new HashMap<>();

    public LoginController() {
        _injector = new DependencyInjector();
        _userService = _injector.userServiceInstance();
        try{
            configures = Map.ofEntries(
                    Map.entry("Owner", LoginController.class.getMethod("configureOwnerController", UserController.class, int.class)),
                    Map.entry("Admin", LoginController.class.getMethod("configureAdminController", UserController.class, int.class)),
                    Map.entry("Agent", LoginController.class.getMethod("configureAgentController", UserController.class, int.class))
            );
        } catch(Exception e) {
            System.out.println("Kaputna contruktora");
        }
    }

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
        ResultLoginModel userResult = _userService.login(username.getText(), password.getText());

        if (userResult != null){

            if (userResult.isFirstLogin()){
                FXMLLoader fxmlLoader = new FXMLLoader(StorageApplication.class.getResource("change_password.fxml"));
                Parent object = fxmlLoader.load();
                var controller = fxmlLoader.<ChangePasswordController>getController();
                controller.setServices(loginBtn.getScene(), _userService, username.getText());
                Scene scene = new Scene(object);
                Stage window = (Stage) loginBtn.getScene().getWindow();
                window.setScene(scene);
                loginBtn.setOpacity(1);
                loginBtn.setDisable(false);
                return;
            }

            String view = switch (userResult.getType()) {
                case "Admin" -> "admin.fxml";
                case "Agent" -> "agent.fxml";
                case "Owner" -> "owner.fxml";
                default -> null;
            };

            assert view != null;
            FXMLLoader fxmlLoader = new FXMLLoader(StorageApplication.class.getResource(view));
            Parent object = fxmlLoader.load();
            var controller = fxmlLoader.<UserController>getController();
            configures.get(userResult.getType()).invoke(null, controller, userResult.getUserId());
            Scene scene = new Scene(object);
            Stage window = (Stage) loginBtn.getScene().getWindow();
            window.setScene(scene);
        } else {
            errorMsg.setText("Invalid Login");
            loginBtn.setOpacity(1);
            loginBtn.setDisable(false);
        }
    }

    public static void configureOwnerController(UserController controller, int id){
        var ownerController = (OwnerController) controller;
        ownerController.setServices(
                _injector.agentServiceInstance(),
                _injector.ownerServiceInstance(),
                _injector.userServiceInstance(),
                _injector.storageServiceInstance(),
                _injector.notificationServiceInstance(),
                _injector.rentServiceInstance()
        );
        ownerController.setOwnerId(id);
        ownerController.startNotificationService();
    }

    public static void configureAdminController(UserController controller, int id){
        var adminController = (AdminController) controller;
        adminController.setServices(
                _injector.agentServiceInstance(),
                _injector.ownerServiceInstance(),
                _injector.userServiceInstance());
    }

    public static void configureAgentController(UserController controller, int id){
        var agentController = (AgentController) controller;
        agentController.setAgentId(id);
        agentController.setServices(
                _injector.userServiceInstance(),
                _injector.storageServiceInstance(),
                _injector.notificationServiceInstance(),
                _injector.rentServiceInstance());
        agentController.startNotificationService();
    }
}