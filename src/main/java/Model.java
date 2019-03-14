package main.java;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Optional;
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
        System.out.println(createdWord);
        return createdWord;//return the createdWord return
    }

    static int amountOfChances = 5;

    public static int decreaseChances(Label chancesLabel, ImageView imageView, Label wordLabel, Label wrongLettersLabel) {
        amountOfChances--; // decrease by 1
        chancesLabel.setText(Integer.toString(amountOfChances)); // update the label
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
            case 0: // game over
                Image image5 = new Image(Model.class.getResourceAsStream("../resources/galgjeStage5.png"));
                imageView.setImage(image5);
                showGameLost(imageView, wordLabel, wrongLettersLabel);
                break;
        }
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

    public static void validateWord(TextField wordInputField, Label chancesLabel, ImageView galgjeStage, Label wordLabel, Label wrongLettersLabel) {//method to validate the word inserted in wordInputField
        if (wordInputField.getText().equals(Model.getRandomWord())){//if text of wordInputField equals the random word
            showGameWin(galgjeStage, wordLabel, wrongLettersLabel);
        } else if (wordInputField.getText().length() <= 1){//else if the wordInputField has less than 2 characters
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Niet genoeg characters!");
            alert.setContentText("Zorg wel dat er genoeg letters zijn om een woord te maken!");
            alert.showAndWait();
        } else {
            decreaseChances(chancesLabel, galgjeStage, wordLabel, wrongLettersLabel);//else decrease the amount of chances
        }
    }

    public static void showGameLost(ImageView imageView, Label wordLabel, Label wrongLettersLabel) { // method for losing screen
        Alert alertLose = new Alert(Alert.AlertType.CONFIRMATION); // alert window with type
        alertLose.setTitle("Je hebt verloren"); // title
        alertLose.setHeaderText("Zelfs dit is te moeilijk voor je..."); // header text
        alertLose.setContentText("Kies een optie:"); // content text

        ButtonType retryButton = new ButtonType("Opnieuw spelen"); // button for retry
        ButtonType quitButton = new ButtonType("Spel afsluiten"); // button for exit

        alertLose.getButtonTypes().setAll(retryButton, quitButton);

        Optional<ButtonType> result = alertLose.showAndWait();
        if (result.get() == retryButton){
            setDefaultValues(imageView, wordLabel, wrongLettersLabel); // reset values
        } else if (result.get() == quitButton) {
            System.exit(0); // exit program
        }
    }

    public static void showGameWin(ImageView imageView, Label wordLabel, Label wrongLettersLabel) {//method to show a message that tells the player he won
        Alert alertWin = new Alert(Alert.AlertType.CONFIRMATION);//creates an alert window
        alertWin.setTitle("WINNER WINNER CHICKEN DINNER!");//sets the title of the window to this
        alertWin.setContentText("Congratulations you win!!");//sets the content text to this

        ButtonType retryButton = new ButtonType("Opnieuw spelen");//creates a button
        ButtonType quitButton = new ButtonType("Spel afsluiten");//creates a button
        alertWin.getButtonTypes().setAll(retryButton, quitButton);//turns the buttons on

        Optional<ButtonType> result = alertWin.showAndWait();//shows the buttons
        if (result.get() == retryButton){//if you press the reset button
            setDefaultValues(imageView, wordLabel, wrongLettersLabel); // reset values
        } else if (result.get() == quitButton) {//if you press quit button
            System.exit(0);//exit program
        }

    }

    public static void setDefaultValues(ImageView imageView, Label wordLabel, Label wrongLettersLabel) { // reset to default values
        wordIsCreated = false; // allow to create a new word
        getRandomWord(); // create a new word
        amountOfChances = 5; // default value of chances
        Image image0 = new Image(Model.class.getResourceAsStream("../resources/galgjeStage0.png"));
        imageView.setImage(image0); // set the hangman to beginning stage
        selectedWordCharArray = getRandomWord().toCharArray(); // reset this char array
        labelWordCharArray = new char[selectedWordCharArray.length]; // reset this char array
        wordLabel.setText(""); // reset the word
        wrongLettersLabel.setText(""); // reset the wrong letters
    }
}