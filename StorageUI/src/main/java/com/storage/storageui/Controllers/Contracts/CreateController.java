package com.storage.storageui.Controllers.Contracts;

import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.util.regex.Pattern;

public abstract class CreateController {

    protected static final String NamePattern = "[A-Z][a-zA-Z]+$";
    protected static final String PhonePattern = "\\+[0-9 ?]{4,}$";
    protected static final String EmailPattern = "[A-Za-z.0-9]+@[A-Za-z]+.[A-Za-z]+";

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
        return (double) Math.round(parsed * 100) / 100;
    }

    protected Integer tryParseInt(String value){
        Integer parsed;
        try {
            parsed = Integer.parseInt(value);
        } catch(Exception e){
            return null;
        }
        return parsed;
    }
}
