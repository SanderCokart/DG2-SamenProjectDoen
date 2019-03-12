package main.java;

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

    public static String getRandomWord() {
        Random rand = new Random();

        // Obtain a number between [0 - 30].
        int randomNumber = rand.nextInt(30);

        String[] words = getWordsArray();

        return words[randomNumber];
    }

    public static int decreaseChances(int amountOfChances, Label label) {
        amountOfChances--; // decrease by 1
        label.setText(Integer.toString(amountOfChances)); // update the label
        return amountOfChances; // return the new amount of chances
    }

    public static boolean validateChar(String word) {
        char input = 'a';
        char[] letters = word.toCharArray();

        for (int i = 0; i < letters.length; i++) {
            if (input == letters[i]) {
                System.out.println("true");
                return true;
            } else {
                return false;
            }
        }
    }
}
