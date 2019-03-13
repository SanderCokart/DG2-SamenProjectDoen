package main.java;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class Controller {

    @FXML private Label chancesLabel;
    @FXML private Label wrongLettersLabel;
    @FXML private Label wordLabel;
    @FXML private TextField charInputField;
    @FXML private TextField wordInputField;
    @FXML private ImageView galgjeStage;

    public void validateCharInput(ActionEvent event) {
        String charInputValue = charInputField.getText(); // get value of input field
        if (Model.validateChar(charInputValue, Model.getRandomWord())) { // char matches
            Model.addCharToSelectedWord(charInputValue.charAt(0), Model.getRandomWord(), wordLabel); //
        } else if (charInputValue.length() == 1 && Character.isLetter(charInputValue.charAt(0))) { // char doesn't match
            Model.decreaseChances(chancesLabel, galgjeStage);
            Model.addCharToFaultyChars(charInputValue, wrongLettersLabel);
        } else { // give error message
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Er is iets mis met de ingevoerde letter");
            alert.setContentText("Controleer of er één letter ingevoerd is!");
            alert.showAndWait();
        }
    }

    public void validateWordInput(ActionEvent event) {
        Model.validateWord(wordLabel, wordInputField, chancesLabel, galgjeStage);
    }

}
