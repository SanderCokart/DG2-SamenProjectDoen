package main.java;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

public class Controller {

    @FXML
    private Label chancesLabel;
    int chances = 5;

    public void validateCharInput(ActionEvent event) { // basic method to test the decreaseChanges method
        chances = Model.decreaseChances(chances);
        chancesLabel.setText(Integer.toString(chances));
    }

}
