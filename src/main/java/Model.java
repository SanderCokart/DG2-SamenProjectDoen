package main.java;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import java.util.Random;

public class Model {



    private static String[] getWordsArray() {
        return new String[]{
                "appel", "banaan", "pelikaan", "auto", "laptop", "beeldscherm", "toetsenbord", "natuur", "lampen",
                "jassen", "adapter", "lichtbron", "bankstel", "stekker", "oordopjes", "koptelefoon", "kruk", "bar",
                "games", "muis", "plafonniere", "wieldopjesfabriek", "autodrop", "galgje", "internet", "desktop",
                "monitor", "oerwoud", "dinosaurus", "programmeur"
        };
    }

    private static boolean wordIsCreated = false;
    private static String createdWord;

    public static String getRandomWord() {
        if (!wordIsCreated) {
            String[] words = getWordsArray();
            Random rand = new Random();
            int randomNumber = rand.nextInt(30); // Obtain a number between [0 - 30]
            createdWord = words[randomNumber];
            wordIsCreated = true;
        }
        return createdWord;

    }

    static int amountOfChances;
    public static int decreaseChances(Label label) {
        amountOfChances--; // decrease by 1
        label.setText(Integer.toString(amountOfChances)); // update the label
        return amountOfChances; // return the new amount of chances
    }

    public static boolean validateChar(String inputChar, String word) {
        if (inputChar.length() == 1) { // check if char matches a char in the word
            char[] charArray = word.toCharArray();
            for (char wordChar : charArray) {
                if (inputChar.equals(Character.toString(wordChar))) {
                    return true; // there's a match, so return false
                }
            }
        } else if (inputChar.length() > 1) { // give error message
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Er is iets mis met de ingevoerde letter");
            alert.setContentText("Controleer of er maar één letter is ingevoerd!");
            alert.showAndWait();
        }

        return false;
    }


}
