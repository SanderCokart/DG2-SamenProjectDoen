package main.java;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Random;

public class Model {

    private static String[] getWordsArray() {//method to return an array of words
        return new String[]{
                "appel", "banaan", "pelikaan", "auto", "laptop", "beeldscherm", "toetsenbord", "natuur", "lampen",
                "jassen", "adapter", "lichtbron", "bankstel", "stekker", "oordopjes", "koptelefoon", "kruk", "bar",
                "games", "muis", "plafonniere", "wieldopjesfabriek", "autodrop", "galgje", "internet", "desktop",
                "monitor", "oerwoud", "dinosaurus", "programmeur"
        };
    }

    private static boolean wordIsCreated = false;//sets the default for the boolean variable wordIsCreated
    private static String createdWord;//initializes a String variable for the createdWord

    public static String getRandomWord() {//method to generate a random word out of the array
        if (!wordIsCreated) {//if wordIsCreated returns false
            String[] words = getWordsArray();//turns the words array from the method getWordsArray into an array we can use locally
            Random rand = new Random();//creates a randomizer object
            int randomNumber = rand.nextInt(30); // Obtain a number between [0 - 30]
            createdWord = words[randomNumber];//createdWord variable  gets set to the random word
            wordIsCreated = true;//sets the wordIsCreated boolean to true
        }
        return createdWord;//return the createdWord return

    }

    static int amountOfChances = 5;

    public static int decreaseChances(Label chancesLabel, ImageView imageView) {
        amountOfChances--; // decrease by 1
        switch (amountOfChances) { // update stage of HANGMAN according to amount of chances
            case 4:
                Image image1 = new Image(Model.class.getResourceAsStream("../resources/galgjeStage1.png"));
                imageView.setImage(image1);
                break;
            case 3:
                Image image2 = new Image(Model.class.getResourceAsStream("../resources/galgjeStage2.png"));
                imageView.setImage(image2);
                break;
            case 2:
                Image image3 = new Image(Model.class.getResourceAsStream("../resources/galgjeStage3.png"));
                imageView.setImage(image3);
                break;
            case 1:
                Image image4 = new Image(Model.class.getResourceAsStream("../resources/galgjeStage4.png"));
                imageView.setImage(image4);
                break;
            case 0:
                Image image5 = new Image(Model.class.getResourceAsStream("../resources/galgjeStage5.png"));
                imageView.setImage(image5);
                // game over
                break;
        }
        chancesLabel.setText(Integer.toString(amountOfChances)); // update the label
        return amountOfChances; // return the new amount of chances
    }

    public static boolean validateChar(String inputChar, String word) {
        if (inputChar.length() == 1) { // check if char matches a char in the word
            char[] charArray = word.toCharArray();
            for (char wordChar : charArray) {
                if (inputChar.equals(Character.toString(wordChar))) {
                    return true; // there's a match, so return true
                }
            }
        }
        return false;
    }

    private static char[] selectedWordCharArray = getRandomWord().toCharArray(); // create char array from the selected word
    private static char[] labelWordCharArray = new char[selectedWordCharArray.length]; // create char array that will be set to the label
    private static int i = 0; // initialise index counter

    public static void addCharToSelectedWord(char inputChar, String word, Label label) {
        for (char selectedWordChar : selectedWordCharArray) {
            if (inputChar == selectedWordChar) { // if char matches char in selectedWordCharArray
                labelWordCharArray[i] = inputChar;
            } else if (labelWordCharArray[i] == 0) { // if the char element is empty, put a ' ' in it
                labelWordCharArray[i] = ' ';
            }
            i++; // increment for use as index
        }
        i = 0; // reset index counter
        String string = new String(labelWordCharArray); // convert char array to String
        label.setText(string); // set array to label

    }


    public static void addCharToFaultyChars(String inputChar, Label wrongLettersLabel) {//method to add the wrong guessed words to the
        if (wrongLettersLabel.getText().isEmpty()) { //if the wrongLettersLabel = empty then
            wrongLettersLabel.setText(inputChar);// add the first faulty char
        } else { // append faulty char
            wrongLettersLabel.setText(wrongLettersLabel.getText() + ", " + inputChar);//using commas
        }
    }

    public static void validateWord(Label wordLabel, TextField wordInputField, Label chancesLabel, ImageView galgjeStage) {//method to validate the word inserted in wordInputField
        if (wordInputField.getText().equals(Model.getRandomWord())){//if text of wordInputField equals the random word
            wordLabel.setText("YOU WIN!");//tell the player he won
        } else if(wordInputField.getText().length() <= 1){//else if the wordInputField has less than 2 characters
            wordLabel.setText("Not enough characters entered to make a word");//tell the player there are not enough characters
        } else {
            decreaseChances(chancesLabel, galgjeStage);//else decrease the amount of chances
//            error
        }
    }
}