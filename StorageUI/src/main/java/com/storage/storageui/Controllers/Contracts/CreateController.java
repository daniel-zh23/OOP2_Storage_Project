package com.storage.storageui.Controllers.Contracts;

import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.util.regex.Pattern;

public abstract class CreateController {
    protected boolean validateInput(TextField field, Text errorLabel, String matchPattern, String errorMessage, boolean result){
        if (!Pattern.matches(matchPattern, field.getText())){
            result = false;
            errorLabel.setText(errorMessage);
        } else {
            errorLabel.setText("");
        }
        return result;
    }

    protected Double tryParseDouble(String value){
        Double parsed;
        try {
            parsed = Double.parseDouble(value);
        } catch(Exception e){
            return null;
        }
        return parsed;
    }
}
