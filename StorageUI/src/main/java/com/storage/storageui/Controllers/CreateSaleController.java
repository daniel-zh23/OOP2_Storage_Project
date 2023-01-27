package com.storage.storageui.Controllers;

import com.storage.storageBusiness.Models.CustomerViewModel;
import com.storage.storageBusiness.Services.*;
import com.storage.storageui.Common.ErrorMessages;
import com.storage.storageui.Common.RentersTable;
import com.storage.storageui.Controllers.Contracts.CreateController;
import com.storage.storageui.StorageApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateSaleController extends CreateController {
    private RentersTable rentersTable = new RentersTable();
    @FXML
    private Button backButton;
    @FXML
    private Button loadRentersButton;
    @FXML
    private Button createRenterBUtton;
    @FXML
    private Button createSellButton;
    @FXML
    private Label recommendedPrice;
    @FXML
    private TableView tableBox;
    @FXML
    private TextField priceText;
    @FXML
    private TextField durationText;
    @FXML
    private Label priceError;
    @FXML
    private Label durationError;
    @FXML
    private Label error;
    @FXML
    private Label tableErrorLabel;
    private UserService _userService;
    private RentService _rentService;
    private StorageService _storageService;
    private Integer _agentId;
    private Integer _storageId;
    private Scene _scene;

    public void setServices(Scene scene, UserService userService, RentService rentService, StorageService storageService, int agentId, int storageId) {

        if(_userService == null){
            _userService = userService;
        }
      if(_rentService==null) {
          _rentService = rentService;
      }
        if(_agentId == null) {
            _agentId = agentId;
        }
        if (_storageId == null){
            _storageId = storageId;
        }
        if (_storageService == null){
            _storageService = storageService;
        }
      this._scene=scene;

        var storage = _storageService.getById(_storageId);
        Double recPrice = (storage.getLength() * storage.getWidth() * storage.getHeight()) / 15;
        recommendedPrice.setText(String.format("Recommended price: %.2f", recPrice));
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
        rentersTable.feedRenters(_rentService.getAllRenters());
        rentersTable.generateTable(tableBox);
    }
    @FXML
    public void onCreateRenter() throws Exception
    {
        FXMLLoader fxmlLoader = new FXMLLoader(StorageApplication.class.getResource("create_customer.fxml"));
        Parent object = fxmlLoader.load();
        var controller = fxmlLoader.<CreateCustomerController>getController();
        controller.setServices(backButton.getScene(), _rentService);
        Scene scene = new Scene(object);
        Stage window = (Stage) backButton.getScene().getWindow();
        window.setScene(scene);
    }
    @FXML
    public void onCreateSell()
    {
        boolean isValid = true;
        var parsedPrice = tryParseDouble(priceText.getText());
        var parsedMonths = tryParseInt(durationText.getText());

        if (parsedPrice == null){
            isValid = false;
            priceError.setText(ErrorMessages.Price);
        } else {
            priceError.setText("");
        }
        if (parsedMonths == null){
            isValid = false;
            durationError.setText(ErrorMessages.Duration);
        } else {
            durationError.setText("");
        }

        if (isValid){
            Integer renterId = null;
            try {
                 renterId=((CustomerViewModel) tableBox.getSelectionModel().getSelectedItem()).getId();
                if(renterId==null){tableErrorLabel.setText("Select renter from table!");return;}

            } catch (Exception e) {
                error.setText(ErrorMessages.InvalidSelection);
                return;
            }
            _rentService.createRent(Double.parseDouble(recommendedPrice.getText().split(" ")[2]),parsedPrice, parsedMonths, _storageId, _agentId, renterId);
            onBack();
        }
    }
}
