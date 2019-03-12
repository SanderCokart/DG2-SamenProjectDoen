package main.java;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {

    @FXML private Label chancesLabel;
    @FXML private Label wrongLettersLabel;
    @FXML private TextField charInputField;
    @FXML private TextField wordInputField;

    public void validateCharInput(ActionEvent event) {
        String charInputValue = charInputField.getText(); // get value of input field
        if (Model.validateChar(charInputValue, Model.getRandomWord())) { // char matches
//            addCharToSelectedWord(charInputValue); //
        } else if (charInputValue.length() == 1 && Character.isLetter(charInputValue.charAt(0))) { // char doesn't match
            Model.decreaseChances(chancesLabel);
            Model.addCharToFaultyCharArray(charInputValue, wrongLettersLabel);
        } else { // give error message
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Er is iets mis met de ingevoerde letter");
            alert.setContentText("Controleer of er één letter ingevoerd is!");
            alert.showAndWait();
        }
    }

    public void validateWordInput(ActionEvent event) {

    }

}
