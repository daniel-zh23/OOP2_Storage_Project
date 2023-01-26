package com.storage.storageui.Controllers;

import com.storage.storageBusiness.Services.*;
import com.storage.storageui.Controllers.Contracts.UserController;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateSaleController {
    @FXML
    private Button backButton;
    @FXML
    private Button loadRentersButton;
    @FXML
    private Button createRenterBUtton;
    @FXML
    private Button createSellButton;
    @FXML
    private TableView tableBox;
    @FXML
    private TextField priceText;
    @FXML
    private TextField durationText;
    private UserService _userService;
    private RentService _rentService;
    private Scene _scene;

    public void setServices(Scene scene, UserService userService, RentService rentService) {

        if(_userService == null){
            _userService = userService;
        }
      if(_rentService==null) {
          _rentService = rentService;
      }
      this._scene=scene;
    }
    @FXML
    public void onBack()
    {
        Stage window = (Stage) backButton.getScene().getWindow();
        window.setScene(_scene);

    }
    @FXML
    public void onLoadRenters()
    {

    }
    @FXML
    public void onCreateRenter()
    {

    }
    @FXML
    public void onCreateSell()
    {

    }
}
